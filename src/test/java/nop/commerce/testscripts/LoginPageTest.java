package nop.commerce.testscripts;

import com.nopcommerce.pages.DashboardPage;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.utils.Browser;
import com.nopcommerce.utils.DataConfigConstants;
import com.nopcommerce.utils.ReadData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest {

    public WebDriver driver;
    ReadData loginData;
    @BeforeMethod
    public void setup() throws Exception //prereq
    {
        ReadData readData = new ReadData("browserpage");
         loginData = new ReadData("loginpage");

        List<String> browserInfo=readData.getData(1);
        String browsername=browserInfo.get(0);
        String url=browserInfo.get(1);

        driver=Browser.launchBrowser(browsername);
        Browser.openURL(url);
    }
    @AfterMethod
    public void teardown() //post
    {
        Browser.close();
    }

    @Test(enabled = true)
    public void verifyLoginBtnFunctionalityEmailAsBlank() throws Exception {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.clearEmailTextBox();
        loginPage.clickOnLoginBtn();
        String actualErrorMsg=loginPage.getErrorMsgAtEmailTextBox();
        Assert.assertEquals(actualErrorMsg, DataConfigConstants.EMAIL_BLANK_ERROR_MSG);
    }

    @Test(enabled = false) ///////////////
    public void verifyLoginBtnFunctionalityWithInvalidEmailFormat() throws Exception {
        String invalidEmailFormat=loginData.getData(8,0);
        System.out.println("invalidEmailFormat"+invalidEmailFormat);
        LoginPage loginPage= new LoginPage(driver);
        loginPage.clearEmailTextBox();
        loginPage.setTextInEmailTextBox(invalidEmailFormat);
        loginPage.clickOnLoginBtn();
        String actualErrorMsg=loginPage.getErrorMsgAtEmailTextBox();
        Assert.assertEquals(actualErrorMsg, DataConfigConstants.INVALID_EMAIL_FORMAT_ERROR_MSG);
    }

    @Test(enabled = false) //////
    public void verifyLoginBtnFunctionalityWithInvalidEmail() throws Exception {
        String invalidEmailFormat=loginData.getData(9,0);
        System.out.println("invalidEmailFormat"+invalidEmailFormat);
        LoginPage loginPage= new LoginPage(driver);
        loginPage.clearEmailTextBox();
        loginPage.setTextInEmailTextBox(invalidEmailFormat);
        loginPage.clickOnLoginBtn();
        String actualErrorMsg=loginPage.getInValidErrorMsg();
       System.out.println(actualErrorMsg);
       Assert.assertTrue(actualErrorMsg.contains(DataConfigConstants.INVALID_LOGIN_ERROR_MSG1));
        Assert.assertTrue(actualErrorMsg.contains(DataConfigConstants.INVALID_LOGIN_ERROR_MSG2));
    }
    @Test(enabled = true,invocationCount = 3)
    public void verifyLoginBtnFunctionalityWithvalidEmail() throws Exception {
        String validEmail=loginData.getData(1,0);
        LoginPage loginPage= new LoginPage(driver);
        DashboardPage dashboardPage= new DashboardPage(driver);
        loginPage.clearEmailTextBox();
        loginPage.setTextInEmailTextBox(validEmail);
        loginPage.clickOnLoginBtn();
       Assert.assertTrue(dashboardPage.isDashboardVisible(),"Test script failed as Dashboard is not visible ");
    }

    @DataProvider(name="loginCredentials")
    public Object[][] getData() throws Exception {
        loginData = new ReadData("loginpage");
        return loginData.getData();
    }

    @Test(enabled = true,dataProvider = "loginCredentials")
    public void verifyLoginBtnFunctionalityWithSetOfCredentials(String email,String pass) throws Exception {
        LoginPage loginPage= new LoginPage(driver);
        DashboardPage dashboardPage= new DashboardPage(driver);
        loginPage.clearEmailTextBox();
        loginPage.setTextInEmailTextBox(email);
        loginPage.clearPasswordTextBox();
        loginPage.setTextInPasswordTextBox(pass);
        loginPage.clickOnLoginBtn();
        Assert.assertTrue(dashboardPage.isDashboardVisible(),"Test script failed as Dashboard is not visible ");
    }

}
