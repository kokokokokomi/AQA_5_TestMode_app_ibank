package ru.netology.web.data;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class AuthElements {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement actionButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement errorNotificationContent = $("[data-test-id=error-notification] .notification__content");
    private SelenideElement errorEmptyLoginFieldMessage = $("[data-test-id=login].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    private SelenideElement errorEmptyPasswordFieldMessage = $("[data-test-id=password].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    public void openBrowser() { open("http://localhost:9999"); }

    public void sendAllFieldsCorrectFilledRequest() {
        loginField.setValue(DataGenerator.generateNewAuth().getLogin());
        passwordField.setValue(DataGenerator.generateNewAuth().getPassword());
        actionButton.click();
    }

    public void sendWrongLoginRequest() {
        loginField.setValue(DataGenerator.generateNewAuthReject().getLogin());
        passwordField.setValue(DataGenerator.generateNewAuth().getPassword());
        actionButton.click();
    }

    public void sendWrongPasswordRequest() {
        loginField.setValue(DataGenerator.generateNewAuth().getLogin());
        passwordField.setValue(DataGenerator.generateNewAuthReject().getPassword());
        actionButton.click();
    }

    public void sendEmptyLoginFieldRequest() {
        passwordField.setValue(DataGenerator.generateNewAuth().getPassword());
        actionButton.click();
    }

    public void sendEmptyPasswordFieldRequest() {
        loginField.setValue(DataGenerator.generateNewAuth().getLogin());
        actionButton.click();
    }

    public void findRejectMessage() {
        errorNotification.waitUntil(visible, 5000);
        errorNotificationContent.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }

    public void findRejectEmptyLoginFieldMessage() {
        errorEmptyLoginFieldMessage.waitUntil(visible, 5000);
    }

    public void findRejectEmptyPasswordFieldMessage() {
        errorEmptyPasswordFieldMessage.waitUntil(visible, 5000);
    }
}
