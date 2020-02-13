package utils;

import FontModule.AddText;
import actions.MobileActions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import extentreports.ExtentReport;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.testng.*;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;


public class testListener implements ITestListener {



    @Override
    public void onTestStart(ITestResult iTestResult) {

        if (iTestResult != null) {
            String testName = iTestResult.getName();
            Log.addLog(testName + " is RUNNING...");

        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        if (iTestResult != null) {
            String testName = iTestResult.getName();
            Log.addLog(testName + " is Passed");
           ExtentReport.shared().getExtentTest().log(Status.PASS, "Test Passed");

        }
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {

        if (iTestResult != null) {
            String testName = iTestResult.getName();
            Log.addLog(testName + " is FAILED...");
            ExtentReport.shared().getExtentTest().log(Status.FAIL, "Test Failed");
            ExtentReport.shared().getExtentTest().fail(iTestResult.getThrowable());
            if (iTestResult.getStatus() == iTestResult.FAILURE) {
                String screenShotPath = MobileActions.Screenshot("Failed");

                try {
                    ExtentReport.shared().getExtentTest().addScreenCaptureFromPath(screenShotPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {


        if (iTestResult != null) {
            String testName = iTestResult.getName();
            Log.addLog(testName + " is SKIPPED...");
            ExtentReport.shared().getExtentTest().log(Status.SKIP, "Test Skipped");


        }
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.addLog(" onTestFailedButWithinSuccessPercentage ");

    }


    @Override
    public void onStart(ITestContext iTestContext) {

        Log.addLog("=====START TEST=====");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        Log.addLog("=====FINISH TEST=====");
    }

    private Annotation getAnnotation(ITestResult result, Class T) {
        return result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(T);

    }
}
