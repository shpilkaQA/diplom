package ru.netology.data;

import lombok.*;

public class DataHelperCard {
    private DataHelperCard() {
    }

    // корректные данные

    @Value
    public static class ValidMonthInfo {
        private String month;
    }

    public static ValidMonthInfo getValidMonthInfo() {
        return new ValidMonthInfo("08");
    }

    @Value
    public static class ValidYearInfo {
        private String year;
    }

    public static ValidYearInfo getValidYearInfo() {
        return new ValidYearInfo("22");
    }

    @Value
    public static class ValidOwnerInfo {
        private String owner;
    }

    public static ValidOwnerInfo getValidOwnerInfo() {
        return new ValidOwnerInfo("Vasya Pupkin");
    }

    @Value
    public static class ValidCvcInfo {
        private String cvc;
    }

    public static ValidCvcInfo getValidCvcInfo() {
        return new ValidCvcInfo("999");
    }


    @Value
    public static class ApprovedCardInfo {
        private String approvedCardNumber;
    }

    public static ApprovedCardInfo getApprovedCardInfo() {
        return new ApprovedCardInfo("4444444444444441");
    }


    @Value
    public static class DeclinedCardInfo {
        private String declinedCardNumber;
    }

    public static DeclinedCardInfo getDeclinedCardInfo() {
        return new DeclinedCardInfo("4444444444444442");
    }

}
