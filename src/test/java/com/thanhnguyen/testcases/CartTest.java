package com.thanhnguyen.testcases;

import com.thanhnguyen.common.BaseTest;
import com.thanhnguyen.helpers.ExcelHelper;
import com.thanhnguyen.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiem tra chuc nang them san pham khong co bien the chua ton tai trong gio hang vao gio hang")
    public void TC_AddProductNoVariantNotExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiem tra chuc nang them san pham co bien the chua ton tai trong gio hang vao gio hang")
    public void TC_AddProductVariantNotExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3, description = "Kiem tra chuc nang them san pham khong co bien the da ton tai trong gio hang vao gio hang")
    public void TC_AddProductNoVariantExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4, description = "Kiem tra chuc nang them san pham co bien the da ton tai trong gio hang vao gio hang")
    public void TC_AddProductVariantExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        String keyProductName = excel.getCellData("productName", 2) + " - " + excel.getCellData("variantName", 2);
        boolean checkExistProduct = getCartPage().checkProductExistInCart(keyProductName);
        if (checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        }
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5, description = "Kiem tra chuc nang them san pham da ton tai trong gio hang voi so luong vuot qua so luong ton kho")
    public void TC_AddProductExistToCartOverQuantity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().addProductOverQuantityToCart(excel.getCellData("productName", 1), "901");
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6, description = "Kiem tra chuc nang them san pham chua ton tai trong gio hang voi so luong vuot qua so luong ton kho")
    public void TC_AddProductNotExistToCartOverQuantity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().removeProductFromCartDetail(excel.getCellData("productName", 1));
        }
        getCartPage().addProductOverQuantityToCart(excel.getCellData("productName", 1), "901");
    }

    //check update quantity product in cart
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7, description = "Kiem tra khi cap nhat so luong san pham hop le trong gio hang")
    public void TC_UpdateQuantityValidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getCartPage().updateQuantityProductInCart(excel.getCellData("productName", 1), "3");
    }

    //check add product over quantity
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8, description = "Kiem tra khi cap nhat san pham vao gio hang vuot qua so luong")
    public void TC_UpdateQuantityInvalidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getCartPage().updateQuantityProductInCart(excel.getCellData("productName", 1), "1000");
    }

    //check remove product from cart
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9, description = "Kiem tra khi xoa san pham khoi gio hang chi tiet")
    public void TC_RemoveProductFromCartDetailWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().removeProductFromCartDetail(excel.getCellData("productName", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10, description = "Kiem tra khi xoa san pham khoi gio hang dropdown")
    public void TC_RemoveProductFromCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().removeProductFromCart(excel.getCellData("productName", 1));
    }
}
