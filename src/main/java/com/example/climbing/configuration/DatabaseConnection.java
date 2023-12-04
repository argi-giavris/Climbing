package com.example.climbing.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        String dbUrl = "jdbc:postgresql://localhost:5432/";
        String dbName = "climbing";
        String dbUsername = "postgres";
        String dbPassword = "2108135592Ar";

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(dbUrl + dbName, dbUsername, dbPassword);

        return con;
    }
}
