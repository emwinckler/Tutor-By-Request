package com.example.myapplication;

import android.content.Context;

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
            db.addData("123", "String username", "String password", "String name", "String email",
                    false, false);
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
        String password = db.getPassword("String NetID");
        assertTrue(password.equals("String password"));
    }




    // More Tests to be added
}