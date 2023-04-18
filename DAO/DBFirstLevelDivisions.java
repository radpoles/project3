package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

/**
 * defines DBFirstLevelDivisons class
 */
public class DBFirstLevelDivisions {

    /**
     * returns list of all divisions
     */
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() throws SQLException {

        ObservableList<FirstLevelDivisions> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                FirstLevelDivisions D = new FirstLevelDivisions(divisionID, division, countryID);
                list.add(D);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * returns list of US divisions
     */
    public static ObservableList<String> selectUS() throws SQLException {
        ObservableList<String> divisionsUS = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            //System.out.println(divisionID + " - " + division);
            if (divisionID < 60) {
                divisionsUS.add(division);
            }
        }
        //System.out.println(divisionsUS);
        return divisionsUS;
    }

    /**
     * returns list of Canada divisions
     */
    public static ObservableList<String> selectCanada() throws SQLException {
        ObservableList<String> divisionsCanada = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            //System.out.println(divisionID + " - " + division);
            if (divisionID < 100 && divisionID > 59) {
                divisionsCanada.add(division);
            }
        }
        //System.out.println(divisionsCanada);
        return divisionsCanada;
    }

    /**
     * returns list of UK divisions
     */
    public static ObservableList<String> selectUK() throws SQLException {
        ObservableList<String> divisionsUK = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            //System.out.println(divisionID + " - " + division);
            if (divisionID > 100) {
                divisionsUK.add(division);
            }
        }
        //System.out.println(divisionsUK);
        return divisionsUK;
    }

    /**
     * converts division ID to corresponding division name
     */
    public static String selectName(int divisionID) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String divisionName = rs.getString("Division");
            System.out.println(divisionName);
            return divisionName;
        }
        return null;
    }

    /**
     * converts division name to corresponding division ID
     */
    public static int selectID(String divisionName) throws SQLException {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, divisionName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_Id");
            System.out.println(divisionID);
            return divisionID;
        }
        return 0;
    }
}
