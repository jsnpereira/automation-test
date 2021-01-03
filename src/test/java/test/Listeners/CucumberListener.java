package test.Listeners;

import com.automation.framework.utils.PropertiesUtil;
import org.testng.*;

import java.io.IOException;

public class CucumberListener implements ISuiteListener {

    @Override
    public void onStart(ISuite iSuite) {
        try {
            PropertiesUtil.setUpSettings();
        } catch (IOException e) {

        }
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }
}
