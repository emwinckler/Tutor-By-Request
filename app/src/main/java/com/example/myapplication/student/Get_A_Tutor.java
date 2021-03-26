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

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

import com.example.myapplication.databases.CoursesDBHelper;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.databases.MySessionsDBHelper;
import com.example.myapplication.databases.TutorAvailabilityDBHelper;
import com.example.myapplication.databases.TutorCoursesDBHelper;

import com.example.myapplication.models.Course;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_A_Tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_A_Tutor extends Fragment implements AdapterView.OnItemSelectedListener {

    MainActivity ma;
    DatabaseHelper database;

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

    Boolean first = true;
    Boolean test = true;

    Button button_prevWeek;
    Button button_nextWeek;


    Spinner spinner_subject;
    ArrayList<String> available_subject;
    ArrayAdapter<String> adapter_subject;

    Spinner spinner_course;
    ArrayList<String> available_course_Default;
    ArrayAdapter<String> adapter_course_Default;
    ArrayList<String> available_course_ECE;
    ArrayAdapter<String> adapter_course_ECE;
    ArrayList<String> available_course_CS;
    ArrayAdapter<String> adapter_course_CS;
    ArrayList<String> available_course_MATH;
    ArrayAdapter<String> adapter_course_MATH;


    ArrayList<Course> available_courses;
    ArrayList<Course> available_courses_CS;
    ArrayList<Course> available_courses_ECE;
    ArrayList<Course> available_courses_MATH;


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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get__a__tutor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ma = (MainActivity) getActivity();
        database = ma.getDatabase();

        textView_get_a_tutor = (TextView) view.findViewById(R.id.textView_get_a_tutor);
        textView_date        = (TextView) view.findViewById(R.id.textView_date);
        textView_subject     = (TextView) view.findViewById(R.id.textView_subject);
        textView_course      = (TextView) view.findViewById(R.id.textView_course);

        button_prevWeek       = (Button) view.findViewById(R.id.button_prevWeek);
        button_nextWeek       = (Button) view.findViewById(R.id.button_nextWeek);

        //SPINNER INITIALIZATION
        spinner_week          = (Spinner) view.findViewById(R.id.spinner_week);
        spinner_subject       = (Spinner) view.findViewById(R.id.spinner_subject);
        spinner_course        = (Spinner) view.findViewById(R.id.spinner_course);



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

        weekHelper();
        subjectHelper();
        courseHelper();


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner_week) {
            if (first) {
                button_prevWeek.setEnabled(false);
                button_prevWeek.setClickable(false);
                button_prevWeek.setBackgroundColor(Color.GRAY);
                button_nextWeek.setEnabled(false);
                button_nextWeek.setClickable(false);
                button_nextWeek.setBackgroundColor(Color.GRAY);
                first = false;

            } else {
                if(test) {
                    available_week.remove(0);
                    test = false;
                }
                spinner_subject.setEnabled(true);
                spinner_subject.setClickable(true);
                button_prevWeek.setEnabled(true);
                button_prevWeek.setClickable(true);
                button_prevWeek.setBackgroundColor(Color.RED);
                button_nextWeek.setEnabled(true);
                button_nextWeek.setClickable(true);
                button_nextWeek.setBackgroundColor(Color.RED);
            }
        }

        if (parent.getId() == R.id.spinner_subject) {

            switch ( (String) spinner_subject.getSelectedItem() ) {
                case "Select a Subject":
                    spinner_course.setAdapter(adapter_course_Default);
                    System.out.println("adapter_course_Default");
                    spinner_course.setSelection(0);
                    break;
                case "Electrical and Computer Engineering":
                    System.out.println("adapter_course_ECE");
                    available_courses_ECE = database.getAllCoursesBySubject("Electrical and Computer Engineering");
                    available_course_ECE = populateCourses(available_courses_ECE);
                    adapter_course_ECE = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_ECE);
                    spinner_course.setAdapter(adapter_course_ECE);

                    break;
                case "Computer Science":
                    System.out.println("adapter_course_CS");
                    available_courses_CS = database.getAllCoursesBySubject("Computer Science");
                    available_course_CS = populateCourses(available_courses_CS);
                    adapter_course_CS = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_CS);
                    spinner_course.setAdapter(adapter_course_CS);
                    break;
                case "Mathematics":
                    System.out.println("adapter_course_MATH");
                    available_courses_MATH = database.getAllCoursesBySubject("Mathematics");
                    available_course_MATH = populateCourses(available_courses_MATH);
                    adapter_course_MATH = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_MATH);
                    spinner_course.setAdapter(adapter_course_MATH);
                    break;
                default:
                    spinner_course.setAdapter(adapter_course_Default);
                    break;
            }


            if (spinner_subject.getSelectedItemPosition() != 0) { // If a subject is selected
                spinner_course.setEnabled(true);
                spinner_course.setClickable(true);
            } else {
                spinner_course.setEnabled(false);
                spinner_course.setClickable(false);
            }

        }
        if (parent.getId() == R.id.spinner_course) {
            // LOAD TUTOR_AVAILABILITY FROM DATABASE
            // LOAD CALENDAR STUFF
            // loadTutorAvailability()
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
            spinner_subject.setClickable(false);
            spinner_course.setEnabled(false);
            spinner_course.setClickable(false);
        }
        if (parent.getId() == R.id.spinner_subject) {
            spinner_course.setEnabled(false);
        }
        if (parent.getId() == R.id.spinner_course) {
            // DISABLE CALENDAR

        }

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
        spinner_week.setOnItemSelectedListener(this);
        spinner_week.setSelection(0, true);

        button_prevWeek.setEnabled(false);
        button_prevWeek.setClickable(false);
        button_nextWeek.setEnabled(false);
        button_nextWeek.setClickable(false);

    }

    public void subjectHelper(){
        // BEGIN SUBJECT
        available_subject = database.getAllSubjects();
        available_subject.add(0, "Select a Subject");
        adapter_subject = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_subject);
        spinner_subject.setAdapter(adapter_subject);
        spinner_subject.setOnItemSelectedListener(this);
        spinner_subject.setSelection(0, true);
        spinner_subject.setEnabled(false);
        spinner_subject.setClickable(false);
        // END SUBJECT
    }

    public void courseHelper() {

        // BEGIN COURSE
        available_course_Default = new ArrayList<String>();
        adapter_course_Default = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_Default);
        available_course_Default.add("Select a course");
        available_course_Default.add("No courses available");

        spinner_course.setAdapter(adapter_course_Default);

        spinner_course.setOnItemSelectedListener(this);
        spinner_course.setSelection(0, true);
        spinner_course.setEnabled(false);
        spinner_course.setClickable(false);
        // END COURSE
    }

    public ArrayList<String> populateCourses(ArrayList<Course> courses) {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Select a Course");
        for (int i = 0; i < courses.size(); i++) {
            result.add(courses.get(i).toString());
        }

        return result;
    }


    /*
    final int idIndex = cursor.getColumnIndex(COLUMN_ID);
    final int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
    final int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
    final int valueIndex = cursor.getColumnIndex(COLUMN_VALUE);


    public static final String TABLE_NAME = "courses_table";
    public static final String COL_1 = "subject";
    public static final String COL_2 = "course";
    public static final String COL_3 = "course_num";
     */
    private class CoursesDBHelperRow {
        String subject;
        String course;
        String course_num;

        public CoursesDBHelperRow() {
        }

        public String getSubject()  { return this.subject; }
        public String getCourse()   { return this.course; }
        public String getCourseNum() { return this.course_num; }
        public void setSubject(String newSubject) { this.subject = newSubject; }
        public void setCourse(String newCourse) { this.course = newCourse; }
        public void setCourseNum(String newCourseNum) { this.course_num = newCourseNum; }
    }


}


