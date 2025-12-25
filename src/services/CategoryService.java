// Design Patterns: 
//   - Singleton Pattern: ensures only one instance handles all category operations
// Where used:
//   - Manages add, edit, delete, and view operations for categories
//   - Used by user via menu or indirectly via budget alerts or analytics
// Why used:
//   - Keeps category logic separate from UI or storage logic (Single Responsibility)
//   - Singleton ensures one consistent category list is managed


package services;

import models.Category;
import util.FileUtil;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private static final String CATEGORY_FILE = "data/categories.txt";
    private static List<Category> categories = new ArrayList<>();

    // Singleton instance
    private static CategoryService instance;

    private CategoryService() {
        loadCategories();
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    //  Load category data from file
    private void loadCategories() {
        List<String> lines = FileUtil.readLines(CATEGORY_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String name = parts[0];
                String type = parts[1];
                double budget = Double.parseDouble(parts[2]);
                categories.add(new Category(name, type, budget));
            }
        }
    }

    //  Menu to manage categories
    public static void categoryMenu() {
        CategoryService service = getInstance();

        while (true) {
            System.out.println("\n----------------------- Category Management -----------------------");
            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Back to Main Menu");

            int choice = InputUtil.getInt("Choose an option: ");

            switch (choice) {
                case 1: service.addCategory(); break;
                case 2: service.viewCategories(); break;
                case 3: service.editCategory(); break;
                case 4: service.deleteCategory(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    //  Add a new category
    public void addCategory() {
        String name = InputUtil.getString("Enter category name: ");
        String type = InputUtil.getString("Enter type (income/expense): ").toLowerCase();
        double budget = 0;

        if (type.equals("expense")) {
            budget = InputUtil.getDouble("Enter monthly budget for this category: ");
        }

        Category category = new Category(name, type, budget);
        categories.add(category);

        saveCategoriesToFile();
        System.out.println("Category added successfully.");
    }

    //  View all categories
    public void viewCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }

    // Edit a category
    public void editCategory() {
        viewCategories();
        int index = InputUtil.getInt("Enter the category number to edit: ") - 1;

        if (index < 0 || index >= categories.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Category cat = categories.get(index);
        String name = InputUtil.getString("Enter new name (leave blank to keep '" + cat.getName() + "'): ");
        String budgetStr = InputUtil.getString("Enter new budget (leave blank to keep '" + cat.getBudget() + "'): ");

        if (!name.isEmpty()) cat = new Category(name, cat.getType(), cat.getBudget());
        if (!budgetStr.isEmpty()) {
            try {
                double newBudget = Double.parseDouble(budgetStr);
                cat.setBudget(newBudget);
            } catch (NumberFormatException e) {
                System.out.println("Invalid budget input.");
            }
        }

        categories.set(index, cat);
        saveCategoriesToFile();
        System.out.println("Category updated.");
    }

    //  Delete a category
    public void deleteCategory() {
        viewCategories();
        int index = InputUtil.getInt("Enter the category number to delete: ") - 1;

        if (index < 0 || index >= categories.size()) {
            System.out.println("Invalid index.");
            return;
        }

        categories.remove(index);
        saveCategoriesToFile();
        System.out.println("Category deleted.");
    }

    //  Save all categories to file
    private void saveCategoriesToFile() {
        List<String> lines = new ArrayList<>();
        for (Category cat : categories) {
            lines.add(cat.toFileString());
        }
        FileUtil.writeLines(CATEGORY_FILE, lines);
    }

    // Optional helper (can be used in analytics later)
    public List<Category> getAllCategories() {
        return categories;
    }
}
