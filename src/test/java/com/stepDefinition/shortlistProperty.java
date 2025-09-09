package com.stepDefinition;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.ShortlistPropertyPage;
import com.setup.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class shortlistProperty extends BaseSteps {
	ShortlistPropertyPage shortlistPage;
	ExtentTest extTest = Hooks.extTest;
	@Given("the user is on the property listing page")
	public void the_user_is_on_the_property_listing_page() {
	    
	}
	@Given("clicks on sortby and selects most recent filter")
	public void clicks_on_sortby_and_selects_most_recent_filter() {
		shortlistPage = new ShortlistPropertyPage(driver, Hooks.extTest);
		boolean actResult = shortlistPage.sortByFilter();
		Assert.assertTrue(actResult, "Failed to select sortby mostrecent filter.");
	}
	@When("I shortlist the first property from the listing")
	public void i_shortlist_the_first_property_from_the_listing() {
		shortlistPage = new ShortlistPropertyPage(driver, Hooks.extTest);
        boolean actResult = shortlistPage.shortlistPropertyFromList();
        Assert.assertTrue(actResult, "Failed to shortlist the first property from the listing.");
	   
	}
	@When("I view the shortlisted properties")
	public void i_view_the_shortlisted_properties() {
		shortlistPage = new ShortlistPropertyPage(driver, Hooks.extTest);
        boolean actResult = shortlistPage.viewShortlistedProperties();
        Assert.assertTrue(actResult, "Failed to view shortlisted properties.");
        
	}
	@Then("I click on property")
	public void i_click_on_property() {
		boolean actResult = shortlistPage.clickFirstShortlistedProperty();
        Assert.assertTrue(actResult, "Failed to click on the shortlisted property.");
    }

}