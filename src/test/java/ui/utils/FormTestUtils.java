package ui.utils;

import com.github.javafaker.Faker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FormTestUtils {
    public static Path getTestImage() {
        return Paths.get("src/test/resources//files/dog.png");
    }

    public static BirthDate getRandomBirthDate(Faker faker) {
        String[] months = {"January", "February", "March", "April", "May"};
        String day = String.valueOf(faker.number().numberBetween(1, 28));
        String month = months[new Random().nextInt(months.length)];
        String year = String.valueOf(faker.number().numberBetween(1985, 2005));
        return new BirthDate(day, month, year);
    }
}
