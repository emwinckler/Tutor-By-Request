package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.UsersDBHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class UserHelperTest {
    private UsersDBHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new UsersDBHelper(appContext);
        try {
            db.addData("String NetID", "String password", "String name", "String email",
                    false, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchPassword() {
        try {
            Cursor cursor = db.getPassword("String NetID");
            String str;
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("password"));
                assertTrue(str.equals("String password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void searchAddress() {
        try {
            Cursor cursor = db.getAddress("String NetID");
            String str;
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("address"));
                // Not sure what address is supposed to be
                assertTrue(str.equals("String name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void searchTutor() {
        try {
            Cursor cursor = db.getUserType("String NetID");
            String str;
            if (cursor.moveToFirst()) {
                // Not sure where this is being stored
               // str = cursor.getString(cursor.getColumnIndex("name"));
                //assertTrue(str.equals("String name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // More Tests to be added
}