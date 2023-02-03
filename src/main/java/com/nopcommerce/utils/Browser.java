package com.nopcommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Browser {
    private static WebDriver driver;
    public static WebDriver launchBrowser(String browsername)
    {
        if(browsername.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "D:\\Automation_Jars_Softwares\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", "D:\\Automation_Jars_Softwares\\chromedriver.exe");
            driver=new EdgeDriver();
        }
        return driver;
    }
    public static void openURL(String url)
    {
        driver.get(url);
    }
    public static void close()
    {
        driver.close();
    }
}
