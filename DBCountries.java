package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * defines DBCountries class
 */
public class DBCountries {

    /**
     * displays list of all countries
     */
    public static ObservableList<Countries> getAllCountries() throws SQLException {

        ObservableList<Countries> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                Countries C = new Countries(countryID, country);
                list.add(C);
                //System.out.println(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * adds new country
     */
    public static int insert(int countryID, String country) throws SQLException {
        String sql = "INSERT INTO countries (Country_ID, Countries) VALUES(?, ?)";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ps.setString(2, country);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * updates existing country
     */
    public static int update(int countryID, String country) throws SQLException {
        String sql = "UPDATE countries SET Countries = ? WHERE Country_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(2, countryID);
        ps.setString(1, country);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * deletes country
     */
    public static int delete(int countryID) throws SQLException {
        String sql = "DELETE FROM countries WHERE Country_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * prints out selected country info
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryId = rs.getInt("Country_Id");
            String countryName = rs.getString("Countries");
            System.out.println(countryId + " - " + countryName);
        }
    }

    /**
     * displays all country info
     */
    public static void showMe() throws SQLException {
        ObservableList<Countries> list = DBCountries.getAllCountries();
        for (Countries C : list) {
            System.out.println("Countries ID: " + C.getCountryID() + " Countries: " + C.getCountry());
        }
    }

    /**
     * converts country ID to corresponding country name
     */
    public static String selectName(int countryID) throws SQLException {
        String sql = "SELECT Country FROM countries WHERE Country_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String countryName = rs.getString("Country");
            //System.out.println(countryName);
            return countryName;
        }
        return null;
    }

    /**
     * converts country name to corresponding country ID
     */
    public static int selectID(String countryName) throws SQLException {
        String sql = "SELECT Country_ID FROM countries WHERE Country = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_Id");
            return countryID;
        }
        return 0;
    }
}


