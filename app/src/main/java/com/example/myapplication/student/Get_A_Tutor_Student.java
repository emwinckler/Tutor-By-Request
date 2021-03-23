package com.example.myapplication.student;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_A_Tutor_Student#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_A_Tutor_Student extends Fragment implements AdapterView.OnItemSelectedListener {

    // UPPER MENU BEGIN
    Button button_home;
    Button button_get_a_tutor;
    Button button_my_sessions;
    Button button_logout;
    // UPPER MENU END

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

    public Get_A_Tutor_Student() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Get_A_Tutor_Student.
     */
    // TODO: Rename and change types and number of parameters
    public static Get_A_Tutor_Student newInstance(String param1, String param2) {
        Get_A_Tutor_Student fragment = new Get_A_Tutor_Student();
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
        View view = inflater.inflate(R.layout.fragment_get__a__tutor__student, container, false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get__a__tutor__student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView_get_a_tutor = (TextView) view.findViewById(R.id.textView_get_a_tutor);
        textView_date        = (TextView) view.findViewById(R.id.textView_date);
        textView_subject     = (TextView) view.findViewById(R.id.textView_subject);
        textView_course      = (TextView) view.findViewById(R.id.textView_course);


        button_prevWeek       = (Button) view.findViewById(R.id.button_prevWeek);
        button_nextWeek       = (Button) view.findViewById(R.id.button_nextWeek);

        // UPPER MENU BEGIN
        button_home        = (Button) view.findViewById(R.id.button_home);
        button_get_a_tutor = (Button) view.findViewById(R.id.button_get_a_tutor);
        button_my_sessions = (Button) view.findViewById(R.id.button_my_sessions);
        button_logout      = (Button) view.findViewById(R.id.button_logout);

        button_home.setBackgroundColor(Color.RED);
        button_get_a_tutor.setBackgroundColor(Color.RED);
        button_my_sessions.setBackgroundColor(Color.RED);
        button_logout.setBackgroundColor(Color.RED);


//        button_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.student.Get_A_Tutor_Student.this)
//                        .navigate(R.id.action_get_a_tutor_student_to_home_student); // refresh student home?
//
//
//            }
//        });

        button_get_a_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                NavHostFragment.findNavController(com.example.myapplication.student.Get_A_Tutor_Student.this)
//                        .navigate(R.id.action_student_home_to_student_get);
                //iteration 0: do nothing
            }
        });

        button_my_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bundle student id and pass it to my sessions fragment for database

//                NavHostFragment.findNavController(com.example.myapplication.student.Get_A_Tutor_Student.this)
//                        .navigate(R.id.action_get_a_tutor_student_to_my_sessions_student);


            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bundle student id and pass it to my sessions fragment for database

//                NavHostFragment.findNavController(com.example.myapplication.student.Get_A_Tutor_Student.this)
//                        .navigate(R.id.action_get_a_tutor_student_to_logout);


            }
        });



    // UPPER MENU END




        //SPINNER INITIALIZATION
        spinner_week          = (Spinner) view.findViewById(R.id.spinner_week);
        spinner_subject       = (Spinner) view.findViewById(R.id.spinner_subject);
        spinner_course        = (Spinner) view.findViewById(R.id.spinner_course);




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

        adapter_week = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, available_week);
        adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_week.setAdapter(adapter_week);
        spinner_week.setEnabled(true);
        spinner_week.setOnItemSelectedListener(this);



        // BEGIN WEEK BUTTONS
        button_prevWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_week.getSelectedItem() != null) {
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
                if (spinner_week.getSelectedItem() != null) {
                    int curr_week_pos = spinner_week.getSelectedItemPosition();
                    if (curr_week_pos < available_week.size() - 1) {
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
        available_subject.add("ECE: Electrical & Computer Engineering");
        available_subject.add("CS: Computer Sciences");
        available_subject.add("MATH: Mathematics");
        spinner_subject.setAdapter(adapter_subject);
        spinner_subject.setOnItemSelectedListener(this);
        // END SUBJECT

        // BEGIN COURSE
        available_course = new ArrayList<String>();
        adapter_course = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course);
        available_course.add("ECE 552");
        available_course.add("CS 506");
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

            spinner_subject.setEnabled(true);
        }
        if (parent.getId() == R.id.spinner_subject) {
            // LOAD COURSES FROM DATABASE

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