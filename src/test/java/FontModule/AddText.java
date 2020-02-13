package FontModule;

import BaseTest.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentReport;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObject.AllButtons;
import pageObject.TextButton;
import pageObject.myDesignPage;
import utils.Log;

import java.io.IOException;
import java.util.NoSuchElementException;

import static actions.MobileActions.Screenshot;
import static actions.MobileActions.isScreenShot;



public class AddText extends BaseTest {




    static ExtentTest test;



    @Test
    public void AddTextTest() throws InterruptedException, NoSuchElementException {
        test = ExtentReport.shared().getExtentTest("Add Text On Card");
        test.assignCategory("Sanity");


        WebDriverWait wait = new WebDriverWait(driver, 25);
        myDesignPage D = new myDesignPage(driver);



        MyDesignPage();

        wait.until(ExpectedConditions.elementToBeClickable(D.CreateOwn));

        CreateOwnButton();
//        wait.until(ExpectedConditions.elementToBeClickable(B.Editbutton));
//        B.Editbutton.click();
//        Log.addLog("Select Edit Button");

        wait.until(ExpectedConditions.elementToBeClickable(D.IstaPortrait));
        D.IstaPortrait.click();
        Log.addLog("Custom size selected");
        test.log(Status.INFO, "Custom size selected");

        if(isScreenShot()) {
            captureScreenShot();
        }

        try {
            ClickSkipButton();
        } catch (Throwable d) {
            d.printStackTrace();
        }
        finally {
            AddTextButton();
        }


        Thread.sleep(2000);

        InputText();

        if(isScreenShot()) {
            captureScreenShot();
        }

        Thread.sleep(2000);

        OkButtonClick();

        if(isScreenShot()) {
            captureScreenShot();
        }


    }


    public void MyDesignPage() {
        myDesignPage D = new myDesignPage(driver);
        if (D.MYDESIGN != null) {
            D.MYDESIGN.click();
                Log.addLog("My design button clicked");
                test.log(Status.INFO, "My design button clicked");
            } else {
                Log.addLog("My design button not visible");
                test.log(Status.FAIL, "My design button not found");
            }
        }


    public void CreateOwnButton() {
        myDesignPage D = new myDesignPage(driver);
        if (D.CreateOwn != null) {
            D.CreateOwn.click();
                Log.addLog("Create own button clicked");
                test.log(Status.INFO, "Create own button clicked");
            } else {
                Log.addLog("Create own button not visible");
                test.log(Status.FAIL, "Create own button not found");
            }
        }


    public void AddTextButton() {
        TextButton T = new TextButton(driver);
        if (T.AddTextButton != null) {
            T.AddTextButton.click();
            Log.addLog("Add text button clicked");
            test.log(Status.INFO, "Add text button clicked");
        } else {
            Log.addLog("Add Text button not visible");
            test.log(Status.FAIL, "Add text button not found");
        }
    }

    public void InputText() {
        TextButton T = new TextButton(driver);
        if (T.iText != null) {
            T.iText.sendKeys("Auto");
                Log.addLog("Input text Auto");
                test.log(Status.INFO, "Entered text Auto");
            } else {
                Log.addLog("Input text field not visible");
                test.log(Status.FAIL, "Input text Field not found");
            }
        }


    public void OkButtonClick() {
        AllButtons B = new AllButtons(driver);
        if (B.OkButton != null) {
            B.OkButton.click();
                Log.addLog("Ok button clicked");
                test.log(Status.INFO, "Ok button clicked");
            } else {
                Log.addLog("Ok button not visible");
                test.log(Status.FAIL, "Ok button not found");
            }
        }


    public void ClickSkipButton() {
        AllButtons B = new AllButtons(driver);
        if (B.Skip != null) {
            B.Skip.click();
            Log.addLog("Skip button is clicked");
            test.log(Status.INFO, "Skip button is clicked");
        } else {
            Log.addLog("User guide do not display");
            test.log(Status.INFO, "User guide do not found");
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
