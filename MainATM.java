import java.util.*;

class ATM {

    private String name;
    private int pin;
    private double balance = 0;

    Scanner scanner = new Scanner(System.in);

    // Store last 5 transactions
    private Queue<String> miniStatement = new LinkedList<>();

    // -------------------- CREATE ACCOUNT ---------------------
    public void createAccount() {
        System.out.println("\n--- Create New Account ---");

        System.out.print("Enter your name: ");
        scanner.nextLine(); // clear buffer
        name = scanner.nextLine();

        System.out.print("Set a 4-digit PIN: ");
        pin = scanner.nextInt();

        System.out.print("Enter initial deposit amount: ");
        balance = scanner.nextDouble();

        addTransaction("Account opened with ₹" + balance);

        System.out.println("\nAccount created successfully!");
        System.out.println("Welcome, " + name + "!");
        System.out.println("----------------------------------");
    }

    // --------------------- LOGIN CHECK -----------------------
    public boolean login() {
        System.out.print("Enter your PIN to continue: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin == pin) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Incorrect PIN.");
            return false;
        }
    }

    // ----------------------- DEPOSIT -------------------------
    public void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        balance += amount;
        addTransaction("Deposited ₹" + amount);

        System.out.println("Amount deposited successfully!");
    }

    // ----------------------- WITHDRAW ------------------------
    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            addTransaction("Withdrawn ₹" + amount);
            System.out.println("Take your cash!");
        }
    }

    // -------------------- CHECK BALANCE ----------------------
    public void checkBalance() {
        System.out.println("Available Balance: ₹" + balance);
    }

    // --------------------- RESET PIN -------------------------
    public void resetPin() {
        System.out.print("Enter old PIN: ");
        int oldPin = scanner.nextInt();

        if (oldPin == pin) {
            System.out.print("Enter new PIN: ");
            int newPin = scanner.nextInt();
            pin = newPin;
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect old PIN!");
        }
    }

    // -------------------- MINI STATEMENT ---------------------
    public void showMiniStatement() {
        System.out.println("\n--- Mini Statement (Last 5 Transactions) ---");
        if (miniStatement.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : miniStatement) {
                System.out.println(t);
            }
        }
        System.out.println("-------------------------------------------");
    }

    // -------------------- STORE TRANSACTIONS -----------------
    private void addTransaction(String detail) {
        if (miniStatement.size() == 5) {
            miniStatement.poll(); // remove oldest
        }
        miniStatement.offer(detail);
    }

    // ------------------------ MENU ---------------------------
    public void start() {
        while (true) {
            System.out.println("""
                \n----- ATM MENU -----
                1. Deposit
                2. Withdraw
                3. Check Balance
                4. Reset PIN
                5. Mini Statement
                6. Exit
                --------------------
                """);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> deposit();
                case 2 -> withdraw();
                case 3 -> checkBalance();
                case 4 -> resetPin();
                case 5 -> showMiniStatement();
                case 6 -> {
                    System.out.println("Thank you for using ATM!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}


// ------------------------ MAIN CLASS -------------------------
public class MainATM {
    public static void main(String[] args) {
        ATM atm = new ATM();

        System.out.println("----- WELCOME TO ATM -----");

        atm.createAccount();  // create new account first

        boolean loggedIn = false;

        // Keep asking until correct PIN
        while (!loggedIn) {
            loggedIn = atm.login();
        }

        atm.start(); // run ATM menu
    }
}
