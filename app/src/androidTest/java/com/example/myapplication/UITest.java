package com.example.myapplication;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.myapplication.databases.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class UITest {
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void student(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.loginFragment,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("student"));
        onView(withId(R.id.password)).perform(clearText(),typeText("student"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.studentSessions)).check(matches((isDisplayed())));
    }
    @Test
    public void tutor(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.loginFragment,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.password)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.tutorSessions)).check(matches((isDisplayed())));
    }
    @Test
    public void stutor(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.loginFragment,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("stutor"));
        onView(withId(R.id.password)).perform(clearText(),typeText("stutor"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.sAndTSessions)).check(matches((isDisplayed())));
    }

}