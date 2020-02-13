package AppiumServer;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;


import java.io.IOException;

public class server {
    public static void startServer(String address, String port, String id) {
        CommandLine command = new CommandLine("cmd");
        System.out.println("-----Starting Appium Server-----");
        command.addArgument("/c");
        command.addArgument("C:/Users/admin/AppData/Roaming/npm/node.exe");
        command.addArgument("C:/Users/admin/AppData/Roaming/npm/node_modules/appium/lib/appium.js");
        command.addArgument("–address");
        command.addArgument(address);
        command.addArgument("–port");
        command.addArgument(port);
        command.addArgument("–bootstrap-port");
        command.addArgument(port+2);
        command.addArgument("–udid");
        command.addArgument(id);
        command.addArgument("–no-reset");
        command.addArgument("–log");
        command.addArgument("E:/appiumLogs.txt");
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
            System.out.println("-----Appium Server started Successfully-----");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}