package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // dynamic timestamp
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("AutoTestX Execution Report - " + timestamp);
        sparkReporter.config().setDocumentTitle("Test Execution Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set dynamic system info
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Sankarraj Subramani");
        extent.setSystemInfo("Generated On", new SimpleDateFormat("MMM dd, yyyy HH:mm:ss").format(new Date()));
    }

    @BeforeMethod
    public void launchApp() {
        // Initialize WebDriver (you can customize based on browser params)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com"); // replace with your actual URL
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Create log entry per test
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed: " + result.getName());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Generates the final HTML report
    }
}
