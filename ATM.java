import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String name;
    private String pin;
    private double balance;

    public User(String name, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void displayBalance() {
        System.out.println("Current balance: $" + balance);
    }
}

public class ATM {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser;

    public static void main(String[] args) {
        // Create some sample users
        users.put("1234", new User("John Doe", "1234", 1000.0));
        users.put("5678", new User("Jane Smith", "5678", 1500.0));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM!");
            System.out.print("Enter your PIN (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            if (users.containsKey(input)) {
                currentUser = users.get(input);
                System.out.println("Hello, " + currentUser.getName() + "!");
                showMenu(scanner);
            } else {
                System.out.println("Invalid PIN. Please try again.");
            }
        }
    }

    private static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the withdrawal amount: $");
                    double amountToWithdraw = scanner.nextDouble();
                    currentUser.withdraw(amountToWithdraw);
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double amountToDeposit = scanner.nextDouble();
                    currentUser.deposit(amountToDeposit);
                    break;
                case 3:
                    currentUser.displayBalance();
                    break;
                case 4:
                    System.out.println("Logging out. Have a nice day!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
