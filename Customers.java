package com.example.c195tasklangridge.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/**
 * defines Customers class
 */
public class Customers {

        private int customerID;
        private String customerName;
        private String address;
        private String postalCode;
        private String phone;
        private int divisionID;

    /**
     * defines Customers object
     */
        public Customers(int customerID, String customerName, String address, String postalCode, String phone, int divisionID) {
            this.customerID = customerID;
            this.customerName = customerName;
            this.address = address;
            this.postalCode = postalCode;
            this.phone = phone;
            this.divisionID = divisionID;
        }

    public ObservableList<Customers> associatedAppointments = FXCollections.observableArrayList();

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
         * returns the customer name
         * @return the customer name
         */
        public String getCustomerName() {
            return customerName;
        }

        /**
         * sets the customer name
         * @param customerName the customer name to set
         */
        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        /**
         * returns the address
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * sets the address
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * returns the postal code
         * @return the postal code
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         * sets the postal code
         * @param postalCode the postal code to set
         */
        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

    /**
     * returns the phone number
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets the phone number
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

        /**
         * returns the division id
         * @return the division ID
         */
        public int getDivisionID() {
            return divisionID;
        }

        /**
         * sets the division id
         * @param divisionID the division ID to set
         */
        public void setDivisionID(int divisionID) {
            this.divisionID = divisionID;
        }

    /** Adds appointments to a customer */
    public void addAssociatedAppointment(Customers selectedAppt) {
        associatedAppointments.add(selectedAppt);
    }

    /** Removes appointments from a customer */
    public void deleteAssociatedAppointment(Customers selectedAppt) { associatedAppointments.remove(selectedAppt);}

    /** Displays all appointments associated with a customer
     * @return the customer's associated appointments */
    public ObservableList<Customers> getAllAssociatedAppointments() {
        return associatedAppointments;
    }
}

