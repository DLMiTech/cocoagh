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
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.BeansRepo;
import com.example.cocoagh.repo.FarmRepo;

import java.util.List;

public class FarmerBeansAdapter extends BaseAdapter {
    private Context context;
    private List<Beans> beansList;

    public FarmerBeansAdapter(Context context, List<Beans> beansList) {
        this.context = context;
        this.beansList = beansList;
    }

    @Override
    public int getCount() {
        return beansList.size();
    }

    @Override
    public Object getItem(int position) {
        return beansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.farmer_beans, parent, false);
        }

        TextView quantity = convertView.findViewById(R.id.textView35);
        TextView price = convertView.findViewById(R.id.textView41);
        TextView total = convertView.findViewById(R.id.textView45);
        TextView status = convertView.findViewById(R.id.textView46);
        ImageView deleteBeans = convertView.findViewById(R.id.imageView10);


        Beans beans = beansList.get(position);

        quantity.setText(beans.getQuantity()+" KG");
        price.setText("GHC "+beans.getPrice());
        total.setText("GHC "+beans.getTotal());
        status.setText(beans.getStatus());

        deleteBeans.setTag(beans.getId());

        deleteBeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.alert_r)
                        .setTitle("CoCoaGH")
                        .setMessage("Are You Sure You Want To Delete Beans.")
                        .setCancelable(false)
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })

                        .setPositiveButton("Yes! Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int beansId = (int) v.getTag();
                                // Delete the product from the database
                                BeansRepo beansRepo = new BeansRepo(context);
                                beansRepo.deleteBeans(beansId);

                                // Remove the product from the list
                                beansList.remove(position);

                                // Notify the adapter that the data has changed
                                notifyDataSetChanged();

                                // Optionally show a Toast message to confirm deletion
                                Toast.makeText(context, "Beans deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        return convertView;
    }
}
