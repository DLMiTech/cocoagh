package com.example.cocoagh.flagments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.ContactInfo;
import com.example.cocoagh.farmer.VideoInfo;
import com.example.cocoagh.farmer.ViewInputs;

public class Home_f extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_f, container, false);

        CardView contact = view.findViewById(R.id.cardView4);
        CardView video = view.findViewById(R.id.cardView5);

        contact.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ContactInfo.class));
        });

        video.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), VideoInfo.class));
        });

        return view;
    }
}