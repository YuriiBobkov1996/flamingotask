package ui.tests;

import org.junit.jupiter.api.Test;
import ui.base.BaseUITest;
import ui.pages.WebtablePage;
import ui.utils.WebTableTestData;
import ui.utils.WebTableTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebTableTest extends BaseUITest {

    @Test
    void addNewRecordTest() {
        WebtablePage webTablePage = new WebtablePage(page);
        WebTableTestData user = WebTableTestUtils.generateUser();
        webTablePage.open();
        webTablePage.addNewRecord(user);
        assertThat(webTablePage.isRecordVisibleByText(user.getEmail())).isTrue();
    }

    @Test
    void deleteRecordTest() {
        WebtablePage webTablePage = new WebtablePage(page);
        WebTableTestData user = WebTableTestUtils.generateUser();
        webTablePage.open();
        webTablePage.addNewRecord(user);
        webTablePage.deleteRecordByEmail(user.getEmail());
        assertThat(webTablePage.isRecordVisibleByText(user.getEmail())).isFalse();
    }

    @Test
    void editRecordTest() {
        WebtablePage webTablePage = new WebtablePage(page);
        WebTableTestData user = WebTableTestUtils.generateUser();
        String updatedFirstName = faker.name().firstName();
        webTablePage.open();
        webTablePage.addNewRecord(user);
        webTablePage.editRecordByEmail(user.getEmail(), updatedFirstName);
        assertThat(webTablePage.isRecordUpdated(user.getEmail(), updatedFirstName)).isTrue();
    }

    @Test
    void searchRecordTest() {
        WebtablePage webTablePage = new WebtablePage(page);
        WebTableTestData user = WebTableTestUtils.generateUser();
        webTablePage.open();
        webTablePage.addNewRecord(user);
        webTablePage.searchRecord(user.getFirstName());
        assertThat(webTablePage.getVisibleRecordsCount()).isEqualTo(1);
        assertThat(webTablePage.isRecordVisibleByText(user.getFirstName())).isTrue();
    }
}

