package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static Workbook workbook;

    public static List<List<String>> getAllData(String sheetName) throws IOException {
        List<List<String>> data = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(cell.toString());
            }
            data.add(rowData);
        }
        return data;
    }

    public static void readExcel(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(file);
    }

    public static void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
