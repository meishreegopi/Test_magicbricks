package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	public static By profileMenu = By.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[1]");
	public static By homeInteriors = By
			.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[3]/div/div[1]/ul/li[1]/span");
	public static By interiorDesignersDelhi = By
			.xpath("//*[@id=\"shortlistWeb\"]/div[1]/div/div/div[2]/div[3]/div/div[2]/div[2]/ul/li/ul/li[2]/a");

	// NEW LOCATORS
	public static By homeInteriorsPageHeader = By.xpath("//h1[text()='Interior Designers in New Delhi']");
	public static By firstDesignerCard = By.xpath("(//div[@class='srpCards']/a)[1]");

	public static By designerImage = By
			.xpath("//*[@id=\"brandDetailsPage\"]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/img");
	public static By designerProfileHeader = By.xpath("//*[@id=\"brandDetailsPage\"]/div[1]/div[1]/div[1]/div[2]/h1");

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

	public boolean clickInteriorDesignersDelhi() {
		try {
			WebElement delhiDesigners = wait.until(ExpectedConditions.elementToBeClickable(interiorDesignersDelhi));
			delhiDesigners.click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Interior Designers in New Delhi");
			wait.until(d -> d.getWindowHandles().size() > 1);
			List<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
			System.out.println("Switched to tab URL: " + driver.getCurrentUrl());
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to click Interior Designers in New Delhi: " + e.getMessage());
			return false;
		}
	}

	public boolean isHomeInteriorsDesignersPageDisplayed() {
		try {
			WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(homeInteriorsPageHeader));

			WebElement firstCard = wait.until(ExpectedConditions.visibilityOfElementLocated(firstDesignerCard));

			if (header.isDisplayed() && firstCard.isDisplayed()) {
				Reporter.generateReport(driver, extTest, Status.PASS,
						"Verified Home Interiors Designers page is displayed via page header and designer card.");
				return true;
			} else {
				Reporter.generateReport(driver, extTest, Status.FAIL,
						"Home Interiors Designers page was not displayed.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Home Interiors Designers page not displayed. Current URL: " + driver.getCurrentUrl());
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Home Interiors Designers page was not displayed. URL: " + driver.getCurrentUrl() + " Error: "
							+ e.getMessage());
			return false;
		}
	}

	public boolean clickFirstDesigner() {
		try {
			WebElement designer = wait.until(ExpectedConditions.elementToBeClickable(firstDesignerCard));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", designer);
			js.executeScript("arguments[0].click();", designer);

			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on first designer from the list");

			wait.until(d -> d.getWindowHandles().size() > 1);
			String parentWindow = driver.getWindowHandle();
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(parentWindow)) {
					driver.switchTo().window(window);
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(designerProfileHeader));

			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click first designer: " + e.getMessage());
			return false;
		}
	}

	public boolean isDesignerProfilePageDisplayed() {
		try {
			WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(designerProfileHeader));
			Reporter.generateReport(driver, extTest, Status.PASS, "Designer's profile page is displayed.");
			return header.isDisplayed();
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Designer's profile page was not displayed. URL: "
					+ driver.getCurrentUrl() + " Error: " + e.getMessage());
			return false;
		}
	}

	public boolean clickDesignerImage() {
		try {
			WebElement image = wait.until(ExpectedConditions.elementToBeClickable(designerImage));
			image.click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on designer's image");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to click designer's image: " + e.getMessage());
			return false;
		}
	}
}
