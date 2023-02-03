package com.nopcommerce.pages;

import com.nopcommerce.utils.ReadLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    private WebDriver driver;
    public DashboardPage(WebDriver driver)
    {
        this.driver=driver;
    }
    private WebElement getElement(String locatorname) throws Exception {
        ReadLocator readLocator= new ReadLocator("dashboardpage");
        String locatorValue=readLocator.getLocator(locatorname);
        By locator= By.xpath(locatorValue);
        WebElement element= driver.findElement(locator);
        return element;
    }

    public boolean isDashboardVisible() throws Exception {
        WebElement dashboardLabel=getElement("dashboardLabel");
        return dashboardLabel.isDisplayed();
    }
}
