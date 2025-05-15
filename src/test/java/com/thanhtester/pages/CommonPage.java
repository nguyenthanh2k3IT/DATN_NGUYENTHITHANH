package com.thanhtester.pages;

public class CommonPage {
    public LoginPage loginPage;
    public AddProductPage addProductPage;
    public ProductInfoPage productInfoPage;
    public RegisterPage registerPage;
    public HomePage homePage;
    public static DashboardPage dashboardPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public AddProductPage getAddProductPage() {
        if (addProductPage == null) {
            addProductPage = new AddProductPage();
        }
        return addProductPage;
    }

    public ProductInfoPage getProductInfoPage() {
        if (productInfoPage == null) {
            productInfoPage = new ProductInfoPage();
        }
        return productInfoPage;
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }
}
