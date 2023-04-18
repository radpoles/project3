package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * defines DBCustomers class
 */
public class DBCustomers {

    /**
     * returns list of all customers
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers C = new Customers(customerID, customerName, address, postalCode, phone, divisionID);
                list.add(C);
                //System.out.println(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * adds new customer
     */
    public static void insert(int customerID, String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.setString(2, customerName);
        ps.setString(3, address);
        ps.setString(4, postalCode);
        ps.setString(5, phone);
        ps.setInt(6, divisionID);
        ps.executeUpdate();
    }

    /**
     * updates existing customer
     */
    public static void update(String customerName, String address, String postalCode, String phone, int divisionID, int customerID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        ps.setInt(6, customerID);
        ps.executeUpdate();
    }

    /**
     * removes customer
     */
    public static int delete(int customerID) throws SQLException {
        String sqlA = "DELETE FROM appointments WHERE Customer_Id = ?";
        PreparedStatement psA = DBC.getConnection().prepareStatement(sqlA);
        psA.setInt(1, customerID);
        psA.executeUpdate();
        String sql = "DELETE FROM customers WHERE Customer_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * prints out selected customer info
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String customerName = rs.getString("Customer_Name");
            System.out.println("Customer: " + customerName);
        }
    }

    /**
     * displays all customers in the US
     */
    public static ObservableList<Customers> getUSCustomers() throws SQLException {
        ObservableList<Customers> listUS = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers WHERE Division_ID < '60'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers C = new Customers(customerID, customerName, address, postalCode, phone, divisionID);
                listUS.add(C);
                //System.out.println(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUS;
    }

    /**
     * displays all customers in Canada
     */
    public static ObservableList<Customers> getCanadaCustomers() throws SQLException {
        ObservableList<Customers> listCanada = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers WHERE Division_ID BETWEEN '60' AND '100'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers C = new Customers(customerID, customerName, address, postalCode, phone, divisionID);
                listCanada.add(C);
                //System.out.println(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listCanada;
    }

    /**
     * displays all customers in the UK
     */
    public static ObservableList<Customers> getUKCustomers() throws SQLException {
        ObservableList<Customers> listUK = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers WHERE Division_ID > '100'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers C = new Customers(customerID, customerName, address, postalCode, phone, divisionID);
                listUK.add(C);
                //System.out.println(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUK;
    }

    /**
     * checks for matching customer ID
     */
    public static int verifyCustomerID(int matchingID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, matchingID);
        //System.out.println(ps); //checks statement
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int custID = rs.getInt("Customer_Id");
            return custID;
        }
        return 0;
    }
}
