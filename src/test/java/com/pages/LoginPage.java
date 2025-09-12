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
import com.setup.BaseSteps;
import com.setup.Reporter;

public class LoginPage extends BaseSteps {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest extTest;

	public static By homeLoginBtn = By.xpath("//*[@id=\"commercialIndex\"]/header/section[1]/div/div[2]/div[2]/a");
	public static By innerLoginBtn = By.xpath("//*[@id=\"commercialIndex\"]/header/section[1]/div/div[2]/div[2]/div/div[2]/a");
	public static By googleLoginBtn = By.xpath("//*[@id=\"my-signin2\"]/div/div");
	public static By phonenumber = By.xpath("//*[@id=\"emailOrMobile\"]");
	public static By nextbtn = By.id("btnStep1");
	public static By captacha = By.xpath("//*[@id=\"captchaCodeSignIn\"]");
	public static By otp1 = By.id("verify01");
	public static By otp2 = By.id("verify02");
	public static By otp3 = By.id("verify03");
	public static By otp4 = By.id("verify04");
	public static By continuebtn = By.xpath("//*[@id=\"verifyOtpDiv\"]/div[2]/div[3]/button");
	public static By closepopup = By.xpath("//*[@id=\"userOnboardingWidget\"]/div/div[1]");

	public LoginPage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.extTest = extTest;
	}

	public boolean clickLoginBtn() {
		try {
			WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(homeLoginBtn));
			loginBtn.click();
			BaseSteps.sleep();

			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Home Login button");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to click Home Login button: " + e.getMessage());
			return false;
		}
	}

	public boolean clickInnerLoginBtn() {
		try {
			WebElement innerLogin = wait.until(ExpectedConditions.elementToBeClickable(innerLoginBtn));
			innerLogin.click();
			BaseSteps.sleep();

			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Inner Login button");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to click Inner Login button: " + e.getMessage());
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
			WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(phonenumber));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", phoneInput);

			Scanner sc = new Scanner(System.in);
			System.out.print("Enter mobile number: ");
			String mobile = sc.nextLine();
			System.out.print("Enter captcha: ");
			String captcha = sc.nextLine();

			phoneInput.sendKeys(mobile);
			driver.findElement(captacha).sendKeys(captcha);
			BaseSteps.sleep();
			driver.findElement(nextbtn).click();

			Reporter.generateReport(driver, extTest, Status.PASS, "Entered mobile number & captcha successfully");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to enter mobile number/captcha: " + e.getMessage());
			return false;
		}
	}

	public boolean enterOtp() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter OTP: ");
			String otp = scanner.nextLine();

			wait.until(ExpectedConditions.visibilityOfElementLocated(otp1)).sendKeys(otp.substring(0, 1));
			wait.until(ExpectedConditions.visibilityOfElementLocated(otp2)).sendKeys(otp.substring(1, 2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(otp3)).sendKeys(otp.substring(2, 3));
			wait.until(ExpectedConditions.visibilityOfElementLocated(otp4)).sendKeys(otp.substring(3, 4));

			wait.until(ExpectedConditions.elementToBeClickable(continuebtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(closepopup)).click();

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
					By.xpath("//*[@id=\"commercialIndex\"]/header/section[2]/div/ul/li[7]/a")));

			Reporter.generateReport(driver, extTest, Status.PASS, "Login validated successfully");
			return true;
		} catch (TimeoutException e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Login validation failed - User not logged in");
			return false;
		}
	}
}