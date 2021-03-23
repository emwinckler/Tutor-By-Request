package com.example.myapplication.Models;

import java.util.Date;

public class TutorAvailablity {

    private String tutorId;
    private Date date;
    private int time;
    private Course course;

    public TutorAvailablity(String tutorId, Date date, int time, String subject, String course,
                            int courseNo) {
        this.tutorId = tutorId;
        this.date = date;
        this.time = time;

        this.course = new Course(subject, course, courseNo);

    }

    public String getTutorId() { return tutorId; }
    public Date getDate() { return date; }
    public int getTime() { return time; }
    public Course getCourse() { return course; }


    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public void setTime(int time) {
        this.time = time;
    }


    public void setCourse(Course course) {
        this.course = course;
    }

}
