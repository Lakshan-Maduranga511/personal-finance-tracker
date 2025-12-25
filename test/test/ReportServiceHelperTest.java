package test;

import models.Transaction;
import org.junit.*;
import services.ReportService;
import util.FileUtil;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class ReportServiceHelperTest {

    private final String TRANSACTION_FILE = "data/transactions.txt";
    private final String CATEGORY_FILE = "data/categories.txt";
    @Before
    public void setUp() {
        // Setup sample transaction data
        List<String> txLines = Arrays.asList(
                "2025-07-01,income,Salary,50000,Cash",
                "2025-07-02,expense,Groceries,3000,Bank",
                "2025-07-03,expense,Rent,15000,Bank"
        );
        FileUtil.writeLines(TRANSACTION_FILE, txLines);

        // Setup sample category data
        List<String> categoryLines = Arrays.asList(
                "Groceries,expense,4000",
                "Rent,expense,10000"
        );
        FileUtil.writeLines(CATEGORY_FILE, categoryLines);
    }
    @Test
    public void testLoadTransactionsViaReflection() throws Exception {
        Method method = ReportService.class.getDeclaredMethod("loadTransactions");
        method.setAccessible(true);

        List<Transaction> transactions = (List<Transaction>) method.invoke(null);

        assertEquals(3, transactions.size());
        assertEquals("Salary", transactions.get(0).getCategory());
        assertEquals("Groceries", transactions.get(1).getCategory());
    }
    @Test
    public void testGetBudgetForCategoryViaReflection() throws Exception {
        Method method = ReportService.class.getDeclaredMethod("getBudgetForCategory", String.class);
        method.setAccessible(true);

        double groceriesBudget = (double) method.invoke(null, "Groceries");
        double rentBudget = (double) method.invoke(null, "Rent");
        double travelBudget = (double) method.invoke(null, "Travel"); // not in file

        assertEquals(4000.0, groceriesBudget, 0.01);
        assertEquals(10000.0, rentBudget, 0.01);
        assertEquals(0.0, travelBudget, 0.01); // No such category in file
    }
    @After
    public void tearDown() {
        new File(TRANSACTION_FILE).delete();
        new File(CATEGORY_FILE).delete();
    }
}
