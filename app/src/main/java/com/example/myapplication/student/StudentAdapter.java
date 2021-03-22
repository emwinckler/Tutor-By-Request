package com.example.myapplication.student;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.common.Logout;
import com.example.myapplication.common.My_Sessions;

public class StudentAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    public StudentAdapter(Context c, FragmentManager fm, int totalTabs) {
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
                Get_A_Tutor getATutorFragment = new Get_A_Tutor();
                return getATutorFragment;
            case 2:
                My_Sessions mySessionsFragment = new My_Sessions();
                return mySessionsFragment;
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
