

import services.LoginService;
import util.InputUtil;
import util.ReflectionUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("==== Welcome to Personal Finance Tracker ====");

        boolean isLoggedIn = false;

        // Loop until successful login
        while (!isLoggedIn) {
            System.out.println("\n----------------------- Authentication -----------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            int authChoice = InputUtil.getInt("Choose an option: ");

            switch (authChoice) {
                case 1:
                    isLoggedIn = LoginService.login(); // login (uses reflection internally)
                    if (!isLoggedIn) {
                        System.out.println("❌ Login failed. Try again.");
                    }
                    break;

                case 2:
                    LoginService.registerUser(); 
                    break;

                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        //  Once logged in, show main features
        while (true) {
            System.out.println("\n=====================  Main Menu ===================== ");
            System.out.println("1. Account Management");
            System.out.println("2. Transaction Management");
            System.out.println("3. Category & Budgeting");
            System.out.println("4. Reporting & Analytics");
            System.out.println("5. Exit");

            int choice = InputUtil.getInt("Choose an option: ");

            switch (choice) {
                case 1:
                    ReflectionUtil.invokeStaticMethod("services.AccountService", "accountMenu");
                    break;
                case 2:
                    ReflectionUtil.invokeStaticMethod("services.TransactionService", "transactionMenu");
                    break;
                case 3:
                    ReflectionUtil.invokeStaticMethod("services.CategoryService", "categoryMenu");
                    break;
                case 4:
                    ReflectionUtil.invokeStaticMethod("services.ReportService", "showReport");
                    break;
                case 5:
                    System.out.println("Goodbye! Your data has been saved.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
