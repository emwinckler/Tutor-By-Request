package com.example.myapplication.models;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databases.TutorAvailabilityDBHelper;
import com.example.myapplication.databases.UsersDBHelper;
import com.example.myapplication.databases.MySessionsDBHelper;
import com.example.myapplication.databases.CoursesDBHelper;
import com.example.myapplication.databases.TutorCoursesDBHelper;
import com.example.myapplication.models.User;


/*
This class is a model that is meant to represent the users of the application
* */
public class Tutor extends User {
    private String netID;
    private String password;
    private String name;
    private String email;
    private boolean tutor;
    private boolean tutee;

    private static long sessionID = 0;


    private MainActivity ma;
    private TutorAvailabilityDBHelper db_availability_table;
    private MySessionsDBHelper db_mysessions_table;
    private TutorCoursesDBHelper db_tutorcourses_table;

    public Tutor(String netID, String password, String name, String email, boolean tutor, boolean tutee){
        super(netID, password, name, email, tutor, tutee);
    }

    // Getters for the Tutor
    public String getNetID() { return super.getNetID(); }
    public String getPassword() { return super.getPassword(); }
    public String getName() { return super.getName(); }
    public String getEmail() { return super.getEmail(); }
    public boolean isTutor() { return super.isTutor(); }
    public boolean isTutee() { return super.isTutee(); }

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



    /*
        public static final String TABLE_NAME = "sessions_table";
        public static final String COL_1 = "student_id";
        public static final String COL_2 = "tutor_id";
        public static final String COL_3 = "date";
        public static final String COL_4 = "time";
        public static final String COL_5 = "subject";
        public static final String COL_6 = "course_num";
        public static final String COL_7 = "location";
        public static final String COL_8 = "description";
        public static final String COL_9 = "session_id";

            private String studentID;
            private String tutorID;
            private Date date;
            private String time;
            private String subject;
            private int courseNo;
            private String location;
            private String description;
            private String sessionID;
    */

}
