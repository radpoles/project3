package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.Contacts;
import com.example.c195tasklangridge.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * defines DBUsers class
 */
public class DBUsers {

    /**
     * returns list of all users
     */
    public static ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from users";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users U = new Users(userID, userName, password);
                list.add(U);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * allows user to log in to application with credentials matching the database
     */
    public static Users authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND password = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        //System.out.println(ps); checks statement
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int userID = rs.getInt("User_Id");
            Users au = new Users(userID, "", password);
            return au;
        }
        return null;
    }

    /**
     * checks for matching user ID
     */
    public static int verifyUserID(int matchingID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, matchingID);
        //System.out.println(ps); //checks statement
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int userID = rs.getInt("User_Id");
            return userID;
        }
        return 0;
    }
}
