package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    public static WebDriver startBrowser() {
        String browser = ConfigReader.get("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        return null;
    }
}
