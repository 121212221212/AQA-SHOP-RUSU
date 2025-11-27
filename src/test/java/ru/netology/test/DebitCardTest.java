package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DebitCardTest {

    @BeforeClass
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterClass
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }

    @Before
    public void setUp() {
        open("http://localhost:8080");
    }

    @Test
    public void shouldSuccessWithApprovedCard() {
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        paymentPage.fillForm(DataHelper.getApprovedCard());
        paymentPage.waitForSuccessNotification();

        System.out.println("Debit approved card test - SUCCESS");
    }

    @Test
    public void shouldFailWithDeclinedCard() {
        MainPage mainPage = new MainPage();
        PaymentPage paymentPage = mainPage.goToPaymentPage();

        paymentPage.fillForm(DataHelper.getDeclinedCard());
        paymentPage.waitForErrorNotification();

        System.out.println("Debit declined card test - SUCCESS");
    }
}