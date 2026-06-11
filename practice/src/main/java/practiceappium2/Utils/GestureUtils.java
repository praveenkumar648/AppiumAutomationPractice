package practiceappium2.Utils;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GestureUtils {
//It defines a reusable class-level method named scroll that accepts an Android Appium driver object as input 
// so it can perform UI automation actions on a connected device, and it does not return any value.

    public static void scroll(AndroidDriver<MobileElement> driver) {

        Dimension size = driver.manage().window().getSize();

        int screenWidth = size.getWidth();
        int screenHeight = size.getHeight();

        System.out.println("Screen width: " + screenWidth);
        System.out.println("Screen height: " + screenHeight);

        int startX = screenWidth / 2;
        int startY = (int)(screenHeight * 0.9);
        int endY = (int)(screenHeight * 0.1);

        PointerInput finger =
                new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        startX,
                        startY
                )
        );

        scroll.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        scroll.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        startX,
                        endY
                )
        );

        scroll.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(Collections.singletonList(scroll));
    }
}