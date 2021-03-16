package com.example.myapplication.studentandtutor;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.common.Logout;
import com.example.myapplication.common.My_Sessions;
import com.example.myapplication.common.HomePage;
import com.example.myapplication.student.Get_A_Tutor;
import com.example.myapplication.tutor.SetTutorAvailability;

public class StudentAndTutorAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public StudentAndTutorAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomePage homePageFragment = new HomePage();
                return homePageFragment;
            case 1:
                My_Sessions mySessionsFragment = new My_Sessions();
                return mySessionsFragment;
            case 2:
                Get_A_Tutor getATutorFragment = new Get_A_Tutor();
                return getATutorFragment;
            case 3:
                SetTutorAvailability tutorAvailabilityFragment = new SetTutorAvailability();
                return tutorAvailabilityFragment;
            case 4:
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
