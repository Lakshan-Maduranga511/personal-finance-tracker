package test;
import org.junit.*;
import services.AccountService;
import util.FileUtil;
import java.io.*;
import java.util.List;
import static org.junit.Assert.*;

public class AccountServiceTest {
    private final String testFile = "data/accounts.txt";
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream testOut;
    @Before
    public void setUp() throws Exception {
        // Setup test file with one account
        FileWriter writer = new FileWriter(testFile);
        writer.write("Bank,TestAccount,1000.0\n");
        writer.close();
        // Redirect output to capture console messages
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    @Test
    public void testEditAccount() throws Exception {
        // Simulate input: select account 1, rename to "UpdatedAccount", update balance to 2000
        String simulatedInput = "1\nUpdatedAccount\n2000\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        // Run editAccount()
        AccountService.editAccount();
        // Read updated file
        List<String> lines = FileUtil.readLines(testFile);
        assertEquals(1, lines.size());
        assertEquals("Bank,UpdatedAccount,2000.0", lines.get(0).trim());
        // Check console output
        String output = testOut.toString();
        assertTrue(output.contains("Account updated."));
    }
    @After
    public void tearDown() throws Exception {
        // Restore original streams
        System.setOut(originalOut);
        System.setIn(originalIn);
        // Clear test file after test
        FileWriter writer = new FileWriter(testFile);
        writer.write("");
        writer.close();
    }
}
