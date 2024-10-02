import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATMSimulation(String initialPin, double initialBalance) {
        this.pin = initialPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Initial balance: " + initialBalance);
    }

    public boolean verifyPin(String inputPin) {
        return inputPin.equals(this.pin);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Balance inquiry: $" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            transactionHistory.add("Deposit: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            transactionHistory.add("Withdrawal: $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (verifyPin(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
            transactionHistory.add("PIN changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize ATM with a default PIN and balance
        ATMSimulation atm = new ATMSimulation("1234", 500.00);
        System.out.print("Enter your PIN: ");
        String inputPin = scanner.nextLine();

        // Check if PIN is correct
        if (!atm.verifyPin(inputPin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        // ATM menu
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Show Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                    break;
                case 4:
                    scanner.nextLine();  // consume newline
                    System.out.print("Enter old PIN: ");
                    String oldPin = scanner.nextLine();
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.nextLine();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
