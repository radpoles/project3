package com.example.c195tasklangridge.model;

import java.sql.Timestamp;

/**
 * defines Users class
 */
public class Users {
    private int userID;
    private String password;
    private String username;

    /**
     * defines Users object
     */
    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.password = password;
        this.username = username;
    }

    /**
     * returns the user id
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets the user id
     * @param userID the user id to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * returns the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
