package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.Date;

public class TutorAvailabilityDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "TutorAvailDBHelper";

    public static final String TABLE_NAME = "tutor_availability_table";
    public static final String COL_1  = "tutor_id";
    public static final String COL_2  = "date";
    public static final String COL_3  = "time";
    public static final String COL_4  = "subject";
    public static final String COL_5  = "course";
    public static final String COL_6  = "course_no";





    public TutorAvailabilityDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                " ("+
                COL_1  +" VARCHAR(30) PRIMARY KEY, "+
                COL_2  +" VARCHAR(30), "+
                COL_3  +" INTEGER(4), "+
                COL_4  +" CHAR(20), "+
                COL_5  +" CHAR(20), "+
                COL_6  +" INTEGER(3) "+

                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String tutor_id, Date date, int time, String subject, String course,
                           int courseNo) throws Exception {
        long result;

        try {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, tutor_id);
            contentValues.put(COL_2, String.valueOf(date));
            contentValues.put(COL_3, time);
            contentValues.put(COL_4, subject);
            contentValues.put(COL_5, course);
            contentValues.put(COL_6, courseNo);

            result = db.insert(TABLE_NAME,null,contentValues);

        }
        catch (Exception e) {
            result = -1;
        }


        if (result == -1){
            Exception e = new Exception("You can't add a tutor availability like that");
            throw e;
        }
        return true;
    }



    public boolean modifyData(String TutorID, String COL, ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv,  "" + COL + " = ? ", new String[] { TutorID } );
        return true; // TODO: what should we return?
    }

    public boolean changeAvailability(String TutorID, String Date, String TimeBlock, String Time, Boolean available) {
       return false;

    }

    public boolean modifyName(String NetID, String newName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, newName);
        return modifyData(NetID, COL_3, contentValues);
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor checkLogin2(String NetID, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {NetID,password};
        Cursor cursor = db.query(TABLE_NAME, new String[]{"NetID", "password"}, "NetID=? and password=?", selectionArgs , null, null, null);
        return cursor;
    }



    public Cursor getPassword(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT password FROM "+TABLE_NAME+"  WHERE " + COL_1 + NetID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAddress(String TutorID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT address FROM "+TABLE_NAME+"  WHERE " + COL_1 + TutorID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPhone(String TutorID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT phoneNumber FROM "+TABLE_NAME+"  WHERE " + COL_1 + TutorID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDate(String TutorID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT userType FROM "+TABLE_NAME + " WHERE "  + COL_1 + TutorID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}



