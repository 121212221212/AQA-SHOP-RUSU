package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DebitPage {

    // Селекторы полей формы
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    // Уведомления - БОЛЕЕ ГИБКИЕ СЕЛЕКТОРЫ
    private SelenideElement successNotification = $(byText("Успешно"));
    private SelenideElement errorNotification = $(byText("Ошибка"));
    private SelenideElement wrongFormatMessage = $(byText("Неверный формат"));

    // Дополнительные селекторы для уведомлений
    private SelenideElement notificationTitle = $(".notification__title");
    private SelenideElement notificationContent = $(".notification__content");

    public void fillForm(CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitForSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void waitForErrorNotification() {
        // Вариант 1: Ждем либо текст "Ошибка", либо проверяем notification блок
        errorNotification.shouldBe(visible, Duration.ofSeconds(20));

        // Вариант 2: Альтернативно можно проверять по классу notification
        // notificationTitle.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void waitForWrongFormatMessage() {
        wrongFormatMessage.shouldBe(visible, Duration.ofSeconds(20));
    }

    // Дополнительный метод для отладки
    public String getNotificationText() {
        return notificationTitle.getText();
    }
}