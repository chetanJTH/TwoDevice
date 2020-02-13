package extentreports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import utils.HTMLFormat;
import utils.Log;
import utils.Utils;

import java.io.File;




public class ExtentReport {


    private static ExtentReport instance;
    private ExtentTest extent_test;

    public static ExtentReport shared() {
        if (instance == null) {
            instance = new ExtentReport();
        }
        return instance;
    }



    private ExtentReports extent;
    ExtentHtmlReporter htmlReporter;





    public ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();



            try {


                htmlReporter = new ExtentHtmlReporter(Utils.getReportDir() + "/report.html");
                htmlReporter.loadXMLConfig(String.valueOf(new File("src/test/java/extentreports/extent-config.xml")));
                htmlReporter.config().setChartVisibilityOnOpen(true);
                htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
                htmlReporter.setAppendExisting(false);

                extent.attachReporter(htmlReporter);
                extent.setSystemInfo("OS Name",System.getProperty("os.name"));
                extent.setSystemInfo("OS Version",System.getProperty("os.version"));
                extent.setSystemInfo("Java Version",System.getProperty("java.version"));
                extent.setSystemInfo("User Name",System.getProperty("user.name"));


            } catch (Exception ex) {
                Log.addLog(ex.getMessage());
            }
        }
        return extent;
    }

    public ExtentTest getExtentTest(String name) {
        ExtentTest extentTest = getExtent().createTest(name);
       // extentTest.assignCategory(name);
        extentTest.assignAuthor(System.getProperty("user.name"));
        this.extent_test = extentTest;

        return extentTest;
    }

    public ExtentTest getExtentTest(String name, String description) {

        ExtentTest extentTest = getExtent().createTest(name, description);
        extentTest.assignCategory(name);
        extentTest.assignAuthor(System.getProperty("user.name"));

        return extentTest;
    }

    public ExtentTest getChildExtentTest(ExtentTest extent, String name) {

        ExtentTest childExtentTest = extent.createNode(name);
        return childExtentTest;
    }

    public ExtentTest getChildExtentTest(ExtentTest extent, String name, String description) {

        ExtentTest childExtentTest = extent.createNode(name, description);
        return childExtentTest;
    }




    // Add log to report file
    public static void Log(ExtentTest extentTest, Status status, String description) {


        extentTest.log(status, "[" + HTMLFormat.bold(status.toString().toUpperCase()) + "] " + description);
    }

    public static void Log(ExtentTest extentTest, boolean isSuccess, String description) {


        Status status = Status.FAIL;

        if(isSuccess) {
            status = Status.PASS;

        }
        extentTest.log(status, "[" + HTMLFormat.bold(status.toString().toUpperCase()) + "] " + description);
    }

    public void setExtentTest(ExtentTest extentTest){
        this.extent_test= extentTest;
    }

    public ExtentTest getExtentTest() {
        return extent_test;
    }


}
