package com.setup;

import java.io.File;
import java.io.IOException;
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

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String stepDetails) {
        try {
            if (status == Status.FAIL) {
                String screenshotPath = captureScreenshot(driver);
                test.log(status, stepDetails,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.log(status, stepDetails);
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Reporting failed: " + e.getMessage());
        }
    }

    private static String captureScreenshot(WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
        File dest = new File(screenshotPath);
        FileUtils.copyFile(src, dest);
        return screenshotPath;
    }
}
