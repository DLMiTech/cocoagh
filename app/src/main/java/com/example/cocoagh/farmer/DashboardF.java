package com.example.cocoagh.farmer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.MainActivity;
import com.example.cocoagh.R;
import com.example.cocoagh.flagments.Farm_f;
import com.example.cocoagh.flagments.Home_f;
import com.example.cocoagh.flagments.Profile_f;
import com.example.cocoagh.flagments.Shopp_f;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardF extends AppCompatActivity {

    private TextView userName;
    private ImageView logout;
    private BottomNavigationView bottomNavigationView;

    // User data
    private String myUsername;
    private String myName;
    private String myPhone;
    private int userType;
    private int myUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard_f);
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

        // Setting default fragment
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Home_f()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.home_nav){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Home_f()).commit();
                } else if (id == R.id.farmer_nav) {
                    Farm_f farmF = Farm_f.newInstance(myUserId, myName);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, farmF).commit();
                } else if (id == R.id.shop_nav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Shopp_f()).commit();
                } else if (id == R.id.profile_nav) {
                    // Pass user data to Profile_f fragment
                    Profile_f profileFragment = Profile_f.newInstance(myUsername, myName, myPhone, userType);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, profileFragment).commit();
                }
                return true;
            }
        });

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

                            startActivity(new Intent(DashboardF.this, MainActivity.class));
                            finish();
                        }
                    })
                    .show();

        });
    }

    public void init(){
        userName = findViewById(R.id.userNameTxt);
        logout = findViewById(R.id.logoutImv);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
}
