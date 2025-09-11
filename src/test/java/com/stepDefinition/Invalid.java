package com.stepDefinition;


import java.util.Scanner;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.InvalidLoginPage;
import com.setup.BaseSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Invalid extends BaseSteps {

    InvalidLoginPage invalidLoginPage;
    ExtentTest extTest = Hooks.extTest;
    
    @Before("@InvalidLogin")
    public void startBrowser() {
        launchBrowser();
        driver.get("https://www.magicbricks.com/");
        System.out.println("Browser launched fresh for InvalidLogin scenario");
    }

    @After("@InvalidLogin")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println(" Browser closed after InvalidLogin scenario");
        }
    }

    @Given("I am on the homepage of magicbricks")
    public void i_am_on_the_homepage_of_magicbricks() {
        invalidLoginPage = new InvalidLoginPage(driver, extTest);
        System.out.println("Browser launched via Hooks and Magicbricks website opened.");
    }

    @When("I click on the login button of home page")
    public void i_click_on_the_login_button_of_home_page() {
        boolean actResult = invalidLoginPage.clickLoginBtn1();
        Assert.assertTrue(actResult, "Failed at login -> clickLoginBtn()");
    }

    @When("I click the inner login button")
    public void i_click_the_inner_login_button() {
        boolean actResult = invalidLoginPage.clickInnerLoginBtn1();
        Assert.assertTrue(actResult, "Failed at login -> clickInnerLoginBtn()");
    }

    @When("I am redirected to the login page")
    public void i_am_redirected_to_the_login_page() {
        boolean switched = invalidLoginPage.switchToLoginTab1();
        Assert.assertTrue(switched, "Failed to switch to login tab");

        boolean actResult = invalidLoginPage.clickPhoneNumber1();
        Assert.assertTrue(actResult, "Failed at login -> clickPhoneNumber()");
    }

    @When("I enter an invalid mobile number {string} and captcha {string}")
    public void i_enter_an_invalid_mobile_number_and_captcha(String phone, String captcha) {
        boolean actResult = invalidLoginPage.enterInvalidMobile(phone, captcha);
        Assert.assertTrue(actResult, "Failed at invalid login -> enterInvalidMobile()");
    }
    
    @Then("I should see an error message for invalid mobile number")
    public void i_should_see_error_message_for_invalid_mobile_number() {
    	 Assert.assertTrue(invalidLoginPage.getNumErrorMessage(), "Expected error not displayed for invalid mobile number");
    }
    
    @When("I enter a mobile number {string} without captcha")
    public void i_enter_a_mobile_number_without_captcha(String phone) {
        boolean actResult = invalidLoginPage.enterMobileWithoutCaptcha(phone);
        Assert.assertTrue(actResult, "Failed at invalid login -> enterMobileWithoutCaptcha()");
    }
    
    @Then("I should see an error message for captcha")
    public void i_should_see_error_message_for_captcha() {
        Assert.assertTrue(invalidLoginPage.getCaptchaErrorMessage(), "Expected error not displayed for captcha");
    }
 
    @When("I try to proceed without entering details")
    public void i_try_to_proceed_without_entering_details() {
        boolean actResult = invalidLoginPage.clickNextWithoutDetails();
        Assert.assertTrue(actResult, "Failed at invalid login -> clickNextWithoutDetails()");
    }
    
    @Then("I should see an error message for mandatory fields")
    public void i_should_see_error_message_for_mandatory_fields() {
    	  By mobileField = By.id("emailOrMobile");  

    	    boolean actResult = invalidLoginPage.validateMandatoryFieldMessage(
    	        mobileField,
    	        "Please fill out this field"
    	    );
    	    Assert.assertTrue(actResult, "Expected error not displayed for mandatory fields");
    }
}