package ImageModule;


import BaseTest.BaseTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentReport;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObject.ImageButtons;
import pageObject.myDesignPage;
import utils.Log;

import java.io.IOException;

import static actions.MobileActions.Screenshot;
import static actions.MobileActions.isScreenShot;


public class AddImage extends BaseTest {

    static ExtentTest test;

    @Test
    public void AddImageTest() throws InterruptedException{
        test = ExtentReport.shared().getExtentTest("Add Image on Canvas");
        test.assignCategory("Functionality");

        WebDriverWait wait;
        wait = new WebDriverWait(driver, 25);
        myDesignPage D = new myDesignPage(driver);
        ImageButtons I = new ImageButtons(driver);

        wait.until(ExpectedConditions.elementToBeClickable(D.MYDESIGN));
        Log.addLog("Select Edit Button");

        D.MYDESIGN.click();
        Log.addLog("Navigate To My Design");
        test.log(Status.INFO, "Navigate To My Design");

        D.CreateOwn.click();
        Log.addLog("Select Create Your Own");
        test.log(Status.INFO, "Select Create Your Own");

        D.IstaPortrait.click();
        Log.addLog("Select Custom Template Card");
        test.log(Status.INFO, "Select Custom Template Card");

        AddImageButton();

        if(isScreenShot()) {
            captureScreenShot();
        }


        ChooseImageButton();

        wait.until(ExpectedConditions.elementToBeClickable(I.RootButton));

        I.RootButton.click();
        Log.addLog("Root Button Clicked");

        driver.findElementByXPath("//android.widget.TextView[@text='Photos']").click();
        Log.addLog("Photos Clicked");

        driver.findElementByXPath("//android.widget.TextView[@text='Image']").click();
        Log.addLog("Image Clicked");


        if(isScreenShot()) {
            captureScreenShot();
        }

        driver.findElementByXPath("//*[@content-desc='Photo taken on 19 Aug 2014 14:23:16']").click();
        Log.addLog("Select Image");
        test.log(Status.INFO, "Select Image");



//        driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Show roots']").click();
//        Log.addLog("Open File Manager");
//
//        driver.findElementByXPath("//android.widget.TextView[@text='Gallery']").click();
//        Log.addLog("Select gallery");
//
//        driver.findElementById("com.sec.android.gallery3d:id/thumbnail").click();
//        Log.addLog("Select catalog");
//
//        driver.findElementById("com.sec.android.gallery3d:id/thumbnail").click();
//        Log.addLog("Select Image");


        if(isScreenShot()) {
            captureScreenShot();
        }

    }

    public void AddImageButton() {
        ImageButtons I = new ImageButtons(driver);
        if (I.AddImage != null) {
            I.AddImage.click();
                Log.addLog("AddImage Button Clickable");
                test.log(Status.INFO, "Add Image Button Clicked");
            } else {
                Log.addLog("AddImage Button Not Visible");
                test.log(Status.FAIL, "AddImage Button Not Visible");
        }
    }

    public void ChooseImageButton() {
        ImageButtons I = new ImageButtons(driver);
        if (I.CameraOption != null) {
            I.ChooseImage.click();
                Log.addLog("Choose Image Button Clickable");
                test.log(Status.INFO, "Choose Image Button Clicked");
            } else {
                Log.addLog("Choose Image Button Not Visible");
                test.log(Status.FAIL, "Choose Image Button Not Visible");
        }
    }

    public static void captureScreenShot(){
        String screenShotPath = Screenshot("");
        try {
            test.addScreenCaptureFromPath(screenShotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
