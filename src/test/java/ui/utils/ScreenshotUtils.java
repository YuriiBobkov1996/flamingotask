package ui.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;


public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static void attachScreenshot(Page page) {

        if (page == null) {
            return;
        }

        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

        Allure.addAttachment("Failure screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
    }
}
