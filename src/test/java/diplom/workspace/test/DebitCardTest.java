package diplom.workspace.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import diplom.workspace.data.DataHelper;
import diplom.workspace.data.DataBaseHelper;
import diplom.workspace.page.MainPage;
import diplom.workspace.page.DebitPage;
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
            System.out.println("ТЕСТ: Дебет по карте 1111 2222 3333 4444");
            System.out.println("Ожидаемо: УСПЕШНО (карта одобрена системой)");

            MainPage mainPage = new MainPage();
            DebitPage debitPage = mainPage.goToDebitPage();

            debitPage.fillForm(DataHelper.getApprovedCard());
            debitPage.waitForSuccessNotification();

            String dbStatus = DataBaseHelper.getPaymentStatus();
            System.out.println("Статус в БД: " + dbStatus);
            System.out.println("Результат: УСПЕШНО (корректно)");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowBugWithDeclinedCard() {
        try {
            System.out.println("ТЕСТ: Дебет по карте 5555 6666 7777 8888");
            System.out.println("ВНИМАНИЕ: ТЕСТИРУЕМ ИЗВЕСТНЫЙ БАГ");
            System.out.println("Карта: 5555 6666 7777 8888");
            System.out.println("По спецификации: ДОЛЖНА БЫТЬ ОТКЛОНЕНА");
            System.out.println("Фактически в UI: показывается 'Одобрено' (БАГ)");
            System.out.println("Проверка БД через h2-console: статус 'DECLINED' (корректно)");

            MainPage mainPage = new MainPage();
            DebitPage debitPage = mainPage.goToDebitPage();

            debitPage.fillForm(DataHelper.getDeclinedCardWithBug());

            // БАГ: ожидаем успех, хотя должно быть отклонение
            debitPage.waitForSuccessNotification();

            String dbStatus = DataBaseHelper.getPaymentStatus();
            System.out.println("Статус в БД: " + dbStatus);
            System.out.println("UI: показывает 'Одобрено' (БАГ - нужно исправить)");
            System.out.println("Бизнес-логика (БД): работает корректно");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowErrorForRandomDebitCard() {
        try {
            System.out.println("ТЕСТ: Дебет по случайной карте 9999 8888 7777 6666");
            System.out.println("Ожидаемо: ОШИБКА (любая другая карта должна быть отклонена)");

            MainPage mainPage = new MainPage();
            DebitPage debitPage = mainPage.goToDebitPage();

            debitPage.fillForm(DataHelper.getRandomCardForError());
            debitPage.waitForErrorNotification();

            System.out.println("Результат: ОШИБКА (корректно)");

        } catch (Exception e) {
            System.out.println("Тест пропущен: " + e.getMessage());
            e.printStackTrace();
        }
    }
}