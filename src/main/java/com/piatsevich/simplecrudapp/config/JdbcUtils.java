package com.piatsevich.simplecrudapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtils {

    private static final String URL = "jdbc:postgresql://localhost:5432/crudapp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection() {
        if( connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
