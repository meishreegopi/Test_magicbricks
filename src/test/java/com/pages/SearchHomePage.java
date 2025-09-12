package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Reporter;

public class SearchHomePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest extTest;

	public static By enterlocation = By.id("keyword");
	public static By selectdropdownloc = By.id("//*[@id=\"serachSuggest\"]/div[2]/span");
	public static By clickPropertType = By.id("propType_buy");
	public static By clearPropertyType = By.xpath("//*[@id=\"propType_buy\"]/div[2]/div/div/div[1]/div[2]/div[4]");
	public static By dropdownProperType = By.xpath("//*[@id=\"propType_buy\"]/div[1]");
	public static By selectPropertyType = By.id("10002_10003_10021_10022");
	public static By closePropertyType = By.xpath("//*[@id=\"buy_proertyTypeDefault\"]");

	public static By clickBudget = By.id("rent_budget_lbl");
	public static By minPrice = By.xpath("//*[@id=\"budgetMin\"]");
	public static By maxPrice = By.xpath("//*[@id=\"budgetMax\"]");
	public static By closeBudget = By.xpath("//*[@id=\"rent_budget_lbl\"]");
	public static By searchButton = By.xpath("//*[@id=\"searchFormHolderSection\"]/section/div/div[1]/div[3]/div[4]");
	public static By sortBy = By.xpath("//*[@id=\"body\"]/div[5]/div/div/div[1]/div[1]/div[1]");

	public SearchHomePage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.extTest = extTest;
	}

	public boolean enterLocation(String location) {
		try {

			WebElement locationBox = wait.until(ExpectedConditions.elementToBeClickable(enterlocation));
			locationBox.sendKeys(location);
			Reporter.generateReport(driver, extTest, Status.PASS, "Entered location: " + location);
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter location: " + e.getMessage());
			return false;
		}
	}

	// Select property type
	public boolean selectPropertyType() {
		try {
			WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(clickPropertType));
			dropdown.click();

			// First try to clear/deselect all (if available)
			try {
				WebElement clearOption = wait.until(ExpectedConditions.elementToBeClickable(clearPropertyType));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearOption);
				Reporter.generateReport(driver, extTest, Status.INFO, "Cleared existing property type selection");
			} catch (TimeoutException te) {
				Reporter.generateReport(driver, extTest, Status.INFO, "No clear option available, continuing...");
			}

			// Now select the new option
			WebElement option = wait.until(ExpectedConditions.elementToBeClickable(selectPropertyType));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
			WebElement close = wait.until(ExpectedConditions.elementToBeClickable(closePropertyType));
			close.click();

			Reporter.generateReport(driver, extTest, Status.PASS, "Selected property type");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to select property type: " + e.getMessage());
			return false;
		}

	}

	// Select budget
	public boolean selectBudget() {
		try {
			// Expand dropdown section if needed
			WebElement dropdownSection = wait.until(ExpectedConditions.elementToBeClickable(clickBudget));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownSection);

			// Min dropdown
			WebElement minOption = wait.until(ExpectedConditions.elementToBeClickable(minPrice));
			// minOption.clear();
			minOption.sendKeys("500000");
			minOption.click();

			// Min dropdown
			WebElement maxOption = wait.until(ExpectedConditions.elementToBeClickable(maxPrice));
			// maxOption.clear();
			maxOption.sendKeys("15000000");
			maxOption.click();
			WebElement closeOption = wait.until(ExpectedConditions.elementToBeClickable(closeBudget));
			closeOption.click();

			Reporter.generateReport(driver, extTest, Status.PASS,
					"Selected Budget successfully using dropdown (Min & Max)");
			return true;

		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to select budget: " + e.getMessage());
			return false;
		}
	}

	// Click search
	public boolean clickSearch() {
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

	// Validate listing page
	public boolean validateListingPage() {
		try {
			WebElement sortby = wait.until(ExpectedConditions.visibilityOfElementLocated(sortBy));
			if (sortby.isDisplayed()) {
				Reporter.generateReport(driver, extTest, Status.PASS, "Listing page validated by Sort By dropdown");
				return true;
			} else {
				Reporter.generateReport(driver, extTest, Status.FAIL, "Sort By dropdown not visible");
				return false;
			}
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to validate listing page: " + e.getMessage());
			return false;
		}
	}
}