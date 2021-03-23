package com.example.myapplication;

/*
This class is a model that is meant to represent the users of the application
* */
public class User {
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
}
