package com.example.myapplication.models;

public class Course {
    private String subject;
    private String course;
    private int courseNo;

    public Course(String subject, String course, int courseNo) {
        this.subject = subject;
        this.course = course;
        this.courseNo = courseNo;
    }

    public String getSubject() { return subject; }

    public String getCourse() { return course; }

    public int getCourseNo() { return courseNo; }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }
}
