package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadConfig() throws IOException {
        FileInputStream fis = new FileInputStream("config.properties");
        properties = new Properties();
        properties.load(fis);
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
