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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import com.example.myapplication.databases.CoursesDBHelper;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.databases.MySessionsDBHelper;
import com.example.myapplication.databases.TutorAvailabilityDBHelper;
import com.example.myapplication.databases.TutorCoursesDBHelper;

import com.example.myapplication.models.Course;
import com.example.myapplication.models.User;
import com.example.myapplication.models.TutorAvailablity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_A_Tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_A_Tutor extends Fragment implements AdapterView.OnItemSelectedListener {
    String YEAR = "2021";
    static int sessionID = 0;
    MainActivity ma;
    DatabaseHelper database;

    User user;
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
    private static final String ARG_PARAM1 = "userID";
    private static final String ARG_PARAM2 = "userID";

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

        user = (User) this.getArguments().getSerializable("user");

        Bundle userData = new Bundle();
        userData.putSerializable("user", user);


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

        //LISTVIEW INITIAZLIZATION
        listView_session = (ListView) view.findViewById(R.id.listView_timeblock);



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
        listViewHelper();


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
                    // available_week.remove(0);
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


                String week;
                String subject;
                String course;

                week = (String) spinner_week.getSelectedItem();
                subject = (String) spinner_subject.getSelectedItem();
                course = (String) spinner_course.getSelectedItem();
                course = course.substring(course.lastIndexOf(" ")+1);

                available_session = populateAvailableTutorSessions(week, subject, course);
                adapter_session = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_session );
                listView_session.setAdapter(adapter_session);
                listViewHelper();


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
            String week;
            String subject;
            String course;

            week = (String) spinner_week.getSelectedItem();
            subject = (String) spinner_subject.getSelectedItem();
            course = (String) spinner_course.getSelectedItem();
            course = course.substring(course.lastIndexOf(" ")+1);



            available_session = populateAvailableTutorSessions(week, subject, course);
            adapter_session = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_session );
            listView_session.setAdapter(adapter_session);

        }
        if (parent.getId() == R.id.listView_timeblock) {

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

    private void listViewHelper() {
        listView_session.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set up my session
                String studentID;
                String tutorID;
                String date;
                String time;
                String subject;
                int courseNo = 0;
                String location;
                String description;

                String week;
                String course;

                week = (String) spinner_week.getSelectedItem();
                course = (String) spinner_course.getSelectedItem();
                course = course.substring(course.lastIndexOf(" ")+1);



                String courseNum;
                String listviewsession;

                subject = (String) spinner_subject.getSelectedItem();
                courseNum = (String) spinner_course.getSelectedItem();
                courseNum = courseNum.substring(courseNum.lastIndexOf(" ")+1);
                available_session = new ArrayList<String>();
                try {
                    courseNo = Integer.parseInt(courseNum);


                    listviewsession = (String) parent.getItemAtPosition(position);
                    String [] availability;

                    availability = listviewsession.split(" ");
                    tutorID = availability[0];
                    date = availability[1];
                    time = availability[2];
                    boolean currentavailability = availability[3].equals("true"); // just for testing the toggling

                    database.modifySessionIsAvailable(tutorID, date, time, !currentavailability);

                    available_session = populateAvailableTutorSessions(week, subject, course);


                    database.addDataSession(
                            user.getNetID(), tutorID, date, time,
                            subject, courseNo, "Virtual",
                            "description", sessionID++);




                } catch (Exception e) {
                    System.out.println("ERROR CLICKING ON TIMEBLOCK. COURSE NUMBER DIDNT WORK OUT");
                }

                adapter_session = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_session );
                listView_session.setAdapter(adapter_session);

            }
        });
    }

    private void weekHelper() {
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

    private void subjectHelper(){
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

    private void courseHelper() {

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

    private ArrayList<String> populateCourses(ArrayList<Course> courses) {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Select a Course");
        for (int i = 0; i < courses.size(); i++) {
            result.add(courses.get(i).toString());
        }

        return result;
    }

    private ArrayList<String> loadTutorAvailability(String date, String subject, String course) {
        // first load tutors who can tutor that subject and course
        ArrayList<String> result;

        ArrayList<String> tutorsIDs_selectedCourse;
        ArrayList<TutorAvailablity> tutorAvailabilityOnDate;


        result = new ArrayList<String>();
        tutorsIDs_selectedCourse = database.getAvailableCourseTutorIDs(subject, course);

        for (int i = 0; i < tutorsIDs_selectedCourse.size(); i++) {
            String tutorID = tutorsIDs_selectedCourse.get(i);
            tutorAvailabilityOnDate = database.getTutorAvailabilityOnDate(tutorID, date);
            for (int j = 0; j < tutorAvailabilityOnDate.size(); j++) {
                if (!tutorAvailabilityOnDate.get(j).isBooked()) {
                    result.add(tutorAvailabilityOnDate.get(j).toString());
                }
            }
        }

        // then from these tutors, load their time and availability
        return result;
    }

    private ArrayList<String> populateAvailableTutorSessions(String week, String subject, String course) {
        ArrayList<String> result;
        ArrayList<String> temp;

        String date_weekStart;
        String date;

        System.out.println("Week = " + week);
        System.out.println("Subject = " + subject);
        System.out.println("Course = " + course);


        result = new ArrayList<String>();
        try {
            date_weekStart = week.split(" ", 2)[0];
            System.out.println(date_weekStart);
            String date_weekStart_MM = date_weekStart.split("/", 2)[0];
            System.out.println(date_weekStart_MM);
            String date_weekStart_DD = date_weekStart.split("/", 2)[1];
            System.out.println(date_weekStart_DD);
            int date_weekStart_MM_int = 0;
            int date_weekStart_DD_int = 0;
            date_weekStart_MM_int = Integer.parseInt(date_weekStart_MM);
            date_weekStart_DD_int = Integer.parseInt(date_weekStart_DD);
            System.out.println("date_weekStart_MM_int = " + date_weekStart_MM_int);
            System.out.println("date_weekStart_DD_int = " + date_weekStart_DD_int);




            if (date_weekStart_DD_int < 23) { // no date month overflow conflict possible. february 22-28 worst case
                for (int i = 0; i < 7; i++) {
                    date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                    System.out.println("Current Date = " + date);
                    temp = loadTutorAvailability(date, subject, course);
                    for (int j = 0; j < temp.size(); j++) {
                        result.add(temp.get(j));
                    }
                    date_weekStart_DD_int++;
                }
            }
            else { // check month to see where conflict should change months
                // TODO: iteration 1 lets not worry about the new year case. there's no school anyway
                if (date_weekStart_MM_int == 2) { // february case
                    int i = 0;
                    while (date_weekStart_DD_int < 29) { // TODO: iteration 1 so we're just gonna go ahead and assume leap year doesn't exist
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }

                    date_weekStart_MM_int++;
                    date_weekStart_DD_int = 1;

                    while ( i < 7 ) {
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }
                }
                else if (date_weekStart_MM_int % 2 == 1) { // 31 day months case
                    int i = 0;
                    while (date_weekStart_DD_int < 32) { // TODO: iteration 1 so we're just gonna go ahead and assume leap year doesn't exist
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }

                    date_weekStart_MM_int++;
                    date_weekStart_DD_int = 1;

                    while ( i < 7 ) {
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }
                }
                else { // 30 day month case
                    int i = 0;
                    while (date_weekStart_DD_int < 31) { // TODO: iteration 1 so we're just gonna go ahead and assume leap year doesn't exist
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }

                    date_weekStart_MM_int++;
                    date_weekStart_DD_int = 1;

                    while ( i < 7 ) {
                        date = "" + ( date_weekStart_MM_int < 10 ? "0" + date_weekStart_MM_int : date_weekStart_MM_int ) + "/" + ( date_weekStart_DD_int < 10 ? "0" + date_weekStart_DD_int : date_weekStart_DD_int ) + "/" + YEAR;
                        System.out.println("Current Date = " + date);
                        temp = loadTutorAvailability(date, subject, course);
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j));
                        }
                        date_weekStart_DD_int++;
                        i++;
                    }
                }
            }


        }
        catch (NumberFormatException e) {
            System.out.println("TUTOR DATE LOAD WEIRD FOR LISTVIEW. this is because of start up the select a week entry isn't formatted like the other dates");
        }
        catch (Exception e) {
            System.out.println("TUTOR DATE LOAD WEIRD FOR LISTVIEW. this is because of start up the select a week entry isn't formatted like the other dates");
        }


        return result;
    }

}


