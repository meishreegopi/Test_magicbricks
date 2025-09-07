package com.stepDefinition;

import com.pages.LoginPage;
import com.setup.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Profile extends BaseSteps
{
	LoginPage loginPage;
	@Given("I launch the magicbricks website")
	public void i_launch_the_magicbricks_website() {
		launchBrowser();
	}
	@Given("I am on the homepage")
	public void i_am_on_the_homepage() {
		loginPage = new LoginPage(driver);
        loginPage.clickLoginBtn();
	}
	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		loginPage.clickinnerloginBtn();
	}
	@When("I am redirected to the login page and click on phonenumber")
	public void i_am_redirected_to_the_login_page_and_click_on_phonenumber() {
		loginPage.switchToLoginTab();
		loginPage.clickphonenumber();
	}
	@When("I enter the otp")
	public void i_enter_the_otp() {
		loginPage.enterotp();
	    
	}
	@Then("I should be redirected to the homepage")
	public void i_should_be_redirected_to_the_homepage() {
	    
	}
}