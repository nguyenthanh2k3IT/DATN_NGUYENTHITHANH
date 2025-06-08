package com.thanhnguyen.reports;

import com.thanhnguyen.helpers.PropertiesHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(PropertiesHelper.getValue("EXTENT_REPORT_PATH"));
        reporter.config().setReportName("Extent Report | " + PropertiesHelper.getValue("AUTHOR"));
        extentReports.attachReporter(reporter);
        return extentReports;
    }

}