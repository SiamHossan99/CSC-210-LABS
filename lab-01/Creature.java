public class Creature {
    String name;
    int size;

    public Creature(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void talk() {
        System.out.println(name + " says hello!");
    }

    public void move() {
        System.out.println(name + " is moving.");
    }

    public static void main(String[] args) {
        Creature creature = new Creature("Dragon", 10);

        System.out.println("Name: " + creature.name);
        System.out.println("Size: " + creature.size);

        creature.eat();
        creature.talk();
        creature.move();
    }
}