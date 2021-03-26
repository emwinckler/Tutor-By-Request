package com.example.myapplication.models;

import java.util.Date;

public class TutorAvailablity {

    private String tutorId;
    private String date;
    private String time;
    private Course course;
    private boolean booked;

    public TutorAvailablity(String tutorId, String date, String time, boolean booked
                            ) { //String course, int courseNo
        this.tutorId = tutorId;
        this.date = date;
        this.time = time;
//        this.course = new Course(subject, course, courseNo);
        this.booked = booked;


    }

    public String getTutorId() { return tutorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public Course getCourse() { return course; }
    public boolean isBooked() { return this.booked; }


    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public void setCourse(Course course) {
        this.course = course;
    }

    public String toString() {
        return "" + this.getTutorId() + " " + this.getDate() + " " + this.getTime() + " " + this.isBooked();
    }

}
