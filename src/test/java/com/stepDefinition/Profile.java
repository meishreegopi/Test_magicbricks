package com.stepDefinition;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.LoginPage;
import com.setup.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Profile extends BaseSteps {

    LoginPage loginPage;
    ExtentTest extTest = Hooks.extTest; 

    @Given("I launch the magicbricks website")
    public void i_launch_the_magicbricks_website() {
        // ✅ browser already launched in Hooks
        System.out.println("Browser launched via Hooks and Magicbricks website opened.");
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        loginPage = new LoginPage(driver, extTest);
        boolean actResult = loginPage.clickLoginBtn();
        Assert.assertTrue(actResult, "Failed at homepage -> clickLoginBtn()");
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        boolean actResult = loginPage.clickInnerLoginBtn();
        Assert.assertTrue(actResult, "Failed at login -> clickInnerLoginBtn()");
    }

    @When("I am redirected to the login page and click on phonenumber")
    public void i_am_redirected_to_the_login_page_and_click_on_phonenumber() {
        boolean switched = loginPage.switchToLoginTab();
        Assert.assertTrue(switched, "Failed to switch to login tab");

        boolean actResult = loginPage.clickPhoneNumber();
        Assert.assertTrue(actResult, "Failed at login -> clickPhoneNumber()");
    }

    @When("I enter the otp")
    public void i_enter_the_otp() {
        boolean actResult = loginPage.enterOtp();
        Assert.assertTrue(actResult, "Failed at login -> enterOtp()");
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        boolean actResult = loginPage.validateLogin();
        Assert.assertTrue(actResult, "Login validation failed -> User not on homepage");
    }
}