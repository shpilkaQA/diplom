package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelperCard;
import ru.netology.data.DataHelperDB;
import ru.netology.page.InputDataPage;

import java.sql.SQLException;

public class TestBuyTravelHappyPath extends TestAfterBefore {

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка утвержденной картой")
    void buyByApprovedCard() throws SQLException {
        InputDataPage.buy();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageSuccessfully();
        DataHelperDB.verifyStatusWithApprovedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка в кредит утвержденной картой")
    void buyInCreditByApprovedCard() throws SQLException {
        InputDataPage.buyInCredit();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageSuccessfully();
        DataHelperDB.verifyStatusWithApprovedByCredit();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке отклоненной картой")
    void rejectBuyByDeclinedCard() throws SQLException {
        InputDataPage.buy();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getDeclinedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageError();
        DataHelperDB.verifyStatusWithDeclinedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке в кредит отклоненной картой")
    void rejectBuyInCreditDeclinedCard() throws SQLException {
        InputDataPage.buyInCredit();
        InputDataPage.setCardNumber(String.valueOf(DataHelperCard.getDeclinedCardInfo()));
        InputDataPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        InputDataPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        InputDataPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        InputDataPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        InputDataPage.clickContinueButton();
        InputDataPage.checkMessageError();
        DataHelperDB.verifyStatusWithDeclinedByCredit();
    }
}
