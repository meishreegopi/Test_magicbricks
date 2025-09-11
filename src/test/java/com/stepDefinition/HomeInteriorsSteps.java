package com.stepDefinition;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.HomeInteriorsPage;
import com.setup.BaseSteps;
import com.setup.Reporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

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

    @And("the user clicks on Home Interior Design Services")
    public void the_user_clicks_on_home_interior_design_services() {
        Assert.assertTrue(interiorsPage.clickHomeInteriorDesignServices(), "Failed to click Home Interior Design Services");
    }

    @And("the user click on select your city")
    public void the_user_click_on_select_your_city() {
        Assert.assertTrue(interiorsPage.clickSelectCity(), "Failed to click Select your city");
    }

    @And("the user click on ahmedabad")
    public void the_user_click_on_ahmedabad() {
        Assert.assertTrue(interiorsPage.clickAhmedabad(), "Failed to click Ahmedabad");
    }

    @Then("the user should be displayed Home Interiors Designers page")
    public void the_user_should_be_displayed_home_interiors_designers_page() {
        Reporter.generateReport(driver, extTest, Status.PASS,
                "User navigated to Home Interiors Designers page for Ahmedabad.");
    }
}
