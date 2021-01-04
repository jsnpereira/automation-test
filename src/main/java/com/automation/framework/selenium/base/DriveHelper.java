package com.automation.framework.selenium.base;

import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import com.automation.framework.selenium.manager.LocalDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DriveHelper {

    public static void launch(String url) {
        LocalDriverHelper.getDriver().get(url);
        LocalDriverHelper.getDriver().manage().window().maximize();
        loading();
    }

    public static void loading() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
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
        return LocalDriverHelper.getDriver().findElements(Locator.getBy(locator, locatorType));
    }

    public static void click(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        getElement(locator, locatorType).click();
    }

    public static void typeInput(String locator, LocatorType locatorType, String text, Boolean clean) throws NotSelectLocatorType {
        WebElement element = getElement(locator, locatorType);
        if (clean){
            element.clear();
        }
        element.sendKeys(text);
    }

    public static void typeInput(String locator, LocatorType locatorType, String text) throws NotSelectLocatorType {
        typeInput(locator,locatorType,text, false);
    }

    public static void switchTabTo(int number) {
        ArrayList<String> tabs = new ArrayList<>(LocalDriverHelper.getDriver().getWindowHandles());
        LocalDriverHelper.getDriver().switchTo().window(tabs.get(number));
    }

    public static void scrollToFindText(String text) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                if (driver.getPageSource().contains(text)) {
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

    public static void waitElementDisplayed(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 180);
        wait.until(ExpectedConditions.visibilityOf(getElement(locator, locatorType)));
    }

    public static void waitElementNotDisplayed(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 180);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator.getBy(locator, locatorType)));
    }

    public static void waitElementPresented(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 180);
        wait.until(ExpectedConditions.presenceOfElementLocated(Locator.getBy(locator, locatorType)));
    }

    public static void scrollToEnd() {
        ((JavascriptExecutor) LocalDriverHelper.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void exit() {
        LocalDriverHelper.getDriver().quit();
    }

    public static void waitElementBeClicked(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        WebDriverWait wait = new WebDriverWait(LocalDriverHelper.getDriver(), 180);
        wait.until(ExpectedConditions.elementToBeClickable(getElement(locator, locatorType)));
    }

    public static void selectByItem(String locator, LocatorType locatorType, String value) throws NotSelectLocatorType {
        Select select = new Select(getElement(locator,locatorType));
        select.selectByVisibleText(value);
    }

    public static String getText(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        return getElement(locator, locatorType).getText();
    }

    public static void scrollToViewElement(String locator, LocatorType locatorType) throws NotSelectLocatorType {
        ((JavascriptExecutor) LocalDriverHelper.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);"
                , getElement(locator,locatorType));
    }
}
