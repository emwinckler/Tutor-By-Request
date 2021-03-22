package com.example.myapplication.tutor;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.common.Logout;
import com.example.myapplication.common.My_Sessions;

public class TutorAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public TutorAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                HomePage homePageFragment = new HomePage();
//                return homePageFragment;
                return null;
            case 1:
                My_Sessions mySessionsFragment = new My_Sessions();
                return mySessionsFragment;
            case 2:
                SetTutorAvailability tutorAvailabilityFragment = new SetTutorAvailability();
                return tutorAvailabilityFragment;
            case 3:
                Logout logoutFragment = new Logout();
                return logoutFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}