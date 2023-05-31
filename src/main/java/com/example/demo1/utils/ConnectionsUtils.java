package com.example.demo1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionsUtils {

    String datasourcePath = "jdbc:postgresql://localhost:5432";
    String datasourceName = "/"; //TODO : put your DB name after slash
    String username = ""; //TODO : put your username
    String password = ""; //TODO : put your password

    static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(datasourcePath + datasourceName, username, password);
    }

    static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
