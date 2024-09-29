package com.example.cocoagh.lbc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;

public class LbcProfile extends AppCompatActivity {

    private ImageView backBtn;
    private TextView myUsername, myName, myPhone, myUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lbc_profile);
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
        int farmerId = sharedPreferences.getInt("user_id", -1); // Retrieve user ID
        String farmerName = sharedPreferences.getString("user_name", "");
        String farmerPhone = sharedPreferences.getString("user_phone", "");


        myUsername.setText(farmerName);
        myPhone.setText(farmerPhone);
        myUserType.setText("LBC");
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);

        myUsername = findViewById(R.id.usernameTxt);
        myName = findViewById(R.id.nameTxt);
        myPhone = findViewById(R.id.phoneNumberTxt);
        myUserType = findViewById(R.id.userTypeTxt);
    }
}