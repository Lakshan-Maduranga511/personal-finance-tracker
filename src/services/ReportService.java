// Reflection Used:
//   - Dynamically invoke `loadTransactions()` and `getBudgetForCategory()`


package services;

import models.Transaction;
import util.FileUtil;


import java.lang.reflect.Method;
import java.util.*;

public class ReportService {

    private static final String TRANSACTION_FILE = "data/transactions.txt";
    private static final String CATEGORY_FILE = "data/categories.txt";

    public static void showReport() {
        System.out.println("\n=====================  Financial Summary Report ===================== ");

        // ✅ Using Reflection to invoke private loadTransactions()
        List<Transaction> transactions = new ArrayList<>();
        try {
            Method method = ReportService.class.getDeclaredMethod("loadTransactions");
            method.setAccessible(true);
            transactions = (List<Transaction>) method.invoke(null);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
        }

        Map<String, Double> incomeByCategory = new HashMap<>();
        Map<String, Double> expenseByCategory = new HashMap<>();
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction tx : transactions) {
            if (tx.getType().equalsIgnoreCase("income")) {
                totalIncome += tx.getAmount();
                incomeByCategory.merge(tx.getCategory(), tx.getAmount(), Double::sum);
            } else if (tx.getType().equalsIgnoreCase("expense")) {
                totalExpense += tx.getAmount();
                expenseByCategory.merge(tx.getCategory(), tx.getAmount(), Double::sum);
            }
        }

        double balance = totalIncome - totalExpense;

        System.out.println("Total Income  : Rs. " + totalIncome);
        System.out.println("Total Expense : Rs. " + totalExpense);
        System.out.println("Current Balance: Rs. " + balance);

        System.out.println("\n--- Income by Category ---");
        if (incomeByCategory.isEmpty()) {
            System.out.println("No income transactions.");
        } else {
            incomeByCategory.forEach((cat, amt) ->
                System.out.println("• " + cat + ": Rs. " + amt)
            );
        }

        System.out.println("\n--- Expense by Category ---");
        if (expenseByCategory.isEmpty()) {
            System.out.println("No expense transactions.");
        } else {
            for (Map.Entry<String, Double> entry : expenseByCategory.entrySet()) {
                String cat = entry.getKey();
                double amt = entry.getValue();
                System.out.print("• " + cat + ": Rs. " + amt);

                // Call private getBudgetForCategory() via reflection
                double budgetLimit = 0;
                try {
                    Method budgetMethod = ReportService.class.getDeclaredMethod("getBudgetForCategory", String.class);
                    budgetMethod.setAccessible(true);
                    budgetLimit = (double) budgetMethod.invoke(null, cat);
                } catch (Exception e) {
                    System.out.print(" ⚠️ (Budget fetch error)");
                }

                if (budgetLimit > 0 && amt > budgetLimit) {
                    System.out.print(" 🔴 (Over Budget: " + budgetLimit + ")");
                }
                System.out.println();
            }
        }

        System.out.println("========================================================= ");
    }

    //  Load transactions from file (now called reflectively)
   private static List<Transaction> loadTransactions() {
    List<Transaction> list = new ArrayList<>();
    List<String> lines = FileUtil.readLines(TRANSACTION_FILE);

    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            String date = parts[0];
            String type = parts[1];
            String category = parts[2];
            double amount = Double.parseDouble(parts[3]);
            String accountName = parts[4];

            list.add(new Transaction(date, type, category, amount, accountName));
        }
    }
    return list;
}


    // Check budget of a given category (now called reflectively)
    private static double getBudgetForCategory(String categoryName) {
        List<String> lines = FileUtil.readLines(CATEGORY_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String name = parts[0];
                String type = parts[1];
                double budget = Double.parseDouble(parts[2]);

                if (name.equalsIgnoreCase(categoryName) && type.equalsIgnoreCase("expense")) {
                    return budget;
                }
            }
        }
        return 0; // Default: no budget
    }

	
}
