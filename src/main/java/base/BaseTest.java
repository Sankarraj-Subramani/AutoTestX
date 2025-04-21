package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;
import utils.ConfigReader;
import utils.ExtentManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) throws IOException {
        ConfigReader.loadConfig();
        driver = BrowserFactory.startBrowser();
        driver.get(ConfigReader.get("baseUrl"));

        extent = ExtentManager.getReportInstance();
        test = extent.createTest(method.getName());
        test.log(Status.INFO, "Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }
        extent.flush();

        if (driver != null) {
            driver.quit();
        }
    }
}
