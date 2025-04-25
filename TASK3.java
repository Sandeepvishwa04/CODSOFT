import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0)
            this.balance = initialBalance;
        else
            this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf(" Successfully deposited ₹%.2f\n", amount);
        } else {
            System.out.println(" Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= amount;
            System.out.printf(" Successfully withdrew ₹%.2f\n", amount);
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option;
        do {
            showMenu();
            option = getValidOption();

            switch (option) {
                case 1 -> handleWithdraw();
                case 2 -> handleDeposit();
                case 3 -> handleCheckBalance();
                case 4 -> System.out.println(" Thank you for using the ATM. Goodbye!");
                default -> System.out.println(" Invalid option. Please try again.");
            }

        } while (option != 4);
    }

    private void showMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    private int getValidOption() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid option: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = getValidAmount();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = getValidAmount();
        account.deposit(amount);
    }

    private void handleCheckBalance() {
        System.out.printf(" Your current balance is: ₹%.2f\n", account.getBalance());
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

// Main class
public class TASK3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize account
        System.out.print("Enter initial account balance: ₹");
        double initialBalance = getValidAmount(scanner);

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        atm.start();

        scanner.close();
    }

    private static double getValidAmount(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
