package com.example.cocoagh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Bought;
import com.example.cocoagh.models.Farms;

import java.util.List;

public class BoughtAdapter extends BaseAdapter {
    private Context context;
    private List<Bought> boughtList;

    public BoughtAdapter(Context context, List<Bought> boughtList) {
        this.context = context;
        this.boughtList = boughtList;
    }

    @Override
    public int getCount() {
        return boughtList.size();
    }

    @Override
    public Object getItem(int position) {
        return boughtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bought_items, parent, false);
        }

        TextView qty = convertView.findViewById(R.id.textView89);
        TextView price = convertView.findViewById(R.id.textView91);
        TextView payment = convertView.findViewById(R.id.textView97);
        TextView date = convertView.findViewById(R.id.textView99);
        TextView fName = convertView.findViewById(R.id.textView94);
        TextView fPhone = convertView.findViewById(R.id.textView100);
        TextView bName = convertView.findViewById(R.id.textView103);
        TextView bPhone = convertView.findViewById(R.id.textView105);

        Bought bought = boughtList.get(position);


        // set name, party and position
        qty.setText(bought.getQuantity());
        price.setText(bought.getTotal());
        payment.setText(bought.getPaymentType());
        fName.setText(bought.getFarmerName());
        fPhone.setText(bought.getFarmerPhone());
        bName.setText(bought.getLbcName());
        bPhone.setText(bought.getLbcPhone());
        date.setText(bought.getDateBought());

        return convertView;
    }
}
