// Design Pattern: Factory Pattern
// Where used: Used to create Account objects based on the account type.
// Why used: This pattern helps to centralize and simplify object creation.
// It also makes it easier to extend with more account types in the future without changing client code.


package factory;

import models.Account;

public class AccountFactory {

    // Factory Pattern used here for creating Account object
    public static Account createAccount(String type, String name, double balance) {
        type = type.trim().toLowerCase();

        
        switch (type) {
            case "cash":
            case "bank":
            case "creditcard":
                return new Account(capitalize(type), name, balance);
            default:
                return null;
        }
    }

    //  Helper method to make type title case
    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
