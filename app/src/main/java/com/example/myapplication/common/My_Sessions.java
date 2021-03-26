package com.example.myapplication.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.Session;
import com.example.myapplication.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link My_Sessions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class My_Sessions extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public My_Sessions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment My_Sessions.
     */
    // TODO: Rename and change types and number of parameters
    public static My_Sessions newInstance(String param1, String param2) {
        My_Sessions fragment = new My_Sessions();
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
        return inflater.inflate(R.layout.fragment_my__sessions, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.sessionsList);
        TextView textView = view.findViewById(R.id.idField);
        Button classButton = view.findViewById(R.id.selectClasses);
        MainActivity ma = (MainActivity) getActivity();
        DatabaseHelper db = ma.getDatabase();

        Bundle bundle = this.getArguments();
        User obj = (User) bundle.getSerializable("user");
        ArrayList<Session> sessions;

        if (obj.isTutor()){
            textView.setText("Getting sessions for tutor with student id: " +obj.getStudentID());
            sessions = db.getTutorSession(obj.getStudentID());
        } else {
            textView.setText("Getting sessions for student with student id: " +obj.getStudentID());
            sessions = db.getStudentSession(obj.getStudentID());
        }


        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<Session> sessionArrayAdapter =
                new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, sessions);
        listView.setAdapter(sessionArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Session session =(Session) (listView.getItemAtPosition(myItemInt));
                Bundle sessionDetails = new Bundle();
                sessionDetails.putSerializable("session", session);
                NavHostFragment.findNavController(com.example.myapplication.common.My_Sessions.this)
                        .navigate(R.id.action_my_Sessions_to_sessionDetails, sessionDetails);


            }
        });

    }
}