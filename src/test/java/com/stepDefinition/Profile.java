package com.stepDefinition;

import com.setup.BaseSteps;

import io.cucumber.java.en.Given;

public class Profile extends BaseSteps{
	
	@Given("user launches MagicBricks website")
	public void user_launches_magic_bricks_website() {
	    launchBrowser();
	}
}