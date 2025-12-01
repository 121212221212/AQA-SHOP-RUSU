package ru.netology.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    // Ваши тестовые карты
    private static final String APPROVED_CARD = "1111 2222 3333 4444";  // Карта 1: должна быть одобрена
    private static final String BUGGED_DECLINED_CARD = "5555 6666 7777 8888";  // Карта 2: должна быть отклонена, но показывает успех (баг)
    private static final String RANDOM_ERROR_CARD = "9999 8888 7777 6666";  // Любая другая карта для проверки ошибки

    public static String getValidMonth() {
        return String.format("%02d", new Faker().number().numberBetween(1, 12));
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidHolder() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCvc() {
        return String.format("%03d", new Faker().number().numberBetween(100, 999));
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(
                APPROVED_CARD,  // 1111 2222 3333 4444
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getDeclinedCardWithBug() {
        return new CardInfo(
                BUGGED_DECLINED_CARD,  // 5555 6666 7777 8888 - должна быть отклонена, но показывает успех (баг)
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getRandomCardForError() {
        return new CardInfo(
                RANDOM_ERROR_CARD,  // Любая другая карта, которая даст ошибку
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String holder;
        private String cvc;

        public CardInfo(String number, String month, String year, String holder, String cvc) {
            this.number = number;
            this.month = month;
            this.year = year;
            this.holder = holder;
            this.cvc = cvc;
        }

        public String getNumber() { return number; }
        public String getMonth() { return month; }
        public String getYear() { return year; }
        public String getHolder() { return holder; }
        public String getCvc() { return cvc; }
    }
}