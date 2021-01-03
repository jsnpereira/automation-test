package com.automation.framework.selenium.base;

import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import com.automation.framework.selenium.manager.LocalDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class DriveHelper {

    public static void launch(String url){
        LocalDriverHelper.getDriver().get(url);
        LocalDriverHelper.getDriver().manage().window().maximize();
        loading();
    }

    public static void loading() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                String status = ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").toString();
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 30);
        wait.until(pageLoadCondition);
    }

    public static WebElement getElement(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        return getElement(Locator.getBy(locator, locatorType));
    }

    private static WebElement getElement(By locator) {
        return LocalDriverHelper.getDriver().findElement(locator);
    }

    public static List<WebElement> getElementList(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        return LocalDriverHelper.getDriver().findElements(Locator.getBy(locator,locatorType));
    }

    public static void click(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        getElement(locator,locatorType).click();
    }

    public static void switchTabTo(int number){
        ArrayList<String> tabs = new ArrayList<>(LocalDriverHelper.getDriver().getWindowHandles());
        LocalDriverHelper.getDriver().switchTo().window(tabs.get(number));
    }

    public static void scrollToFindText(String text){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
              if(driver.getPageSource().contains(text)){
                  return true;
              } else {
                  scrollToEnd();
                  return false;
              }
            }
        };
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 180);
        wait.pollingEvery(Duration.ofMillis(300)).until(pageLoadCondition);

    }

    public static void scrollToEnd(){
        ( (JavascriptExecutor) LocalDriverHelper.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void exit(){
        LocalDriverHelper.getDriver().quit();
    }
}
