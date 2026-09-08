package ru.netology.patterns.generator;

import com.github.javafaker.Faker;
import ru.netology.patterns.data.RegistrationDto;

import java.util.Locale;

public class ApiGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    public static RegistrationDto generateActiveUser() {
        return new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "active"
        );
    }

    public static RegistrationDto generateBlockedUser() {
        return new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "blocked"
        );
    }
}