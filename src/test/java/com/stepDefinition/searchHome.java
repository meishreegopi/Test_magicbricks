package com.stepDefinition;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.SearchHomePage;
import com.setup.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchHome extends BaseSteps {

    SearchHomePage searchPage;
    ExtentTest extTest = Hooks.extTest;

    @Given("the user is on the Magicbricks home page")
    public void the_user_is_on_the_magicbricks_home_page() {
        searchPage = new SearchHomePage(driver, extTest);
        String actUrl = driver.getCurrentUrl();
        Assert.assertTrue(actUrl.contains("magicbricks.com"),
                "User is not on the Magicbricks home page. Current URL: " + actUrl);
    }

    @When("the user enters {string} in the Search bar")
    public void the_user_enters_in_the_search_bar(String location) {
   
        boolean actResult = searchPage.enterLocation(location);
        Assert.assertTrue(actResult, "Failed to enter location: " + location);
    }

    @When("the user selects Property Type from drop down")
    public void the_user_selects_property_type_from_drop_down() {
        boolean actResult = searchPage.selectPropertyType();
        Assert.assertTrue(actResult, "Failed to select property type " );
    }
    
    @When("the user selects a budget range from the dropdown")
    public void the_user_selects_a_budget_range_from_the_dropdown() {
      	boolean actResult = searchPage.selectBudget();
        Assert.assertTrue(actResult, "Failed to select budget from dropdown");
    }

    @When("the user clicks on the Search button")
    public void the_user_clicks_on_the_search_button() {
        boolean actResult = searchPage.clickSearch();
        Assert.assertTrue(actResult, "Failed to click on Search button");
    }

    @Then("the user should be redirected to the property listing page")
    public void the_user_should_be_redirected_to_the_property_listing_page() {
    	boolean actResult = searchPage.validateListingPage();
        Assert.assertTrue(actResult, "User not redirected to listing page or Sort By dropdown not visible");
    }
}