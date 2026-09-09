package ru.netology.patterns.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.RegistrationDto;
import ru.netology.patterns.generator.ApiGenerator;
import ru.netology.patterns.generator.ApiClient;

import static com.codeborne.selenide.Selenide.*;

public class AuthUITest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithActiveUser() {
        RegistrationDto user = ApiGenerator.generateUser("active");
        ApiClient.registerUser(user);

        $("[data-test-id='login'] input")
                .shouldBe(Condition.visible)
                .setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();

        $("h2")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Личный кабинет"));
    }

    @Test
    void shouldNotLoginWithBlockedUser() {
        RegistrationDto user = ApiGenerator.generateUser("blocked");
        ApiClient.registerUser(user);

        $("[data-test-id='login'] input")
                .shouldBe(Condition.visible)
                .setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();

        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Пользователь заблокирован"));
    }

    @Test
    void shouldNotLoginWithInvalidLogin() {
        $("[data-test-id='login'] input")
                .shouldBe(Condition.visible)
                .setValue("invalid_login");
        $("[data-test-id='password'] input").setValue("invalid_password");
        $("[data-test-id='action-login']").click();

        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotLoginWithInvalidPassword() {
        RegistrationDto user = ApiGenerator.generateUser("active");
        ApiClient.registerUser(user);

        $("[data-test-id='login'] input")
                .shouldBe(Condition.visible)
                .setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue("wrong_password");
        $("[data-test-id='action-login']").click();

        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Неверно указан логин или пароль"));
    }
}