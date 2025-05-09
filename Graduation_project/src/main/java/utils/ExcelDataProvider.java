package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class ExcelDataProvider {
    /**
     * Đọc dữ liệu từ file Excel với cấu trúc:
     * - Row[0] là header: [Action, Locator, Value1, Value2, ...]
     * - Từ cột thứ 3 (index = 2) trở đi, mỗi cột được xem là 1 test case.
     *
     * @param filePath  Đường dẫn file Excel.
     * @param sheetName Tên sheet cần đọc.
     * @return Object[][] cho TestNG.
     * @throws IOException
     */
    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException, IOException {
        ExcelUtils.readExcel(filePath);
        List<List<String>> rows = ExcelUtils.getAllData(sheetName);

        if (rows.isEmpty()) {
            throw new RuntimeException("Sheet '" + sheetName + "' không có dữ liệu!");
        }
        // Row[0] = header: [Action, Locator, Value1, Value2, ...]
        int totalColumns = rows.get(0).size();
        if (totalColumns <= 2) {
            throw new RuntimeException("File Excel phải có ít nhất 3 cột: Action, Locator, và 1 cột Value");
        }

        // Số test case = tổng cột - 2 (Action + Locator)
        int testCaseCount = totalColumns - 2;
        Object[][] data = new Object[testCaseCount][1];
        for (int colIndex = 2; colIndex < totalColumns; colIndex++) {
            // Đóng gói rows và colIndex vào một Object[] để truyền vào hàm test.
            data[colIndex - 2][0] = new Object[] { rows, colIndex };
        }

        ExcelUtils.closeWorkbook();
        return data;
    }
}
