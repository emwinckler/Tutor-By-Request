package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TutorCoursesDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "TutorCourseDBHelper";

    public static final String TABLE_NAME = "tutor_courses_table";
    public static final String COL_1  = "TutorID";
    public static final String COL_2  = "Subject";
    public static final String COL_3  = "CourseNumber";
    // public static final String COL_4 = "ComfortLevel"; // stretch goal


    public TutorCoursesDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                " ("+
                COL_1  +" VARCHAR(30) PRIMARY KEY, "+
                COL_2  +" VARCHAR(5), "+
                COL_3  +" VARCHAR(5) "+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addTutorCourse(String TutorID, String Subject, String CourseNumber) {
        long result;

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, TutorID);
            contentValues.put(COL_2, Subject);
            contentValues.put(COL_3, CourseNumber);

            result = db.insert(TABLE_NAME,null,contentValues);

        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

//    public Integer deleteTutorCourse(String TutorID, String Subject, String CourseNumber) {
//        Integer result;
//
//        try {
//            SQLiteDatabase db = this.getWritableDatabase();
//            result = db.delete(TABLE_NAME, COL_1 + " = ?" + " AND " + COL_2 + " = ?" + " AND " + COL_3 + " = ?", new String[] {TutorID, Date, StartTime});
//
//        }
//        catch (Exception e) {
//            return null;
//        }
//
//        return result;
//    }


    public Cursor getAllTutorCourses() {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }

//    public Cursor getTutorCourses(String TutorID) {
//        Cursor result;
//
//        try {
//            SQLiteDatabase db = this.getWritableDatabase();
//            result = db.db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ?", new String[]{TutorID});
//
//        } catch (Exception e) {
//            return null;
//        }
//
//        return result;
//    }

//    public Cursor getCourseTutors(String Subject, String CourseNumber) {
//        Cursor result;
//
//        try {
//            SQLiteDatabase db = this.getWritableDatabase();
//            result = db.db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ?" + " AND " + COL_3 + " = ?", new String[] {Subject, CourseNumber});
//
//        } catch (Exception e) {
//            return null;
//        }
//
//        return result;
//    }


}



