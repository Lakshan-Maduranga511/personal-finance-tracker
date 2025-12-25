package test;
import models.Account;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class AccountTest {
    private Account account;
    @Before
    public void setUp() {
        account = new Account("Cash", "My Wallet", 1000.0);
    }
    @Test
    public void testConstructorAndGetters() {
        assertEquals("Cash", account.getType());
        assertEquals("My Wallet", account.getName());
        assertEquals(1000.0, account.getBalance(), 0.001);
    }
    @Test
    public void testSetName() {
        account.setName("Emergency Fund");
        assertEquals("Emergency Fund", account.getName());
    }
    @Test
    public void testSetBalance() {
        account.setBalance(2000.5);
        assertEquals(2000.5, account.getBalance(), 0.001);
    }
    @Test
    public void testToFileString() {
        String expected = "Cash,My Wallet,1000.0";
        assertEquals(expected, account.toFileString());
    }
    @Test
    public void testFromFileString_ValidInput() {
        String line = "Bank,Savings,5000.25";
        Account acc = Account.fromFileString(line);
        assertNotNull(acc);
        assertEquals("Bank", acc.getType());
        assertEquals("Savings", acc.getName());
        assertEquals(5000.25, acc.getBalance(), 0.001);
    }
    @Test
    public void testFromFileString_InvalidInput() {
        String line = "InvalidLineWithNoCommas";
        Account acc = Account.fromFileString(line);
        assertNull(acc);
    }
    @Test
    public void testToStringFormat() {
        String expected = "Type: Cash, Name: My Wallet, Balance: 1000.0";
        assertEquals(expected, account.toString());
    }
}
