package com.example.cocoagh.lbc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.adapters.BoughtAdapter;
import com.example.cocoagh.adapters.MarketBeansAdapter;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Bought;
import com.example.cocoagh.repo.BeansRepo;
import com.example.cocoagh.repo.BoughtRepo;

import java.util.List;

public class LbcShop extends AppCompatActivity {

    private ImageView backBtn;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lbc_shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backBtn.setOnClickListener(view -> {
            finish();
        });

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            int lbcId = sharedPreferences.getInt("user_id", -1);

            BoughtRepo boughtRepo = new BoughtRepo(this);
            List<Bought> boughtList = boughtRepo.getBoughtBeansByLBC(lbcId);

            // Create and set the adapter
            BoughtAdapter adapter = new BoughtAdapter(this, boughtList);
            gridView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to logcat
            Toast.makeText(this, "Failed to retrieve bought beans: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        gridView = findViewById(R.id.beans_grid_view);
    }
}