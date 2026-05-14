package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.util.Random;

public class FormPage extends BasePage {
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator maleGenderRadio;
    private final Locator mobileInput;
    private final Locator dateOfBirthInput;
    private final Locator subjectsInput;
    private final Locator subjectOptions;
    private final Locator sportsHobbyCheckbox;
    private final Locator uploadPictureInput;
    private final Locator addressTextarea;
    private final Locator stateDropdown;
    private final Locator cityDropdown;
    private final Locator dropdownOptions;
    private final Locator submitButton;
    private final Locator successModal;
    private final Locator successModalTitle;

    public FormPage(Page page) {
        super(page);

        this.firstNameInput = page.locator("#firstName");
        this.lastNameInput = page.locator("#lastName");
        this.emailInput = page.locator("#userEmail");
        this.maleGenderRadio = page.locator("label[for='gender-radio-1']");
        this.mobileInput = page.locator("#userNumber");
        this.dateOfBirthInput = page.locator("#dateOfBirthInput");
        this.subjectsInput = page.locator("#subjectsInput");
        this.subjectOptions = page.locator("div[role='option']");
        this.sportsHobbyCheckbox = page.locator("label[for='hobbies-checkbox-1']");
        this.uploadPictureInput = page.locator("#uploadPicture");
        this.addressTextarea = page.locator("#currentAddress");
        this.stateDropdown = page.locator("#state");
        this.cityDropdown = page.locator("#city");
        this.dropdownOptions = page.locator("div[role='option']");
        this.submitButton = page.locator("#submit");
        this.successModal = page.locator(".modal-content");
        this.successModalTitle = page.locator("#example-modal-sizes-title-lg");
    }

    @Override
    protected String getPath() {
        return "/automation-practice-form";
    }

    public void fillRequiredFields(String firstName, String lastName, String email, String mobile) {
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        emailInput.fill(email);
        maleGenderRadio.click();
        mobileInput.fill(mobile);
    }

    public void selectDateOfBirth(String month, String year, String day) {
        dateOfBirthInput.click();
        page.locator(".react-datepicker__month-select").selectOption(month);
        page.locator(".react-datepicker__year-select").selectOption(year);
        String formattedDay = String.format("%03d", Integer.parseInt(day));
        page.locator(".react-datepicker__day--" + formattedDay + ":not(.react-datepicker__day--outside-month)").first().click();
    }

    public void selectSubject(String subject) {
        subjectsInput.fill(subject);
        subjectOptions.getByText(subject).click();
    }

    public void selectSportsHobby() {
        sportsHobbyCheckbox.click();
    }

    public void uploadPicture(Path filePath) {
        uploadPictureInput.setInputFiles(filePath);
    }

    public void fillAddress(String address) {
        addressTextarea.fill(address);
    }


    public void selectState() {
        stateDropdown.click();
        int count = dropdownOptions.count();
        int randomIndex = new Random().nextInt(count);
        dropdownOptions.nth(randomIndex).click();
    }

    public void selectCity() {
        cityDropdown.click();
        int count = dropdownOptions.count();
        int randomIndex = new Random().nextInt(count);
        dropdownOptions.nth(randomIndex).click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public Locator successModal() {
        return successModal;
    }

    public Locator successModalTitle() {
        return successModalTitle;
    }
}
