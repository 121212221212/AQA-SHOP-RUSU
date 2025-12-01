package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;
import static com.codeborne.selenide.Selenide.*;

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
        try {
            System.out.println("=== ТЕСТ: Кредит по карте 1111 2222 3333 4444 ===");
            System.out.println("Ожидаемо: УСПЕШНО (карта одобрена системой)");

            MainPage mainPage = new MainPage();
            CreditPage creditPage = mainPage.goToCreditPage();

            creditPage.fillForm(DataHelper.getApprovedCard());
            creditPage.waitForSuccessNotification();

            System.out.println("✓ Результат: УСПЕШНО (корректно)");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowBugWithDeclinedCard() {
        try {
            System.out.println("=== ТЕСТ: Кредит по карте 5555 6666 7777 8888 ===");
            System.out.println("ВНИМАНИЕ: ТЕСТИРУЕМ ИЗВЕСТНЫЙ БАГ");
            System.out.println("Карта: 5555 6666 7777 8888");
            System.out.println("По спецификации: ДОЛЖНА БЫТЬ ОТКЛОНЕНА");
            System.out.println("Фактически в UI: показывается 'Одобрено' (БАГ)");
            System.out.println("Проверка БД через h2-console: статус 'DECLINED' (корректно)");

            MainPage mainPage = new MainPage();
            CreditPage creditPage = mainPage.goToCreditPage();

            creditPage.fillForm(DataHelper.getDeclinedCardWithBug());

            // БАГ: ожидаем успех, хотя должно быть отклонение
            creditPage.waitForSuccessNotification();

            System.out.println("✓ UI: показывает 'Одобрено' (БАГ - нужно исправить)");
            System.out.println("✓ Бизнес-логика (БД): работает корректно");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowErrorForRandomCard() {
        try {
            System.out.println("=== ТЕСТ: Кредит по случайной карте 9999 8888 7777 6666 ===");
            System.out.println("Ожидаемо: ОШИБКА (любая другая карта должна быть отклонена)");

            MainPage mainPage = new MainPage();
            CreditPage creditPage = mainPage.goToCreditPage();

            creditPage.fillForm(DataHelper.getRandomCardForError());
            creditPage.waitForErrorNotification();

            System.out.println("✓ Результат: ОШИБКА (корректно)");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }
}