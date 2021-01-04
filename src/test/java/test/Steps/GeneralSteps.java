package test.Steps;

import com.automation.framework.selenium.base.DriveHelper;
import io.cucumber.java.en.Given;

public class GeneralSteps {

    @Given("Navegar o link {string}")
    public void navegarOLink(String link) {
        DriveHelper.launch(link);
    }
}
