package com.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSteps {
    protected static WebDriver driver;

    public static void launchBrowser() {
        if (driver == null) {
            driver = new ChromeDriver();   // initialize driver
            driver.manage().window().maximize();
        }
        driver.get("https://www.magicbricks.com");  // open URL
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


		public static void sleep() {
		    try {
		        Thread.sleep(2000); // 2 seconds wait
		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt();
		    }
		}

		
	}

