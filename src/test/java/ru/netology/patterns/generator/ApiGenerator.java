package ru.netology.patterns.generator;

import com.github.javafaker.Faker;
import ru.netology.patterns.data.RegistrationDto;

import java.util.Locale;

public class ApiGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    public static RegistrationDto generateUser(String status) {
        return new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                status
        );
    }
}