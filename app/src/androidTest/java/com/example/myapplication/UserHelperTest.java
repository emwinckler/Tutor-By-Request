package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.DatabaseHelper;

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
    private DatabaseHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new DatabaseHelper(appContext);
//        try {
//            db.addData("123", "String username", "String password", "String name", "String email",
//                    false, true);
//            db.addData("1234", "username", "password", "name", "email",
//                    false, true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchPassword() {
        String password = db.getPassword("student");
        Log.d("Password",db.getPassword("student"));
        assertTrue(password.equals("student"));
    }
    @Test
    public void checkTutor() {
        String tutor = db.getTutor("student");
        assertTrue(tutor.equals("false"));
    }
    @Test
    public void checkTutee() {
        String tutee = db.getStudent("student");
        assertTrue(tutee.equals("true"));
    }
    @Test
    public void passwordChange() {
        db.modifyPassword("tutor1", "assa");
        String password = db.getPassword("tutor1");
        assertTrue(password.equals("assa"));
    }




    // More Tests to be added
}