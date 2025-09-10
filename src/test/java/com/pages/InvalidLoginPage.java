package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.setup.BaseSteps;
import com.setup.Reporter;

public class InvalidLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public InvalidLoginPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extTest = extTest;
    }
    
    // ---------------- Invalid Login Methods ---------------- //
    public boolean clickPhoneNumber1() {
        try {
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(Locators.phonenumber));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", phoneInput);

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on phone number input field");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click on phone number input field: " + e.getMessage());
            return false;
        }
    }

    
    public boolean switchToLoginTab1() {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            Reporter.generateReport(driver, extTest, Status.PASS, "Switched to Login Tab");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to switch to Login Tab: " + e.getMessage());
            return false;
        }
    }
    
    public boolean clickLoginBtn1() {
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.homeLoginBtn));

            // Scroll into view & click via JS (if normal click fails)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
            loginBtn.click();

            BaseSteps.sleep(); // short wait after click
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Home Login button");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Home Login button: " + e.getMessage());
            return false;
        }
    }

    public boolean clickInnerLoginBtn1() {
        try {
            WebElement innerLogin = wait.until(ExpectedConditions.elementToBeClickable(Locators.innerLoginBtn));
            innerLogin.click();
            BaseSteps.sleep();

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Inner Login button");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Inner Login button: " + e.getMessage());
            return false;
        }
    }

    
 // Enter invalid mobile dynamically
    public boolean enterInvalidMobile(String phone, String captcha) {
        try {
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(Locators.phonenumber));
            phoneInput.clear();
            phoneInput.sendKeys(phone);  
            driver.findElement(Locators.captacha).sendKeys(captcha); 
            driver.findElement(Locators.nextbtn).click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid mobile: " + phone);
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed in enterInvalidMobile(): " + e.getMessage());
            return false;
        }
    }

    // Enter mobile without captcha dynamically
    public boolean enterMobileWithoutCaptcha(String phone) {
        try {
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(Locators.phonenumber));
            phoneInput.clear();
            phoneInput.sendKeys(phone);
            driver.findElement(Locators.nextbtn).click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Tried login without captcha using: " + phone);
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed in enterMobileWithoutCaptcha(): " + e.getMessage());
            return false;
        }
    }






    public boolean clickNextWithoutDetails() {
        try {
            driver.findElement(Locators.nextbtn).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Next without entering details");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed in clickNextWithoutDetails(): " + e.getMessage());
            return false;
        }
    }

    // ---------------- Error Message Handling ---------------- //

//
    public boolean getNumErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Mobile') or contains(text(),'phone')]")
            ));

            String actualError = errorElement.getText().trim().toLowerCase();
            System.out.println("Captured mobile error: " + actualError);

            List<String> expectedErrors = Arrays.asList(
                "enter mobile", 
                "valid phone", 
                "phone number required"
            );

            boolean matched = expectedErrors.stream().anyMatch(actualError::contains);
            if (matched) {
                extTest.log(Status.PASS, "Invalid mobile number error displayed: " + actualError);
            } else {
                extTest.log(Status.FAIL, "Unexpected mobile error: " + actualError);
            }
            return matched;
        } catch (Exception e) {
            extTest.log(Status.FAIL, "No error message found for mobile number");
            return false;
        }
    }

//     Validate captcha error
    public boolean getCaptchaErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'captcha') or contains(text(),'Captcha')]")
            ));

            String actualError = errorElement.getText().trim().toLowerCase();
            System.out.println("Captured captcha error: " + actualError);

            List<String> expectedErrors = Arrays.asList(
                "enter captcha", 
                "captcha required", 
                "invalid captcha"
            );

            boolean matched = expectedErrors.stream().anyMatch(actualError::contains);
            if (matched) {
                extTest.log(Status.PASS, "Captcha error displayed: " + actualError);
            } else {
                extTest.log(Status.FAIL, "Unexpected captcha error: " + actualError);
            }
            return matched;
        } catch (Exception e) {
            extTest.log(Status.FAIL, "No error message found for captcha");
            return false;
        }
 
    }


}