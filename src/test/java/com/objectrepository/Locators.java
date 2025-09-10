package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
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
    
     public static By enterlocation = By.id("keyword");
     public static By clickPropertType = By.id("propType_buy");
     public static By clearPropertyType = By.xpath("//*[@id=\"propType_buy\"]/div[2]/div/div/div[1]/div[2]/div[4]");
     public static By dropdownProperType = By.xpath("//*[@id=\"propType_buy\"]/div[1]");
     public static By selectPropertyType = By.id("10002_10003_10021_10022");
     public static By closePropertyType = By.xpath("//*[@id=\"buy_proertyTypeDefault\"]");
     
     public static By clickBudget = By.id("rent_budget_lbl");
     //public static By dropdownBudget = By.xpath("//*[@id=\"searchFormHolderSection\"]/section/div/div[1]/div[3]/div[3]/div[1]");
     public static By minPrice = By.xpath("//*[@id=\"budgetMin\"]");
     public static By maxPrice = By.xpath("//*[@id=\"budgetMax\"]");
     public static By closeBudget = By.xpath("//*[@id=\"rent_budget_lbl\"]");
     public static By searchButton = By.xpath("//*[@id=\"searchFormHolderSection\"]/section/div/div[1]/div[3]/div[4]");
     public static By sortBy = By.xpath("//*[@id=\"body\"]/div[5]/div/div/div[1]/div[1]/div[1]");
     public static By mostrecent = By.xpath("//*[@id=\"body\"]/div[5]/div/div/div[1]/div[1]/div/div[2]/ul/li[4]");
     public static By shortlistButton = By.xpath("//*[@id=\"cardid80170161\"]/div/div[1]/div[2]/span[2]");
     public static By mainshortlistbtn = By.xpath("//*[@id=\"propertysrp\"]/div[1]/div/div/div[2]/div[5]/span");
     public static By viewShortlistBtn = By.xpath("//*[@id=\"propertysrp\"]/div[1]/div/div/div[2]/div[5]/div/div/a");
     public static By shortlistedPropertyTitle = By.xpath("//*[@id=\"cardid81061849\"]/div/div[1]/div[2]/h2");
     public static By shortlistedPropertyCard = By.xpath("//*[@id=\"cardid80170161\"]/div[2]");
     public static By shortlistTabHeader = By.xpath("//*[@id=\"m-tab-Shortlisted\"]");
}