package com.example.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:h2:mem:cinemaDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; //am schimbat si in app proprieties

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



