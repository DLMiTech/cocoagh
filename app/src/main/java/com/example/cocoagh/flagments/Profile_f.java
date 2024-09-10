package com.example.cocoagh.flagments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoagh.R;

public class Profile_f extends Fragment {

    private static final String ARG_USERNAME = "username";
    private static final String ARG_NAME = "name";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_USER_TYPE = "userType";

    private CardView editAccountCV, changePasswordCV;
    private TextView myUsername, myName, myPhone, myUserType;

    public Profile_f() {
        // Required empty public constructor
    }


    public static Profile_f newInstance(String username, String name, String phone, int userType) {
        Profile_f fragment = new Profile_f();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putString(ARG_NAME, name);
        args.putString(ARG_PHONE, phone);
        args.putInt(ARG_USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_f, container, false);

        editAccountCV = view.findViewById(R.id.editAccountCV);
        changePasswordCV = view.findViewById(R.id.changePasswordCV);
        myUsername = view.findViewById(R.id.usernameTxt);
        myName = view.findViewById(R.id.nameTxt);
        myPhone = view.findViewById(R.id.phoneNumberTxt);
        myUserType = view.findViewById(R.id.userTypeTxt);

        // Retrieve the arguments passed from the activity
        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            String name = getArguments().getString(ARG_NAME);
            String phone = getArguments().getString(ARG_PHONE);
            int userType = getArguments().getInt(ARG_USER_TYPE);

            myUsername.setText(username);
            myName.setText(name);
            myPhone.setText(phone);
            myUserType.setText(getUserTypeString(userType));
        }

        // Set click listeners for the CardViews
        editAccountCV.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Edit account.", Toast.LENGTH_SHORT).show();
            // Implement edit account functionality here
        });

        changePasswordCV.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Change password.", Toast.LENGTH_SHORT).show();
            // Implement change password functionality here
        });

        return view;
    }


    private String getUserTypeString(int userType) {
        switch (userType) {
            case 0:
                return "Farmer";
            case 1:
                return "Administrator";
            // Add more cases if there are more user types
            default:
                return "Unknown";
        }
    }
}
