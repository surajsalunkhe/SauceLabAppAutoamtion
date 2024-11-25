package utils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static report_manager.ExtentTestManager.getTest;

public class AppiumServerUtil {

    public static AppiumDriverLocalService appiumService;
    private String nodePath;
    private String appiumPath;
    private int port;
    private static Process appiumProcess;
    private static final String hostIPAddress = "127.0.0.1";
    public void startAppiumServer(String os) throws Exception {

        if (os.contains("win")) {
            // Windows paths
            nodePath = "C:\\Program Files\\nodejs\\node.exe";
            appiumPath = System.getenv("APPDATA") + "\\npm\\node_modules\\appium\\build\\lib\\main.js";
            port = 4723;
        } else if (os.contains("mac")) {
            // macOS paths
            nodePath = "/usr/local/bin/node"; // Update if your Node.js path is different
            appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js"; // Update if your Appium path is different
            port = 4723;
        } else if (os.contains("nix") || os.contains("nux")) {
            // Linux paths
            nodePath = "/usr/bin/node"; // Update if your Node.js path is different
            appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js"; // Update if your Appium path is different
            port = 4723;
        } else {
            throw new Exception("Unable to start Appium server, '" + os + "' OS is not yet supported");
        }

        // Start the Appium server programmatically
        startAppiumProcess();
    }

    private void startAppiumProcess() {
        try {
            List<String> command = new ArrayList<>();

            // Add node and appium paths
            command.add(nodePath);
            command.add(appiumPath);

            // Add server arguments
            command.add("--address");
            command.add(hostIPAddress);
            command.add("--port");
            command.add(String.valueOf(port));
            // Add the log level argument
            command.add("--log-level");
            command.add("error");
            // Add the allow-insecure argument for chromedriver_autodownload
            command.add("--allow-insecure");
            command.add("chromedriver_autodownload");
            //ProcessBuilder processBuilder = new ProcessBuilder(nodePath, appiumPath, "--address", hostIPAddress, "--port", String.valueOf(port));
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // Inherit I/O for logging
            appiumProcess = processBuilder.start();
            getTest().log(Status.INFO, "Appium server started");
            System.out.println("Appium server started");
        } catch (Exception e) {
            getTest().log(Status.FAIL, "Could not start appium server");
            throw new RuntimeException(e);
        }

        // Optionally, you can log or handle the process output
        System.out.println("Appium server started on port " + port);
    }

    /*public static void startAppiumServer(String system) {
        try {
            String nodePath;
            String appiumPath;
            int port;

            if (system.contains("windows")) {
                nodePath = "C:\\Program Files\\nodejs\\node.exe";
                appiumPath = "C:\\Users\\suraj_shivajisalunkh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
                port = 4723;
            } else {
                throw new Exception("Unable to start Appium server, '" + system + "' os is not yet supported");
            }
            appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath)).withAppiumJS(new File(appiumPath)).usingPort(port).withIPAddress(hostIPAddress).withArgument(() -> "--allow-insecure","chromedriver_autodownload"));
            appiumService.start();
            getTest().log(Status.INFO, "Appium server started");
            System.out.println("Appium server started");
        } catch (Exception e) {
            getTest().log(Status.FAIL, "Could not start appium server");
            throw new RuntimeException(e);
        }
    }*/

    /* This method is used to stop appium server */
    public void stopAppiumServer() {
        try {
            if(appiumProcess!=null){
                appiumProcess.destroy();
                getTest().log(Status.INFO, "Appium server stopped");
                System.out.println("Appium server stopped");
            }else {
                System.out.println("Appium server is not running.");
            }
            //appiumService.stop();
        } catch (Exception e) {
            getTest().log(Status.FAIL, "Could not stop appium server");
            throw new RuntimeException(e);
        }
    }
}
