package com.setup;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSteps {
    
    public static WebDriver driver;  // shared driver
    public final static int TIME = 3000;

    public static void launchBrowser() {
        if (driver == null) {   // avoid multiple launches
            Properties prop = PropertyReader.readProperties();
            driver = new ChromeDriver();
            driver.manage().window().maximize(); 
            driver.get(prop.getProperty("URL"));
        }
    }
    
    public static void sleep() {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}