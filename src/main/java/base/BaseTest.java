package base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize ExtentReports if not already done
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        // Start logging the current test method
        test = extent.createTest(method.getName());
        test.log(Status.INFO, "Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Log result in report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        // Quit driver
        if (driver != null) {
            driver.quit();
        }

        // Flush report
        extent.flush();
    }
}
