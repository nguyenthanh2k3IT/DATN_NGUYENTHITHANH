package com.thanhnguyen.testcases;

import com.thanhnguyen.common.BaseTest;
import com.thanhnguyen.helpers.ExcelHelper;
import com.thanhnguyen.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Kiểm tra đăng nhập với tài khoản admin hợp lệ
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 1, description = "Kiem tra dang nhap voi tai khoan admin hop le")
    public void TC_LoginSuccessAdminPage() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
    }

    //Kiểm tra đăng nhập với tài khoản khách  hợp lệ
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 2, description = "Kiem tra dang nhap voi tai khoan khach da xac thuc hop le")
    public void TC_LoginSuccessWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
    }

    //Kiểm tra đăng nhập với email , mật khẩu trống
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 3, description = "Kiem tra đang nhap với email , mat khau trong")
    public void TC_LoginwithnullEmailPassword() {
        com.thanhnguyen.helpers.ExcelHelper excel = new com.thanhnguyen.helpers.ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginwithnullEmailPassword(excel.getCellData("email", 6), excel.getCellData("password", 6));
    }

    //Kiểm tra đăng nhập khi để trống email
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 4, description = "Kiem tra dang nhap khi de trong email")
    public void TC_LoginFailWithEmailNull() {
        getLoginPage().loginFailWithEmailNull();
    }

    //Kiểm tra đăng nhập với email hợp lệ, để trống mật khẩu
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 5, description = "Kiem tra dang nhap voi email hop le, de trong mat khau")
    public void TC_LoginFailWithNullPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithNullPassword(excel.getCellData("email", 2));
    }

    //Kiểm tra đăng nhập với email hợp lệ, mật khẩu sai
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 6, description = "Kiem tra dang nhap voi email hop le, mat khau sai")
    public void TC_LoginFailWithIncorrectPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithIncorrectPassword(excel.getCellData("email", 3), excel.getCellData("password", 3));
    }

    //Kiểm tra đăng nhập với email đúng định dạng nhưng không tồn tại
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 7, description = "Kiem tra dang nhap voi email dung dinh dang nhung khong ton tai")
    public void TC_LoginFailWithEmailDoesNotExist() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithEmailDoesNotExist(excel.getCellData("email", 1), excel.getCellData("password", 1));

    }

    //Kiểm tra đăng nhập với email sai định dạng
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 8, description = "Kiem tra dang nhap voi email sai dinh dang")
    public void TC_LoginFailWithInvalidEmailFormat() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithInvalidEmailFormat(excel.getCellData("email", 7), excel.getCellData("password", 7));

    }

}
