package com.example.myapplication.student;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Student#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Student extends Fragment {

    // UPPER MENU BEGIN
    Button button_home;
    Button button_get_a_tutor;
    Button button_my_sessions;
    Button button_logout;

    // UPPER MENU END

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Student() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Student.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Student newInstance(String param1, String param2) {
        Home_Student fragment = new Home_Student();
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
        // Inflate the layout for this    fragment
        View view = inflater.inflate(R.layout.fragment_home__student, container, false);

        // UPPER MENU BEGIN
        button_home = (Button) view.findViewById(R.id.button_home);
        button_get_a_tutor = (Button) view.findViewById(R.id.button_get_a_tutor);
        button_my_sessions = (Button) view.findViewById(R.id.button_my_sessions);
        button_logout = (Button) view.findViewById(R.id.button_logout);
        // UPPER MENU END


        return inflater.inflate(R.layout.fragment_home__student, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // UPPER MENU BEGIN
        button_home        = (Button) view.findViewById(R.id.button_home);
        button_get_a_tutor = (Button) view.findViewById(R.id.button_get_a_tutor);
        button_my_sessions = (Button) view.findViewById(R.id.button_my_sessions);
        button_logout      = (Button) view.findViewById(R.id.button_logout);

        button_home.setBackgroundColor(Color.RED);
        button_get_a_tutor.setBackgroundColor(Color.RED);
        button_my_sessions.setBackgroundColor(Color.RED);
        button_logout.setBackgroundColor(Color.RED);


        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.student.Get_A_Tutor_Student.this)
//                        .navigate(R.id.action_home_student_to_home_student); // refresh student home?

                // iteration0: do nothing

            }
        });

        button_get_a_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                NavHostFragment.findNavController(com.example.myapplication.student.Home_Student.this)
                        .navigate(R.id.action_home_student_to_get_a_tutor_student);

            }
        });

        button_my_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bundle student id and pass it to my sessions fragment for database

                NavHostFragment.findNavController(com.example.myapplication.student.Home_Student.this)
                        .navigate(R.id.action_home_student_to_my_sessions_student);


            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bundle student id and pass it to my sessions fragment for database

                NavHostFragment.findNavController(com.example.myapplication.student.Home_Student.this)
                        .navigate(R.id.action_home_student_to_logout);


            }
        });

    }

        // UPPER MENU END
}