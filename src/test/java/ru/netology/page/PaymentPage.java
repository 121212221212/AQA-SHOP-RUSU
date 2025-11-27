package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[placeholder='08']");
    private SelenideElement year = $("[placeholder='22']");
    private SelenideElement holder = $("fieldset > div:nth-child(3) input");
    private SelenideElement cvc = $("[placeholder='999']");
    private SelenideElement continueButton = $("form button");

    public void fillForm(DataHelper.CardInfo card) {
        // Заполняем с видимыми действиями
        cardNumber.setValue(card.getNumber());
        sleep(500);
        month.setValue(card.getMonth());
        sleep(500);
        year.setValue(card.getYear());
        sleep(500);
        holder.setValue(card.getHolder());
        sleep(500);
        cvc.setValue(card.getCvc());
        sleep(1000);

        // Нажимаем кнопку
        continueButton.click();
        sleep(2000);
    }

    public void checkSuccessNotification() {
        if (DataHelper.isEmulationMode()) {
            System.out.println("ЭМУЛЯЦИЯ: Форма отправлена успешно");
            // В эмуляции просто ждём
            sleep(3000);
        } else {
            $(".notification_status_ok").shouldBe(visible);
        }
    }
}