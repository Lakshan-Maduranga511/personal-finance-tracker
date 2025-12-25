
package models;

public class Account {
    private String type;
    private String name;
    private double balance;

    public Account(String type, String name, double balance) {
        this.type = type;
        this.name = name;
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //  Format data to save in file
    public String toFileString() {
        return type + "," + name + "," + balance;
    }

    //  Create Account from CSV line
    public static Account fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            String type = parts[0];
            String name = parts[1];
            double balance = Double.parseDouble(parts[2]);
            return new Account(type, name, balance);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Name: " + name + ", Balance: " + balance;
    }
}
