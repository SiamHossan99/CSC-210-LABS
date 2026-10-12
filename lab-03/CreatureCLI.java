import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Program 3: CRUD commands using CreatureRegistry. */
public class CreatureCLI {
    private static final String DATA_FILE = "creature-data.csv";

    private static void help() {
        System.out.println("Usage:");
        System.out.println("  java CreatureCLI create 'name:dragon weight:500 color:green'");
        System.out.println("  java CreatureCLI read 1");
        System.out.println("  java CreatureCLI update 2 'name:phoenix weight:12 color:orange'");
        System.out.println("  java CreatureCLI delete 3");
        System.out.println("Indices shown to users are 1-based (first creature is 1).");
    }

    private static Creature parseCreature(String description) {
        Map<String, String> values = new HashMap<>();
        for (String field : description.trim().split("\\s+")) {
            String[] pair = field.split(":", 2);
            if (pair.length != 2 || pair[1].isEmpty() ||
                    !(pair[0].equals("name") || pair[0].equals("weight") || pair[0].equals("color")) ||
                    values.putIfAbsent(pair[0], pair[1]) != null) {
                throw new IllegalArgumentException("Invalid creature property: " + field);
            }
        }
        if (values.size() != 3) {
            throw new IllegalArgumentException("Provide name, weight, and color.");
        }
        return new Creature(values.get("name"), Double.parseDouble(values.get("weight")), values.get("color"));
    }

    private static int parseIndex(String text, CreatureRegistry registry) {
        int userIndex = Integer.parseInt(text);
        if (userIndex < 1 || userIndex > registry.count()) {
            throw new IndexOutOfBoundsException("Creature index out of range: " + userIndex);
        }
        return userIndex - 1;
    }

    private static boolean validArguments(String[] args) {
        if (args.length == 0) return false;
        switch (args[0]) {
            case "create": return args.length == 2;
            case "read": return args.length == 2;
            case "delete": return args.length == 2;
            case "update": return args.length == 3;
            default: return false;
        }
    }

    public static void main(String[] args) {
        if (!validArguments(args)) {
            help();
            System.exit(1);
        }
        try {
            CreatureRegistry registry = new CreatureRegistry(DATA_FILE);
            switch (args[0]) {
                case "create": {
                    Creature creature = parseCreature(args[1]);
                    registry.add(creature);
                    registry.writeBack();
                    System.out.println("Created: " + creature);
                    break;
                }
                case "read": {
                    int index = parseIndex(args[1], registry);
                    System.out.println(registry.get(index));
                    break;
                }
                case "update": {
                    int index = parseIndex(args[1], registry);
                    Creature creature = parseCreature(args[2]);
                    registry.modify(index, creature);
                    registry.writeBack();
                    System.out.println("Updated: " + creature);
                    break;
                }
                case "delete": {
                    int index = parseIndex(args[1], registry);
                    Creature old = registry.get(index);
                    registry.delete(index);
                    registry.writeBack();
                    System.out.println("Deleted: " + old);
                    break;
                }
            }
        } catch (IOException | IllegalArgumentException | IndexOutOfBoundsException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(1);
        }
    }
}
