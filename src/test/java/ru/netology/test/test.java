package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class test {

    @Test
    public void shouldOpenMainPage() {
        open("http://localhost:8080");

        // Ждём 5 секунд чтобы увидеть что открылось
        sleep(5000);

        // Выводим заголовок страницы
        System.out.println("Page title: " + Selenide.title());
        System.out.println("Page URL: " + Selenide.webdriver().driver().url());

        System.out.println("✅ Страница открыта успешно!");
    }

    @Test
    public void shouldFindButtons() {
        open("http://localhost:8080");

        // Проверяем кнопку "Купить"
        $(byText("Купить")).shouldBe(visible);
        System.out.println("✅ Кнопка 'Купить' найдена!");

        // Проверяем кнопку "Купить в кредит"
        $(byText("Купить в кредит")).shouldBe(visible);
        System.out.println("✅ Кнопка 'Купить в кредит' найдена!");

        // Проверяем поля формы (после клика на кнопку)
        $(byText("Купить")).click();

        $(byText("Номер карты")).shouldBe(visible);
        $("[placeholder='0000 0000 0000 0000']").shouldBe(visible);
        System.out.println("✅ Поля формы найдены!");
    }

    @Test
    public void shouldFindAllFormFields() {
        open("http://localhost:8080");
        $(byText("Купить")).click();

        // Проверяем все поля формы
        $("[placeholder='0000 0000 0000 0000']").shouldBe(visible);
        System.out.println("✅ Поле 'Номер карты' найдено");

        $("[placeholder='08']").shouldBe(visible);
        System.out.println("✅ Поле 'Месяц' найдено");

        $("[placeholder='22']").shouldBe(visible);
        System.out.println("✅ Поле 'Год' найдено");

        $(byText("Владелец")).shouldBe(visible);
        System.out.println("✅ Поле 'Владелец' найдено");

        $("[placeholder='999']").shouldBe(visible);
        System.out.println("✅ Поле 'CVC' найдено");

        $(byText("Продолжить")).shouldBe(visible);
        System.out.println("✅ Кнопка 'Продолжить' найдена");
    }
}