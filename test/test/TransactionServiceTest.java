// File: TransactionServiceTest.java
// Location: /test/services/TransactionServiceTest.java (or wherever your test folder is)

package test;

import models.Transaction;
import services.TransactionService;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionServiceTest {

    private TransactionService service;

    @Before
    public void setUp() {
        service = TransactionService.getInstance(); // uses Singleton
    }

    @Test
    public void testValidIncomeTransaction() {
        Transaction tx = new Transaction("2025-07-01", "income", "Salary", 10000.0, "Bank");
        boolean result = service.addTransactionLogic(tx);
        assertTrue("Should accept valid income transaction", result);
    }

    @Test
    public void testValidExpenseTransaction() {
        Transaction tx = new Transaction("2025-07-01", "expense", "Groceries", 500.0, "Cash");
        boolean result = service.addTransactionLogic(tx);
        assertTrue("Should accept valid expense transaction", result);
    }

    @Test
    public void testInvalidTransactionType() {
        Transaction tx = new Transaction("2025-07-01", "donation", "Charity", 200.0, "Wallet");
        boolean result = service.addTransactionLogic(tx);
        assertFalse("Should reject invalid transaction type", result);
    }

    @Test
    public void testNegativeAmountAllowedLogic() {
        Transaction tx = new Transaction("2025-07-01", "expense", "Test", -200.0, "Bank");
        boolean result = service.addTransactionLogic(tx);
        assertTrue("Currently allows negative amount - add validation if needed", result);
    }
}
