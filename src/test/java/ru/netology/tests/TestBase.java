package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.netology.data.DataHelperDB;
import ru.netology.page.InputDataPage;

import java.sql.SQLException;

public class TestAfterBefore {
    protected static InputDataPage inputDataPage;

    @BeforeAll
    protected static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        inputDataPage = new InputDataPage();
    }

    @AfterAll
    protected static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    protected void clearAll() throws SQLException {
        DataHelperDB.clearDB();
    }
}
