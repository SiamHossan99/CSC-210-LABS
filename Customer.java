import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer {

    private double wallet;

    // Default customer starts with $500
    public Customer() {
        wallet = 500.00;
    }

    // Load customer's money from a file
    public Customer(String filename) {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);

            wallet = input.nextDouble();
            input.close();

        } catch (Exception e) {
            System.out.println("Could not load customer file.");
            System.out.println("Starting customer with $500.");
            wallet = 500.00;
        }
    }

    // Spend money from the wallet
    public double spend(double amount) {

        if (amount > wallet) {
            double remainingMoney = wallet;
            wallet = 0;
            return remainingMoney;
        }

        wallet = wallet - amount;
        return amount;
    }

    // Add winnings to wallet
    public void receive(double amount) {
        wallet = wallet + amount;
    }

    // Check wallet balance
    public double checkWallet() {
        return wallet;
    }

    // Save wallet balance
    public void save(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(Double.toString(wallet));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving customer data.");
        }
    }
}