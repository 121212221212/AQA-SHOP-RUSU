package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Condition.visible;
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

    // Уведомления
    private SelenideElement successNotification = $(byText("Успешно"));
    private SelenideElement errorNotification = $(byText("Ошибка"));
    private SelenideElement wrongFormatMessage = $(byText("Неверный формат"));

    public void fillForm(CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitForSuccessNotification() {
        successNotification.shouldBe(visible);
    }

    public void waitForErrorNotification() {
        errorNotification.shouldBe(visible);
    }

    public void waitForWrongFormatMessage() {
        wrongFormatMessage.shouldBe(visible);
    }
}