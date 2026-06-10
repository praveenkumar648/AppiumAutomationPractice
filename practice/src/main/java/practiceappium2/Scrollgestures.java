package practiceappium2;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Scrollgestures {
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

        // --- SCROLL DOWN GESTURE ON VIEWS SCREEN ---
        /*System.out.println("Starting scroll gesture...");

        // 1. Create a pointer for scrolling
        PointerInput finger2Input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scrollDown = new Sequence(finger, 1);

        // 2. Move finger to the start position near the bottom (X: 640, Y: 1800)
        scrollDown.addAction(
                finger2Input.createPointerMove(
                        Duration.ofMillis(200), 
                        PointerInput.Origin.viewport(), 
                        640, 
                        2200
                )
        );
        
        // 3. Press finger down on the screen
        scrollDown.addAction(
                finger2Input.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // 4. Drag upward to the top position over 1 second (X: 640, Y: 400)
        // Adjusting Y from 1800 to 400 pulls the list up, scrolling it down.
        scrollDown.addAction(
                finger2Input.createPointerMove(
                        Duration.ofMillis(1000), 
                        PointerInput.Origin.viewport(), 
                        640, 
                        200
                )
        );

        // 5. Lift finger off the screen
        scrollDown.addAction(
                finger2Input.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // 6. Execute the scroll sequence
        driver.perform(Collections.singletonList(scrollDown));
        System.out.println("Scroll complete!");

    Sequence scrollDown2 = new Sequence(finger2Input, 2);

    scrollDown2.addAction(
        finger2Input.createPointerMove(
            Duration.ofMillis(200),
            PointerInput.Origin.viewport(),
            640,
            1800
        )
    );

    scrollDown2.addAction(
        finger2Input.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        )
    );

    scrollDown2.addAction(
        finger2Input.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            640,
            400
        )
    );

    scrollDown2.addAction(
        finger2Input.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        )
    );

    driver.perform(Collections.singletonList(scrollDown2));*/

driver.findElement(
    MobileBy.AndroidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
        ".scrollIntoView(new UiSelector().text(\"WebView2\"))"
    )
).click();

        // Give the screen a second to stop moving before doing your next action
        Thread.sleep(3000);

        driver.quit();
    }}
