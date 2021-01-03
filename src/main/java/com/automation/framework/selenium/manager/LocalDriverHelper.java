package com.automation.framework.selenium.manager;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalDriverHelper {
    private static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void set(RemoteWebDriver remoteWebDriver){
       LocalDriverHelper.driverThreadLocal.set(remoteWebDriver);
    }

    public static RemoteWebDriver getDriver(){
        return driverThreadLocal.get();
    }
}
