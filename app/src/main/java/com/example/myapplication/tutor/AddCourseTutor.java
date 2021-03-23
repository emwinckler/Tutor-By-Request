package com.example.myapplication.tutor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databases.CoursesDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseTutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseTutor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseTutor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseTutor.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseTutor newInstance(String param1, String param2) {
        AddCourseTutor fragment = new AddCourseTutor();
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
        return inflater.inflate(R.layout.fragment_add_course_tutor, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_my__sessions, container, false);
//        super.onViewCreated(view, savedInstanceState);
        final Spinner subjectSpinner = (Spinner) view.findViewById(R.id.spinner2);
        final EditText courseName = view.findViewById(R.id.courseNameButton);
        final EditText courseNo = view.findViewById(R.id.courseNoButton);
        final Button addCourseButton = view.findViewById(R.id.addCourseButton);
        MainActivity ma = (MainActivity) getActivity();
        CoursesDBHelper coursesDBHelper = ma.getCoursesDB();

        ArrayList<String> subjects = new ArrayList<String>();
        subjects.add("Biomedical Engineering");
        subjects.add("Chemical and Biological Engineering");
        subjects.add("Chemistry");
        subjects.add("Civil and Environmental Engineering");
        subjects.add("Computer Sciences");
        subjects.add("Electrical and Computer Engineering");
        subjects.add("Engineering Mechanics and Astronautics");
        subjects.add("Industrial and Systems Engineering");
        subjects.add("Materials Science and Engineering");
        subjects.add("Mathematics");
        subjects.add("Mechanical Engineering");
        subjects.add("Physics");
        subjects.add("Physiology");
        subjects.add("Statistics");

        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, subjects);
        subjectSpinner.setAdapter(subjectAdapter);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = subjectSpinner.getSelectedItem().toString();
                String course = courseName.getText().toString();

                try {
                    int courseNum = Integer.parseInt(courseNo.getText().toString());
                    coursesDBHelper.addData(subject, course, courseNum);
                } catch (NumberFormatException e){
                    Toast.makeText(ma, "Course number needs to be a 3 digit integer!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ma, "Addition didn't work", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}