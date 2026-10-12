import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Loads, manages and saves creatures. Registry indices are zero-based. */
public class CreatureRegistry {
    private final String filename;
    private final ArrayList<Creature> creatures;

    public CreatureRegistry(String filename) throws IOException {
        this.filename = filename;
        this.creatures = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                try {
                    creatures.add(Creature.fromCSV(line));
                } catch (IllegalArgumentException exception) {
                    throw new IOException("Invalid CSV row " + lineNumber + ": " + exception.getMessage(), exception);
                }
            }
        }
    }

    public int count() {
        return creatures.size();
    }

    /** Returns a separate object; callers cannot mutate the stored creature. */
    public Creature get(int index) {
        return new Creature(creatures.get(index));
    }

    public void modify(int index, Creature replacement) {
        creatures.set(index, new Creature(replacement));
    }

    public void delete(int index) {
        creatures.remove(index);
    }

    public void add(Creature newCreature) {
        creatures.add(new Creature(newCreature));
    }

    public void writeBack() throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Creature creature : creatures) {
                writer.write(creature.toCSV() + System.lineSeparator());
            }
        }
    }

    /** Test each operation on a temporary copy, preserving creature-data.csv. */
    public static void main(String[] args) {
        try {
            File temp = File.createTempFile("creature-registry-test-", ".csv");
            temp.deleteOnExit();
            try (FileWriter writer = new FileWriter(temp)) {
                writer.write("dragon,500,green\nphoenix,12,orange\nunicorn,200,white\n");
            }
            CreatureRegistry registry = new CreatureRegistry(temp.getAbsolutePath());
            System.out.println("Initial count: " + registry.count());
            Creature copy = registry.get(0);
            copy.setColor("blue");
            System.out.println("Original after changing copy: " + registry.get(0));
            registry.modify(0, new Creature("dragon", 550, "red"));
            registry.delete(1);
            registry.add(new Creature("griffin", 320, "gold"));
            registry.writeBack();
            CreatureRegistry reloaded = new CreatureRegistry(temp.getAbsolutePath());
            System.out.println("Reloaded count: " + reloaded.count());
            for (int i = 0; i < reloaded.count(); i++) {
                System.out.println((i + 1) + ": " + reloaded.get(i));
            }
        } catch (IOException exception) {
            System.err.println("Registry test failed: " + exception.getMessage());
            System.exit(1);
        }
    }
}
