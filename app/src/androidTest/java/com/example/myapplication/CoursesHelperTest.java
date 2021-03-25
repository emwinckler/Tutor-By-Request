package com.example.myapplication;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.databases.CoursesDBHelper;
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
    private CoursesDBHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new CoursesDBHelper(appContext);
        try {
            db.addData("subject", "course", 1);
            db.addData("math", "intro", 2);
            db.addData("math", "to", 3);
            db.addData("geo", "death", 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void searchSubjects() {
        ArrayList<String> subjects = db.getAllSubjects();
        assertTrue(subjects.get(0).equals("subject"));
        assertTrue(subjects.get(1).equals("math"));
    }
    @Test
    public void searchCourses() {
        ArrayList<Course> courses = db.getAllCoursesBySubject("math");
        assertTrue(courses.get(0).toString().equals("intro 2"));
    }
}
