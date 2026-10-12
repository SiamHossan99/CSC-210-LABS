import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Program 1: direct file processing using an ArrayList<Creature>. */
public class ProcessCreatureFile {
    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "creature-data.csv";
        ArrayList<Creature> creatures = new ArrayList<>();
        try {
            try (Scanner reader = new Scanner(new File(filename))) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (!line.trim().isEmpty()) creatures.add(Creature.fromCSV(line));
                }
            }
            System.out.println("Loaded " + creatures.size() + " creatures.");
            creatures.add(new Creature("mermaid", 140, "blue"));
            System.out.println("Added mermaid.");
            if (!creatures.isEmpty()) {
                Creature first = creatures.get(0);
                first.setWeight(first.getWeight() + 10);
                first.setColor("purple");
                System.out.println("Modified first creature.");
            }
            if (creatures.size() > 1) {
                Creature removed = creatures.remove(1);
                System.out.println("Removed " + removed.getName() + ".");
            }
            try (FileWriter writer = new FileWriter(filename)) {
                for (Creature creature : creatures) {
                    writer.write(creature.toCSV() + System.lineSeparator());
                }
            }
            System.out.println("Saved " + creatures.size() + " creatures to " + filename);
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("File processing failed: " + exception.getMessage());
            System.exit(1);
        }
    }
}
