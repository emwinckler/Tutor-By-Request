package com.example.myapplication.tutor;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.model.LoggedInUser;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.databases.TutorAvailabilityDBHelper;
import com.example.myapplication.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tutorSetDateAndTime#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tutorSetDateAndTime extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper dbHelper;
    // Required empty public constructor
    private User user;
    private Spinner spinner_week;
    private ArrayList<String> available_week;
    private ArrayAdapter<String> adapter_week;
    private GridView calendar;
    static int[][] selectedSlot2 = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    String[] slotText = {
            "07:00am","","","","","","","",
            "07:30am","","","","","","","",
            "08:00am","","","","","","","",
            "08:30am","","","","","","","",
            "09:00am","","","","","","","",
            "09:30am","","","","","","","",
            "10:00am","","","","","","","",
            "10:30am","","","","","","","",
            "11:00am","","","","","","","",
            "11:30am","","","","","","","",
            "12:00pm","","","","","","","",
            "12:30pm","","","","","","","",
            "01:00pm","","","","","","","",
            "01:30pm","","","","","","","",
            "02:00pm","","","","","","","",
            "02:30pm","","","","","","","",
            "03:00pm","","","","","","","",
            "03:30pm","","","","","","","",
            "04:00pm","","","","","","","",
            "04:30pm","","","","","","","",
            "05:00pm","","","","","","","",
            "05:30pm","","","","","","","",
            "06:00pm","","","","","","","",
            "06:30pm","","","","","","","",
            "07:00pm","","","","","","","",
            "07:30pm","","","","","","",""
    } ;


    public tutorSetDateAndTime() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tutorSetDateAndTime.
     */
    // TODO: Rename and change types and number of parameters
    public static tutorSetDateAndTime newInstance(String param1, String param2) {
        tutorSetDateAndTime fragment = new tutorSetDateAndTime();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        user = (User) getActivity().getIntent().getSerializableExtra("user");
//        Context c = getContext();
//        Bundle bundle = c.getArguments();
//        User obj = (User) bundle.getSerializable("user");

//        user = (User) getArguments().getSerializable("user");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity ma = (MainActivity) getActivity();
        dbHelper = ma.getDatabase();
        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
        return inflater.inflate(R.layout.fragment_tutor_set_date_and_time, container, false);




    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar adapter = new Calendar(getContext(),slotText);

        Button confirm = (Button) view.findViewById(R.id.confirm_availability);

//        Context c = getContext();
//        Bundle bundle = c.getArguments();
//        User obj = (User) bundle.getSerializable("user");



        calendar=(GridView) view.findViewById(R.id.calendar);
        calendar.setAdapter(adapter);
        calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "You Clicked at " +slotText[+ position], Toast.LENGTH_SHORT).show();
                int row = (position / 8);
                int col = position % 8;
                if(selectedSlot2[row][col]==1) {
                    selectedSlot2[row][col] = 0;
                }else{
                    selectedSlot2[row][col] = 1;
                }
                adapter.notifyDataSetChanged();


            }
        });


        spinner_week          = (Spinner) view.findViewById(R.id.selectWeek);
        weekHelper();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 8; i++){
                    for(int j= 0; j < 26; j++){
                        if(selectedSlot2[i][j]==1){
                            String time = slotText[i*8];
                            String date = spinner_week.getSelectedItem().toString();
                            String tutorID = user.getStudentID();
                            dbHelper.addAvailability(tutorID,date,time);
                        }
                    }
                }
            }
        });

    }
    public void weekHelper() {
        // BEGIN WEEK
        available_week = new ArrayList<String>();
        // TODO: NEED A GOOD WAY TO LOAD THESE FOR ONLY VALID WEEKS EACH SEMESTER
        // THIS WILL DO FOR NOW THO
        // ADMIN ACTIVITY TO SET WEEKS?
        available_week.add("Select a week");
        available_week.add("01/24 - 01/30");
        available_week.add("01/31 - 02/06");
        available_week.add("02/07 - 02/13");
        available_week.add("02/14 - 02/20");
        available_week.add("02/21 - 02/27");
        available_week.add("02/28 - 03/06");
        available_week.add("03/07 - 03/13");
        available_week.add("03/14 - 03/20");
        available_week.add("03/21 - 03/27");
        available_week.add("03/28 - 04/03");
        available_week.add("04/04 - 04/10");
        available_week.add("04/11 - 04/17");
        available_week.add("04/18 - 04/24");
        available_week.add("04/25 - 05/01");
        available_week.add("05/02 - 05/08");

        adapter_week = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, available_week);
        adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_week.setAdapter(adapter_week);
        spinner_week.setEnabled(true);


    }







}