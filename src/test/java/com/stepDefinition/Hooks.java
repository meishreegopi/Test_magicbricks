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
    public static int currentrow = 0;

   	public static int firstrow;

    @BeforeAll
    public static void setUpReportsAndBrowser() {
        //  Setup reports (✅ changed path here)
        spark = new ExtentSparkReporter("reports/ExtentReports.html");
        extReports = new ExtentReports();
        extReports.attachReporter(spark);

        // Debug log to check exact location
        System.out.println("ExtentReport will be saved at: " + 
            new File("reports/ExtentReports.html").getAbsolutePath());

        //  Launch browser once for all features
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
            System.out.println("Browser closed after all tests ");
        }
        if (extReports != null) {   // ✅ added null check to ensure flush runs safely
            extReports.flush();
            System.out.println("Extent report generated!");
        }
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