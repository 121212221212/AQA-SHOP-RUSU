package ru.netology.data;

import java.sql.*;

public class DataBaseHelper {

    public static String getPaymentStatus() {
        String status = "NOT_FOUND";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "root", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1")) {

            if (rs.next()) {
                status = rs.getString("status");
                System.out.println("PAYMENT STATUS FROM DB: " + status);
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return status;
    }

    public static String getCreditStatus() {
        String status = "NOT_FOUND";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "root", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1")) {

            if (rs.next()) {
                status = rs.getString("status");
                System.out.println("CREDIT STATUS FROM DB: " + status);
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return status;
    }
}