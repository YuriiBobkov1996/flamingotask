package ui.listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import ui.base.BaseUITest;
import ui.utils.ScreenshotUtils;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseUITest baseTest) {

            ScreenshotUtils.attachScreenshot(
                    baseTest.getPage()
            );
        }

        throw throwable;
    }
}
