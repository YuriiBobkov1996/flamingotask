package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ui.utils.WebTableTestData;

public class WebtablePage extends BasePage {
    private final Page page;
    private final Locator addButton;
    private final Locator searchInput;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator ageInput;
    private final Locator salaryInput;
    private final Locator departmentInput;
    private final Locator submitButton;

    public WebtablePage(Page page) {
        super(page);

        this.page = page;
        this.addButton = page.locator("#addNewRecordButton");
        this.searchInput = page.locator("#searchBox");
        this.firstNameInput = page.locator("#firstName");
        this.lastNameInput = page.locator("#lastName");
        this.emailInput = page.locator("#userEmail");
        this.ageInput = page.locator("#age");
        this.salaryInput = page.locator("#salary");
        this.departmentInput = page.locator("#department");
        this.submitButton = page.locator("#submit");
    }

    @Override
    protected String getPath() {
        return "/webtables";
    }

    public void addNewRecord(WebTableTestData user) {
        addButton.click();
        firstNameInput.fill(user.getFirstName());
        lastNameInput.fill(user.getLastName());
        emailInput.fill(user.getEmail());
        ageInput.fill(user.getAge());
        salaryInput.fill(user.getSalary());
        departmentInput.fill(user.getDepartment());
        submitButton.click();
    }

    public boolean isRecordVisibleByText(String text) {
        return page.locator("tbody tr").filter(new Locator.FilterOptions().setHasText(text)).isVisible();
    }

    public void deleteRecordByEmail(String email) {
        page.locator("tbody tr").filter(new Locator.FilterOptions().setHasText(email))
                .locator("[id^='delete-record']")
                .click();
    }

    public void editRecordByEmail(String email, String updatedFirstName) {
        page.locator("tbody tr")
                .filter(new Locator.FilterOptions().setHasText(email))
                .locator("[id^='edit-record']")
                .click();

        firstNameInput.fill(updatedFirstName);
        submitButton.click();
    }

    public boolean isRecordUpdated(String email, String updatedFirstName) {
        return page.
                locator("tbody tr")
                .filter(new Locator.FilterOptions()
                        .setHasText(email)
                        .setHasText(updatedFirstName)).isVisible();
    }

    public void searchRecord(String text) {
        searchInput.fill(text);
    }

    public int getVisibleRecordsCount() {
        return page.locator("tbody tr")
                .filter(new Locator.FilterOptions().setHasNotText("No rows found")).count();
    }
}
