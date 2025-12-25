package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.ReportService;
import util.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.Assert.*;

public class ReportServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before
    public void setUpStreams() {
        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outContent));
        // Write dummy transaction data
        FileUtil.writeLines("data/transactions.txt", List.of(
                "2025-07-01,income,Salary,50000,Bank",
                "2025-07-02,expense,Food,3000,Bank",
                "2025-07-03,expense,Food,2500,Cash"
        ));
        // Write dummy category data
        FileUtil.writeLines("data/categories.txt", List.of(
                "Salary,income,0",
                "Food,expense,4000"
        ));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut); // Restore original System.out
    }
    @Test
    public void testShowReportOutput() {
        ReportService.showReport();
        String output = outContent.toString();
        assertTrue("Output should contain 'Total Income'", output.contains("Total Income"));
        assertTrue("Output should contain 'Salary'", output.contains("Salary"));
        assertTrue("Output should contain 'Food'", output.contains("Food"));
        assertTrue("Output should mention 'Over Budget'", output.contains("Over Budget"));
        assertTrue("Output should contain 'Current Balance'", output.contains("Current Balance"));
    }
}
