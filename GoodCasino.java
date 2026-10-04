import java.util.Scanner;

public class GoodCasino {

    public static double play(
            Customer customer,
            SlotMachine machine,
            double amount) {

        double moneySpent = customer.spend(amount);
        double winnings = machine.pullLever(moneySpent);

        return winnings;
    }

    public static void main(String[] args) {

        Customer customer = new Customer("customer.txt");
        SlotMachine machine = new SlotMachine("slot-machine.txt");

        Scanner input = new Scanner(System.in);

        System.out.println("====================");
        System.out.println("     GOOD CASINO");
        System.out.println("====================");

        while (customer.checkWallet() > 0 &&
               machine.getMoneyPot() > 0) {

            System.out.printf(
                "%nYour wallet: $%.2f%n",
                customer.checkWallet()
            );

            System.out.print(
                "Enter bet amount or type quit: "
            );

            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("quit")) {
                break;
            }

            try {

                double bet = Double.parseDouble(answer);

                if (bet <= 0) {
                    System.out.println(
                        "Please enter an amount greater than 0."
                    );
                    continue;
                }

                double winnings =
                    play(customer, machine, bet);

                System.out.println(
                    "Slots: " + machine
                );

                System.out.printf(
                    "Money returned: $%.2f%n",
                    winnings
                );

                customer.receive(winnings);

                if (winnings > 0) {
                    System.out.println(
                        "Congratulations! You won!"
                    );
                } else {
                    System.out.println(
                        "No match. Try again!"
                    );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid input. Enter a number or type quit."
                );
            }
        }

        customer.save("customer.txt");
        machine.save("slot-machine.txt");

        System.out.printf(
            "%nFinal wallet: $%.2f%n",
            customer.checkWallet()
        );

        System.out.printf(
            "Final casino money: $%.2f%n",
            machine.getMoneyPot()
        );

        System.out.println("Game saved. Goodbye!");

        input.close();
    }
}