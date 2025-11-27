package ru.netology.data;

import lombok.Value;

public class DataHelper {

    // Режим эмуляции - true для нашего случая
    private static final boolean EMULATION_MODE = true;

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }

    // Данные карт ПО ЗАДАНИЮ ДИПЛОМА:

    // Одобренная карта (должна проходить)
    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN PETROV", "123");
    }

    // Отклоненная карта (должна не проходить)
    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "12", "25", "MARIA IVANOVA", "456");
    }

    // Дополнительные тестовые данные
    public static CardInfo getInvalidCardNumber() {
        return new CardInfo("9999 8888 7777 6666", "12", "26", "PETR SIDOROV", "789");
    }

    public static CardInfo getExpiredCard() {
        return new CardInfo("1111 2222 3333 4444", "01", "23", "EXPIRED CARD", "111");
    }

    public static boolean isEmulationMode() {
        return EMULATION_MODE;
    }

    public static String getAppUrl() {
        return "http://localhost:8080";
    }
}
