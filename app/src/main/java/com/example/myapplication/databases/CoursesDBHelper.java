package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.models.Course;

import java.util.ArrayList;

public class CoursesDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "coursesDBHelper";

    public static final String TABLE_NAME = "courses_table";
    public static final String COL_1 = "subject";
    public static final String COL_2 = "course";
    public static final String COL_3 = "course_num";



    public CoursesDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                "("+
                COL_1 +" CHAR(30), "+
                COL_2 +" CHAR(30), "+
                COL_3 +" INTEGER(3)," +
                "PRIMARY KEY (subject, course_num)"+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String subject, String course, int courseNumber) throws Exception {

        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, subject);
            contentValues.put(COL_2, course);
            contentValues.put(COL_3, courseNumber);

            result = db.insert(TABLE_NAME,null,contentValues);

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

    public ArrayList<Course> getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
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
        data = db.rawQuery("SELECT DISTINCT " + COL_1 + " FROM " + TABLE_NAME, null);

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

        data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ?", new String[]{subjectQuery});

        ArrayList<Course> courseList = new ArrayList<Course>();
        while(data.moveToNext()){
            String subject = data.getString(0);
            String course = data.getString(1);
            int courseNo = Integer.parseInt(data.getString(2));
            courseList.add(new Course(subject,course,courseNo));
        }
        return courseList;
    }

}
