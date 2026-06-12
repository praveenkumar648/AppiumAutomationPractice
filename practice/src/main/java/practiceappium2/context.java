package practiceappium2;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class context {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.0");
        dc.setCapability(MobileCapabilityType.NO_RESET, false);

        dc.setCapability("autoGrantPermissions", true);
        dc.setCapability("appium:chromedriverAutodownload", true);

        dc.setCapability("appPackage", "io.appium.android.apis");
        dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        AndroidDriver<MobileElement> driver =
                new AndroidDriver<>(new URL("http://127.0.0.1:4723/"), dc);

        System.out.println("Application launched successfully");

        // Click Views
        driver.findElement(
                MobileBy.AccessibilityId("Views"))
                .click();

        // Scroll to WebView and click
        driver.findElement(
                MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().description(\"WebView\"))"))
                .click();

        System.out.println("Opened WebView");

        Thread.sleep(3000);

        // Get all contexts
        Set<String> contexts = driver.getContextHandles();

        System.out.println("Available Contexts:");
        for (String context : contexts) {
            System.out.println(context);
        }

        // Switch to WebView
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                System.out.println("Switched to : " + context);
                break;
            }
        }

        // Verify current context
        System.out.println("Current Context : " + driver.getContext());

        // ==========================
        // WEBVIEW LOCATORS (Selenium)
        // ==========================

        driver.findElement(By.linkText("i am a link")).click();

        // Alternative:
        // driver.findElement(By.xpath("//a[text()='i am a link']")).click();

        System.out.println("Clicked on WebView Link");

        Thread.sleep(3000);

        // Switch back to Native App
        driver.context("NATIVE_APP");

        System.out.println("Switched back to Native App");

        driver.quit();
    }
}