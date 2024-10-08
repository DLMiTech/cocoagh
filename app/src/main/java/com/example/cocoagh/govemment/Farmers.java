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
import com.example.cocoagh.adapters.FarmersAdapter;
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.FarmRepo;
import com.example.cocoagh.repo.UserRepo;

import java.util.List;

public class Farmers extends AppCompatActivity {

    private ImageView backBtn;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farmers);
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
            UserRepo userRepo = new UserRepo(this);
            List<Users> usersList = userRepo.getUsersByType(0);

            FarmersAdapter adapter = new FarmersAdapter(this, usersList);
            gridView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to logcat
            Toast.makeText(this, "Failed to retrieve farmer: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        gridView = findViewById(R.id.farms_grid_view);
    }
}