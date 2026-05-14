package ui.utils;

import com.github.javafaker.Faker;

public class WebTableTestUtils {
    private static final Faker faker = new Faker();
    public static WebTableTestData generateUser() {
        return WebTableTestData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .age(String.valueOf(faker.number().numberBetween(18, 65)))
                .salary(String.valueOf(faker.number().numberBetween(1000, 10000)))
                .department(faker.company().industry())
                .build();
    }
}
