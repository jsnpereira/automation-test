package com.automation.framework.selenium.base;

import com.automation.framework.commons.BrowserType;
import com.automation.framework.selenium.manager.LocalDriverHelper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

public class DriveFactory {
    private static String className = DriveFactory.class.getName();

    public static void initialize(BrowserType browserType) {

        switch (browserType) {
            case Firefox:
                createDriverFirefox();
                break;
            case Chrome:
                createDriverChrome();
                break;
        }
    }

    private static void createDriverFirefox() {
        System.setProperty(
                "webdriver.gecko.driver",
                "geckodriver" + getExtensionValue());
        LocalDriverHelper.set(new FirefoxDriver());
    }

    private static void createDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType
                .ForSeleniumServer
                .ENSURING_CLEAN_SESSION, true);
        System.setProperty("webdriver.chrome.driver", "chromedriver" + getExtensionValue());
        LocalDriverHelper.set(new ChromeDriver(options));
    }

    public static String getExtensionValue() {
        String extension = "";
        String operationalSystem = System.getProperty("os.name").toLowerCase();

        if (operationalSystem.indexOf("win") >= 0) {
            extension = ".exe";
        }
        return extension;
    }
}
