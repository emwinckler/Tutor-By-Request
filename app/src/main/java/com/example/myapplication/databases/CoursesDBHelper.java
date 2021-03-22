package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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




