package com.example.c195tasklangridge.model;

/**
 * defines Contacts class
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * defines Contacts object
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
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
     * returns the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the contact name
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * sets the contact name
     * @param contactName the contact name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
