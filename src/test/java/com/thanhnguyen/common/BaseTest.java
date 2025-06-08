package com.thanhnguyen.common;

import com.thanhnguyen.constants.ConfigData;
import com.thanhnguyen.drivers.DriverManager;
import com.thanhnguyen.helpers.PropertiesHelper;
import com.thanhnguyen.listeners.TestListener;
import com.thanhnguyen.pages.CommonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

@Listeners(TestListener.class)
public class BaseTest extends CommonPage {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {

        //Đang sử dụng BROWSER trong file configs.properties
        String browserName = System.getProperty("BROWSER") != null ? System.getProperty("BROWSER")
                : ConfigData.BROWSER;

        WebDriver driver = setupDriver(browserName);
        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver;
        System.out.println(browserName.trim().toLowerCase());
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false); //Turn off Save Address popup
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        options.setAcceptInsecureCerts(true);

        if (ConfigData.HEADLESS.equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1550,900");
        }

        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        WebDriver driver;
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false); //Turn off Save Address popup
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        options.setAcceptInsecureCerts(true);

        if (ConfigData.HEADLESS.equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1550,900");
        }

        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        WebDriver driver;
        FirefoxOptions options = new FirefoxOptions();
        if (PropertiesHelper.getValue("HEADLESS").equals("true")) {
            options.addArguments("--headless");
            options.addArguments("window-size=1600,900");
        }
        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}