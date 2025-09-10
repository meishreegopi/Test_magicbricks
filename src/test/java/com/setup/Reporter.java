package com.setup;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Reporter {

    public static void generateReport(WebDriver driver, ExtentTest extTest, Status status, String stepMessage) {
        if (extTest == null) {
            System.out.println("ExtentTest object is null. Skipping report logging.");
            return;
        }

        if (status.equals(Status.PASS)) {
            System.out.println(" ******* " + stepMessage + " is passed");
            extTest.log(status, stepMessage);
        } else if (status.equals(Status.FAIL)) {
            System.out.println("***************** step is failed");
            String screenshotPath = null;

            // take screenshot only if driver is valid
            if (driver != null) {
                screenshotPath = captureScreenshot(driver, stepMessage);
            }

            try {
                if (screenshotPath != null) {
                    extTest.log(status, stepMessage,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    extTest.log(status, stepMessage + " (No screenshot - driver was null)");
                }
            } catch (Exception e) {
                extTest.log(status, stepMessage + " (Screenshot capture failed: " + e.getMessage() + ")");
            }
        }
    }

    public static String captureScreenshot(WebDriver driver, String errorMessage) {
        String userDir = System.getProperty("user.dir");

        // timestamped file name
        String dateTime = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss").format(new Date());
        String fileName = userDir + "\\screenshots\\" + errorMessage.replaceAll("[^a-zA-Z0-9]", "_") 
                          + "_" + dateTime + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile, destFile);
            return fileName;
        } catch (Exception e) {
            System.out.println(" Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}