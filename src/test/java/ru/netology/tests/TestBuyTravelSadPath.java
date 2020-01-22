package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.data.DataHelperCard;
import ru.netology.page.InputDataPage;

public class TestBuyTravelSadPath extends TestAfterBefore {
    // тестирование с использованием утвержденной карты
    // Раздел 'Купить'
    @Test
    @DisplayName("Поля не заполнены. Раздел 'Купить'")
    void shouldErrorTestOfNullByBuy() {
        InputDataPage.buy();
        DataHelperCard.getApprovedCardInfo();
        InputDataPage.setCardMonth("");
        InputDataPage.setCardYear("");
        InputDataPage.setCardOwner("");
        InputDataPage.setCardCVV("");
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
        InputDataPage.checkMessageRequiredField();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfMonth.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Месяц', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfMonthByBuy(String month) {
        InputDataPage.buy();
        DataHelperCard.getApprovedCardInfo();
        InputDataPage.setCardMonth(month);
        DataHelperCard.getValidYearInfo();
        DataHelperCard.getValidOwnerInfo();
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfYear.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Год', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfYearByBuy(String year) {
        InputDataPage.buy();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        InputDataPage.setCardYear(year);
        DataHelperCard.getValidOwnerInfo();
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfOwner.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Владелец', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfOwnerByBuy(String owner) {
        InputDataPage.buy();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        DataHelperCard.getValidYearInfo();
        InputDataPage.setCardOwner(owner);
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfCvc.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'CVC', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfCvcByBuy(String cvc) {
        InputDataPage.buy();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        DataHelperCard.getValidYearInfo();
        DataHelperCard.getValidOwnerInfo();
        InputDataPage.setCardCVV(cvc);
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    // Раздел 'Купить в кредит'
    @Test
    @DisplayName("Поля не заполнены. Раздел 'Купить в кредит'")
    void shouldErrorTestOfNullByBuyInCred() {
        InputDataPage.buyInCredit();
        DataHelperCard.getApprovedCardInfo();
        InputDataPage.setCardMonth("");
        InputDataPage.setCardYear("");
        InputDataPage.setCardOwner("");
        InputDataPage.setCardCVV("");
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
        InputDataPage.checkMessageRequiredField();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfMonth.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Месяц', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfMonthByBuyInCred(String month) {
        InputDataPage.buyInCredit();
        DataHelperCard.getApprovedCardInfo();
        InputDataPage.setCardMonth(month);
        DataHelperCard.getValidYearInfo();
        DataHelperCard.getValidOwnerInfo();
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfYear.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Год', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfYearByBuyInCred(String year) {
        InputDataPage.buyInCredit();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        InputDataPage.setCardYear(year);
        DataHelperCard.getValidOwnerInfo();
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfOwner.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Владелец', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfOwnerByBuyInCred(String owner) {
        InputDataPage.buyInCredit();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        DataHelperCard.getValidYearInfo();
        InputDataPage.setCardOwner(owner);
        DataHelperCard.getValidCvcInfo();
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfCvc.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'CVC', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfCvcByBuyInCred(String cvc) {
        InputDataPage.buyInCredit();
        DataHelperCard.getApprovedCardInfo();
        DataHelperCard.getValidMonthInfo();
        DataHelperCard.getValidYearInfo();
        DataHelperCard.getValidOwnerInfo();
        InputDataPage.setCardCVV(cvc);
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageIncorrectFormat();
    }
}
