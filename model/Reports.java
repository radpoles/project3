package com.example.c195tasklangridge.model;

/**
 * defines Reports class
 */
public class Reports {
    private String name;
    private int count;

    /**
     * defines Reports object
     */
    public Reports(String name, int count) {
        this.name = name;
        this.count = count;
    }

    /**
     * returns the report name
     * @return the report name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the report name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the report count
     * @return the report count
     */
    public int getCount() {
        return count;
    }

    /**
     * sets the report count
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
}
