package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.models.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class CoursesHelperTest {
    private DatabaseHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new DatabaseHelper(appContext);
//        try {
//            db.addDataCourses("subject", "course", 1);
//            db.addDataCourses("math", "intro", 2);
//            db.addDataCourses("math", "to", 3);
//            db.addDataCourses("geo", "death", 4);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchSubjects() {
        ArrayList<String> subjects = db.getAllSubjects();
        assertTrue(subjects.get(0).equals("Computer Science"));
        assertTrue(subjects.get(1).equals("Electrical and Computer Engineering"));
    }
    @Test
    public void searchCourses() {
        ArrayList<Course> courses = db.getAllCoursesBySubject("Computer Science");
        assertTrue(courses.get(0).toString().equals("Programming 1 200"));
    }
}
