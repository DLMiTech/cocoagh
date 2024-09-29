package com.example.cocoagh.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.ViewFarms;
import com.example.cocoagh.govemment.AdminViewFarmerFarms;
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.models.Users;

import java.util.List;

public class FarmersAdapter extends BaseAdapter {

    private Context context;
    private List<Users> usersList;

    public FarmersAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }


    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.farmer_items, parent, false);
        }

        TextView farmerName = convertView.findViewById(R.id.farmerName);
        TextView farmerPhone = convertView.findViewById(R.id.farmerPhone);
        ImageView viewFarms = convertView.findViewById(R.id.viewFarms);

        Users users = usersList.get(position);

        farmerName.setText(users.getName());
        farmerPhone.setText(users.getPhone());
        viewFarms.setTag(users.getId());


        viewFarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = (int) v.getTag();

                Intent intent = new Intent(context, AdminViewFarmerFarms.class);
                intent.putExtra("farmerId", userId);
                context.startActivity(intent);

            }
        });

        return convertView;
    }
}
