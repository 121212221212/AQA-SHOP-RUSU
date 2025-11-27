package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {}

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    // Approved card from assignment: 1111 2222 3333 4444
    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN PETROV", "123");
    }

    // Declined card from assignment: 5555 6666 7777 8888
    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "12", "25", "MARIA IVANOVA", "456");
    }
    public static CardInfo getInvalidCardNumber() {
        return new CardInfo("4444 4444 4444 4443", "12", "25", "IVAN PETROV", "123");
    }

    public static CardInfo getExpiredCard() {
        return new CardInfo("1111 2222 3333 4444", "01", "20", "IVAN PETROV", "123");
    }
}