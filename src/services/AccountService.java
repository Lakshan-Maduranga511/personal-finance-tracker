// Design Patterns:
//   - Factory Pattern: Used to create different types of Account objects based on user input.
// Where used:
//   - Used for all account management operations: add, view, edit, delete.
// Why used:
//   - Factory Pattern promotes object creation without specifying exact class names (supports open-closed principle).
//   - Keeps service logic separate from file and input handling (Single Responsibility Principle).


package services;

import factory.AccountFactory;
import models.Account;
import util.FileUtil;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private static final String ACCOUNT_FILE = "data/accounts.txt";

    //  Main menu for account-related options
    public static void accountMenu() {
        while (true) {
            System.out.println("\n----------------------- Account Management -----------------------");
            System.out.println("1. Add Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Edit Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Back to Main Menu");

            int choice = InputUtil.getInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    viewAccounts();
                    break;
                case 3:
                    editAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    //  Add account using Factory pattern
    public static void addAccount() {
        String type = InputUtil.getString("Enter account type (Cash/Bank/CreditCard): ");
        String name = InputUtil.getString("Enter account name: ");
        double balance = InputUtil.getDouble("Enter initial balance: ");

        Account account = AccountFactory.createAccount(type, name, balance);
        if (account == null) {
            System.out.println("Invalid account type!");
            return;
        }

        FileUtil.appendLine(ACCOUNT_FILE, account.toFileString());
        System.out.println("Account added successfully.");
    }

    //  View accounts
    public static void viewAccounts() {
        List<String> lines = FileUtil.readLines(ACCOUNT_FILE);
        if (lines.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("Your Accounts:");
        int i = 1;
        for (String line : lines) {
            Account account = Account.fromFileString(line);
            if (account != null) {
                System.out.println(i + ". " + account);
                i++;
            }
        }
    }

    // Edit account
    public static void editAccount() {
        List<String> lines = FileUtil.readLines(ACCOUNT_FILE);
        List<Account> accounts = new ArrayList<>();

        for (String line : lines) {
            Account acc = Account.fromFileString(line);
            if (acc != null) {
                accounts.add(acc);
            }
        }

        if (accounts.isEmpty()) {
            System.out.println("No accounts to edit.");
            return;
        }

        // Show accounts
        int i = 1;
        for (Account acc : accounts) {
            System.out.println(i + ". " + acc);
            i++;
        }

        int index = InputUtil.getInt("Enter the account number to edit: ") - 1;
        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Account selected = accounts.get(index);
        String name = InputUtil.getString("Enter new name (current: " + selected.getName() + "): ");
        double balance = InputUtil.getDouble("Enter new balance (current: " + selected.getBalance() + "): ");

        selected.setName(name);
        selected.setBalance(balance);

        // Convert back to file lines
        List<String> updatedLines = new ArrayList<>();
        for (Account acc : accounts) {
            updatedLines.add(acc.toFileString());
        }

        FileUtil.writeLines(ACCOUNT_FILE, updatedLines);
        System.out.println("Account updated.");
    }

    // Delete account
    public static void deleteAccount() {
        List<String> lines = FileUtil.readLines(ACCOUNT_FILE);
        List<Account> accounts = new ArrayList<>();

        for (String line : lines) {
            Account acc = Account.fromFileString(line);
            if (acc != null) {
                accounts.add(acc);
            }
        }

        if (accounts.isEmpty()) {
            System.out.println("No accounts to delete.");
            return;
        }

        // Show accounts
        int i = 1;
        for (Account acc : accounts) {
            System.out.println(i + ". " + acc);
            i++;
        }

        int index = InputUtil.getInt("Enter the account number to delete: ") - 1;
        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        accounts.remove(index);

        // Convert back to file lines
        List<String> updatedLines = new ArrayList<>();
        for (Account acc : accounts) {
            updatedLines.add(acc.toFileString());
        }

        FileUtil.writeLines(ACCOUNT_FILE, updatedLines);
        System.out.println("Account deleted.");
    }
}
