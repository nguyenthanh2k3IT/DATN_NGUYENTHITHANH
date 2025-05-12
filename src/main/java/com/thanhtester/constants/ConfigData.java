package com.thanhtester.constants;

import com.thanhtester.helpers.PropertiesHelper;

public class ConfigData {
    public static boolean HIGHLIGHT_ELEMENT = Boolean.parseBoolean(PropertiesHelper.getValue("HIGHLIGHT_ELEMENT"));
    public static double HIGHLIGHT_TIMEOUT = Double.parseDouble(PropertiesHelper.getValue("HIGHLIGHT_TIMEOUT"));
    public static String AUTHOR = PropertiesHelper.getValue("AUTHOR");
    public static String BROWSER = PropertiesHelper.getValue("BROWSER");
    public static String URL = PropertiesHelper.getValue("URL");
    public static String URL_REGISTER = PropertiesHelper.getValue("URL_REGISTER");
    public static String HEADLESS = PropertiesHelper.getValue("HEADLESS");
    public static String VIDEO_RECORD = PropertiesHelper.getValue("VIDEO_RECORD");
    public static String SCREENSHOT_FAIL = PropertiesHelper.getValue("SCREENSHOT_FAIL");
    public static String SCREENSHOT_PASS = PropertiesHelper.getValue("SCREENSHOT_PASS");
    public static String product_P01 = PropertiesHelper.getValue("product_P01");
    public static String product_P02 = PropertiesHelper.getValue("product_P02");
}
