package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NotificationPage {

    public void waitForNotificationWithText(String expectedText) {
        // Ждем появления любого уведомления
        $(".notification")
                .shouldBe(visible, Duration.ofSeconds(15));

        // Ищем уведомление с нужным текстом
        boolean found = false;
        for (SelenideElement notification : $$(".notification")) {
            if (notification.isDisplayed() &&
                    notification.$(".notification__content").exists() &&
                    notification.$(".notification__content").getText().contains(expectedText)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new AssertionError("Не найдено видимое уведомление с текстом: " + expectedText);
        }
    }

    public void debugAllNotifications() {
        System.out.println("=== ВСЕ УВЕДОМЛЕНИЯ НА СТРАНИЦЕ ===");

        if ($$(".notification").size() == 0) {
            System.out.println("На странице нет элементов с классом 'notification'");
        } else {
            for (int i = 0; i < $$(".notification").size(); i++) {
                SelenideElement notification = $$(".notification").get(i);
                System.out.println("\nУведомление #" + (i + 1) + ":");
                System.out.println("  Классы: " + notification.getAttribute("class"));
                System.out.println("  Видимое: " + notification.isDisplayed());

                if (notification.$(".notification__title").exists()) {
                    System.out.println("  Заголовок: " + notification.$(".notification__title").getText());
                }

                if (notification.$(".notification__content").exists()) {
                    System.out.println("  Содержимое: " + notification.$(".notification__content").getText());
                }
            }
        }

        System.out.println("==================================");
    }
}