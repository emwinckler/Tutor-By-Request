package com.example.myapplication.common;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databases.DatabaseHelper;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.Session;
import com.example.myapplication.models.TutorAvailablity;
import com.example.myapplication.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link My_Sessions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class My_Sessions extends Fragment {

    ViewGroup view_viewGroup;

    MainActivity ma;
    DatabaseHelper db;
    User user;

    ArrayList<Session> tutorSessions;
    ArrayList<Session> studentSessions;

    ArrayAdapter<Session> adapter_tutorSessions;
    ArrayAdapter<Session> adapter_studentSessions;

    ListView listView_sessions;

    TextView textView_sessionType;

    Button button_studentSessions;
    Button button_tutorSessions;


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

        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_viewGroup = container;
        return inflater.inflate(R.layout.fragment_my__sessions, view_viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView_sessions = view.findViewById(R.id.listView_sessions);

        textView_sessionType = view.findViewById(R.id.textView_sessionType);

        button_studentSessions = view.findViewById(R.id.button_studentSession);
        button_tutorSessions = view.findViewById(R.id.button_tutorSessions);

        ma = (MainActivity) getActivity();
        db = ma.getDatabase();

        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");

        if (user.isTutor() && user.isTutee() ){ // STUTOR CASE

            //textView.setText("Getting sessions for stutor with student id: " + user.getStudentID());
            button_studentSessions.setEnabled(true);
            button_tutorSessions.setEnabled(true);
            button_tutorSessions.setBackgroundColor(Color.RED);
            button_studentSessions.setBackgroundColor(Color.RED);
            button_tutorSessions.setVisibility(View.VISIBLE);
            button_studentSessions.setVisibility(View.VISIBLE);

            tutorSessions = db.getTutorSession(user.getStudentID());
            studentSessions = db.getStudentSession(user.getStudentID());
            adapter_tutorSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, tutorSessions);

            adapter_studentSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, studentSessions);

        } else if (user.isTutor()){
            //textView.setText("Getting sessions for tutor with tutor id: " + user.getStudentID());
            button_studentSessions.setEnabled(false);
            button_tutorSessions.setEnabled(false);
            button_tutorSessions.setVisibility(View.GONE);
            button_studentSessions.setVisibility(View.GONE);
            tutorSessions = db.getTutorSession(user.getStudentID());

            adapter_tutorSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, tutorSessions);

            listView_sessions.setAdapter(adapter_tutorSessions);

        } else if (user.isTutee()) {
            //textView.setText("Getting sessions for student with tutor id: " + user.getStudentID());
            button_studentSessions.setEnabled(false);
            button_tutorSessions.setEnabled(false);
            button_tutorSessions.setVisibility(View.GONE);
            button_studentSessions.setVisibility(View.GONE);
            studentSessions = db.getStudentSession(user.getStudentID());

            adapter_studentSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, studentSessions);

            listView_sessions.setAdapter(adapter_studentSessions);
        } else {
            System.out.println("SOMETHING WENT WRONG: MY SESSIONS PAGE. LOGGED IN USER OBJECT IS BUNK");
        }


        listView_sessions.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        button_studentSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView_sessions.setAdapter(adapter_studentSessions);
            }
        });

        button_tutorSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView_sessions.setAdapter(adapter_tutorSessions);
            }
        });

                // listView.setAdapter(studentSessions);

        listView_sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {


                Session session =(Session) (listView_sessions.getItemAtPosition(myItemInt));
                Bundle sessionDetails = new Bundle();
                sessionDetails.putSerializable("session", session);


                showSessionDetailsPopup(myView, session);

//                NavHostFragment.findNavController(com.example.myapplication.common.My_Sessions.this)
//                        .navigate(R.id.action_my_Sessions_to_sessionDetails, sessionDetails);


            }
        });

    }


    // CONFIRMATION AND ADD DESCRIPTION FOR CREATE SESSION
    public void showSessionDetailsPopup(View view, Session session) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.my_sessions_session_details_popup, view_viewGroup, false);

        PopupWindow popup = new PopupWindow(popupView, 1000,1000, true);
        popup.setOutsideTouchable(true);
        //popup.setContentView(view);
        popup.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView textView = popupView.findViewById(R.id.sessionDetails);

        textView.setText(session.sessionDetails());

        Button button_cancelSession = (Button) popupView.findViewById(R.id.button_cancelSession);
        Button button_dismiss = (Button) popupView.findViewById(R.id.button_dismiss);

        button_cancelSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TutorAvailablity timeBlock;




                popup.dismiss();
            }
        });


        button_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });




    }
}