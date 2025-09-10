package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.setup.Reporter;

public class ShortlistPropertyPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public ShortlistPropertyPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.extTest = extTest;
    }

    /**
     * Clicks the "View Shortlisted Properties" button, switches to the new tab,
     * and validates the page title.
     * @return true if the action is successful, false otherwise.
     */
    public boolean viewShortlistedProperties() {
        try {
            WebElement viewShortlistBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.viewShortlistBtn));
            viewShortlistBtn.click();
            
            // Get all window handles
            Set<String> allWindowHandles = driver.getWindowHandles();
            ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
            
            // Switch to the new tab (assuming it's the second one)
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.get(2));
                Reporter.generateReport(driver, extTest, Status.PASS, "Shortlisted Property page. Current URL: " + driver.getCurrentUrl());
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to switch to a new tab. Only one tab was found.");
                return false;
            }

            // Validate the presence of the new tab's header using the specific locator
            WebElement shortlistedTabHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.shortlistTabHeader));
            if (shortlistedTabHeader.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Successfully navigated to the shortlisted properties");
                return true;
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to navigate to the shortlisted properties page. Header not found.");
                return false;
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to view shortlisted properties: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clicks on the first shortlisted property in the listing.
     * @return true if the property is clicked successfully, false otherwise.
     */
    public boolean clickFirstShortlistedProperty() {
        try {
            // Use the more specific locator for the first property
            WebElement firstProperty = wait.until(ExpectedConditions.elementToBeClickable(Locators.shortlistedPropertyCard));
            firstProperty.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Selected first shortlisted property.");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click on the first shortlisted property: " + e.getMessage());
            return false;
        }
    }
    public boolean sortByFilter() {
    	try {
    		WebElement shortlistBtn1 = wait.until(ExpectedConditions.elementToBeClickable(Locators.sortBy));
    	    shortlistBtn1.click();
    	    WebElement shortlistBtn2 = wait.until(ExpectedConditions.elementToBeClickable(Locators.mostrecent));
    	    shortlistBtn2.click();
    	    Reporter.generateReport(driver, extTest, Status.PASS, "Sortby most recent filter clicked");
            return true;
    	}
    	 catch (Exception e) {
             Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click the main shortlist button: " + e.getMessage());
             return false;
    	 }
    }
    public boolean shortlistPropertyFromList() {
        try {
        	   
          	WebElement shortlistBtn3=wait.until(ExpectedConditions.elementToBeClickable(Locators.shortlistButton));
          	shortlistBtn3.click();
            WebElement shortlistBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.mainshortlistbtn));
            shortlistBtn.click();
            
            Reporter.generateReport(driver, extTest, Status.PASS, "Successfully clicked the main shortlist button.");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click the main shortlist button: " + e.getMessage());
            return false;
        }
    }
    }