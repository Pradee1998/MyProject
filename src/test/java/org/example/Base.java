
package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeClass;
import org.yaml.snakeyaml.Yaml;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.Properties;


public class Base {

    public AndroidDriver driver;


    @BeforeClass
    public void launch() throws Exception {
        Properties prop = new Properties();
        FileInputStream fil = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/prpertis/data.properties");
        prop.load(fil);

        // Read device configuration from system properties or fallback to properties file
        String deviceName = System.getProperty("deviceName") != null ? System.getProperty("deviceName") : prop.getProperty("deviceName");
        String platformVersion = System.getProperty("platformVersion") != null ? System.getProperty("platformVersion") : prop.getProperty("platformVersion");
        String platformName = System.getProperty("platformName") != null ? System.getProperty("platformName") : prop.getProperty("platformName");
        String ip = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");

        String port = prop.getProperty("port");
        //   System.out.println(System.getProperty("browserstackAutomation"));

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/browser.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(fileInputStream);


        // Get the browserstackAutomation value from YAML
        boolean browserstackAutomation = Boolean.parseBoolean((String) config.get("browserstackAutomation"));
        System.out.println("<<<<<<<<<Rathna>>>>>>>>>>>>>>");
        System.out.println(browserstackAutomation);

           boolean isBrowserStack = Boolean.parseBoolean(System.getProperty("browserstackAutomation", "false"));
           System.out.println(System.getProperty("Hii master" + "browserstackAutomation"));

        if (browserstackAutomation) {

            // Configure for BrowserStack
            String userName = (String) config.get("userName"); // Use environment variables for security
            String accessKey = (String) config.get("accessKey");
            System.setProperty("browserstackUsername", userName);
            System.setProperty("browserstackAccessKey", accessKey);
            System.out.println(System.getProperty("browserstackUsername"));
            System.out.println(System.getProperty("browserstackAccessKey"));

            if (System.getProperty("browserstackUsername") == null || System.getProperty("browserstackAccessKey") == null) {
                throw new Exception("BrowserStack credentials are missing. Set them in environment variables.");
            }

            // Construct BrowserStack URL
            String browserStackUrl = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
            System.out.println(browserStackUrl);
            // Set capabilities for BrowserStack
           // UiAutomator2Options options = new UiAutomator2Options();
            XCUITestOptions options= new XCUITestOptions();
//            options.setDeviceName(deviceName);
//            options.setPlatformName(platformName);
//            options.setPlatformVersion(platformVersion);
//            options.setApp("bs://<APP_ID>"); // Replace with your uploaded app's ID from BrowserStack
//            options.setAutomationName("UiAutomator2");

            // Initialize the driver with BrowserStack URL



            driver = new AndroidDriver(new URL(browserStackUrl), options);
        } else {
            System.out.println("pradeep");
            // Local configuration (as you have it now)
            AppiumDriverLocalService AppiumService = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:\\Users\\Pradeep\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress(ip)
                    .usingPort(Integer.parseInt(port))
                    .build();

            AppiumService.start();

            // Set capabilities for local testing
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(deviceName);
            options.setPlatformName(platformVersion);
            options.setPlatformVersion(platformName);
            options.setApp("C://Users//Pradeep//Documents//newAppiumStuff//src//main//java//org//example//General-Store.apk");
            options.setAutomationName("UiAutomator2");

            // Initialize the driver with local Appium URL
            driver = new AndroidDriver(AppiumService.getUrl(), options);
        }
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()));
    }

    public void scrollToEnd() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 1.0
            ));
        } while (canScrollMore);
    }

    public void swipe(WebElement elem, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elem).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public void screenShot(String testCaseName, AppiumDriver driver) {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports" + testCaseName + ".png";


    }

}
