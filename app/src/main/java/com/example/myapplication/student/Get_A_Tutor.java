package com.example.myapplication.student;

import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_A_Tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_A_Tutor extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView textView_get_a_tutor;
    TextView textView_date;
    TextView textView_subject;
    TextView textView_course;

    Spinner spinner_week;
    ArrayList<String> available_week;
    ArrayAdapter<String> adapter_week;

    Button button_prevWeek;
    Button button_nextWeek;


    Spinner spinner_subject;
    ArrayList<String> available_subject;
    ArrayAdapter<String> adapter_subject;

    Spinner spinner_course;
    ArrayList<String> available_course;
    ArrayAdapter<String> adapter_course;

    ListView listView_session;
    ArrayList<String> available_session;
    ArrayAdapter<String> adapter_session;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Get_A_Tutor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Get_A_Tutor.
     */
    // TODO: Rename and change types and number of parameters
    public static Get_A_Tutor newInstance(String param1, String param2) {
        Get_A_Tutor fragment = new Get_A_Tutor();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get__a__tutor, container, false);


        textView_get_a_tutor = (TextView) view.findViewById(R.id.textView_get_a_tutor);
        textView_date        = (TextView) view.findViewById(R.id.textView_date);
        textView_subject     = (TextView) view.findViewById(R.id.textView_subject);
        textView_course      = (TextView) view.findViewById(R.id.textView_course);

        spinner_week          = (Spinner) view.findViewById(R.id.spinner_week);

        button_prevWeek       = (Button) view.findViewById(R.id.button_prevWeek);
        button_nextWeek       = (Button) view.findViewById(R.id.button_nextWeek);

        spinner_subject       = (Spinner) view.findViewById(R.id.spinner_subject);
        spinner_course        = (Spinner) view.findViewById(R.id.spinner_course);

        //MainActivity ma = (MainActivity) this.getActivity();
        //dbHelper=ma.getMenuDB();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get__a__tutor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // BEGIN WEEK
        available_week = new ArrayList<String>();
        // TODO: NEED A GOOD WAY TO LOAD THESE FOR ONLY VALID WEEKS EACH SEMESTER
        // THIS WILL DO FOR NOW THO
        // ADMIN ACTIVITY TO SET WEEKS?
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

        String[] array_available_week = new String[available_week.size()];
        array_available_week = available_week.toArray(array_available_week);

        adapter_week = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, array_available_week);
        adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_week.setAdapter(adapter_week);
        spinner_week.setEnabled(true);
        spinner_week.setOnItemSelectedListener(this);
        spinner_week.setSelection(0, true);


        System.out.println("available_week[0] = " + available_week.get(0));
        System.out.println("MADE IT TO THE SET SELECTION STUFF");


        // BEGIN WEEK BUTTONS
        button_prevWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_week.isSelected()) {
                    int curr_week_pos = spinner_week.getSelectedItemPosition();
                    if (curr_week_pos > 0) {
                        curr_week_pos--;
                        spinner_week.setSelection(curr_week_pos, true);
                    }
                }
            }
        });

        button_nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_week.isSelected()) {
                    int curr_week_pos = spinner_week.getSelectedItemPosition();
                    if (curr_week_pos < available_week.size()) {
                        curr_week_pos++;
                        spinner_week.setSelection(curr_week_pos, true);
                    }
                }
            }
        });
        // END WEEK BUTTONS
        // END WEEK


        // BEGIN SUBJECT
        available_subject = new ArrayList<String>();
        adapter_subject = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_subject);
        spinner_subject.setAdapter(adapter_subject);
        spinner_subject.setOnItemSelectedListener(this);
        // END SUBJECT

        // BEGIN COURSE
        available_course = new ArrayList<String>();
        adapter_course = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course);
        spinner_course.setAdapter(adapter_course);
        spinner_course.setOnItemSelectedListener(this);
        // END COURSE

        // BEGIN CALENDAR
        // FOR FIRST ITERATION, LET'S JUST DO A SIMPLE LISTVIEW
        // TODO: CHANGE TO A NICE GRID OF BUTTONS
        // I THINK A 2D GRID OF BUTTONS WOULD BE EASY WAY TO IMPLEMENT THE CALENDAR
        // CAN DISABLE BUTTONS, SET THEIR VISIBILITY, AND CHANGE THEIR COLOR PROGRAMMATICALLY
        // CAN USE SET ON CLICK LISTENER TO LAUNCH CONFIRMATION WINDOW FRAGMENT
//        available_session = new ArrayList<String>();
//        adapter_session = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_session);
//        listView_session.setAdapter(adapter_session);
        // END CALENDAR



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_week) {
            button_prevWeek.setEnabled(true);
            button_prevWeek.setBackgroundColor(Color.RED);
            button_nextWeek.setEnabled(true);
            button_nextWeek.setBackgroundColor(Color.RED);

            // LOAD SUBJECTS FROM DATABASE
            available_subject.add("ECE: Electrical & Computer Engineering");
            available_subject.add("CS: Computer Sciences");
            available_subject.add("MATH: Mathematics");
            spinner_subject.setEnabled(true);
        }
        if (parent.getId() == R.id.spinner_subject) {
            // LOAD COURSES FROM DATABASE
            available_course.add("ECE 552");
            spinner_course.setEnabled(true);
        }
        if (parent.getId() == R.id.spinner_course) {
            // LOAD TUTOR_AVAILABILITY FROM DATABASE
            // LOAD CALENDAR STUFF
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_week) {
            button_prevWeek.setEnabled(false);
            button_prevWeek.setBackgroundColor(Color.GRAY);
            button_nextWeek.setEnabled(false);
            button_nextWeek.setBackgroundColor(Color.GRAY);

            spinner_subject.setEnabled(false);
            spinner_course.setEnabled(false);
        }
        if (parent.getId() == R.id.spinner_subject) {
            spinner_course.setEnabled(false);
        }
        if (parent.getId() == R.id.spinner_course) {
            // DISABLE CALENDAR

        }

    }
}