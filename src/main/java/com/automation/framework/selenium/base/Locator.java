package com.automation.framework.selenium.base;

import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import org.openqa.selenium.By;

public class Locator {

    protected Locator() {
    }

    public static By getBy(final String locator,
                           final LocatorType locatorType)
            throws NotSelectLocatorType {
        switch (locatorType) {
            case XPATH:
                return By.xpath(locator);
            case CSS_SELECTOR:
                return By.cssSelector(locator);
            case NAME:
                return By.name(locator);
            case LINK_TEXT:
                return By.linkText(locator);
            case ID:
                return By.id(locator);
            case TAG_NAME:
                return By.tagName(locator);
            case CLASS:
                return By.className(locator);
            default:
                throw new
                NotSelectLocatorType("The locator type wasn't defined");
        }
    }
}
