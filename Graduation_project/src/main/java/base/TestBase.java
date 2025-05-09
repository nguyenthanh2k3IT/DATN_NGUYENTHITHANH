
// KHOI TAO TRINH DUYET
package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class TestBase {
    protected WebDriver driver;
    protected boolean useExistingProfile = false; // Set thành true nếu muốn dùng profile, false để mở Chrome mới

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");

        if (useExistingProfile){
            File profilePath = new File("C:\\Users\\ducth\\AppData\\Local\\Google\\Chrome\\User Data");
            if (profilePath.exists()) {
                options.addArguments("--user-data-dir=" + profilePath.getAbsolutePath());
                options.addArguments("--profile-directory=Default");
            }
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
