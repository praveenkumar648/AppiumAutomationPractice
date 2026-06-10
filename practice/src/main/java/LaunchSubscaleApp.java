import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class LaunchSubscaleApp {

    public static void main(String[] args)
            throws MalformedURLException, InterruptedException {

        System.out.println("Starting Appium Test...");

        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.0");

        dc.setCapability("appPackage", "com.davemorrissey.labs.subscaleview.sample");
        dc.setCapability("appActivity", "com.davemorrissey.labs.subscaleview.sample.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/");

        AndroidDriver<MobileElement> driver =
                new AndroidDriver<>(url, dc);

        System.out.println("App launched successfully!");

        driver.findElementByXPath("//android.widget.Button[@resource-id='android:id/button1']").click();

        Thread.sleep(2000);

        // 🔥 ZOOM IN
        zoomIn(driver);
        Thread.sleep(3000);

        // 🔥 ZOOM OUT
        zoomOut(driver);
        Thread.sleep(3000);

        driver.quit();
    }

    // ===================== ZOOM IN =====================
    private static void zoomIn(AndroidDriver<MobileElement> driver) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence s1 = new Sequence(finger1, 1);
        Sequence s2 = new Sequence(finger2, 1);

        // start close
        s1.addAction(finger1.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), 500, 800));
        s2.addAction(finger2.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), 520, 820));

        s1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        s2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // move apart (zoom in)
        s1.addAction(finger1.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), 300, 600));

        s2.addAction(finger2.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), 700, 1000));

        s1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        s2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(s1, s2));
    }

    // ===================== ZOOM OUT =====================
    private static void zoomOut(AndroidDriver<MobileElement> driver) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence s1 = new Sequence(finger1, 1);
        Sequence s2 = new Sequence(finger2, 1);

        // start far apart
        s1.addAction(finger1.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), 300, 600));
        s2.addAction(finger2.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), 700, 1000));

        s1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        s2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // move closer (zoom out)
        s1.addAction(finger1.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), 500, 800));

        s2.addAction(finger2.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), 520, 820));

        s1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        s2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(s1, s2));
    }
}