import java.text.*;      // For formatting currency
import java.util.*;      // For Scanner, HashMap, etc.

// Base class for handling account operations
class Account {

    Scanner sc = new Scanner(System.in);

    // Format balance output in currency
    DecimalFormat df1 = new DecimalFormat("##,###0.00' Rupee'"); // Current account
    DecimalFormat df2 = new DecimalFormat("##,###0.00' Dollar'"); // Savings account

    // Account details
    int CN;  // Customer Number
    int pn;  // PIN Number
    double CB = 2000; // Current Account Balance
    double SB = 1000; // Savings Account Balance

    // Setters
    void setCustomerNumber(int cn){
        CN = cn;
    }

    void setPINNumber(int pn) {
        this.pn = pn;
    }

    // Getters
    int getCustomerNumber() {
        return CN;
    }

    int getPINNumber() {
        return pn;
    }

    // Display current account balance
    void getCurrentBalance() {
        System.out.println("\nCurrent account balance is " + df1.format(CB));
    }

    // Display savings account balance
    void getSavingBalance() {
        System.out.println("\nSaving account balance is " + df2.format(SB));
    }

    // Withdraw from current account
    void getCurrentWithdrawInput() {
        System.out.println("\nCurrent account balance is " + df1.format(CB));
        System.out.println("\nEnter the Amount you want to withdraw");
        double amount = sc.nextDouble();

        if ((CB - amount) >= 0) {
            calcCurrentWithdraw(amount);
            System.out.println("Transaction Successful");
            System.out.println("\nCurrent account balance is " + df1.format(CB));
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    // Calculate updated current account balance after withdrawal
    double calcCurrentWithdraw(double amount) {
        CB = CB - amount;
        return CB;
    }

    // Withdraw from savings account
    void getSavingWithdrawInput() {
        System.out.println("\nSaving account balance is " + df2.format(SB));
        System.out.println("\nEnter the Amount you want to withdraw");
        double amount = sc.nextDouble();

        if ((SB - amount) >= 0) {
            calcSavingWithdraw(amount);
            System.out.println("Transaction Successful");
            System.out.println("\nSaving account balance is " + df2.format(SB));
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    // Calculate updated savings balance after withdrawal
    double calcSavingWithdraw(double amount) {
        SB = SB - amount;
        return SB;
    }

    // Deposit into current account
    void getCurrentDepositInput() {
        System.out.println("\nCurrent account balance is " + df1.format(CB));
        System.out.println("\nEnter the Amount you want to Deposit : ");
        double amount = sc.nextDouble();

        if ((CB + amount) >= 0) {
            calcCurrentDeposit(amount);
            System.out.println("Transaction Successful");
            System.out.println("\nCurrent account balance is " + df1.format(CB));
        } else {
            System.out.println("Invalid Amount");
        }
    }

    // Calculate updated current balance after deposit
    double calcCurrentDeposit(double amount) {
        CB = CB + amount;
        return CB;
    }

    // Deposit into savings account
    void getSavingDepositInput() {
        System.out.println("\nSaving account balance is " + df2.format(SB));
        System.out.println("\nEnter the Amount you want to Deposit : ");
        double amount = sc.nextDouble();

        if ((SB + amount) >= 0) {
            calcSavingDeposit(amount);
            System.out.println("Transaction Successful");
            System.out.println("\nSaving account balance is " + df2.format(SB));
        } else {
            System.out.println("Invalid Amount");
        }
    }

    // Calculate updated savings balance after deposit
    double calcSavingDeposit(double amount) {
        SB = SB + amount;
        return SB;
    }
}

// Subclass to handle menu and login functionalities
class OptionMenu extends Account {

    Scanner sc = new Scanner(System.in);
    HashMap<Integer, Integer> map = new HashMap<>();  // Store CN and PIN

    // Login and authentication method
    void getLogin() {
        int i = 10; // Infinite loop equivalent
        do {
            try {
                // Predefined login credentials
                map.put(11111, 111);
                map.put(11112, 222);
                map.put(11113, 333);
                map.put(11114, 444);
                map.put(11115, 111);

                System.out.println("\n\nWelcome to the ATM");
                System.out.println("\nEnter Customer Number :");

                setCustomerNumber(sc.nextInt());

                System.out.print("\nEnter PIN Number   : ");
                setPINNumber(sc.nextInt());

                int P = getCustomerNumber();
                int Q = getPINNumber();

                // Authenticate user
                if (map.containsKey(P) && map.get(P) == Q) {
                    getAccountType();  // Proceed to account menu
                } else {
                    System.out.println("Wrong PIN or Customer Number");
                }

            } catch (InputMismatchException e) {
                System.out.print("\nEnter only Numbers");
                System.out.println("\nCharacters and symbols not allowed");
                sc.next(); // Clear scanner buffer
                getLogin(); // Retry login
            }
        } while (i == 10);
    }

    // Account type selection menu
    void getAccountType() {
        System.out.println("\nSelect the account type : ");
        System.out.println("Type 1 : Current Account");
        System.out.println("Type 2 : Saving Account");
        System.out.println("Type 3 : Exit");

        System.out.print("\nChoice : ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1: getCurrent(); break;
            case 2: getSaving(); break;
            case 3:
                System.out.println("\nThank you for visiting");
                System.out.println("VISIT AGAIN");
                break;
            default:
                System.out.println("\nInvalid Choice");
                getAccountType(); // Retry
                break;
        }
    }

    // Menu for current account operations
    void getCurrent() {
        System.out.println("\nCurrent Account");
        System.out.println("Type 1 : Balance Enquiry");
        System.out.println("Type 2 : Withdraw Money");
        System.out.println("Type 3 : Deposit Money");
        System.out.println("Type 4 : Exit");

        System.out.print("\nChoice : ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1: getCurrentBalance(); getAccountType(); break;
            case 2: getCurrentWithdrawInput(); getAccountType(); break;
            case 3: getCurrentDepositInput(); getAccountType(); break;
            case 4:
                System.out.println("\nThank you for visiting");
                System.out.println("VISIT AGAIN");
                break;
            default:
                System.out.println("\nInvalid Choice");
                getAccountType(); break;
        }
    }

    // Menu for savings account operations
    void getSaving() {
        System.out.println("\nSaving Account");
        System.out.println("Type 1 : Balance Enquiry");
        System.out.println("Type 2 : Withdraw Money");
        System.out.println("Type 3 : Deposit Money");
        System.out.println("Type 4 : Exit");

        System.out.print("\nChoice : ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1: getSavingBalance(); getAccountType(); break;
            case 2: getSavingWithdrawInput(); getAccountType(); break;
            case 3: getSavingDepositInput(); getAccountType(); break;
            case 4:
                System.out.println("\nThank you for visiting");
                System.out.println("VISIT AGAIN");
                break;
            default:
                System.out.println("\nInvalid Choice");
                getAccountType(); break;
        }
    }
}

// Main class with entry point
public class ATM extends OptionMenu {
    public static void main(String[] args) {
        OptionMenu menu = new OptionMenu();
        menu.getLogin();  // Start ATM application
    }
}
