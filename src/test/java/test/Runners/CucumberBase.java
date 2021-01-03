package test.Runners;

import com.automation.framework.commons.BrowserType;
import com.automation.framework.commons.Settings;
import com.automation.framework.selenium.base.DriveFactory;
import com.automation.framework.selenium.base.DriveHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CucumberBase {

    @BeforeClass
    public void beforeSuite() {
        DriveFactory.initialize(BrowserType.valueOf(Settings.browserType.toString()));
    }

    @AfterClass
    public void afterSuite() {
        DriveHelper.exit();
    }
}
