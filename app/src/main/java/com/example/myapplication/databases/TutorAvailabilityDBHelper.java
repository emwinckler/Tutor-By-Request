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
    public static final String COL_2  = "Date";
    public static final String COL_3  = "TimeBlock";
    public static final String COL_4  = "00:00-00:30";
    public static final String COL_5  = "00:30-01:00";
    public static final String COL_6  = "01:00-01:30";
    public static final String COL_7  = "01:30-02:00";
    public static final String COL_8  = "02:00-02:30";
    public static final String COL_9  = "02:30-03:00";
    public static final String COL_10 = "03:00-03:30";
    public static final String COL_11 = "03:30-04:00";
    public static final String COL_12 = "04:00-04:30";
    public static final String COL_13 = "04:30-05:00";
    public static final String COL_14 = "05:00-05:30";
    public static final String COL_15 = "05:30-06:00";
    public static final String COL_16 = "06:00-06:30";
    public static final String COL_17 = "06:30-07:00";
    public static final String COL_18 = "07:00-07:30"; // FIRST VALID TIME
    public static final String COL_19 = "07:30-08:00";
    public static final String COL_20 = "08:00-08:30";
    public static final String COL_21 = "08:30-09:00";
    public static final String COL_22 = "09:00-09:30";
    public static final String COL_23 = "09:30-10:00";
    public static final String COL_24 = "10:00-10:30";
    public static final String COL_25 = "10:30-11:00";
    public static final String COL_26 = "11:00-11:30";
    public static final String COL_27 = "11:30-12:00";
    public static final String COL_28 = "12:00-12:30";
    public static final String COL_29 = "12:30-13:00";
    public static final String COL_30 = "12:00-13:30";
    public static final String COL_31 = "13:30-14:00";
    public static final String COL_32 = "13:00-14:30";
    public static final String COL_33 = "14:30-15:00";
    public static final String COL_34 = "15:00-15:30";
    public static final String COL_35 = "15:30-16:00";
    public static final String COL_36 = "16:00-16:30";
    public static final String COL_37 = "16:30-17:00";
    public static final String COL_38 = "17:00-17:30";
    public static final String COL_39 = "17:30-18:00";
    public static final String COL_40 = "18:00-18:30";
    public static final String COL_41 = "18:30-19:00";
    public static final String COL_42 = "19:00-19:30";
    public static final String COL_43 = "19:30-20:00";
    public static final String COL_44 = "20:00-20:30";
    public static final String COL_45 = "20:30-21:00";
    public static final String COL_46 = "21:00-21:30";
    public static final String COL_47 = "21:30-22:00"; // LAST VALID TIME
    public static final String COL_48 = "22:00-22:30";
    public static final String COL_49 = "22:30-23:00";
    public static final String COL_50 = "23:00-23:30";
    public static final String COL_51 = "23:30-00:00";



    public TutorAvailabilityDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                " ("+
                COL_1  +" VARCHAR(30) PRIMARY KEY, "+
                COL_2  +" VARCHAR(30), "+
                COL_3  +" VARCHAR(30), "+
                COL_4  +" VARCHAR(1), "+
                COL_5  +" VARCHAR(1), "+
                COL_6  +" VARCHAR(1), "+
                COL_7  +" VARCHAR(1), "+
                COL_8  +" VARCHAR(1), "+
                COL_9  +" VARCHAR(1), "+
                COL_10 +" VARCHAR(1), "+
                COL_11 +" VARCHAR(1), "+
                COL_12 +" VARCHAR(1), "+
                COL_13 +" VARCHAR(1), "+
                COL_14 +" VARCHAR(1), "+
                COL_15 +" VARCHAR(1), "+
                COL_16 +" VARCHAR(1), "+
                COL_17 +" VARCHAR(1), "+
                COL_18 +" VARCHAR(1), "+
                COL_19 +" VARCHAR(1), "+
                COL_20 +" VARCHAR(1), "+
                COL_21 +" VARCHAR(1), "+
                COL_22 +" VARCHAR(1), "+
                COL_23 +" VARCHAR(1), "+
                COL_24 +" VARCHAR(1), "+
                COL_25 +" VARCHAR(1), "+
                COL_26 +" VARCHAR(1), "+
                COL_27 +" VARCHAR(1), "+
                COL_28 +" VARCHAR(1), "+
                COL_29 +" VARCHAR(1), "+
                COL_30 +" VARCHAR(1), "+
                COL_31 +" VARCHAR(1), "+
                COL_32 +" VARCHAR(1), "+
                COL_33 +" VARCHAR(1), "+
                COL_34 +" VARCHAR(1), "+
                COL_35 +" VARCHAR(1), "+
                COL_36 +" VARCHAR(1), "+
                COL_37 +" VARCHAR(1), "+
                COL_38 +" VARCHAR(1), "+
                COL_39 +" VARCHAR(1), "+
                COL_40 +" VARCHAR(1), "+
                COL_41 +" VARCHAR(1), "+
                COL_42 +" VARCHAR(1), "+
                COL_43 +" VARCHAR(1), "+
                COL_44 +" VARCHAR(1), "+
                COL_45 +" VARCHAR(1), "+
                COL_46 +" VARCHAR(1), "+
                COL_47 +" VARCHAR(1), "+
                COL_48 +" VARCHAR(1), "+
                COL_49 +" VARCHAR(1), "+
                COL_50 +" VARCHAR(1), "+
                COL_51 +" VARCHAR(1), "+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String TutorID, String Date, String TimeBlock, Boolean[] availability,
                           String account_type) throws Exception {
        int i;
        long result;

        try {
            if (availability.length != 48) {
                throw new Exception();
            }
            i = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, TutorID);
            contentValues.put(COL_2, Date);
            contentValues.put(COL_3, TimeBlock);
            contentValues.put(COL_4 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_5 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_6 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_7 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_8 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_9 , "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_10, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_11, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_12, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_13, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_14, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_15, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_16, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_17, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_18, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_19, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_20, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_21, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_22, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_23, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_24, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_25, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_26, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_27, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_28, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_29, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_30, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_31, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_32, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_33, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_34, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_35, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_36, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_37, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_38, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_39, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_40, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_41, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_42, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_43, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_44, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_45, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_46, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_47, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_48, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_49, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_50, "" + (availability[i++] ? 1 : 0) );
            contentValues.put(COL_51, "" + (availability[i++] ? 1 : 0) );


            /*
            // String * COL_N = &COL_4;
            for (int i = 0; i < 48; i++) {
                contentValues.put(*COL_N, "" + (availability[i++] ? 1 : 0));
                COL_N += sizeof(char) * COL_N.length(); ??? iterate through addresses ??? idk
                // sizeof(char) is 1  im just tryna be explicit
                // something like this would be nice
                // we could maybe iterate through memory addresses because the COL variables are
                // created one after the other?
            }
            */
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
        // STRING TIME MUST BE IN SAME FORMAT AS COLUMN ENTRIES
        // LETS MAKE SURE THIS IS PASSED CORRECTLY FROM FRONT END
        // HOWEVER WE CAN ALSO DO SOME REGEX HERE
        // ONLY ALPHA NUMERIC SEPARATED BY A HYPHEN


        ContentValues contentValues = new ContentValues();

        String column = "COL_";
        String [] start_time = Time.split("-")[0].split(":", 2);
        int column_number = ( Integer.parseInt(start_time[0]) * 2 ) + ( (Integer.parseInt(start_time[1]) == 0) ? 1 : 0 ) + 3;

        column = column + column_number;

        contentValues.put(column_number, );

        modifyData(TutorID, column, contentValues);

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



