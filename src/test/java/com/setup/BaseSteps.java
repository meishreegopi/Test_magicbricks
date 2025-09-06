package com.setup;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSteps {
	public final static int TIME = 2000;
	public static WebDriver driver;
	public void launchBrowser() {
		Properties prop = PropertyReader.readProperties();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
			
		}
		
public static void sleep() {
	try {
		Thread.sleep(TIME);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}