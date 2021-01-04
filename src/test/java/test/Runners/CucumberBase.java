package test.Runners;

import com.automation.framework.commons.BrowserType;
import com.automation.framework.commons.Settings;
import com.automation.framework.selenium.base.DriveFactory;
import com.automation.framework.selenium.base.DriveHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CucumberBase {

    @BeforeMethod
    public void beforeTest() {
        DriveFactory.initialize(BrowserType.valueOf(Settings.browserType.toString()));
    }

    @AfterMethod
    public void afterTest() {
        DriveHelper.exit();
    }
}
