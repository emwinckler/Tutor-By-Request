package com.example.myapplication.student;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.Get_A_Tutor.Get_A_Tutor;
import com.example.myapplication.Logout;
import com.example.myapplication.MySessions.My_Sessions;

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
                Get_A_Tutor getATutorFragment = new Get_A_Tutor();
                return getATutorFragment;
            case 1:
                My_Sessions mySessionsFragment = new My_Sessions();
                return mySessionsFragment;
            case 2:
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
