package ru.netology.web.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.AuthPath;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement actionButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement errorNotificationContent = $("[data-test-id=error-notification] .notification__content");
    private ElementsCollection nextPage = $$(".heading");

    @BeforeEach
    void openBrowser() { open("http://localhost:9999"); }

    @Test
    void shouldConfirmAuthRequest() {
        AuthPath userData = DataGenerator.generateNewAuth();
        loginField.setValue(userData.getLogin());
        passwordField.setValue(userData.getPassword());
        actionButton.click();
        nextPage.find(exactText("Личный кабинет")).shouldBe(exist);
    }

    @Test
    void shouldNotConfirmBlockedUserAuthRequest() {
        AuthPath userData = DataGenerator.generateNewAuthReject();
        loginField.setValue(userData.getLogin());
        passwordField.setValue(userData.getPassword());
        actionButton.click();
        errorNotification.waitUntil(visible, 15000);
        errorNotificationContent.shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void shouldNotConfirmWrongPasswordAuthRequest() {
        AuthPath userData = DataGenerator.generateNewAuthRejectWrongPassword();
        loginField.setValue(userData.getLogin());
        passwordField.setValue(userData.getPassword());
        actionButton.click();
        errorNotification.waitUntil(visible, 5000);
        errorNotificationContent.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotConfirmWrongLoginAuthRequest() {
        AuthPath userData = DataGenerator.generateNewAuthRejectWrongLogin();
        loginField.setValue(userData.getLogin());
        passwordField.setValue(userData.getPassword());
        actionButton.click();
        errorNotification.waitUntil(visible, 5000);
        errorNotificationContent.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }
}
