package practiceappium2;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TapGestures {
    // Start Appium:
    // appium --allow-cors

    public static void main(String[] args)
            throws MalformedURLException, InterruptedException {

        System.out.println("Hello World!");

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

        System.out.println("App launched successfully!");

        Thread.sleep(3000);

        // Create finger
        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        // Create tap sequence
        Sequence tap = new Sequence(finger, 1);

        // Move finger to coordinates
        tap.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        640,
                        2013
                )
        );

        // Finger touches screen
        tap.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // Finger lifts from screen
        tap.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // Perform tap
        driver.perform(Collections.singletonList(tap));

        System.out.println("Tapped successfully!");

        Thread.sleep(3000);

        driver.quit();
    }
}