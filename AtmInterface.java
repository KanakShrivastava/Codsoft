import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Invalid deposit amount.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayOptions();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + userAccount.checkBalance());
                    } else {
                        System.out.println("Withdrawal failed.");
                    }
                    break;

                case "2":
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (userAccount.deposit(depositAmount)) {
                        System.out.println("Deposit successful. Updated balance: " + userAccount.checkBalance());
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;

                case "3":
                    System.out.println("Current balance: " + userAccount.checkBalance());
                    break;

                case "4":
                    System.out.println("Exiting ATM. Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        System.out.print("Enter initial balance: ");
        double initialBalance = Double.parseDouble(new Scanner(System.in).nextLine());
        
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
