package com.example.myapplication.tutor;

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
 * Use the {@link TutorHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    User user;

    public TutorHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorHome.
     */
    // TODO: Rename and change types and number of parameters
    public static TutorHome newInstance(String param1, String param2) {
        TutorHome fragment = new TutorHome();
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


//        user = (User) this.getArguments().getSerializable("user");
//
//        Bundle bundle = getContext().getArguments();
//        User obj = (User) bundle.getSerializable("user");

        //user = (User) getArguments().getSerializable("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
//        user = (User) getActivity().getIntent().getSerializableExtra("user");
        return inflater.inflate(R.layout.fragment_tutor_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_my__sessions, container, false);
//        super.onViewCreated(view, savedInstanceState);
//        final Button tutorReminders = view.findViewById(R.id.tutorReminders);
        final Button tutorSessions = view.findViewById(R.id.tutorSessions);
        final Button tutorAvailability = view.findViewById(R.id.tutorAvailability);
        final Button tutorLogout = view.findViewById(R.id.tutorLogout);
//        final Button tutorAddCourse = view.findViewById(R.id.tutorAddCourse);
        final Button tutorSetCourse = view.findViewById(R.id.tutorSetCourse);



        Bundle userData = new Bundle();
        userData.putSerializable("user", user);

//        tutorReminders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
//                        .navigate(R.id.action_tutorHome_to_reminders);
//            }
//        });
        tutorSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
                        .navigate(R.id.action_tutorHome_to_my_Sessions);
            }
        });
        tutorAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
                        .navigate(R.id.action_tutorHome_to_tutorSetDateAndTime2,userData);
            }
        });
        tutorLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
                        .navigate(R.id.action_tutorHome_to_logoutConfirmation);
            }
        });

//        tutorAddCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
//                        .navigate(R.id.action_tutorHome_to_addCourseTutor);
//            }
//        });

        tutorSetCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.tutor.TutorHome.this)
                        .navigate(R.id.action_tutorHome_to_tutorSetClasses);
            }
        });

    }
}