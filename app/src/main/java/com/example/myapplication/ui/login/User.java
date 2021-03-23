package com.example.myapplication.ui.login;

import java.io.Serializable;

public class User implements Serializable {

    private String password, netID;
    private boolean Tutor,Student;

    public User(String netID, String password, boolean tutor, boolean student) {
        this.netID = netID;
        this.password = password;

        Tutor = tutor;
        Student = student;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTutor() {
        return Tutor;
    }

    public void setTutor(boolean tutor) {
        Tutor = tutor;
    }

    public boolean isStudent() {
        return Student;
    }

    public void setStudent(boolean student) {
        Student = student;
    }
}
