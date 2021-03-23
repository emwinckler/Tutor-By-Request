package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.CoursesDBHelper;

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
public class CoursesHelperTest {
    private CoursesDBHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new CoursesDBHelper(appContext);
        try {
            db.addData("String subject", "String course", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchCourses() {
        try {
            Cursor cursor = db.getPassword("String subject");
            String str;
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("course"));
                assertTrue(str.equals("String course"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getSubject() {
        try {
            // Create a get subject method
            Cursor cursor = db.getPassword("String course");
            String str;
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("subject"));
                assertTrue(str.equals("String subject"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
