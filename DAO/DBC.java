package com.example.c195tasklangridge.DAO;

import java.sql.*;

/**
 * defines DBC class
 */
public abstract class DBC {
    //jdbc:mysql://localhost:3306/?user=sqlUser - from SQL

    //jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=SERVER - recommended by Oracle

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * opens the connection
     */
    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * gets the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * closes the connection
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * tests date conversion
     */
    public static void checkDateConversion() {
        System.out.println("CREATE DATE TEST");
        String sql = "SELECT * from countries";
        try {
            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

