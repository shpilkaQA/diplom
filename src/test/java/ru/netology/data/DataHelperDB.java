package ru.netology.data;

import io.qameta.allure.Step;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHelperDB {
    private DataHelperDB() {
    }

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

    private static String checkPayment = "SELECT * FROM payment_entity JOIN order_entity " +
            "on payment_entity.transaction_id = order_entity.payment_id;";

    private static String checkCredit = "SELECT * FROM payment_entity JOIN order_entity " +
            "on payment_entity.transaction_id = order_entity.payment_id;";

    private static String checkOrder = "SELECT * FROM order_entity;";

    @Step("Соединение с БД")
    public static Connection getConnectionDB() throws SQLException {
        try (
                val conn = DriverManager.getConnection(url, user, password);
        ) {
            return conn;
        }
    }

    @Step("Очистка БД")
    public static void clearDB() throws SQLException {
        val runner = new QueryRunner();
        runner.update(getConnectionDB(), deleteCredit);
        runner.update(getConnectionDB(), deletePayment);
        runner.update(getConnectionDB(), deleteOrder);
        System.out.println("Таблицы очищены");
    }
    /*@Step("Очистка БД")
    public static void clearDB() throws SQLException {
        val runner = new QueryRunner();
        val deleteCredit = "DELETE FROM credit_request_entity;";
        val deletePayment = "DELETE FROM payment_entity;";
        val deleteOrder = "DELETE FROM order_entity;";
        runner.update(getConnectionDB(), deleteCredit);
        runner.update(getConnectionDB(), deletePayment);
        runner.update(getConnectionDB(), deleteOrder);
        System.out.println("Таблицы очищены");
    }*/
    /*public static void clearDB() throws SQLException {
        val runner = new QueryRunner();
        runner.update(getConnectionDB(), "DELETE FROM credit_request_entity;");
        runner.update(getConnectionDB(), "DELETE FROM payment_entity;");
        runner.update(getConnectionDB(), "DELETE FROM order_entity;");
        System.out.println("Таблицы очищены");
    }*/

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditDataInfo {
        private String id;
        private String bank_id;
        private String created;
        private String status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDataInfo {
        private String id;
        private String created;
        private String credit_id;
        private String payment_id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentDataInfo {
        private String id;
        private int amount;
        private String created;
        private String status;
        private String transaction_id;
    }

    @Step("Проверка наличия записи в базе о покупке с выводом статуса карты")
    public static String verifyOrderByPayment() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.update(getConnectionDB(), verificationOrderByPaymentStatus,
                new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Проверка наличия записи в базе о покупке в кредит с выводом статуса карты")
    public static String verifyOrderByPaymentByCredit() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.update(getConnectionDB(), verificationOrderByCreditStatus,
                new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить'")
    public static void verifyStatusWithApprovedBuy() throws SQLException {
        val status = DataHelperDB.verifyOrderByPayment();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить'")
    public static void verifyStatusWithDeclinedBuy() throws SQLException {
        val status = DataHelperDB.verifyOrderByPayment();
        assertEquals("DECLINED", status);
    }

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithApprovedByCredit() throws SQLException {
        val status = DataHelperDB.verifyOrderByPaymentByCredit();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithDeclinedByCredit() throws SQLException {
        val status = DataHelperDB.verifyOrderByPaymentByCredit();
        assertEquals("DECLINED", status);
    }

    @Step("Проверка наличия записей в таблице payment_entity")
    public static void verifyPayment() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.update(getConnectionDB(), checkPayment,
                new BeanHandler<>(PaymentDataInfo.class));
        System.out.println(data);
    }

    @Step("Проверка наличия записей в таблице credit_request_entity")
    public static void verifyCredit() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.update(getConnectionDB(), checkCredit,
                new BeanHandler<>(CreditDataInfo.class));
        System.out.println(data);
    }

    @Step("Проверка наличия записей в таблице order_entity")
    public static void verifyOrder() throws SQLException {
        val runner = new QueryRunner();
        val data = runner.update(getConnectionDB(), checkOrder,
                new BeanHandler<>(OrderDataInfo.class));
        System.out.println(data);
    }
}
