package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TutorAvailabilityDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "StudentDBHelper";

    public static final String TABLE_NAME = "tutor_availability_table";
    public static final String COL_1  = "TutorID";
    public static final String COL_2  = "Date"; // FORMAT: "MM/DD/YYYY"
    public static final String COL_3  = "StartTime"; // FORMAT: "HH:MM"


    public TutorAvailabilityDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                " ("+
                COL_1  +" VARCHAR(30) PRIMARY KEY, "+
                COL_2  +" VARCHAR(10), "+
                COL_3  +" VARCHAR(5), "+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addAvailability(String TutorID, String Date, String StartTime) {
        long result;

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, TutorID);
            contentValues.put(COL_2, Date);
            contentValues.put(COL_3, TimeBlock);

            result = db.insert(TABLE_NAME,null,contentValues);

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
            result = db.delete(TABLE_NAME, COL_1 + " = ?" + " AND " + COL_2 + " = ?" + " AND " + " = ?", new String[] {TutorID, Date, StartTime});

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }


    public Cursor getAllTutorAvailability() {
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

    public Cursor getTutorAvailability(String TutorID) {
        Cursor result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ?", new String[] {TutorID});

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
            result = db.db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ?" + " AND " + COL_2 + " = ?", new String[] {TutorID, Date};

        }
        catch (Exception e) {
            return null;
        }

        return result;
    }


}



