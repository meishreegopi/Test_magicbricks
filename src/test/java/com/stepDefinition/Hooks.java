package com.stepDefinition;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.setup.BaseSteps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks extends BaseSteps {

    static ExtentSparkReporter spark;
    static ExtentReports extReports;
    public static ExtentTest extTest;

    @BeforeAll
    public static void setUpReportsAndBrowser() {
        // Setup reports
        spark = new ExtentSparkReporter("reports\\ExtentReports.html");
        extReports = new ExtentReports();
        extReports.attachReporter(spark);

        // Launch browser once for all features
        if (driver == null) {
            launchBrowser();
            System.out.println("Browser launched once via @BeforeAll ");
        }
    }
    
    public static void cleanOldReports() {
        File reportsDir = new File("reports");
        if (reportsDir.exists()) {
            for (File file : reportsDir.listFiles()) {
                file.delete();
            }
        }

        File targetDir = new File("target");
        if (targetDir.exists()) {
            for (File file : targetDir.listFiles()) {
                file.delete();
            }
        }
    }


    @AfterAll
    public static void afterAll() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed after all tests ✅");
        }
        extReports.flush();
    }

    @Before
    public void setUpScenario(Scenario scenario) {
        // Create test entry for reporting per scenario
        extTest = extReports.createTest(scenario.getName());
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        // Add delay if needed (optional)
        BaseSteps.sleep();
    }
}