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

    public boolean viewShortlistedProperties() {
        try {
            WebElement viewShortlistBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.viewShortlistBtn));
            viewShortlistBtn.click();

            // Switch to latest tab
            Set<String> allWindowHandles = driver.getWindowHandles();
            ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
            driver.switchTo().window(tabs.get(tabs.size() - 1));

            Reporter.generateReport(driver, extTest, Status.PASS, 
                "Switched to shortlisted properties tab. Current URL: " + driver.getCurrentUrl());

            WebElement shortlistedTabHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.shortlistTabHeader));
            if (shortlistedTabHeader.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Successfully navigated to shortlisted properties");
                return true;
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Shortlist page header not found.");
                return false;
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to view shortlisted properties: " + e.getMessage());
            return false;
        }
    }

    public boolean clickFirstShortlistedProperty() {
        try {
            WebElement firstProperty = wait.until(ExpectedConditions.elementToBeClickable(Locators.shortlistedPropertyCard));
            firstProperty.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Selected first shortlisted property.");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click first shortlisted property: " + e.getMessage());
            return false;
        }
    }

    public boolean sortByFilter() {
        try {
            WebElement shortlistBtn1 = wait.until(ExpectedConditions.elementToBeClickable(Locators.sortBy));
            shortlistBtn1.click();
            WebElement shortlistBtn2 = wait.until(ExpectedConditions.elementToBeClickable(Locators.mostrecent));
            shortlistBtn2.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Sort by Most Recent applied");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to apply Sort By filter: " + e.getMessage());
            return false;
        }
    }

    public boolean shortlistPropertyFromList() {
        try {
            WebElement shortlistBtn3 = wait.until(ExpectedConditions.elementToBeClickable(Locators.shortlistButton));
            shortlistBtn3.click();
            WebElement shortlistBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.mainshortlistbtn));
            shortlistBtn.click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Successfully shortlisted a property.");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to shortlist property: " + e.getMessage());
            return false;
        }
    }
}