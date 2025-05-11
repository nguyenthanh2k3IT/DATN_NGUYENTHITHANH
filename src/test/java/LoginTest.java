import base.TestBase;
import keywords.Keywords;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelDataProvider;

import java.awt.*;
import java.io.IOException;

public class LoginTest extends TestBase {
    private Keywords keywords;
    private static final String FILE_PATH = "src/main/resources/testcases/cmsanhtester.xlsx";
    private static final String SHEET_NAME = "Login";
    private static final String TEST_CASE_TO_RUN = "";

    @BeforeClass
    public void setUp(){
        super.setUp();
        keywords = new Keywords(driver);
    }

    @AfterClass
    public void tearDown(){
        super.tearDown();
    }

    @DataProvider(name = "excelData")
    public static Object[][] excelDataProvider() throws IOException {
        Object[][] allData = ExcelDataProvider.getExcelData(FILE_PATH, SHEET_NAME);
        String testCaseName = TEST_CASE_TO_RUN;
        return Keywords.filterTestCaseData(allData, testCaseName);
    }

    @Test(dataProvider = "excelData")
    public void executeTest(Object[] testData) throws InterruptedException, AWTException {
        keywords.executeTestSteps(testData);
    }
}
