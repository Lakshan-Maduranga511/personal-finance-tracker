
package models;

public class Transaction {
    private String date;
    private String type; // "income" or "expense"
    private String category;
    private double amount;
    private String accountName;

    public Transaction(String date, String type, String category, double amount, String accountName) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.accountName = accountName;
    }

    // --- Getters ---
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountName() {
        return accountName;
    }

    // --- Setters ---
    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    
    @Override
    public String toString() {
        return "[" + date + "] " + type.toUpperCase() + " | " + category +
               " | Rs. " + amount + " | Account: " + accountName;
    }
}
