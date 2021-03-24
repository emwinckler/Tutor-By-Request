package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class MySessionsDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "sessionsBHelper";

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


    public MySessionsDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                "("+
                COL_1 +" VARCHAR(30) PRIMARY KEY, "+
                COL_2 +" VARCHAR(30), "+
                COL_3 +" VARCHAR(30), "+
                COL_4 +" CHAR(30), "+
                COL_5 +" VARCHAR(30), "+
                COL_6 +" INTEGER(3), "+
                COL_7 +" VARCHAR(30), "+
                COL_8 +" TEXT, "+
                COL_9 +" INTEGER(8) "+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String studentID, String tutorID, String date, String time,
                           String subject, int courseNum, String location,
                           String description, int sessionID) throws Exception {

        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, studentID);
            contentValues.put(COL_2, tutorID);
            contentValues.put(COL_3, date);
            contentValues.put(COL_4, time);
            contentValues.put(COL_5, subject);
            contentValues.put(COL_6, courseNum);
            contentValues.put(COL_7, location);
            contentValues.put(COL_8, description);
            contentValues.put(COL_9, sessionID);
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



    public boolean modifyData(String NetID, String COL, ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv,  "" + COL + " = ? ", new String[] { NetID } );
        return true; // TODO: what should we return?
    }

    public boolean modifyName(String NetID, String newName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, newName);
        return modifyData(NetID, COL_3, contentValues);
    }

    public boolean modifyPassword(String NetID, String newPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, newPassword);
        return modifyData(NetID, COL_2, contentValues);
    }

    public boolean modifyAccountTYpe(String NetID, String newPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, newPassword);
        return modifyData(NetID, COL_2, contentValues);
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

    public Cursor getAddress(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT address FROM "+TABLE_NAME+"  WHERE " + COL_1 + NetID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPhone(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT phoneNumber FROM "+TABLE_NAME+"  WHERE " + COL_1 + NetID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getUserType(String NetID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT userType FROM "+TABLE_NAME + " WHERE "  + COL_1 + NetID +"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}




