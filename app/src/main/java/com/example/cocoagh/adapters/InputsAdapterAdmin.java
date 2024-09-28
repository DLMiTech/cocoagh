package com.example.cocoagh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.models.Users;

import java.util.List;

public class InputsAdapterAdmin extends BaseAdapter {
    private Context context;
    private List<Inputs> inputsList;

    public InputsAdapterAdmin(Context context, List<Inputs> inputsList) {
        this.context = context;
        this.inputsList = inputsList;
    }


    @Override
    public int getCount() {
        return inputsList.size();
    }

    @Override
    public Object getItem(int position) {
        return inputsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.input_items_admin, parent, false);
        }

        TextView farmerName = convertView.findViewById(R.id.farmerNameTxt);
        TextView inputType = convertView.findViewById(R.id.inputTypeTxt);
        TextView inputQty = convertView.findViewById(R.id.inputQtyTxt);
        TextView inputStatus = convertView.findViewById(R.id.inputStatusTxt);
        TextView inputNote = convertView.findViewById(R.id.noteTxt);
        ImageView approveInput = convertView.findViewById(R.id.approveBtn);

        Inputs inputs = inputsList.get(position);

        farmerName.setText(inputs.getFarmName());
        inputType.setText(inputs.getInputType());
        inputQty.setText(inputs.getInputQty());
        inputStatus.setText(inputs.getStatus());
        inputNote.setText(inputs.getInputNote());
        approveInput.setTag(inputs.getId());




        approveInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputId = (int) v.getTag();

                Toast.makeText(context, "Change input status "+inputId, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
