package keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.time.Duration;

public class Keywords {
    private static WebDriver driver;
    public Keywords(WebDriver driver){
        Keywords.driver = driver;
    }

    /**
     *
     * @param second
     */
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Thực hiện hành động điều hướng đến một url
     * @param value
     */
    public void getUrl(String value){
        driver.get(value);
        sleep(3);
    }

    /**
     * Thực hiện hành động nhập văn bản vào một trường bằng JavaScript
     * @param locator
     * @param value
     */
    public void enterText(By locator, String value) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value + "';", element);
    }

    /**
     * Xóa dữ liệu trong input field bằng JavaScript
     * @param locator
     */
    public void clearText(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';" +
                "arguments[0].dispatchEvent(new Event('input'));", element);
    }

    /**
     * Thực hiện hành động click vào button
     * @param locator
     */
    public void clickButton(By locator) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        Thread.sleep(2000); // Giữ nguyên nếu bạn muốn delay sau khi click
    }

    /**
     * Thực hiện hành động cuộn xuống một phần tử
     * @param locator
     * @param value
     */
    public void scrollView(String locator, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    /**
     * Thực hiện hành động upload file
     * @param elementLocator
     * @param filePath
     */
    public void uploadFile(By elementLocator, String filePath) throws AWTException {
        WebElement fileInput = driver.findElement(elementLocator);
        fileInput.sendKeys(filePath);
        sleep(5);
    }

    public void selectExchangeType(By locator, String value){
        WebElement selectElement = driver.findElement(locator);
        Select exchangeTypeSelect = new Select(selectElement);
        exchangeTypeSelect.selectByValue(value);
        System.out.println("Đã chọn exchange type: " + value);
    }

    public void selectCustomChoice(By locator, String value){
        // 1. Click vào dropdown để mở danh sách
        WebElement dropdown = driver.findElement(locator);
        dropdown.click();

        // 2. Chờ và tìm item theo data-value
        String choiceXpath = String.format("//div[@data-value='%s' and contains(@class, 'choices__item')]", value);
        WebElement choiceItem = driver.findElement(By.xpath(choiceXpath));
        choiceItem.click();

        System.out.println("Đã chọn: " + value);
    }

    /**
     * Tìm kiếm
     * @param locator
     * @param value
     */
    public void search(By locator, String value){
        WebElement search = driver.findElement(locator);
        search.sendKeys(value);
        search.submit();
    }

    /**
     *  Wait for element visible
     * @param by
     * @param timeout: thời gian tối đa có thể chờ để phần tử hiển thị trong DOM
     */
    public static void waitForElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            System.out.println("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    /**
     * Verify element display
     */
    public static boolean verifyElementDisplay(By locator) {
        try {
            Keywords.waitForElementVisible(locator, 10); // Chờ tối đa 10s
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyTextDisplayed(String text) {
        try {
            // Tìm tất cả element có chứa text (có thể chứa một phần của text)
            java.util.List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));

            // Kiểm tra từng phần tử xem có khớp chính xác không
            for (WebElement element : elements) {
                if (element.getText().trim().equals(text)) {
                    System.out.println("✔ Found exact text: " + text);
                    return true;
                }
            }

            System.out.println("✖ Not found exact text: " + text);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean verifyCurrentUrl(String Value) {
        try {
            sleep(3);
            Assert.assertEquals(driver.getCurrentUrl(),Value);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void executeTestSteps(Object[] testData) throws InterruptedException, AWTException {
        @SuppressWarnings("unchecked")
        java.util.List<java.util.List<String>> rows = (java.util.List<java.util.List<String>>) testData[0];
        int colIndex = (int) testData[1];

        // Lấy tên test case từ header (row[0])
        String header = rows.get(0).get(colIndex);

        // Duyệt qua các dòng dữ liệu, bỏ qua dòng header
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
            java.util.List<String> row = rows.get(rowIndex);

            // Phải có ít nhất 2 cột: action và locator
            if (row.size() < 2) {
                continue;
            }

            String action = row.get(0);   // Action ở cột A
            String locator = row.get(1);  // Locator ở cột B

            // Lấy value từ cột test case hiện tại (colIndex)
            String value = "";
            if (row.size() > colIndex) {
                value = row.get(colIndex);
            }

            // Nếu value trống, bỏ qua bước này
            if (value == null || value.trim().isEmpty()) {
                continue;
            }

            System.out.println("=== [TestCase: " + header +")] Action: "
                    + action + ", Locator: " + locator + ", Value: " + value);

            By elementLocator = By.xpath(locator);
            switch (action.toLowerCase()) {
                case "geturl":
                    getUrl(value);
                    break;
                case "entertext":
                    enterText(elementLocator, value);
                    break;
                case "clearText":
                    clearText(elementLocator);
                    break;
                case "selectexchangetype":
                    selectExchangeType(elementLocator, value);
                    break;
                case "selectcustomchoice":
                    selectCustomChoice(elementLocator, value);
                    break;
                case "clickbutton":
                    clickButton(elementLocator);
                    break;
                case "uploadfile":
                    uploadFile(elementLocator, value);
                    break;
                case "search":
                    search(elementLocator, value);
                    break;
                case "verifyelementdisplay":
                    Assert.assertTrue(verifyElementDisplay(elementLocator),"Element not found: " + elementLocator);
                    break;
                case "verifytextdisplayed":
                    Assert.assertTrue(verifyTextDisplayed(value), "Text not found: " + value);
                    break;
                case "verifycurrenturl":
                    verifyCurrentUrl(value);
                    break;
                default:
                    System.out.println("Unknown action: " + action);
            }
        }
    }

    /**
     * Lọc dữ liệu test case từ allData dựa trên tên test case (header).
     *
     * @param allData      Mảng dữ liệu test được lấy từ Excel.
     * @param testCaseName Tên test case cần lọc (ví dụ "value1", "value2", ...).
     * @return Mảng dữ liệu chứa test case được lọc; nếu không tìm thấy, ném Exception.
     */
    public static Object[][] filterTestCaseData(Object[][] allData, String testCaseName) {
        if (testCaseName != null && !testCaseName.trim().isEmpty()) {
            for (Object[] testCase : allData) {
                // Mỗi testCase được đóng gói dưới dạng { { rows, colIndex } }
                Object[] data = (Object[]) testCase[0];
                @SuppressWarnings("unchecked")
                java.util.List<java.util.List<String>> rows = (java.util.List<java.util.List<String>>) data[0];
                int colIndex = (Integer) data[1];
                String header = rows.get(0).get(colIndex);
                if (header.equalsIgnoreCase(testCaseName)) {
                    System.out.println("Đang chạy test case: " + testCaseName);
                    return new Object[][] { testCase };
                }
            }
            throw new RuntimeException("Không tìm thấy test case: " + testCaseName);
        }
        System.out.println("Đang chạy toàn bộ test cases");
        return allData;
    }
}
