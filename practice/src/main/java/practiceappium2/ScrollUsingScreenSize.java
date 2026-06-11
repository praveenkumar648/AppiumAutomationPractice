package practiceappium2;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import practiceappium2.Utils.GestureUtils;

public class ScrollUsingScreenSize {

    public static void main(String[] args)
            throws MalformedURLException, InterruptedException {

        // Desired Capabilities
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.0");

        dc.setCapability("appPackage", "io.appium.android.apis");
        dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        URL url = new URL("http://127.0.0.1:4723/");

        AndroidDriver<MobileElement> driver =
                new AndroidDriver<>(url, dc);

        System.out.println("Application launched successfully");

        Thread.sleep(3000);

        // Click on Views
        driver.findElement(
                MobileBy.AccessibilityId("Views")
        ).click();

        System.out.println("Clicked on Views");

        Thread.sleep(2000);

        // Reusable Scroll
        GestureUtils.scroll(driver);

        System.out.println("Scroll completed");

        Thread.sleep(3000);

        driver.quit();
    }
}