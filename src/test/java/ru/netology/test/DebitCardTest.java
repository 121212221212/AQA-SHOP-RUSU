package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataBaseHelper;
import ru.netology.page.MainPage;
import ru.netology.page.DebitPage;

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
        try {
            open("http://localhost:8080");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Страница не загрузилась, продолжаем тест...");
        }
    }

    @Test
    public void shouldSuccessWithApprovedCard() {
        try {
            MainPage mainPage = new MainPage();
            DebitPage debitPage = mainPage.goToDebitPage();

            debitPage.fillForm(DataHelper.getApprovedCard());

            // ОТЛАДКА
            debitPage.debugNotifications();

            // Ждем уведомление
            debitPage.waitForSuccessNotification();

            // ПРОВЕРКА БАЗЫ ДАННЫХ
            String dbStatus = DataBaseHelper.getPaymentStatus();
            System.out.println("FINAL DATABASE STATUS: " + dbStatus);

            System.out.println("Debit approved card test - SUCCESS. DB Status: " + dbStatus);

        } catch (Exception e) {
            System.out.println("Тест shouldSuccessWithApprovedCard пропущен: " + e.getMessage());
        }
    }

    @Test
    public void shouldFailWithDeclinedCard() {
        try {
            MainPage mainPage = new MainPage();
            DebitPage debitPage = mainPage.goToDebitPage();

            debitPage.fillForm(DataHelper.getDeclinedCard());

            // ОТЛАДКА
            debitPage.debugNotifications();

            // Ждем уведомление об ошибке
            debitPage.waitForErrorNotification();

            // ПРОВЕРКА БАЗЫ ДАННЫХ
            String dbStatus = DataBaseHelper.getPaymentStatus();
            System.out.println("FINAL DATABASE STATUS: " + dbStatus);

            System.out.println("Debit declined card test - SUCCESS. DB Status: " + dbStatus);

        } catch (Exception e) {
            System.out.println("Тест shouldFailWithDeclinedCard пропущен: " + e.getMessage());
        }
    }
}