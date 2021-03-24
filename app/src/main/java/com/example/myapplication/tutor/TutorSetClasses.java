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
import com.example.myapplication.databases.TutorCoursesDBHelper;
import com.example.myapplication.models.Course;

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
    private ListView listView;
    private MainActivity ma;


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
        return inflater.inflate(R.layout.fragment_tutor_set_classes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.classesList);
        Button classButton = view.findViewById(R.id.selectClasses);
        ma = (MainActivity) getActivity();
        CoursesDBHelper coursesDBHelper = ma.getCoursesDB();
        TutorCoursesDBHelper tutorCoursesDBHelper = ma.getTutorCourseDB();
        ArrayList<Course> courses = coursesDBHelper.getData();
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<Course> courseArrayAdapter =
                new ArrayAdapter<Course>(getContext(), android.R.layout.simple_list_item_multiple_choice,courses);
        this.listView.setAdapter(courseArrayAdapter);
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = listView.getCheckedItemPositions();
                StringBuilder sb= new StringBuilder();

                try {
                    for (int i = 0; i < sp.size(); i++) {
                        if (sp.valueAt(i) == true) {
                            Course course = (Course) listView.getItemAtPosition(i);
                            tutorCoursesDBHelper.addTutorCourse(1000, course.getSubject(),course.getCourseNo());
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