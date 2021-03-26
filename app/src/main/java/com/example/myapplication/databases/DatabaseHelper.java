package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.models.Course;
import com.example.myapplication.models.TutorAvailablity;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "TutorDatabase";
    // SESSION TABLE
    public static final String TABLE_NAME_SESSION = "sessions_table";
    public static final String COL_1_SESSION = "student_id";
    public static final String COL_2_SESSION= "tutor_id";
    public static final String COL_3_SESSION= "date";
    public static final String COL_4_SESSION = "time";
    public static final String COL_5_SESSION = "subject";
    public static final String COL_6_SESSION = "course_num";
    public static final String COL_7_SESSION = "location";
    public static final String COL_8_SESSION = "description";
    public static final String COL_9_SESSION = "session_id";
    public static final String CREATE_TABLE_SESSION = "CREATE TABLE "+TABLE_NAME_SESSION+
            "("+
            COL_1_SESSION +" VARCHAR(30), "+
            COL_2_SESSION +" VARCHAR(30), "+
            COL_3_SESSION +" VARCHAR(30), "+
            COL_4_SESSION +" CHAR(30), "+
            COL_5_SESSION +" VARCHAR(30), "+
            COL_6_SESSION +" INTEGER(3), "+
            COL_7_SESSION +" VARCHAR(30), "+
            COL_8_SESSION +" TEXT, "+
            COL_9_SESSION +" INTEGER(8), "+
            "PRIMARY KEY(student_id, tutor_id, date, time)"+
            ")";
    // COURSES TABLE
    public static final String TABLE_NAME_COURSES = "courses_table";
    public static final String COL_1_COURSES = "subject";
    public static final String COL_2_COURSES = "course";
    public static final String COL_3_COURSES = "course_num";
    public static final String CREATE_TABLE_COURSES = "CREATE TABLE "+TABLE_NAME_COURSES+
            "("+
            COL_1_COURSES +" CHAR(30), "+
            COL_2_COURSES +" CHAR(30), "+
            COL_3_COURSES +" INTEGER(3)," +
            "PRIMARY KEY (subject, course_num)"+
            ")";
    // TUTOR AVAILABILITY TABLE
    public static final String TABLE_NAME_TUTOR_A = "tutor_availability_table";
    public static final String COL_1_TUTOR_A  = "tutor_id";
    public static final String COL_2_TUTOR_A  = "date"; // FORMAT: "MM/DD/YYYY"
    public static final String COL_3_TUTOR_A  = "time"; // FORMAT: "HH:MM"
    public static final String COL_4_TUTOR_A  = "booked"; // FORMAT: "TRUE" or "FALSE"
    public static final String CREATE_TABLE_TUTOR_A =  "CREATE TABLE "+TABLE_NAME_TUTOR_A+
            "("+
            COL_1_TUTOR_A  +" VARCHAR(30), "+
            COL_2_TUTOR_A  +" VARCHAR(10), "+
            COL_3_TUTOR_A  +" VARCHAR(5), "+
            COL_4_TUTOR_A  +" CHAR(5), "+
            "PRIMARY KEY(tutor_id, date, time)"+
            ")";
    // TUTOR COURSES TABLE
    public static final String TABLE_NAME_TUTOR_C = "tutor_courses_table";
    public static final String COL_1_TUTOR_C  = "tutor_id";
    public static final String COL_2_TUTOR_C  = "subject";
    public static final String COL_3_TUTOR_C  = "course_num";
    public static final String CREATE_TABLE_TUTOR_C = "CREATE TABLE "+TABLE_NAME_TUTOR_C+
            "("+
            COL_1_TUTOR_C  +" VARCHAR(10), "+
            COL_2_TUTOR_C  +" VARCHAR(30), "+
            COL_3_TUTOR_C  +" INTEGER(3), "+
            "PRIMARY KEY(tutor_id,subject,course_num)"+
            ")";
    // USERS TABLE
    public static final String TABLE_NAME_USERS = "user_table";
    public static final String COL_1_USERS = "student_id";
    public static final String COL_2_USERS = "net_id";
    public static final String COL_3_USERS = "password";
    public static final String COL_4_USERS = "name";
    public static final String COL_5_USERS = "email";
    public static final String COL_6_USERS = "tutor";
    public static final String COL_7_USERS = "tutee";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE "+ TABLE_NAME_USERS +
            "("+
            COL_1_USERS +" VARCHAR(30) PRIMARY KEY, "+
            COL_2_USERS +" INTEGER(10), "+
            COL_3_USERS +" VARCHAR(30), "+
            COL_4_USERS +" VARCHAR(30), "+
            COL_5_USERS +" VARCHAR(30), "+
            COL_6_USERS +" CHAR(5), "+
            COL_7_USERS +" CHAR(5) "+
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context,
                DATABASE_NAME,
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SESSION);
        db.execSQL(CREATE_TABLE_COURSES);
        db.execSQL(CREATE_TABLE_TUTOR_A);
        db.execSQL(CREATE_TABLE_TUTOR_C);
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TUTOR_A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TUTOR_C);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);

        onCreate(db);
    }

    /* SECTION FOR SESSIONS START */

    public boolean addDataSession(String studentID, String tutorID, String date, String time,
                           String subject, int courseNum, String location,
                           String description, int sessionID) throws Exception {
        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_SESSION, studentID);
            contentValues.put(COL_2_SESSION, tutorID);
            contentValues.put(COL_3_SESSION, date);
            contentValues.put(COL_4_SESSION, time);
            contentValues.put(COL_5_SESSION, subject);
            contentValues.put(COL_6_SESSION, courseNum);
            contentValues.put(COL_7_SESSION, location);
            contentValues.put(COL_8_SESSION, description);
            contentValues.put(COL_9_SESSION, sessionID);
            result = db.insert(TABLE_NAME_SESSION,null,contentValues);

        }
        catch (Exception e) {
            result = -1;
        }


        if (result == -1){
            Exception e = new Exception("You can't add a student like that");
            throw e;
        }
        return true;
    }
    public boolean modifyDataSession(String NetID, String COL, ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME_SESSION, cv,  "" + COL + " = ? ", new String[] { NetID } );
        return true; // TODO: what should we return?
    }

    public boolean modifyNameSession(String NetID, String newName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3_SESSION, newName);
        return modifyDataSession(NetID, COL_3_SESSION, contentValues);
    }

    public boolean modifyPasswordSession(String NetID, String newPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_SESSION, newPassword);
        return modifyDataSession(NetID, COL_2_SESSION, contentValues);
    }

    public boolean modifyAccountTYpeSession(String NetID, String newPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_SESSION, newPassword);
        return modifyDataSession(NetID, COL_2_SESSION, contentValues);
    }

    public Cursor getDataSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_SESSION;
        Cursor data = db.rawQuery(query, null);
        return data;
    }public Cursor checkLogin2Session(String NetID, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {NetID,password};
        Cursor cursor = db.query(TABLE_NAME_SESSION, new String[]{"NetID", "password"}, "NetID=? and password=?", selectionArgs , null, null, null);
        return cursor;
    }

    public Cursor getPasswordSession(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT password FROM "+TABLE_NAME_SESSION+"  WHERE " + COL_1_SESSION + NetID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /*SECTION FOR SESSION END*/

    /*SECTION FOR COURSES START*/

    public boolean addDataCourses(String subject, String course, int courseNumber) throws Exception {

        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_COURSES, subject);
            contentValues.put(COL_2_COURSES, course);
            contentValues.put(COL_3_COURSES, courseNumber);

            result = db.insert(TABLE_NAME_COURSES,null,contentValues);

        }
        catch (Exception e) {
            result = -1;
        }


        if (result == -1){
            Exception e = new Exception("You can't add a student like that");
            throw e;
        }
        return true;
    }

    public ArrayList<Course> getDataCourses(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_COURSES;
        Cursor data = db.rawQuery(query, null);
        ArrayList<Course> courseList = new ArrayList<Course>();
        while(data.moveToNext()){
            String subject = data.getString(0);
            String course = data.getString(1);
            int courseNo = Integer.parseInt(data.getString(2));
            courseList.add(new Course(subject,course,courseNo));
        }
        return courseList;
    }

    public ArrayList<String> getAllSubjects(){
        ArrayList<String> subjectList;
        Cursor data;
        SQLiteDatabase db;

        db = this.getWritableDatabase();
        data = db.rawQuery("SELECT DISTINCT " + COL_1_COURSES + " FROM " + TABLE_NAME_COURSES, null);

        subjectList = new ArrayList<String>();
        while(data.moveToNext()){
            String subject = data.getString(0);
            subjectList.add(subject);
        }

        return subjectList;
    }

    public ArrayList<Course> getAllCoursesBySubject(String subjectQuery){
        Cursor data;
        SQLiteDatabase db = this.getWritableDatabase();

        data = db.rawQuery("SELECT * FROM " + TABLE_NAME_COURSES + " WHERE " + COL_1_COURSES + " = ?", new String[]{subjectQuery});

        ArrayList<Course> courseList = new ArrayList<Course>();
        while(data.moveToNext()){
            String subject = data.getString(0);
            String course = data.getString(1);
            int courseNo = Integer.parseInt(data.getString(2));
            courseList.add(new Course(subject,course,courseNo));
        }
        return courseList;
    }

    /*SECTION FOR COURSES START*/

    /*SECTION FOR AVAILABLITY START*/

    public boolean addAvailability(String TutorID, String date, String time) {
        long result;

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_TUTOR_A, TutorID);
            contentValues.put(COL_2_TUTOR_A, date);
            contentValues.put(COL_3_TUTOR_A, time);
            contentValues.put(COL_4_TUTOR_A, "FALSE");

            result = db.insert(TABLE_NAME_TUTOR_A,null,contentValues);

        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public Integer deleteAvailability(String TutorID, String Date, String StartTime) {
        Integer result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.delete(TABLE_NAME_TUTOR_A, COL_1_TUTOR_A + " = ?" + " AND " + COL_2_TUTOR_A + " = ?" + " AND " + COL_3_TUTOR_A + " = ?", new String[] {TutorID, Date, StartTime});

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

    public boolean modifySessionIsAvailable(String TutorID, String Date, String StartTime, String isAvailable) {
        long result;

        try {
            if ( isAvailable.length() != 1 ) { // TODO: still some work to do to check input but im sleepy rn
                return false;
            }

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_TUTOR_A, TutorID);
            contentValues.put(COL_2_TUTOR_A, Date);
            contentValues.put(COL_3_TUTOR_A, StartTime);
            contentValues.put(COL_4_TUTOR_A, isAvailable);



            result = db.update(TABLE_NAME_TUTOR_A, contentValues, COL_1_TUTOR_A+ " =?" + " AND " + COL_2_TUTOR_A+ " =?" + " AND " + COL_3_TUTOR_A + " =?", new String[] {TutorID, Date, StartTime});

        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    //    public Cursor getAllTutorAvailability() {
//        Cursor result;
//
//        try {
//            SQLiteDatabase db = this.getWritableDatabase();
//            result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//
//        }
//        catch (Exception e) {
//            return null;
//        }
//
//        return result;
//    }

    public ArrayList<TutorAvailablity> getAllTutorAvailability() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_TUTOR_A;
        Cursor data = db.rawQuery(query, null);
        ArrayList<TutorAvailablity> tutorAvailabilityList = new ArrayList<TutorAvailablity>();
        while(data.moveToNext()){
            String tutorID = data.getString(0);
            String date = data.getString(1);
            String time = data.getString(2);
//            String course = data.getString(3);
            String booked = data.getString(3);

//            int courseNo = Integer.parseInt(data.getString(2));
            tutorAvailabilityList.add(new TutorAvailablity(tutorID, date,
                    time, booked));
        }
        return tutorAvailabilityList;
    }

    public Cursor getTutorAvailability(String TutorID) {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TUTOR_A + " WHERE " + COL_1_TUTOR_A + " = ?", new String[] {TutorID});

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

    public Cursor getTutorAvailabilityOnDate(String TutorID, String Date) {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TUTOR_A + " WHERE " + COL_1_TUTOR_A + " = ?" + " AND " + COL_2_TUTOR_A + " = ?", new String[] {TutorID, Date});

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

    public ArrayList<String> getTutorAvailabilityOnDate_String(String TutorID, String Date) {
        ArrayList<String> result;
        Cursor data;
        result = new ArrayList<String>();
        data = this.getTutorAvailabilityOnDate(TutorID, Date);
        while(data.moveToNext()){
            String availability;
            String tutorID = data.getString(0);
            String date = data.getString(1);
            String time = data.getString(2);
//            String course = data.getString(3);
            String booked = data.getString(3);
            availability = tutorID + " " + date + " " + time + " " + booked;

//            int courseNo = Integer.parseInt(data.getString(2));
            result.add(availability);
        }


        return result;
    }

    /*SECTION FOR AVAILABLITY END*/

    /*SECTION FOR TUTOR COURSES START*/

    public boolean addTutorCourse(String TutorID, String Subject, int CourseNum) throws Exception {
        long result;

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_TUTOR_C, TutorID);
            contentValues.put(COL_2_TUTOR_C, Subject);
            contentValues.put(COL_3_TUTOR_C, CourseNum);

            result = db.insert(TABLE_NAME_TUTOR_C,null,contentValues);

        }
        catch (Exception e) {
            result =-1;
        }

        if (result == -1){
            throw new Exception("You can't add a student like that");
        }

        return true;
    }

    public Integer deleteTutorCourse(String TutorID, String Subject, String CourseNumber) {
        Integer result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.delete(TABLE_NAME_TUTOR_C, COL_1_TUTOR_C + " = ?" + " AND " + COL_2_TUTOR_C + " = ?" + " AND " + COL_3_TUTOR_C + " = ?", new String[] {TutorID, Subject, CourseNumber});

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

    public Cursor getAllTutorCourses() {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TUTOR_C, null);

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

    public Cursor getTutorCourses(String TutorID) {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TUTOR_C + " WHERE " + COL_1_TUTOR_C + " = ?", new String[]{TutorID});

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    public Cursor getCourseTutors(String Subject, String CourseNumber) {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TUTOR_C + " WHERE " + COL_2_TUTOR_C + " = ?" + " AND " + COL_3_TUTOR_C + " = ?", new String[] {Subject, CourseNumber});

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    public ArrayList<String> getAvailableCourseTutorIDs(String Subject, String CourseNumber) {
        Cursor data;
        ArrayList<String> result;
        String tutorID;

        result = new ArrayList<String>();
        data = this.getCourseTutors(Subject, CourseNumber);
        while(data.moveToNext()){
            tutorID = data.getString(0);
            result.add(tutorID);
        }

        return result;
    }

    /*SECTION FOR TUTOR COURSES END*/

    /*SECTION FOR USERS START*/

    public boolean addData(String studentID, String netID, String password, String name, String email,
                           boolean tutor, boolean tutee) throws Exception {

        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1_USERS, studentID);
            contentValues.put(COL_2_USERS, netID);
            contentValues.put(COL_3_USERS, password);
            contentValues.put(COL_4_USERS, name);
            contentValues.put(COL_5_USERS, email);
            contentValues.put(COL_6_USERS, Boolean.toString(tutor));
            contentValues.put(COL_7_USERS, Boolean.toString(tutee));

            result = db.insert(TABLE_NAME_USERS,null,contentValues);

        }
        catch (Exception e) {
            result = -1;
        }


        if (result == -1){
            Exception e = new Exception("You can't add a student like that");
            throw e;
        }
        return true;
    }

    public String getPassword(String NetID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("net",NetID);
        Cursor data = db.rawQuery("SELECT " +COL_3_USERS+" FROM " + TABLE_NAME_USERS + " WHERE " + COL_2_USERS + " = ?", new String[] {NetID});
        data.moveToFirst();
        Log.d("names length", String.valueOf(data.getCount()));
        if (data.getCount() > 0) return data.getString(0);
        else return "__ERROR__";
    }

    public String getTutor(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " +COL_6_USERS+" FROM " + TABLE_NAME_USERS + " WHERE " + COL_2_USERS + " = ?", new String[] {NetID});
        data.moveToNext();
        return data.getString(0);
    }

    public String getStudent(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " +COL_7_USERS+" FROM " + TABLE_NAME_USERS + " WHERE " + COL_2_USERS + " = ?", new String[] {NetID});
        data.moveToFirst();
        return data.getString(0);
    }

    /*SECTION FOR USERS END*/
}
