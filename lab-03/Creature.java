/** Represents one creature. All fields are private (encapsulation). */
public class Creature {
    private String name;
    private double weight;
    private String color;

    public Creature(String name, double weight, String color) {
        setName(name);
        setWeight(weight);
        setColor(color);
    }

    /** Copy constructor: changes to this object do not affect the original. */
    public Creature(Creature other) {
        this(other.getName(), other.getWeight(), other.getColor());
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.contains(",") || name.contains("\n")) {
            throw new IllegalArgumentException("Name must be nonempty and cannot contain a comma or newline.");
        }
        this.name = name.trim();
    }

    public void setWeight(double weight) {
        if (!Double.isFinite(weight) || weight < 0) {
            throw new IllegalArgumentException("Weight must be a nonnegative finite number.");
        }
        this.weight = weight;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty() || color.contains(",") || color.contains("\n")) {
            throw new IllegalArgumentException("Color must be nonempty and cannot contain a comma or newline.");
        }
        this.color = color.trim();
    }

    /** Used to store one creature in creature-data.csv. */
    public String toCSV() {
        return name + "," + weight + "," + color;
    }

    public static Creature fromCSV(String line) {
        String[] fields = line.split(",", -1);
        if (fields.length != 3) {
            throw new IllegalArgumentException("Expected 3 CSV columns: name,weight,color");
        }
        return new Creature(fields[0].trim(), Double.parseDouble(fields[1].trim()), fields[2].trim());
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Weight: " + weight + ", Color: " + color;
    }

    public static void main(String[] args) {
        Creature original = new Creature("dragon", 500, "green");
        Creature copy = new Creature(original);
        copy.setColor("red");
        System.out.println("Original: " + original);
        System.out.println("Copy: " + copy);
    }
}
