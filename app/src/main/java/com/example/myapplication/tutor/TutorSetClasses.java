package com.example.myapplication.tutor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databases.CoursesDBHelper;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.databases.TutorCoursesDBHelper;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TutorSetClasses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorSetClasses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "ListViewExample";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView selectListView;
    private ListView myClasses;
    private MainActivity ma;
    private DatabaseHelper dbHelper;
    private User user;


    public TutorSetClasses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorSetClasses.
     */
    // TODO: Rename and change types and number of parameters
    public static TutorSetClasses newInstance(String param1, String param2) {
        TutorSetClasses fragment = new TutorSetClasses();
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
        // Inflate the layout for this fragment
        MainActivity ma = (MainActivity) getActivity();
        dbHelper = ma.getDatabase();
        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
        return inflater.inflate(R.layout.fragment_tutor_set_classes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectListView = (ListView) view.findViewById(R.id.listView_availableClasses);
        myClasses = (ListView) view.findViewById(R.id.listView_myClasses);

        Button classButton = view.findViewById(R.id.selectClasses);
        ma = (MainActivity) getActivity();

//        TutorCoursesDBHelper tutorCoursesDBHelper = ma.getTutorCourseDB();
        ArrayList<Course> courses = dbHelper.getDataCourses();
        ArrayList<Course> myCourses = dbHelper.getTutorCourses(user.getStudentID());

        ArrayList<String> courseString = new ArrayList<String>();
        ArrayList<String> myCourseString = new ArrayList<String>();

        for(Course c : courses) {
            courseString.add(c.toStringSubjectCourseNo());
        }
        for(Course c : myCourses){
            myCourseString.add(c.toStringSubjectCourseNo());
            courseString.remove(c.toStringSubjectCourseNo());
        }
        this.selectListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        /*int numItems = listView.getAdapter().getCount();
        for(int i=0; i<numItems; i++){
            String tempCourse = listView.getItemAtPosition(i).toString();
            for(Course c : myCourses){
                int subLength = c.getSubject().length();
                if(tempCourse.substring(0,subLength).equals(c.getSubject()) && tempCourse.substring(subLength+1).equals(c.getCourseNo())){
                   CheckedTextView check = (CheckedTextView) listView.getItemAtPosition(i);
                    check.setChecked(!check.isChecked());
                }
            }
        }*/
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listView.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "You Clicked at " +item +"asd", Toast.LENGTH_SHORT).show();
            }
        });

         */
        ArrayAdapter<String> courseArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,courseString);
        ArrayAdapter<String> myClassesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,myCourseString);
        this.myClasses.setAdapter(myClassesAdapter);
        this.selectListView.setAdapter(courseArrayAdapter);

        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = selectListView.getCheckedItemPositions();
                StringBuilder sb= new StringBuilder();

                try {

                    for (int i = 0; i < sp.size(); i++) {
                        if (sp.valueAt(i) == true) {
                            String course = (String) selectListView.getItemAtPosition(i);
                            String subject = course.split(" ",2)[0];
                            int courseNo = Integer.parseInt(course.split(" ",2)[1].trim());

                            dbHelper.addTutorCourse(user.getStudentID(), subject, courseNo);
                            myClassesAdapter.notifyDataSetChanged();
                            courseArrayAdapter.notifyDataSetChanged();
                        }
                    }
                    Toast.makeText(ma, "Course selection saved", Toast.LENGTH_LONG).show();

                }catch(Exception e){
                    Toast.makeText(ma, "Course selection not saved! Try again", Toast.LENGTH_LONG).show();
                }





                SparseBooleanArray sp2 = myClasses.getCheckedItemPositions();
                try {

                    for (int i = 0; i < sp.size(); i++) {
                        if (sp.valueAt(i) == true) {
                            String course = (String) selectListView.getItemAtPosition(i);
                            String subject = course.split(" ",2)[0];
                            int courseNo = Integer.parseInt(course.split(" ",2)[1].trim());

                            dbHelper.deleteTutorCourse(user.getStudentID(), subject, courseNo);
                            myClassesAdapter.notifyDataSetChanged();
                            courseArrayAdapter.notifyDataSetChanged();
                        }
                    }
                    Toast.makeText(ma, "Course selection saved", Toast.LENGTH_LONG).show();

                }catch(Exception e){
                    Toast.makeText(ma, "Course selection not saved! Try again", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

}