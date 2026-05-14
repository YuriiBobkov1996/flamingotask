package ui.pages;
import static config.TestConfig.config;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;
    protected BasePage(Page page) {
        this.page = page;
    }

    protected abstract String getPath();

    public void open() {
        page.navigate(config().baseUrl() + getPath());
    }
}
