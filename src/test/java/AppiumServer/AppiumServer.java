package AppiumServer;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;


public class AppiumServer {

    private static AppiumServer appiumServer;
    public static AppiumServer shared() {

        if (appiumServer == null){
            appiumServer = new AppiumServer();
        }
        return appiumServer;
    }

    private AppiumDriverLocalService appiumService;
    private AppiumServiceBuilder builder;


    public AppiumDriverLocalService appiumService() {

        	//String Appium_Node_Path="C:/Users/admin/AppData/Roaming/npm/appium.cmd";
            String appiumJSPath = "C:Users/admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
            builder = new AppiumServiceBuilder()
           		//.usingDriverExecutable(new File(Appium_Node_Path))
                    .withAppiumJS(new File(appiumJSPath))
                    .withIPAddress("0.0.0.0")
                    .usingAnyFreePort()
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
            appiumService = builder.build();
            appiumService.start();



            System.out.println("[Appium] started on " + appiumService.getUrl());
            


        return appiumService;
    }

    public void appiumStop() {

        if (appiumService != null) {
            appiumService.stop();
            appiumService = null;
            System.out.println("[Appium] stoped");
        }
    }
}
