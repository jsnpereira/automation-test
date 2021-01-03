package com.automation.framework.utils;

import com.automation.framework.commons.BrowserType;
import com.automation.framework.commons.Settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private String currentDirectory = System.getProperty("user.dir");
    private static String filePath = "/src/main/resources/config.properties";

    public static void setUpSettings() throws IOException {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        propertiesUtil.readProperty();
    }

    private void readProperty() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(currentDirectory + filePath);
        properties.load(inputStream);
        Settings.browserType = BrowserType.valueOf(properties.getProperty("browser.type"));
    }
}
