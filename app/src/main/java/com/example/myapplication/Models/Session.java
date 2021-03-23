package com.example.myapplication.Models;

import java.util.Date;

/*
This class is a model that is meant to represent the users of the application
* */
public class Session {
    private String studentID;
    private String tutorID;
    private Date date;
    private String time;
    private String subject;
    private int courseNo;
    private String location;
    private String description;
    private String sessionID;


    public Session(String studentID, String tutorID, Date date, String time, String subject,
                   int courseNo, String location, String description, String sessionID){
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.courseNo = courseNo;
        this.location = location;
        this.description = description;
        this.sessionID = sessionID;
    }
}
