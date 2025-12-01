package diplom.workspace.page;

import com.codeborne.selenide.SelenideElement;
import diplom.workspace.data.DataHelper.CardInfo;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DebitPage extends NotificationPage {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    public void fillForm(CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void waitForSuccessNotification() {
        waitForNotificationWithText("Операция одобрена Банком.");
    }

    public void waitForErrorNotification() {
        waitForNotificationWithText("Ошибка! Банк отказал в проведении операции.");
    }

    public void debugNotifications() {
        debugAllNotifications();
    }

    public void waitForWrongFormatMessage() {
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(15));
    }
}