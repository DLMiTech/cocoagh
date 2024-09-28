package com.example.cocoagh.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.repo.FarmRepo;
import com.example.cocoagh.repo.InputRepo;

import java.util.List;

public class InputsAdapter extends BaseAdapter {

    private Context context;
    private List<Inputs> inputsList;

    public InputsAdapter(Context context, List<Inputs> inputsList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.input_items, parent, false);
        }

        TextView inputType = convertView.findViewById(R.id.textView22);
        TextView inputQty = convertView.findViewById(R.id.textView25);
        TextView inputNote = convertView.findViewById(R.id.textView28);
        TextView inputStatus = convertView.findViewById(R.id.textView31);
        ImageView deleteFarm = convertView.findViewById(R.id.imageView7);
        ImageView editFarm = convertView.findViewById(R.id.imageView9);

        Inputs inputs = inputsList.get(position);


        inputType.setText(inputs.getInputType());
        inputQty.setText(inputs.getInputQty());
        inputNote.setText(inputs.getInputNote());
        inputStatus.setText(inputs.getStatus());

        deleteFarm.setTag(inputs.getId());
        editFarm.setTag(inputs.getId());


        editFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int farmId = (int) v.getTag();

                // Open an activity or dialog to edit the product

//                Intent intent = new Intent(context, CandidateEditActivity.class);
//                intent.putExtra("INPUT_ID", inputId);
//                context.startActivity(intent);
                Toast.makeText(context, "Edit Input Info", Toast.LENGTH_SHORT).show();
            }
        });

        deleteFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.alert_r)
                        .setTitle("CoCoaGH")
                        .setMessage("Are You Sure You Want To Delete Input.")
                        .setCancelable(false)
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })

                        .setPositiveButton("Yes! Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int inputId = (int) v.getTag();
                                // Delete the product from the database
                                InputRepo inputRepo = new InputRepo(context);
                                inputRepo.deleteInput(inputId);

                                // Remove the product from the list
                                inputsList.remove(position);

                                // Notify the adapter that the data has changed
                                notifyDataSetChanged();

                                // Optionally show a Toast message to confirm deletion
                                Toast.makeText(context, "Input deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        return convertView;
    }
}
