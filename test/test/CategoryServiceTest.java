// File: test/CategoryServiceTest.java
package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.CategoryService;
import util.FileUtil;
import java.io.*;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CategoryServiceTest {

    private final String testCategoryFile = "data/categories.txt";
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws IOException {
        // Redirect output for checking
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        // Prepare mock file content
        FileUtil.writeLines(testCategoryFile, List.of("Food,expense,1000.0", "Salary,income,0.0"));
    }
    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    @Test
    public void testEditCategory() {
        // Simulate: choose 1st category, rename to "Groceries", new budget = 1200
        String simulatedInput = "1\nGroceries\n1200\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        CategoryService service = CategoryService.getInstance();
        service.editCategory();
        List<String> updatedLines = FileUtil.readLines(testCategoryFile);

        assertEquals("Groceries,expense,1200.0", updatedLines.get(0));
        assertTrue(outputStream.toString().contains("Category updated."));
    }
}
