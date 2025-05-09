package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    public static void readConfig() throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/config.properties");
        properties = new Properties();
        properties.load(file);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
