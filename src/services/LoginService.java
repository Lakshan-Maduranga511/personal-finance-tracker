
package services;

import util.FileUtil;
import util.InputUtil;
import java.lang.reflect.Method;
import java.util.List;

public class LoginService {

    private static final String USER_FILE = "data/users.txt";

    
    public static boolean login() {
        try {
            // Use Reflection internally to call doLogin method
            Class<?> clazz = LoginService.class;
            Method method = clazz.getDeclaredMethod("doLogin");
            method.setAccessible(true); // Access private method

            return (boolean) method.invoke(null); 

        } catch (Exception e) {
            System.out.println("Login failed due to error: " + e.getMessage());
            return false;
        }
    }

    //  Private method to actually perform login
    private static boolean doLogin() {
        System.out.println("=====================  Login ===================== ");

        String username = InputUtil.getString("Username: ");
        String password = InputUtil.getString("Password: ");

        // Read all users from file
        List<String> users = FileUtil.readLines("data/users.txt");

        for (String userLine : users) {
            String[] parts = userLine.split(",");
            if (parts.length == 2) {
                String savedUsername = parts[0].trim();
                String savedPassword = parts[1].trim();

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    System.out.println("✅ Login successful!\n");
                    return true;
                }
            }
        }

        System.out.println("❌ Invalid username or password.\n");
        return false;
    }

    // Static method to register a new user
    public static void registerUser() {
        System.out.println("=====================  Register===================== ");

        String username = InputUtil.getString("Choose username: ");
        String password = InputUtil.getString("Choose password: ");

        // Save user in format: username,password
        FileUtil.appendLine("data/users.txt", username + "," + password);
        System.out.println("✅ Registration successful!\n");
    }

 // For testing purpose: no System.in dependency
    public static boolean loginWithCredentials(String username, String password) {
        try {
            Method method = LoginService.class.getDeclaredMethod("doLogin", String.class, String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, username, password);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
            return false;
        }
    }

    // Create this overloaded method for reflection
    private static boolean doLogin(String username, String password) {
        List<String> users = FileUtil.readLines(USER_FILE);

        for (String userLine : users) {
            String[] parts = userLine.split(",");
            if (parts.length == 2) {
                String savedUsername = parts[0].trim();
                String savedPassword = parts[1].trim();

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    return true;
                }
            }
        }

        return false;
    }

}
