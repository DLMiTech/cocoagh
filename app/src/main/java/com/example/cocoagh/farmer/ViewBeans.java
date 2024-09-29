package com.example.cocoagh.farmer;

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
import com.example.cocoagh.adapters.FarmAdapter;
import com.example.cocoagh.adapters.FarmerBeansAdapter;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.repo.BeansRepo;
import com.example.cocoagh.repo.FarmRepo;

import java.util.List;

public class ViewBeans extends AppCompatActivity {

    private ImageView backBtn;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_beans);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backBtn.setOnClickListener(view -> {
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int beansId = sharedPreferences.getInt("user_id", -1);

        try {
            BeansRepo beansRepo = new BeansRepo(this);
            List<Beans> beansList = beansRepo.getBeansByFarmerId(beansId);

            // Create and set the adapter
            FarmerBeansAdapter adapter = new FarmerBeansAdapter(this, beansList);
            gridView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to logcat
            Toast.makeText(this, "Failed to retrieve farmers beans: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        gridView = findViewById(R.id.beans_grid_view);
    }
}