package com.thanhtester.testcases;

import com.thanhtester.common.BaseTest;
import com.thanhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class AddProductTest extends BaseTest {
    @Test(priority = 1, description = "Kiem tra them san pham moi khong co variant, co discount hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithHaveDiscount() {
        ExcelHelper excel = new ExcelHelper();
        getLoginPage().loginSuccessAdminPage();
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleAdmin(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }

    @Test(priority = 2, description = "Kiem tra them san pham moi khong co variant, co discount khong hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleAdmin(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @Test(priority = 3, description = "Kiem tra them san pham moi co variant, co discount hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithHaveDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductVariantValidRoleAdmin(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }

    @Test(priority = 4, description = "Kiem tra them san pham moi co variant, co discount khong hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductVariantValidRoleAdmin(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @Test(priority = 5, description = "Kiem tra them san pham moi khong hop le voi role admin")
    public void TC_AddProductVariantInvalidRoleAdmin() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductInvalidRoleAdmin(excel.getCellData("productName", 3), excel.getCellData("category", 3), excel.getCellData("unit", 3), excel.getCellData("weight", 3), excel.getCellData("tags", 3), excel.getCellData("unitPrice", 3), excel.getCellData("discountDate", 3), excel.getCellData("quantity", 3), excel.getCellData("description", 3), excel.getCellData("discount", 3), excel.getCellData("image", 3), excel.getCellData("vat", 1));
    }
}


