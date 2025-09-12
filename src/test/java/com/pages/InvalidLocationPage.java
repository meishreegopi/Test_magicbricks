package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.setup.Reporter;

public class InvalidLocationPage {
	// Locators
	public static By deselect = By.xpath("//*[@id=\"keyword_autoSuggestSelectedDiv\"]/div/div[2]");
	public static By loc = By.xpath("//*[@id=\"keyword\"]");
	public static By searchButton = By.xpath("//*[@id=\"searchFormHolderSection\"]/section/div/div[1]/div[3]/div[4]");

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest extTest;

	public InvalidLocationPage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.extTest = extTest;
	}

	// Enter location
	public boolean enterLocation1(String location) {

		try {

			WebElement locationBox = wait.until(ExpectedConditions.elementToBeClickable(loc));
			locationBox.click();
			WebElement locationBox1 = wait.until(ExpectedConditions.elementToBeClickable(deselect));
			locationBox1.click();
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// locationBox1);
			locationBox.clear();
			locationBox.sendKeys(location);

			// WebElement locationBox2 =
			// wait.until(ExpectedConditions.elementToBeClickable(selectbgl));
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// locationBox2);

			Reporter.generateReport(driver, extTest, Status.PASS, "Entered location: " + location);
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter location: " + e.getMessage());
			return false;

		}
	}

	// Click search
	public boolean clickSearch1() {
		try {
			WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			searchBtn.click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Search button");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Search button: " + e.getMessage());
			return false;
		}
	}

	// Validate stay on home
	public boolean validateStayOnHome() {
		try {
			WebElement buyHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));

			if (buyHeading.isDisplayed()) {
				Reporter.generateReport(driver, extTest, Status.PASS, "User remained on home page.");
				return true;
			}
			return false;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"User did not remain on home page: " + e.getMessage());
			return false;
		}
	}

}