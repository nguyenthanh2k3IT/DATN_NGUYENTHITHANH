package com.thanhnguyen.pages;

import com.thanhnguyen.helpers.ExcelHelper;
import com.thanhnguyen.helpers.PropertiesHelper;
import com.thanhnguyen.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends CommonPage {

    public static By closeAdvertisementPopup = By.xpath("//i[@class='la la-close fs-20']");
    public static By buttonLogin = By.xpath("//a[normalize-space() = 'Registration']/parent::li/preceding-sibling::li/a");
    public static By buttonSubmitLogin = By.xpath("//button[normalize-space()='Login']");
    public static By titleLoginPage = By.xpath("//h1[normalize-space() = 'Login to your account.']");
    public static By messageRequiredEmail = By.xpath("//strong[contains(text(),'The email field is required when phone is not present.')]");
    public static By inputEmail = By.xpath("//input[@id='email']");
    public static By inputPassword = By.xpath("//input[@id='password']");
    public static By messageInvalidEmailFormat = By.xpath("//input[contains(@class, 'is-invalid') and @id = 'email']");
    public static By messageAccDoesNotExist = By.xpath("//span[@data-notify='message']");
    public static By messageRequiredPassword = By.xpath("//input[contains(@class, 'is-invalid') and @id = 'password']");
    public static By roleUser = By.xpath("//span[contains(@class,'avatar')]/following-sibling::span//descendant::span[contains(@class,'small')]");
    public static By buttonOkCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");

    public void openLoginPage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        if (WebUI.getWebElement(LoginPage.closeAdvertisementPopup).isDisplayed()) {
            WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        }
        if (WebUI.getWebElement(LoginPage.buttonOkCookies).isDisplayed()) {
            WebUI.clickElement(LoginPage.buttonOkCookies);
        }
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(titleLoginPage, "Trang đăng nhập KHÔNG được hiển thị");
        WebUI.sleep(1);
    }

    public void logOutRoleAdmin() {
        WebUI.clickElement(LoginPage.roleUser);
        WebUI.clickElement(HomePage.buttonLogoutWithRoleAdmin);
    }

    public void openHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(closeAdvertisementPopup);
        WebUI.clickElement(buttonOkCookies);
        WebUI.waitForPageLoaded();
    }

    public void loginFailWithEmailNull() {
        openLoginPage();
        WebUI.setText(inputPassword, "123456");
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageInvalidEmailFormat, "Email không bắt buộc.");
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Thông báo bắt buộc nhập email không xuất hiện.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageRequiredEmail), "The email field is required when phone is not present.", "Nội dung của thông báo không đúng.");
    }

    public void loginFailWithEmailDoesNotExist(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Thông báo tài khoản không hợp lệ không xuất hiện.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageAccDoesNotExist), "Invalid login credentials", "Nội dung của thông báo không đúng.");
    }

    public void loginFailWithNullPassword(String email) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPassword, "Mật khẩu không bắt buộc.");
    }

    public void loginFailWithIncorrectPassword(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Không hiển thị thông báo lỗi.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageAccDoesNotExist), "Invalid login credentials", "Nội dung của thông báo không đúng.");
    }

    public void loginSuccessWithCustomerAccount(String email, String password) {
        try {
            openLoginPage();
            WebUI.setTextAndClear(inputEmail, email);
            WebUI.setTextAndClear(inputPassword, password);
            WebUI.clickElement(buttonSubmitLogin);
            WebUI.waitForElementVisible(DashboardPage.titleDashboard);
            WebUI.sleep(2);
            WebUI.verifyAssertTrueIsDisplayed(DashboardPage.titleDashboard, "Trang Dashboard không được hiển thị.");
            WebUI.verifyAssertEquals(WebUI.getElementText(DashboardPage.titleDashboard), "Dashboard", "Tiêu đề trang Dashboard không đúng.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginwithnullEmailPassword(String email, String password) {
        openLoginPage();
        com.thanhnguyen.keywords.WebUI.clickElement(buttonSubmitLogin);
        com.thanhnguyen.keywords.WebUI.verifyAssertTrueIsDisplayed(messageInvalidEmailFormat, "Email không bắt buộc.");
        com.thanhnguyen.keywords.WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Thông báo bắt buộc nhập email không xuất hiện.");
        com.thanhnguyen.keywords.WebUI.verifyAssertEquals(com.thanhnguyen.keywords.WebUI.getElementText(messageRequiredEmail), "The email field is required when phone is not present.", "Nội dung của thông báo không đúng.");
    }

    @Step("Login with admin account")
    public void loginSuccessAdminPage() {
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, excelLogin.getCellData("email", 5));
        WebUI.setTextAndClear(inputPassword, excelLogin.getCellData("password", 5));
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.waitForElementVisible(roleUser);
        WebUI.verifyAssertTrueIsDisplayed(roleUser, "Đăng nhập vào hệ thống không thành công.");
        WebUI.verifyAssertTrueEqual(roleUser, "staff", "Tài khoản đăng nhập không phải admin.");
    }

    public void loginSuccessAdminPage(String email, String password) {
        try {
            openLoginPage();
            WebUI.setTextAndClear(inputEmail, email);
            WebUI.setTextAndClear(inputPassword, password);
            WebUI.clickElement(buttonSubmitLogin);
            WebUI.waitForElementVisible(roleUser);
            WebUI.verifyAssertTrueIsDisplayed(roleUser, "Đăng nhập vào hệ thống không thành công.");
            WebUI.verifyAssertTrueEqual(roleUser, "staff", "Tài khoản đăng nhập không phải admin.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginFailWithInvalidEmailFormat(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.checkHTML5MessageWithValueInvalid(inputEmail, "Email sai dinh dang");
    }
}


