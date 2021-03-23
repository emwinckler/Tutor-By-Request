package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.MySessionsDBHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class SessionsHelperTest {
    private MySessionsDBHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new MySessionsDBHelper(appContext);
        try {
            Date date = new Date();
            db.addData("String studentID", "String tutorID",  date, "String time",
                   " String subject", 1, "String location",
                    "String description", 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchSessions() {
        try {
            // Add get sessions to db helper
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

    // More Tests
}