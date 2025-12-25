
package util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);  // Use one scanner for the app

    // Get a non-empty string
    public static String getString(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.println("Input cannot be empty. Try again.");
            System.out.print(message);
            input = scanner.nextLine().trim();
        }

        return input;
    }

    // Get a valid integer
    public static int getInt(String message) {
        int number;

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please enter a valid number.");
            }
        }
    }

    // Get a valid double 
    public static double getDouble(String message) {
        double number;

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                number = Double.parseDouble(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid decimal number.");
            }
        }
    }
}
