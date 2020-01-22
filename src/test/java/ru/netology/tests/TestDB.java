package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelperCard;
import ru.netology.data.DataHelperDB;
import ru.netology.page.InputDataPage;

import java.sql.SQLException;

// проверка поддержки СУБД
public class TestDB extends TestAfterBefore {

    @Test
    @DisplayName("Проверка заполнения полей таблиц БД")
    void verifyDB() throws SQLException {
        InputDataPage.buy();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();

        InputDataPage.buyInCredit();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();

        DataHelperDB.verifyPayment();
        DataHelperDB.verifyCredit();
        DataHelperDB.verifyOrder();
    }

    @Test
    void clearDB() throws SQLException {
        DataHelperDB.clearDB();
    }
}
