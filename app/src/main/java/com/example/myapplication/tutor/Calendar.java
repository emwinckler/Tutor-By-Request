package com.example.myapplication.tutor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

public class Calendar extends BaseAdapter{
    private Context mContext;
    private final String[] slot;

    public Calendar(Context c, String[] slot) {
        mContext = c;
        this.slot = slot;
    }

    @Override
    public int getCount() {
        return slot.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);




        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.calendar_slot, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(slot[position]);
        } else {
            grid = (View) convertView;
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
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
            }
        }
        return grid;
    }
}
