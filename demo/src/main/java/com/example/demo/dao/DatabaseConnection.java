package com.example.demo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/escola";
        String user = "root";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

