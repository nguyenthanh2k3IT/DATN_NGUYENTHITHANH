package com.thanhnguyen.dataproviders;

import com.thanhnguyen.helpers.ExcelHelper;
import com.thanhnguyen.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderAddProduct {
    @DataProvider(name = "data_provider_add_product")
    public Object[][] dataAddProduct() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getDataHashTable(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct", 1, 2);
        return data;
    }


}
