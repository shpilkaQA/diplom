package ru.netology.data;

import io.qameta.allure.Step;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.models.CreditDataModel;
import ru.netology.models.OrderDataModel;
import ru.netology.models.PaymentDataModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataHelperDB {
    private DataHelperDB() {
    }

    private static Connection conn;
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("user");
    private static String password = System.getProperty("password");

    private static String deleteCredit = "DELETE FROM credit_request_entity;";
    private static String deletePayment = "DELETE FROM payment_entity;";
    private static String deleteOrder = "DELETE FROM order_entity;";

    private static String verificationOrderByPaymentStatus = "SELECT payment_entity.status FROM payment_entity " +
            "JOIN order_entity on payment_entity.transaction_id = order_entity.payment_id " +
            "WHERE payment_entity.created IN (SELECT max(payment_entity.created) FROM payment_entity);";

    private static String verificationOrderByCreditStatus = "SELECT credit_request_entity.status " +
            "FROM credit_request_entity JOIN order_entity " +
            "on credit_request_entity.bank_id = order_entity.credit_id " +
            "WHERE credit_request_entity.created IN (SELECT max(credit_request_entity.created) " +
            "FROM credit_request_entity);";

    private static String checkPayment = "SELECT payment_entity.id, payment_entity.amount, payment_entity.created, " +
            "payment_entity.status, payment_entity.transaction_id FROM payment_entity " +
            "JOIN order_entity on transaction_id=payment_id;";

    private static String checkCredit = "SELECT credit_request_entity.id, credit_request_entity.bank_id, " +
            "credit_request_entity.created, credit_request_entity.status FROM credit_request_entity JOIN order_entity " +
            "on credit_request_entity.bank_id = order_entity.credit_id;";

    private static String checkOrder = "SELECT * FROM order_entity;";

    @Step("Соединение с БД")
    public static Connection getConnectionDB() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                System.out.println("Проблема с подключением к БД - " + ex.getMessage());
            }
        }
        return conn;
    }

    @Step("Закрытие соединения с БД")
    public static void closeConnectionDB() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Невозможно закрыть соединение" + ex.getMessage());
        }
    }

    @Step("Очистка БД")
    public static void clearDB() throws SQLException {
        val runner = new QueryRunner();
        runner.update(getConnectionDB(), deletePayment);
        runner.update(getConnectionDB(), deleteCredit);
        runner.update(getConnectionDB(), deleteOrder);
    }

    @Step("Проверка наличия записи в базе о покупке с выводом статуса карты")
    public static String verifyOrderByPayment() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.query(getConnectionDB(), verificationOrderByPaymentStatus, new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Проверка наличия записи в базе о покупке в кредит с выводом статуса карты")
    public static String verifyOrderByPaymentByCredit() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.query(getConnectionDB(), verificationOrderByCreditStatus, new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить'")
    public static void verifyStatusWithApprovedBuy() throws SQLException {
        val status = verifyOrderByPayment();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить'")
    public static void verifyStatusWithDeclinedBuy() throws SQLException {
        val status = verifyOrderByPayment();
        assertEquals("DECLINED", status);
    }

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithApprovedByCredit() throws SQLException {
        val status = verifyOrderByPaymentByCredit();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithDeclinedByCredit() throws SQLException {
        val status = verifyOrderByPaymentByCredit();
        assertEquals("DECLINED", status);
    }

    @Step("Проверка наличия записей в таблице payment_entity")
    public static void verifyPayment() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.query(getConnectionDB(), checkPayment, new BeanHandler<>(PaymentDataModel.class));
        assertNotNull(data);
    }

    @Step("Проверка наличия записей в таблице credit_request_entity")
    public static void verifyCredit() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.query(getConnectionDB(), checkCredit, new BeanHandler<>(CreditDataModel.class));
        assertNotNull(data);
    }

    @Step("Проверка наличия записей в таблице order_entity")
    public static void verifyOrder() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.query(getConnectionDB(), checkOrder, new BeanHandler<>(OrderDataModel.class));
        assertNotNull(data);
    }
}
