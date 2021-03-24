package com.example.myapplication.models;

import java.io.Serializable;

/*
This class is a model that is meant to represent the users of the application
* */
public class User implements Serializable {
    private String netID;
    private String password;
    private String name;
    private String email;
    private boolean tutor;
    private boolean tutee;


    public User(String netID, String password, String name, String email, boolean tutor, boolean tutee){
        this.netID = netID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.tutor = tutor;
        this.tutee = tutee;
    }

    // Getters for the users
    public String getNetID() { return netID; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isTutor() { return tutor; }
    public boolean isTutee() { return tutee; }

    // Setters for the users
    // Have to update the database

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTutor(boolean tutor) {
        this.tutor = tutor;
    }

    public void setTutee(boolean tutee) {
        this.tutee = tutee;
    }
}
