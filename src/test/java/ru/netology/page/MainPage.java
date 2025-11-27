package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    // Селекторы
    private SelenideElement buyButton = $(byText("Купить"));
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