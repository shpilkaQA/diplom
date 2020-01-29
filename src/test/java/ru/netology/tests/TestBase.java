package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.netology.data.DataHelperDB;
import ru.netology.page.BuyingPage;

import java.sql.SQLException;

public class TestBase {
    protected static BuyingPage buyingPage;

    @BeforeAll
    protected static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        buyingPage = new BuyingPage();
    }

    @AfterAll
    protected static void tearDownAll() throws SQLException {
        SelenideLogger.removeListener("allure");
        DataHelperDB.getConnectionDB();
        DataHelperDB.clearDB();
        DataHelperDB.closeConnectionDB();
    }
}
