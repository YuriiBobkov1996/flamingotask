package ui.tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.base.BaseUITest;
import ui.pages.FormPage;
import ui.utils.BirthDate;
import ui.utils.FormTestUtils;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Tag("ui")
public class FormTest extends BaseUITest {

    @Test
    @DisplayName("Verify user can submit registration form")
    public void SubmitRegistrationFormTest() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String mobile = faker.number().digits(10);
        String address = faker.address().streetAddress();
        BirthDate birthDate = FormTestUtils.getRandomBirthDate(faker);
        FormPage formPage = new FormPage(page);

        formPage.open();
        formPage.fillRequiredFields(firstName, lastName, email, mobile);
        formPage.selectDateOfBirth(birthDate.getMonth(), birthDate.getYear(), birthDate.getDay());
        formPage.selectSubject("Maths");
        formPage.selectSportsHobby();
        formPage.uploadPicture(FormTestUtils.getTestImage());
        formPage.fillAddress(address);
        formPage.selectState();
        formPage.selectCity();
        formPage.clickSubmitButton();
        assertThat(formPage.successModal()).isVisible();
        assertThat(formPage.successModalTitle()).hasText("Thanks for submitting the form");
    }
}
