package com.example.myapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "usersDBHelper";

    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "net_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "name";
    public static final String COL_5 = "email";
    public static final String COL_6 = "tutor";
    public static final String COL_7 = "tutee";




    public UsersDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+
                "("+
                COL_1 +" VARCHAR(30) PRIMARY KEY, "+
                COL_2 +" INTEGER(10), "+
                COL_3 +" VARCHAR(30), "+
                COL_4 +" VARCHAR(30), "+
                COL_5 +" VARCHAR(30), "+
                COL_6 +" CHAR(5), "+
                COL_7 +" CHAR(5) "+
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(int NetID, String username, String password, String name, String email,
            boolean tutor, boolean tutee) throws Exception {

        long result;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, NetID);
            contentValues.put(COL_2, username);
            contentValues.put(COL_3, password);
            contentValues.put(COL_4, name);
            contentValues.put(COL_5, email);
            contentValues.put(COL_6, Boolean.toString(tutor));
            contentValues.put(COL_7, Boolean.toString(tutee));

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



