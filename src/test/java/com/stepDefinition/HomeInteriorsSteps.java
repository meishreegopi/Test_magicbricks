package com.stepDefinition;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.pages.HomeInteriorsPage;
import com.setup.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class HomeInteriorsSteps extends BaseSteps {

    HomeInteriorsPage interiorsPage;
    ExtentTest extTest = Hooks.extTest;

    @Given("the user is on the shortlist page")
    public void the_user_is_on_the_shortlist_page() {
        interiorsPage = new HomeInteriorsPage(driver, extTest);
        String actUrl = driver.getCurrentUrl();
        Assert.assertTrue(actUrl.contains("shortlist"),
            "User is not on the shortlist page. Current URL: " + actUrl);
    }

    @And("the user clicks on the profile menu")
    public void the_user_clicks_on_the_profile_menu() {
        Assert.assertTrue(interiorsPage.clickProfileMenu(), "Failed to click profile menu");
    }

    @And("the user selects Home Interiors")
    public void the_user_selects_home_interiors() {
        Assert.assertTrue(interiorsPage.clickHomeInteriors(), "Failed to click Home Interiors");
    }

    @And("the user clicks on Interior Designers in new delhi")
    public void the_user_clicks_on_interior_designers_in_new_delhi() {
        Assert.assertTrue(interiorsPage.clickInteriorDesignersDelhi(), "Failed to click Interior Designers in New Delhi");
    }

    @Then("the user should be displayed Home Interiors Designers page")
    public void the_user_should_be_displayed_home_interiors_designers_page() {
        Assert.assertTrue(interiorsPage.isHomeInteriorsDesignersPageDisplayed(),
                "Home Interiors Designers page was not displayed");
    }

    @And("the user clicks on the first designer")
    public void the_user_clicks_on_the_first_designer() {
        Assert.assertTrue(interiorsPage.clickFirstDesigner(), "Failed to click on first designer");
    }
    
    @Then("the designer's profile page should be displayed")
    public void the_designers_profile_page_should_be_displayed() {
        Assert.assertTrue(interiorsPage.isDesignerProfilePageDisplayed(), "Designer's profile page was not displayed");
    }

    @And("the user clicks on the designer image")
    public void the_user_clicks_on_the_designer_image() {
        Assert.assertTrue(interiorsPage.clickDesignerImage(), "Failed to click on designer image");
    }
}
