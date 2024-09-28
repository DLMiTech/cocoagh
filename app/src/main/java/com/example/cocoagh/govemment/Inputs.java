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
import com.example.cocoagh.adapters.FarmersAdapter;
import com.example.cocoagh.adapters.InputsAdapterAdmin;
import com.example.cocoagh.repo.InputRepo;

import java.util.List;

public class Inputs extends AppCompatActivity {

    private ImageView backBtn;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inputs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, DashboardG.class));
            finish();
        });

        try {
            InputRepo inputRepo = new InputRepo(this);
            List<com.example.cocoagh.models.Inputs> inputsList = inputRepo.getAllInput();

            InputsAdapterAdmin adapter = new InputsAdapterAdmin(this, inputsList);
            gridView.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to logcat
            Toast.makeText(this, "Failed to retrieve inputs: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        gridView = findViewById(R.id.inputs_grid_view);
    }
}