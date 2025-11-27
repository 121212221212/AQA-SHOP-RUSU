package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    // Селектор для "Купить" - по тексту кнопки
    private SelenideElement buyButton = $(byText("Купить"));

    // Селектор для "Купить в кредит" - по тексту кнопки
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public PaymentPage goToPaymentPage() {
        buyButton.click();
        return new PaymentPage();
    }

    public CreditPage goToCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}