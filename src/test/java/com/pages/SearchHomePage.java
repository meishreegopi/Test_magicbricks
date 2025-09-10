package com.pages;

import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
    /**
     * Clicks the close button to remove the default location.
     * @return true if the close button is clicked successfully, false otherwise.
     */
    
    public boolean enterLocation(String location) {
        try {
        	
            WebElement locationBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.enterlocation));
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
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(Locators.clickPropertType));
            dropdown.click();

            // First try to clear/deselect all (if available)
            try {
                WebElement clearOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.clearPropertyType));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearOption);
                Reporter.generateReport(driver, extTest, Status.INFO, "Cleared existing property type selection");
            } catch (TimeoutException te) {
                Reporter.generateReport(driver, extTest, Status.INFO, "No clear option available, continuing...");
            }

            // Now select the new option
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(Locators.selectPropertyType));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
            WebElement close = wait.until(ExpectedConditions.elementToBeClickable(Locators.closePropertyType));
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
            WebElement dropdownSection = wait.until(ExpectedConditions.elementToBeClickable(Locators.clickBudget));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownSection);
            
            //Min dropdown
            WebElement minOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.minPrice));
            //minOption.clear();              
            minOption.sendKeys("500000");
            minOption.click();

            //Min dropdown
            WebElement maxOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.maxPrice));
            //maxOption.clear();              
            maxOption.sendKeys("15000000");
            maxOption.click();
            WebElement closeOption = wait.until(ExpectedConditions.elementToBeClickable(Locators.closeBudget));
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