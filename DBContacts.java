package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * defines DBContacts class
 */
public class DBContacts {

    /**
     * displays all contacts
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contacts C = new Contacts(contactID, contactName, email);
            list.add(C);
        }
        return list;
    }

    /**
     * prints out selected contact info
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_Id");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            System.out.println(contactID + " - " + contactName + " - " + email);
        }
    }

    /**
     * converts contact ID to corresponding contact name
     */
    public static String selectName(int contactID) throws SQLException {
        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String contactName = rs.getString("Contact_Name");
            //System.out.println(contactName);
            return contactName;
        }
        return null;
    }

    /**
     * converts contact name to corresponding contact ID
     */
    public static int selectID(String contactName) throws SQLException {
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_Id");
            System.out.println(contactID);
            return contactID;
        }
        return 0;
    }
}
