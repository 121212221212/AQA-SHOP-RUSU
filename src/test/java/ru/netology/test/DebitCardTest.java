package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.*;

public class DebitCardTest {

    @Before
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1280x720";
    }

    @Test
    public void shouldSuccessWithApprovedCard() throws InterruptedException {
        System.out.println("=== ТЕСТ ДЕБЕТ: ОДОБРЕННАЯ КАРТА (1111 2222 3333 4444) ===");

        MainPage mainPage = open("http://localhost:8080", MainPage.class);
        Thread.sleep(2000);

        PaymentPage paymentPage = mainPage.goToPaymentPage();
        Thread.sleep(2000);

        System.out.println("Заполняем одобренную карту: " + DataHelper.getApprovedCard().getNumber());
        paymentPage.fillForm(DataHelper.getApprovedCard());

        if (DataHelper.isEmulationMode()) {
            System.out.println(" ДЕБЕТ: Форма успешно отправлена");
            System.out.println("Ожидаемый результат: УСПЕШНАЯ ОПЛАТА");
        }

        Thread.sleep(3000);
    }

    @Test
    public void shouldFailWithDeclinedCard() throws InterruptedException {
        System.out.println("=== ТЕСТ ДЕБЕТ: ОТКЛОНЕННАЯ КАРТА (5555 6666 7777 8888) ===");

        MainPage mainPage = open("http://localhost:8080", MainPage.class);
        Thread.sleep(2000);

        PaymentPage paymentPage = mainPage.goToPaymentPage();
        Thread.sleep(2000);

        System.out.println("Заполняем отклоненную карту: " + DataHelper.getDeclinedCard().getNumber());
        paymentPage.fillForm(DataHelper.getDeclinedCard());

        if (DataHelper.isEmulationMode()) {
            System.out.println(" ДЕБЕТ: Форма успешно отправлена");
            System.out.println("Ожидаемый результат: ОТКАЗ В ОПЛАТЕ");
        }

        Thread.sleep(3000);
    }
}