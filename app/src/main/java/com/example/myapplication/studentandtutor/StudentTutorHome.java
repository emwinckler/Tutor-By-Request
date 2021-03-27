package com.example.myapplication.studentandtutor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentTutorHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentTutorHome extends Fragment {

    User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentTutorHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentTutorHome.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentTutorHome newInstance(String param1, String param2) {
        StudentTutorHome fragment = new StudentTutorHome();
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
        return inflater.inflate(R.layout.fragment_student_tutor_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_my__sessions, container, false);
//        super.onViewCreated(view, savedInstanceState);
//        final Button reminders = view.findViewById(R.id.sAndTReminders);
        final Button sessions = view.findViewById(R.id.sAndTSessions);
        final Button getTutor = view.findViewById(R.id.sAndTGetTutor);
        final Button availability = view.findViewById(R.id.sAndTAvailability);
        final Button tutorLogout = view.findViewById(R.id.sAndTLogout);
        final Button setCourses = view.findViewById(R.id.sAndTSelectCourses);

        user = (User) this.getArguments().getSerializable("user");
        // user = (User) getActivity().getIntent().getSerializableExtra("user");

        Bundle userData = new Bundle();
        userData.putSerializable("user", user);

        setCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.studentandtutor.StudentTutorHome.this)
                        .navigate(R.id.action_studentTutorHome_to_tutorSetClasses);
            }
        });
        sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.studentandtutor.StudentTutorHome.this)
                        .navigate(R.id.action_studentTutorHome_to_my_Sessions,userData);
            }
        });
        getTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.studentandtutor.StudentTutorHome.this)
                        .navigate(R.id.action_studentTutorHome_to_get_A_Tutor,userData);
            }
        });
        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.studentandtutor.StudentTutorHome.this)
                        .navigate(R.id.action_studentTutorHome_to_tutorSetDateAndTime2,userData);
            }
        });

        tutorLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.studentandtutor.StudentTutorHome.this)
                        .navigate(R.id.action_studentTutorHome_to_logoutConfirmation);
            }
        });

    }
}