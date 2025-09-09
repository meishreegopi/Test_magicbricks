package com.pages;

import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;

import com.setup.Reporter;


public class SearchHomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public SearchHomePage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.extTest = extTest;
    }

    // Enter location
    public boolean enterLocation(String location) {
        try {
            WebElement locationBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.enterlocation));
            locationBox.clear();
            locationBox.sendKeys(location);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered location: " + location);
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter location: " + e.getMessage());
            return false;
        }
    }

    // Select property type
    public boolean selectPropertyType(String propertyType) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(Locators.clickPropertyType));
            dropdown.click();

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(Locators.selectPropertyType));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            Reporter.generateReport(driver, extTest, Status.PASS, "Selected property type: " + propertyType);
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to select property type: " + e.getMessage());
            return false;
        }
    }

    // Select budget
    public boolean selectBudget() {
        try {
            // Click on budget dropdown
            //WebElement budgetDropdown = wait.until(ExpectedConditions.elementToBeClickable(Locators.clickBudget));
            //budgetDropdown.click();

            // Expand dropdown section if needed
            WebElement dropdownSection = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dropdownBudget));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownSection);

            // Select Min Price
            WebElement minOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.minPrice));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", minOption);

            // Select Max Price
            WebElement maxOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.maxPrice));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maxOption);

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
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.searchButton));
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
            WebElement sortBy = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.sortBy));
            if (sortBy.isDisplayed()) {
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