package com.example.cocoagh.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.cocoagh.R;
import com.example.cocoagh.lbc.ViewBeansInfo;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.repo.BeansRepo;

import java.util.List;

public class MarketBeansAdapter extends BaseAdapter {
    private Context context;
    private List<Beans> beansList;

    public MarketBeansAdapter(Context context, List<Beans> beansList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.market_beans_items, parent, false);
        }

        TextView quantity = convertView.findViewById(R.id.textView56);
        TextView price = convertView.findViewById(R.id.textView57);
        TextView total = convertView.findViewById(R.id.textView58);
        Button viewBuy = convertView.findViewById(R.id.viewBuyBtn);

        Beans beans = beansList.get(position);

        quantity.setText(beans.getQuantity()+" KG of beans");
        price.setText("GHC "+beans.getPrice()+ " per KG");
        total.setText("GHC "+beans.getTotal());
        viewBuy.setTag(beans.getId());

        viewBuy.setOnClickListener(view -> {
            int beansId = beans.getId();

            BeansRepo beansRepo = new BeansRepo(context);
            Beans beans1 = beansRepo.getBeansById(beansId);

            if (beans1 != null) {
                Intent intent = new Intent(context, ViewBeansInfo.class);
                intent.putExtra("beans", beans1);  // Pass the student object
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "beans not found! "+ beansId, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
