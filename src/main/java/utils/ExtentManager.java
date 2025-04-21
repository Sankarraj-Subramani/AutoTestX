package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            new File("reports").mkdirs();  // ✅ Ensure reports folder exists
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
            reporter.config().setReportName("AutoTestX Execution Report");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Framework", "AutoTestX");
            extent.setSystemInfo("Author", "Sankarraj Subramani");
        }
        return extent;
    }
}
