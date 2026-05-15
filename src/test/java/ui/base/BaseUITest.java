package ui.base;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.listeners.ScreenshotOnFailureExtension;

import static config.TestConfig.config;

@Getter
@ExtendWith(ScreenshotOnFailureExtension.class)
@Tag("ui")
public class BaseUITest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected Faker faker;
    @BeforeEach
    void setUp() {
        faker = new Faker();
        playwright = Playwright.create();
        browser = createBrowser();
        page = browser.newPage();
        page.setDefaultTimeout(config().timeout()
        );
    }

    private Browser createBrowser() {
        return switch (config().browser()) {

            case "firefox" ->
                    playwright.firefox().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config().headless())
                    );

            case "webkit" ->
                    playwright.webkit().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config().headless())
                    );

            default ->
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config().headless())
                    );
        };
    }

    @AfterEach
    void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}