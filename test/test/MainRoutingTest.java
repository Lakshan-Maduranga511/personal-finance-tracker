

package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.ReflectionUtil;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MainRoutingTest {

    private final InputStream originalIn = System.in;
    @Before
    public void setUp() {
        // Backup original System.in
    }
    @After
    public void tearDown() {
        // Restore System.in
        System.setIn(originalIn);
    }
    @Test
    public void testAccountServiceRouting() {
        boolean result = ReflectionUtil.invokeStaticMethod("services.AccountService", "accountMenu");
        assertTrue(result); // Should return true if method is invoked
    }
    @Test
    public void testTransactionServiceRouting() {
        boolean result = ReflectionUtil.invokeStaticMethod("services.TransactionService", "transactionMenu");
        assertTrue(result);
    }
    @Test
    public void testCategoryServiceRouting() {
        boolean result = ReflectionUtil.invokeStaticMethod("services.CategoryService", "categoryMenu");
        assertTrue(result);
    }
    @Test
    public void testReportServiceRouting() {
        boolean result = ReflectionUtil.invokeStaticMethod("services.ReportService", "showReport");
        assertTrue(result);
    }
    @Test
    public void testInvalidRoutingReturnsFalse() {
        boolean result = ReflectionUtil.invokeStaticMethod("non.existing.ClassName", "fakeMethod");
        assertFalse(result);
    }
}
