package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelperCard;
import ru.netology.data.DataHelperDB;
import ru.netology.page.BuyingPage;

import java.sql.SQLException;

public class TestBuyTravelHappyPath extends TestBase {

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка утвержденной картой")
    void buyByApprovedCard() throws SQLException {
        BuyingPage.buy();
        BuyingPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        BuyingPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        BuyingPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        BuyingPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        BuyingPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageSuccessfully();
        DataHelperDB.verifyStatusWithApprovedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка в кредит утвержденной картой")
    void buyInCreditByApprovedCard() throws SQLException {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(String.valueOf(DataHelperCard.getApprovedCardInfo()));
        BuyingPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        BuyingPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        BuyingPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        BuyingPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageSuccessfully();
        DataHelperDB.verifyStatusWithApprovedByCredit();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке отклоненной картой")
    void rejectBuyByDeclinedCard() throws SQLException {
        BuyingPage.buy();
        BuyingPage.setCardNumber(String.valueOf(DataHelperCard.getDeclinedCardInfo()));
        BuyingPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        BuyingPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        BuyingPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        BuyingPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageError();
        DataHelperDB.verifyStatusWithDeclinedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке в кредит отклоненной картой")
    void rejectBuyInCreditDeclinedCard() throws SQLException {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(String.valueOf(DataHelperCard.getDeclinedCardInfo()));
        BuyingPage.setCardMonth(String.valueOf(DataHelperCard.getValidMonthInfo()));
        BuyingPage.setCardYear(String.valueOf(DataHelperCard.getValidYearInfo()));
        BuyingPage.setCardOwner(String.valueOf(DataHelperCard.getValidOwnerInfo()));
        BuyingPage.setCardCVV(String.valueOf(DataHelperCard.getValidCvcInfo()));
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageError();
        DataHelperDB.verifyStatusWithDeclinedByCredit();
    }

    @Test
    @DisplayName("Проверка заполнения полей таблиц БД")
    void verifyDB() throws SQLException {
        DataHelperDB.verifyPayment();
        DataHelperDB.verifyCredit();
        DataHelperDB.verifyOrder();
    }

    @Test
    @DisplayName("Очистка таблиц БД")
    void clearAll() throws SQLException {
        DataHelperDB.clearDB();
    }
}
