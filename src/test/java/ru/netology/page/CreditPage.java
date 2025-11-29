package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {

    // Селекторы полей формы
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    public void fillForm(DataHelper.CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    // ОТЛАДОЧНЫЙ МЕТОД
    public void debugNotifications() {
        System.out.println("=== DEBUG NOTIFICATIONS ===");
        System.out.println("Success visible: " + $(".notification_status_ok").isDisplayed());
        System.out.println("Error visible: " + $(".notification_status_error").isDisplayed());
        System.out.println("Success text: " + $(".notification_status_ok .notification__content").getText());
        System.out.println("Error text: " + $(".notification_status_error .notification__content").getText());
        System.out.println("===========================");
    }

    public void waitForSuccessNotification() {
        $(".notification_status_ok .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Операция одобрена Банком."));
    }

    public void waitForErrorNotification() {
        $(".notification_status_error .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    }

    public void waitForWrongFormatMessage() {
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(15));
    }
}