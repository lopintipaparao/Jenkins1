package com.nopcommerce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadLocator {

    private FileInputStream instream;
    private Properties pro;
    public ReadLocator(String filename) throws Exception {
        String filepath=".\\src\\main\\resources\\locators\\"+filename+".properties";
        instream = new FileInputStream(filepath);
        pro=new Properties();
        pro.load(instream);
    }

    public String getLocator(String locatorName)
    {
        return pro.getProperty(locatorName);
    }

}
