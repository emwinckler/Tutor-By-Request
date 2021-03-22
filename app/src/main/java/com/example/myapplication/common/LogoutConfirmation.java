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
 * Use the {@link LogoutConfirmation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogoutConfirmation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogoutConfirmation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutConfirmation.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoutConfirmation newInstance(String param1, String param2) {
        LogoutConfirmation fragment = new LogoutConfirmation();
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
        return inflater.inflate(R.layout.fragment_logout_confirmation, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        super.onViewCreated(view, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_logout_confirmation, container, false);
        final Button logoutYes = view.findViewById(R.id.logoutYes);
        final Button logoutNo = view.findViewById(R.id.logoutNo);

        logoutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.common.LogoutConfirmation.this)
                        .navigate(R.id.action_logoutConfirmation_to_loginFragment);
            }
        });

        logoutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.common.LogoutConfirmation.this)
//                        .navigate(R.id.action_logoutConfirmation_to_logout);
            }
        });

    }
}