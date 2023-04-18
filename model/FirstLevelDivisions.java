package com.example.c195tasklangridge.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
/**
 * defines FirstLevelDivisions class
 */
public class FirstLevelDivisions {

    private int divisionID;
    private String division;
    private int countryID;

    /**
     * defines FirstLevelDivisions object
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * returns the division name
     * @return the division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * sets the division name
     * @param division the division name to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * returns the division id
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * sets the division id
     * @param divisionID the division id to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * returns the country id
     * @return the country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * sets the country id
     * @param countryID the country id to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
