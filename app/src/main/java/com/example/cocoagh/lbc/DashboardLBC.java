package com.example.cocoagh.lbc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.MainActivity;
import com.example.cocoagh.R;
import com.example.cocoagh.adapters.FarmerBeansAdapter;
import com.example.cocoagh.adapters.MarketBeansAdapter;
import com.example.cocoagh.farmer.DashboardF;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.repo.BeansRepo;

import java.util.List;

public class DashboardLBC extends AppCompatActivity {

    private TextView userName;
    private ImageView logout;
    private GridView gridView;

    private ImageView shop, search, profile;

    private String myUsername;
    private String myName;
    private String myPhone;
    private int userType;
    private int myUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard_lbc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        // Retrieve user data from Intent extras
        Intent intent = getIntent();
        myUsername = intent.getStringExtra("username");
        myName = intent.getStringExtra("name");
        myPhone = intent.getStringExtra("phone");
        userType = intent.getIntExtra("userType", -1);
        myUserId = intent.getIntExtra("id", -1);

        userName.setText(myUsername);

        logout.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert_w)
                    .setTitle("CoCoa GH")
                    .setMessage("Are you sure you want to logout")
                    .setCancelable(false)
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    })

                    .setPositiveButton("Yes! Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            startActivity(new Intent(DashboardLBC.this, MainActivity.class));
                            finish();
                        }
                    })
                    .show();

        });


        shop.setOnClickListener(view -> {
            Intent shopIntent = new Intent(this, LbcShop.class);
            startActivity(shopIntent);
        });
        search.setOnClickListener(view -> {
            Intent searchIntent = new Intent(this, LbcSearch.class);
            startActivity(searchIntent);
        });
        profile.setOnClickListener(view -> {
            Intent profilIntent = new Intent(this, LbcProfile.class);
            startActivity(profilIntent);
        });



        try {
            BeansRepo beansRepo = new BeansRepo(this);
            List<Beans> beansList = beansRepo.getAllBeans();

            // Create and set the adapter
            MarketBeansAdapter adapter = new MarketBeansAdapter(this, beansList);
            gridView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to logcat
            Toast.makeText(this, "Failed to retrieve farmers beans: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        userName = findViewById(R.id.userNameTxt);
        logout = findViewById(R.id.logoutImv);

        shop = findViewById(R.id.imageView20);
        search = findViewById(R.id.imageView22);
        profile = findViewById(R.id.imageView21);

        gridView = findViewById(R.id.beans_grid_view);
    }
}