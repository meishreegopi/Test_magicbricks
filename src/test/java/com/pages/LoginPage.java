package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Scanner;
import org.openqa.selenium.By;

import com.parameters.Locators;
import com.setup.BaseSteps;

public class LoginPage extends BaseSteps {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickLoginBtn() {
        driver.findElement(Locators.homeLoginBtn).click();
        BaseSteps.sleep();
    }
    public void clickinnerloginBtn() {
        driver.findElement(Locators.innerLoginBtn).click();
        BaseSteps.sleep();
    }

    // Step 2: Switch to the new tab
    public void switchToLoginTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // second tab
    }
    public void clickphonenumber() {
        driver.findElement(Locators.phonenumber).click();
        Scanner sc = new Scanner(System.in);
        String mobile = sc.nextLine();
        String captcha = sc.nextLine();

        driver.findElement(By.id("emailOrMobile")).sendKeys(mobile); 
        driver.findElement(Locators.captacha).sendKeys(captcha); 
        BaseSteps.sleep();
        driver.findElement(Locators.nextbtn).click();
    }
    public void enterotp() {
    	Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine();
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp1)).sendKeys(otp.substring(0, 1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp2)).sendKeys(otp.substring(1, 2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp3)).sendKeys(otp.substring(2, 3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otp4)).sendKeys(otp.substring(3, 4));

            wait.until(ExpectedConditions.elementToBeClickable(Locators.continuebtn)).click();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Always close the scanner in the finally block
            scanner.close();
        }
    }     

          
    // This is the only method you need now, for validating the login status.
    public boolean validateLogin() {
        try {
            return driver.findElement(By.xpath("//span[contains(text(),'Mei')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}