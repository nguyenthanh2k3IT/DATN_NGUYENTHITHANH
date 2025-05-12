package com.thanhtester.dataproviders;

import com.thanhtester.helpers.ExcelHelper;
import com.thanhtester.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderAddProductToCart {
    @DataProvider(name = "data_provider_add_product_to_cart")
    public Object[][] dataAddProductToCart() {
        ExcelHelper excelHelpers = new ExcelHelper();
//        Object[][] data = excelHelpers.getExcelData(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct");
        Object[][] data = excelHelpers.getDataHashTable(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct", 1, 2);
        return data;
    }
}
