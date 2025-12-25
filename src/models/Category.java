
package models;

public class Category {
    private String name;
    private String type; // income or expense
    private double budget; // optional 

    public Category(String name, String type, double budget) {
        this.name = name;
        this.type = type;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String toFileString() {
        return name + "," + type + "," + budget;
    }

    @Override
    public String toString() {
        return "Category: " + name + " | Type: " + type + " | Budget: " + budget;
    }
}
