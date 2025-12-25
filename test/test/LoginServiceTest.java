package test;

import org.junit.Before;
import org.junit.Test;
import services.LoginService;
import util.FileUtil;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private final String TEST_USER_FILE = "data/users.txt";

    @Before
    public void setup() {
        FileUtil.writeLines(TEST_USER_FILE, Arrays.asList("testuser,testpass", "john,1234"));
    }

    @Test
    public void testLoginReflection_ValidCredentials() {
        boolean result = LoginService.loginWithCredentials("testuser", "testpass");
        assertTrue("✅ Login should succeed with valid credentials", result);
    }

    @Test
    public void testLoginReflection_InvalidCredentials() {
        boolean result = LoginService.loginWithCredentials("wronguser", "wrongpass");
        assertFalse("❌ Login should fail with invalid credentials", result);
    }
}
