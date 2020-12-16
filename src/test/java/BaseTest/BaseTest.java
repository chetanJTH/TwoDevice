package BaseTest;



import com.aventstack.extentreports.ExtentReports;
import actions.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.Log;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static actions.MobileActions.setScreenShot;
import static extentreports.ExtentReport.*;


public class BaseTest {

    public AndroidDriver driver;
    protected MobileActions actions;
    protected ExtentReports extent;


    @BeforeSuite
    public void beforeSuite() {
        extent = shared().getExtent();
        Log.addLog("BeforeTest");
        setScreenShot(true);
    }

    public static String firstDeviceName = "330033acecf394bd";
    public static String secondDeviceName = "RZ8M422FZJA";

    @Parameters({"port", "deviceID"})

    @BeforeClass
    public void startTest(String portNo, String device_ID) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        File f=new File("src");
        File fs=new File(f,"Product_Marketing.apk");

        if (device_ID.equalsIgnoreCase(firstDeviceName)) {

            System.out.println(portNo + "---" + System.currentTimeMillis());
            System.out.println(device_ID + "---" + System.currentTimeMillis());

            capabilities.setCapability("chromedriverExecutable","D://Projects/chromedriver.exe");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, firstDeviceName);
            capabilities.setCapability(MobileCapabilityType.UDID, firstDeviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, 8.1);
            capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());


        } else if (device_ID.equalsIgnoreCase(secondDeviceName)) {

            System.out.println(portNo + "---" + System.currentTimeMillis());
            System.out.println(device_ID + "---" + System.currentTimeMillis());

            capabilities.setCapability("chromedriverExecutable","D://Projects/chromedriver.exe");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, secondDeviceName);
            capabilities.setCapability(MobileCapabilityType.UDID, secondDeviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, 9);
            capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());




            
    }
        driver = new AndroidDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        actions = new MobileActions(driver);
        System.out.println("[Driver] setup is completed!");

    }



    @AfterTest
    protected void endTest(){

        if (driver!= null)
            driver.quit();
        Log.addLog("Driver Stop");
    }

    @AfterSuite
    public void flushReport (){
        extent.flush();
    Log.addLog("After Test");
    }

}

