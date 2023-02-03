package com.nopcommerce.pages;

import com.nopcommerce.utils.ReadData;
import com.nopcommerce.utils.ReadLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private WebElement getElement(String locatorname) throws Exception {
        ReadLocator readLocator= new ReadLocator("loginpage");
        String locatorValue=readLocator.getLocator(locatorname);
        By locator= By.xpath(locatorValue);
        WebElement element= driver.findElement(locator);
         return element;
    }
    private  void clearTextBox(WebElement element)
    {
        element.clear();
    }
    private  void setTextInTextBox(WebElement element,String data)
    {
        element.sendKeys(data);
    }
    private  void clickOnElement(WebElement ele)
    {
        ele.click();
    }

    private String getLabelText(WebElement element)
    {
      return element.getText();
    }

    private String getAttribute(WebElement element)
    {
        return element.getAttribute("innerHTML");
    }
    public void clearEmailTextBox() throws Exception {
        WebElement emailTextBox=getElement("emailTextboxL");
        clearTextBox(emailTextBox);
    }
    public void setTextInEmailTextBox(String email) throws Exception {
        WebElement emailTextBox=getElement("emailTextboxL");
        setTextInTextBox(emailTextBox,email);
    }
    public void clearPasswordTextBox() throws Exception {
        WebElement passwordTextbox=getElement("passwordTextboxL");
        clearTextBox(passwordTextbox);
    }
    public void setTextInPasswordTextBox(String pass) throws Exception {
        WebElement passwordTextbox=getElement("passwordTextboxL");
        setTextInTextBox(passwordTextbox,pass);
    }
    public  void clickOnLoginBtn() throws Exception {
        WebElement loginBtn=getElement("loginBtn");
        clickOnElement(loginBtn);
    }

    public String getErrorMsgAtEmailTextBox() throws Exception {
        WebElement emailErrorLabel=getElement("emailErrorLabelL");
        return getLabelText(emailErrorLabel);
    }

    public String getInValidErrorMsg() throws Exception {
        WebElement invalidLoginErrorLabel=getElement("invalidLoginErrorLabelL");
                    return getAttribute(invalidLoginErrorLabel);
    }


}
