package com.example.cocoagh.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.ViewFarms;
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.repo.FarmRepo;

import java.util.List;

public class FarmAdapter extends BaseAdapter {

    private Context context;
    private List<Farms> farmsList;

    public FarmAdapter(Context context, List<Farms> farmsList) {
        this.context = context;
        this.farmsList = farmsList;
    }


    @Override
    public int getCount() {
        return farmsList.size();
    }

    @Override
    public Object getItem(int position) {
        return farmsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_farmer_items, parent, false);
        }

        ImageView farmImage = convertView.findViewById(R.id.imageView8);
        TextView farmStatus = convertView.findViewById(R.id.farm_status_txt);
        TextView farmLocation = convertView.findViewById(R.id.farm_location_txt);
        TextView farmSize = convertView.findViewById(R.id.farm_size_txt);
        ImageView deleteFarm = convertView.findViewById(R.id.delete_farm);
        ImageView editFarm = convertView.findViewById(R.id.edit_farm);


        Farms farms = farmsList.get(position);

        // Set image
//        byte[] imageBytes = farms.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        farmImage.setImageBitmap(bitmap);

        // set name, party and position
        farmStatus.setText(farms.getStage());
        farmLocation.setText(farms.getLocation());
        farmSize.setText(String.valueOf(farms.getSize()));

        // Set the candidate ID as a tag on the buttons
        deleteFarm.setTag(farms.getId());
        editFarm.setTag(farms.getId());


        // Set click listeners for edit and delete buttons
        editFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int farmId = (int) v.getTag();

                // Open an activity or dialog to edit the product

//                Intent intent = new Intent(context, CandidateEditActivity.class);
//                intent.putExtra("FARM_ID", farmId);
//                context.startActivity(intent);
                Toast.makeText(context, "Edit Farm Info", Toast.LENGTH_SHORT).show();
            }
        });

        deleteFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.alert_r)
                        .setTitle("CoCoaGH")
                        .setMessage("Are You Sure You Want To Delete Farm.")
                        .setCancelable(false)
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })

                        .setPositiveButton("Yes! Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int farmId = (int) v.getTag();
                                // Delete the product from the database
                                FarmRepo farmRepo = new FarmRepo(context);
                                farmRepo.deleteFarm(farmId);

                                // Remove the product from the list
                                farmsList.remove(position);

                                // Notify the adapter that the data has changed
                                notifyDataSetChanged();

                                // Optionally show a Toast message to confirm deletion
                                Toast.makeText(context, "Farm deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        return convertView;
    }
}
