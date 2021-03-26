package com.example.myapplication.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.tutor.tutorSetDateAndTime;

public class Sessions extends BaseAdapter {
    private Context context;
    private String[][] listSessions;

    public Sessions(Context c, String[][] listSessions){
        this.context = c;
        this.listSessions = listSessions;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            list = new View(context);
            list = inflater.inflate(R.layout.session, null);
            TextView date = (TextView) list.findViewById(R.id.sessionDate);
            TextView course = (TextView) list.findViewById(R.id.sessionCourse);
            TextView tutorName = (TextView) list.findViewById(R.id.sessionTutor);
            date.setText(listSessions[position][0]);

        }else{

            list = (View) convertView;
            /*
            TextView textView = (TextView) list.findViewById(R.id.grid_text);
            if(tutorSetDateAndTime.selectedSlot2[position /8][position %8] == 1){
                textView.setBackgroundResource(R.drawable.select);
            }else if(tutorSetDateAndTime.selectedSlot2[position /8][position %8] == 2){
                textView.setBackgroundResource(R.drawable.selected);
            }else if(tutorSetDateAndTime.selectedSlot2[position /8][position %8] == 3) {
                textView.setBackgroundResource(R.drawable.cancellable);
            }else if(tutorSetDateAndTime.selectedSlot2[position /8][position %8] == 4){
                textView.setBackgroundResource(R.drawable.booked);
            }else{
                textView.setBackgroundResource(R.drawable.border);
            }*/
        }
        return list;
    }
}
