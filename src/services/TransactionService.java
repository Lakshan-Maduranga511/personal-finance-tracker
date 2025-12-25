// Design Patterns: 
//   - Singleton Pattern (ensures only one instance of service manages transactions)
//   - Observer Pattern (not implemented here but can be used to notify other parts on transaction changes)
// Where used: 
//   - This class manages all transaction-related operations: add, edit, delete, list, and transfer between accounts.
// Why used: 
//   - Singleton ensures consistent transaction state throughout the app.
//   - This structure keeps transaction logic decoupled from UI and data persistence (single responsibility).


package services;

import models.Transaction;
import models.Account;
import factory.AccountFactory;
import util.FileUtil;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private static final String TRANSACTION_FILE = "data/transactions.txt";
    private static List<Transaction> transactions = new ArrayList<>();

    // Singleton instance
    private static TransactionService instance;

    private TransactionService() {
        // Load transactions from file at initialization
        transactions = FileUtil.readTransactions(TRANSACTION_FILE);
    }

    // Singleton getter
    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    // Menu for transaction management (static for ease of calling)
    public static void transactionMenu() {
        TransactionService service = getInstance();

        while (true) {
            System.out.println("\n===================== Transaction Management ===================== ");
            System.out.println("1. Add Transaction");
            System.out.println("2. Edit Transaction");
            System.out.println("3. Delete Transaction");
            System.out.println("4. List Transactions");
            System.out.println("5. Transfer between Accounts");
            System.out.println("6. Back to Main Menu");

            int choice = InputUtil.getInt("Choose an option: ");

            switch (choice) {
                case 1:
                    service.addTransaction();
                    break;
                case 2:
                    service.editTransaction();
                    break;
                case 3:
                    service.deleteTransaction();
                    break;
                case 4:
                    service.listTransactions();
                    break;
                case 5:
                    service.transferBetweenAccounts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Add a transaction (income or expense)
    public void addTransaction() {
        String date = InputUtil.getString("Enter date (YYYY-MM-DD): ");
        String type = InputUtil.getString("Enter transaction type (income/expense): ").toLowerCase();

        String category = InputUtil.getString("Enter category: ");
        double amount = InputUtil.getDouble("Enter amount: ");

        String accountName = InputUtil.getString("Enter account name: ");

        // Validate transaction type
        if (!type.equals("income") && !type.equals("expense")) {
            System.out.println("🔴 Invalid transaction type. Must be 'income' or 'expense'. 🔴");
            return;
        }

        Transaction newTransaction = new Transaction(date, type, category, amount, accountName);

        transactions.add(newTransaction);
        FileUtil.writeTransactions(TRANSACTION_FILE, transactions);

        System.out.println("Transaction added successfully.");
    }

    // Edit transaction by index 
    public void editTransaction() {
        listTransactions();

        int index = InputUtil.getInt("Enter transaction number to edit: ") - 1;

        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid transaction number.");
            return;
        }

        Transaction t = transactions.get(index);

        System.out.println("Editing Transaction: " + t);

        String date = InputUtil.getString("Enter new date (leave blank to keep '" + t.getDate() + "'): ");
        String type = InputUtil.getString("Enter new type (income/expense, leave blank to keep '" + t.getType() + "'): ").toLowerCase();
        String category = InputUtil.getString("Enter new category (leave blank to keep '" + t.getCategory() + "'): ");
        String amountStr = InputUtil.getString("Enter new amount (leave blank to keep '" + t.getAmount() + "'): ");
        String accountName = InputUtil.getString("Enter new account name (leave blank to keep '" + t.getAccountName() + "'): ");

        if (!date.isEmpty()) t.setDate(date);
        if (!type.isEmpty() && (type.equals("income") || type.equals("expense"))) t.setType(type);
        if (!category.isEmpty()) t.setCategory(category);
        if (!amountStr.isEmpty()) {
            try {
                double amt = Double.parseDouble(amountStr);
                t.setAmount(amt);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount input. Keeping previous amount.");
            }
        }
        if (!accountName.isEmpty()) t.setAccountName(accountName);

        FileUtil.writeTransactions(TRANSACTION_FILE, transactions);
        System.out.println("Transaction updated successfully.");
    }

    // Delete transaction by index
    public void deleteTransaction() {
        listTransactions();

        int index = InputUtil.getInt("Enter transaction number to delete: ") - 1;

        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid transaction number.");
            return;
        }

        transactions.remove(index);
        FileUtil.writeTransactions(TRANSACTION_FILE, transactions);

        System.out.println("Transaction deleted successfully.");
    }

    // List all transactions
    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n==== Transactions ====");
        int i = 1;
        for (Transaction t : transactions) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    // Transfer money between two accounts by creating two transactions (expense + income)
    public void transferBetweenAccounts() {
        System.out.println("===================== Transfer between Accounts===================== ");
        String fromAccount = InputUtil.getString("From Account Name: ");
        String toAccount = InputUtil.getString("To Account Name: ");
        double amount = InputUtil.getDouble("Amount to transfer: ");

        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        String date = InputUtil.getString("Date (YYYY-MM-DD): ");

        // Create two transactions: expense from source account, income to destination account
        Transaction expense = new Transaction(date, "expense", "Transfer Out", amount, fromAccount);
        Transaction income = new Transaction(date, "income", "Transfer In", amount, toAccount);

        transactions.add(expense);
        transactions.add(income);

        FileUtil.writeTransactions(TRANSACTION_FILE, transactions);

        System.out.println("Transfer recorded successfully.");
    }
 // for addtransaction testing
    public boolean addTransactionLogic(Transaction tx) {
        if (!tx.getType().equalsIgnoreCase("income") && !tx.getType().equalsIgnoreCase("expense")) {
            return false;
        }

        transactions.add(tx); 
        return true;
    }

}
