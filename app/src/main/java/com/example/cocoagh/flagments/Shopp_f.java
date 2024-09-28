package com.example.cocoagh.flagments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.RequestInputs;
import com.example.cocoagh.farmer.ViewFarms;
import com.example.cocoagh.farmer.ViewInputs;

public class Shopp_f extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopp_f, container, false);

        CardView viewFarm = view.findViewById(R.id.viewFarm);
        CardView addInputs = view.findViewById(R.id.addInputs);
        CardView viewInputs = view.findViewById(R.id.viewInputs);
        CardView farmRequests = view.findViewById(R.id.farmRequests);


        viewFarm.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ViewFarms.class));
        });

        addInputs.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), RequestInputs.class));
        });

        viewInputs.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ViewInputs.class));
        });

        farmRequests.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Farm requests Info", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}