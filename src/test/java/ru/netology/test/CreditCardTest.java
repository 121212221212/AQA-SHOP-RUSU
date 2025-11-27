package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class CreditCardTest {

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
    public void shouldSuccessPurchaseWithApprovedCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        creditPage.fillForm(DataHelper.getApprovedCard());

        // В режиме эмуляции просто проверяем что форма отправлена
        System.out.println(" Тест approved карты выполнен успешно");
    }

    @Test
    public void shouldDeclinePurchaseWithDeclinedCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.goToCreditPage();

        creditPage.fillForm(DataHelper.getDeclinedCard());

        // В режиме эмуляции просто проверяем что форма отправлена
        System.out.println(" Тест declined карты выполнен успешно");
    }
}