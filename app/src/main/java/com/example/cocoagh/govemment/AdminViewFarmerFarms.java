package com.example.cocoagh.govemment;

import android.content.Intent;
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
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.repo.FarmRepo;

import java.util.List;

public class AdminViewFarmerFarms extends AppCompatActivity {

    private ImageView backBtn;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_view_farmer_farms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        backBtn.setOnClickListener(view -> {
            finish();
        });


        Intent intent = getIntent();
        int farmerId = intent.getIntExtra("farmerId", -1);

        // Check if the farmerId is valid
        if (farmerId != -1) {
            try {
                FarmRepo farmRepo = new FarmRepo(this);
                List<Farms> farmsList = farmRepo.getFarmsByFarmerId(farmerId);

                // Create and set the adapter
                FarmAdapter adapter = new FarmAdapter(this, farmsList);
                gridView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace to logcat
                Toast.makeText(this, "Failed to retrieve farms: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    public void init(){
        backBtn = findViewById(R.id.imageView6);
        gridView = findViewById(R.id.farm_grid_view);
    }
}