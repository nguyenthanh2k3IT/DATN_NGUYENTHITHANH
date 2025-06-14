package com.thanhnguyen.pages;

import com.aventstack.extentreports.Status;
import com.thanhnguyen.drivers.DriverManager;
import com.thanhnguyen.helpers.PropertiesHelper;
import com.thanhnguyen.keywords.WebUI;
import com.thanhnguyen.reports.ExtentTestManager;
import com.thanhnguyen.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends CommonPage {
    public static By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
    public static By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By fullNameAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::h4");
    public static By emailAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::div");
    public static By buttonLogoutRoleCustomer = By.xpath("//a[normalize-space()='My Panel']/parent::li/following-sibling::li/a[normalize-space()='Logout']");
    public static By divSearchResult = By.xpath("//div[contains(@class,'typed-search-box')]");
    public static By resultSearchProduct = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Products']/following-sibling::ul//div[contains(@class,'product-name')]");
    public static By resultCategorySuggestions = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Category Suggestions']/following-sibling::ul//a");
    public static By resultTagSuggestions = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Popular Suggestions']/following-sibling::ul//a");
    public static By messageSearchNothing = By.xpath("//div[contains(@class,'search-nothing')]");

    public void searchProduct(String keySearchProduct) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        if (WebUI.getWebElement(LoginPage.closeAdvertisementPopup).isDisplayed()) {
            WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        }
        if (WebUI.getWebElement(LoginPage.buttonOkCookies).isDisplayed()) {
            WebUI.clickElement(LoginPage.buttonOkCookies);
        }
        WebUI.setTextFromSplitString(inputSearchProduct, keySearchProduct);
        WebUI.sleep(2);
        WebUI.setTextAndBackspace(inputSearchProduct, " ");
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
    }

    public void testSearchProduct(String keySearchProduct) {
        searchProduct(keySearchProduct);
        WebUI.verifyAssertTrueIsDisplayed(divSearchResult, "Khối Search result KHONG xuat hien");

    }

    public void testSearchProductHaveResult(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        System.out.println("Size of listResultSearchProduct: " + listResultSearchProduct.size());
        if (listResultSearchProduct.size() == 0) {
            ExtentTestManager.logMessage(Status.FAIL, "Ket qua tim kiem KHONG xuat hien");
            LogUtils.info("Ket qua tim kiem KHONG xuat hien");
        } else {
            ExtentTestManager.logMessage(Status.PASS, "Ket qua tim kiem xuat hien");
            LogUtils.info("Ket qua tim kiem xuat hien");
        }
        List<String> listResultSearchProductValues = new ArrayList<>();
        //int j = 0;
        for (WebElement resultWebElementSearchProduct : listResultSearchProduct) {
            listResultSearchProductValues.add(WebUI.removeAccent(resultWebElementSearchProduct.getText().toLowerCase()));
        }
        //
        keySearchProduct = WebUI.removeAccent(keySearchProduct.toLowerCase());
        List<String> textSplit = List.of(keySearchProduct.split(" "));

        for (String resultSearchProductValue : listResultSearchProductValues) {
            int count = 0;
            resultSearchProductValue = WebUI.removeAccent(resultSearchProductValue.toLowerCase());
            for (int i = 0; i < textSplit.size(); i++) {
                if (resultSearchProductValue.contains(textSplit.get(i))) {
                    count++;
                    //System.out.println("Text: " + textSplit.get(i) + " is contained in: " + resultSearchProductValue);
                    ExtentTestManager.logMessage(Status.PASS, "Text: " + textSplit.get(i) + " is contained in: " + resultSearchProductValue);
                    LogUtils.info("Text: " + textSplit.get(i) + " is contained in: " + resultSearchProductValue);
                }
            }
            if (count == 0) {
                ExtentTestManager.logMessage(Status.FAIL, "Text: " + keySearchProduct + " is NOT contained in: " + resultSearchProductValue);
                LogUtils.info("Text: " + keySearchProduct + " is NOT contained in: " + resultSearchProductValue);
            } else if (count == textSplit.size()) {
                ExtentTestManager.logMessage(Status.PASS, "Text: " + keySearchProduct + " is contained in: " + resultSearchProductValue);
                LogUtils.info("Text: " + keySearchProduct + " is contained in: " + resultSearchProductValue);
            }
            WebUI.sleep(1);
        }

        WebUI.sleep(2);

    }

    public void testSearchProductHaveNotResult(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        WebUI.sleep(5);
        WebUI.verifyAssertTrueIsDisplayed(messageSearchNothing, "Ket qua tim kiem van xuat hien");
        WebUI.verifyAssertTrueTextContain(messageSearchNothing, "Sorry, nothing found", "Message khong tim thay KHONG dung");
        WebUI.sleep(2);
    }

    public void testSearchSuggestionCategoryContainKeySearchProduct(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        WebUI.verifyAssertTrueIsDisplayed(resultCategorySuggestions, "Ket qua tim kiem KHONG xuat hien");
        //Check result contain keySearchProduct
        WebUI.verifyAssertTrueTextContain(resultCategorySuggestions, keySearchProduct, "Ket qua tim kiem KHONG dung");
        WebUI.sleep(2);
    }

    public void testSearchSuggestionTagContainKeySearchProduct(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        WebUI.verifyAssertTrueIsDisplayed(resultTagSuggestions, "Ket qua tim kiem KHONG xuat hien");
        WebUI.verifyAssertTrueTextContain(resultTagSuggestions, keySearchProduct, "Ket qua tim kiem KHONG dung");
        WebUI.sleep(2);
    }

}
