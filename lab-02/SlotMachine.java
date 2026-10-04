import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class SlotMachine {

    private char slot1;
    private char slot2;
    private char slot3;

    private double moneyPot;
    private Random random = new Random();

    // Default constructor
    public SlotMachine() {
        moneyPot = 1000000.00;
    }

    // Constructor that loads money from a file
    public SlotMachine(String filename) {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);

            moneyPot = input.nextDouble();
            input.close();

        } catch (Exception e) {
            System.out.println("Could not load slot machine file.");
            System.out.println("Starting with $1,000,000.");
            moneyPot = 1000000.00;
        }
    }

    // Randomly choose one of three symbols
    private char randomSymbol() {
        int number = random.nextInt(3);

        if (number == 0) {
            return '☺';
        } else if (number == 1) {
            return '♥';
        } else {
            return '7';
        }
    }

    // Pull the lever
    public double pullLever(double amount) {

        slot1 = randomSymbol();
        slot2 = randomSymbol();
        slot3 = randomSymbol();

        if (slot1 == slot2 && slot2 == slot3) {

            double winnings = amount * 10;

            if (winnings > moneyPot) {
                winnings = moneyPot;
            }

            moneyPot = moneyPot - winnings;

            return winnings;
        }

        return 0.0;
    }

    // Display the three symbols
    @Override
    public String toString() {
        return slot1 + " | " + slot2 + " | " + slot3;
    }

    // Check casino money
    public double getMoneyPot() {
        return moneyPot;
    }

    // Save casino money
    public void save(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(Double.toString(moneyPot));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving slot machine data.");
        }
    }
}