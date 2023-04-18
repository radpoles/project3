package com.example.c195tasklangridge.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/**
 * defines Appointments class
 */
public class Appointments {

    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        private int appointmentID;
        private String title;
        private String description;
        private String location;
        private String type;
        private Timestamp start;
        private Timestamp end;
        private int customerID;
        private int userID;
        private int contactID;

    /**
     * defines Appointments object
     */
        public Appointments(int appointmentID, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
            this.appointmentID = appointmentID;
            this.title = title;
            this.description = description;
            this.location = location;
            this.type = type;
            this.start = start;
            this.end = end;
            this.customerID = customerID;
            this.userID = userID;
            this.contactID = contactID;
        }

        /**
         * returns the appointment id
         * @return the appointment id
         */
        public int getAppointmentID() {
            return appointmentID;
        }

        /**
         * sets the appointment id
         * @param appointmentID the appointment id to set
         */
        public void setAppointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
        }

        /**
         * returns the appointment title
         * @return the appointment title
         */
        public String getTitle() {
            return title;
        }

        /**
         * sets the appointment title
         * @param title the appointment title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * returns the description
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * sets the description
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * returns the location
         * @return the location
         */
        public String getLocation() {
            return location;
        }

        /**
         * sets the location
         * @param location the location to set
         */
        public void setLocation(String location) {
            this.location = location;
        }

        /**
         * returns the appointment type
         * @return the appointment type
         */
        public String getType() {
            return type;
        }

        /**
         * sets the appointment type
         * @param type the appointment type to set
         */
        public void setType(String type) {
            this.type = type;
        }

    /**
     * returns the start date and time
     * @return the start date and time
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * sets the start date and time
     * @param start the start date and time to set
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * returns the end date and time
     * @return the end date and time
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * sets the end date and time
     * @param end the end date and time to set
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * returns the customer id
     * @return the customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * sets the customer id
     * @param customerID the customer id to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
     * returns the contact id
     * @return the contact id
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * sets the contact id
     * @param contactID the contact id to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * returns the list of all appointments
     * @return allAppointments the list of all appointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        System.out.println(allAppointments);
        return allAppointments;
    }
}

