package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.setup.BaseSteps;
import com.setup.Reporter;


public class LoginPage extends BaseSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public LoginPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extTest = extTest;
    }

    public boolean clickLoginBtn() {
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.homeLoginBtn));
            loginBtn.click();
            BaseSteps.sleep();

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Home Login button");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Home Login button: " + e.getMessage());
            return false;
        }
    }

    public boolean clickInnerLoginBtn() {
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

    public boolean switchToLoginTab() {
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

    public boolean clickPhoneNumber() {
        try {
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(Locators.phonenumber));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", phoneInput);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter mobile number: ");
            String mobile = sc.nextLine();
            System.out.print("Enter captcha: ");
            String captcha = sc.nextLine();

            phoneInput.sendKeys(mobile);
            driver.findElement(Locators.captacha).sendKeys(captcha);
            BaseSteps.sleep();
            driver.findElement(Locators.nextbtn).click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered mobile number & captcha successfully");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter mobile number/captcha: " + e.getMessage());
            return false;
        }
    }

    public boolean enterOtp() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter OTP: ");
            String otp = scanner.nextLine();

            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp1)).sendKeys(otp.substring(0, 1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp2)).sendKeys(otp.substring(1, 2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp3)).sendKeys(otp.substring(2, 3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp4)).sendKeys(otp.substring(3, 4));

            wait.until(ExpectedConditions.elementToBeClickable(Locators.continuebtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Locators.closepopup)).click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered OTP successfully and logged in");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter OTP: " + e.getMessage());
            return false;
        }
    }

    public boolean validateLogin() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"commercialIndex\"]/header/section[2]/div/ul/li[7]/a")
            ));

            Reporter.generateReport(driver, extTest, Status.PASS, "Login validated successfully");
            return true;
        } catch (TimeoutException e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Login validation failed - User not logged in");
            return false;
        }
    }
}