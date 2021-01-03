package test.Check;

import com.automation.framework.commons.BrowserType;
import com.automation.framework.commons.Settings;
import com.automation.framework.selenium.base.DriveFactory;
import com.automation.framework.selenium.base.DriveHelper;
import com.automation.framework.utils.PropertiesUtil;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstRunTest {
    private static String className = FirstRunTest.class.getName();

    @Test
    public void test() throws IOException {

        PropertiesUtil.setUpSettings();
        DriveFactory.initialize(BrowserType.valueOf(Settings.browserType.toString()));
        DriveHelper.exit();
    }


}
