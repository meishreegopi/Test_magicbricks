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

public class HomeInteriorsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    // Locators (validate in UI)
    private By profileMenu = By.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[1]/span");
    private By homeInteriors = By.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[3]/div/div[1]/ul/li[1]/span");
    private By homeInteriorDesignServices = By.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[3]/div/div[2]/div[2]/ul/li/ul/li[1]/a");
    private By selectCity = By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/div[2]/div[2]/div/div/div");
    private By ahmedabadCity = By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/a");

    public HomeInteriorsPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extTest = extTest;
    }

    public boolean clickProfileMenu() {
        try {
            WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
            menu.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on profile menu");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click profile menu: " + e.getMessage());
            return false;
        }
    }

    public boolean clickHomeInteriors() {
        try {
            WebElement interiors = wait.until(ExpectedConditions.elementToBeClickable(homeInteriors));
            interiors.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Home Interiors");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Home Interiors: " + e.getMessage());
            return false;
        }
    }

    public boolean clickHomeInteriorDesignServices() {
        try {
            WebElement designServices = wait.until(ExpectedConditions.elementToBeClickable(homeInteriorDesignServices));
            designServices.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Home Interior Design Services");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Home Interior Design Services: " + e.getMessage());
            return false;
        }
    }

    public boolean clickSelectCity() {
        try {
            WebElement cityPopup = wait.until(ExpectedConditions.elementToBeClickable(selectCity));
            cityPopup.click();
            Thread.sleep(1000); // wait for dropdown
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Select your city");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Select your city: " + e.getMessage());
            return false;
        }
    }

    public boolean clickAhmedabad() {
        try {
            WebElement ahmedabad = wait.until(ExpectedConditions.elementToBeClickable(ahmedabadCity));
            ahmedabad.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Ahmedabad city");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Ahmedabad: " + e.getMessage());
            return false;
        }
    }
}
