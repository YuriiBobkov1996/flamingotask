package ui.pages;

import com.microsoft.playwright.Page;
import config.TestConfig;

public abstract class BasePage {
    protected final Page page;
    protected BasePage(Page page) {
        this.page = page;
    }

    protected abstract String getPath();

    public void open() {
        page.navigate(TestConfig.config().uiBaseUrl() + getPath());
    }
}
