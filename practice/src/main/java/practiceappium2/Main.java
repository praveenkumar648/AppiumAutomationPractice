package practiceappium2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Main {

    //Appium Server start; "appium --allow-cors" ---- For Web Appium Inspector
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        System.out.println("Hello world!");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.0");
        //dc.setCapability(MobileCapabilityType.APP, "/Users/praveenananthakumar/Downloads/universal.apk");

        dc.setCapability("appPackage","io.appium.android.apis");
        dc.setCapability("appActivity","io.appium.android.apis.ApiDemos");
    

        URL url = new URL("http://127.0.0.1:4723/");

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(url, dc);

    
        System.out.println("App launched successfully!");

        
    driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Views\"]").click();
    System.out.println("Clicked on Views");

    driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Gallery\"]").click();
    System.out.println("Clicked on Gallery");

    driver.findElementByXPath("//android.widget.TextView[@content-desc=\"1. Photos\"]").click();
    System.out.println("Clicked on 1. Photos");

    driver.executeScript("mobile: swipeGesture", Map.of(
        "left", 100,
        "top", 300,
        "width", 800,
        "height", 300,
        "direction", "left",
        "percent", 0.75
));

System.out.println("Swiped left successfully!");

driver.quit();


    }
}