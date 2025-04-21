package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLoginFunctionality() {
        extentTest = extent.createTest("verifyLoginFunctionality");

        // Example: Navigate to login page
        driver.get("https://example.com/login");

        // Example assertions (modify per your app logic)
        String expectedTitle = "Login - My App";
        String actualTitle = driver.getTitle();

        extentTest.info("Navigated to login page");
        Assert.assertEquals(actualTitle, expectedTitle, "Page title doesn't match!");

        extentTest.pass("Login page loaded and verified successfully");
    }
}
