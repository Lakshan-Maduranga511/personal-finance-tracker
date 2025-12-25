// Design Patterns:
//   - Utility Pattern (all methods are static, stateless, and reusable)
// Where used:
//   - Used for all low-level file operations across the app.
//   - Used by TransactionService to save/load transactions as objects.
// Why used:
//   - Centralizes file I/O logic, improves code reuse and reduces duplication.


package util;

import models.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

 

    // Read all lines from a file. If file doesn't exist, create it first.
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create folder if not exist
                file.createNewFile();          // Create the file
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }

    // Append a line to a file 
    public static void appendLine(String filePath, String line) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    // Overwrite file with a list of lines 
    public static void writeLines(String filePath, List<String> lines) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    
    // Transaction-Specific File Operations


    // Read transactions from file and return as a list of Transaction objects
    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = readLines(filePath);

        for (String line : lines) {
            // Expected format: date|type|category|amount|accountName
            String[] parts = line.split("\\,");
            if (parts.length == 5) {
                try {
                    String date = parts[0];
                    String type = parts[1];
                    String category = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    String accountName = parts[4];

                    Transaction t = new Transaction(date, type, category, amount, accountName);
                    transactions.add(t);
                } catch (Exception e) {
                    System.out.println("Skipping invalid transaction line: " + line);
                }
            }
        }

        return transactions;
    }

    // Write list of Transaction objects to file in line format
    public static void writeTransactions(String filePath, List<Transaction> transactions) {
        List<String> lines = new ArrayList<>();

        for (Transaction t : transactions) {
            // Convert transaction to: date|type|category|amount|accountName
            String line = t.getDate() + "," + t.getType() + "," + t.getCategory()
                        + "," + t.getAmount() + "," + t.getAccountName();
            lines.add(line);
        }

        writeLines(filePath, lines);
    }
}
