package com.example.c195tasklangridge.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/**
 * defines Countries class
 */
public class Countries {
    private int countryID;
    private String country;

    /**
     * defines Countries object
     */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * returns the country id
     * @return the country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * returns the country name
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    ObservableList<FirstLevelDivisions> associatedDivisions = FXCollections.observableArrayList();

    public ObservableList<FirstLevelDivisions> getAllAssociatedDivisions() {
        return associatedDivisions;
    }
}
