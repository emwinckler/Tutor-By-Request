package com.example.myapplication.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

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
//        View view = inflater.inflate(R.layout.fragment_my__sessions, container, false);
//        super.onViewCreated(view, savedInstanceState);
//        final Button sessionDetails = view.findViewById(R.id.sessionDetailsButton);
//
//        sessionDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.common.My_Sessions.this)
//                        .navigate(R.id.action_my_Sessions_to_sessionDetails);
//            }
//        });
    }
}