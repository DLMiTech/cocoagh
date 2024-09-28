package com.example.cocoagh.govemment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.InputRepo;
import com.example.cocoagh.repo.UserRepo;

public class DashboardG extends AppCompatActivity {

    private ImageView logoutBtn;
    private CardView purchaseBeans, provideInputs, connectFarmers, addLBC;
    private TextView numberOfLBC, numberOfFarmers, numberOfInputs, numberOfPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard_g);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        logoutBtn.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert)
                    .setTitle("CoCoa GH")
                    .setMessage("Are You Sure You Want To Logout.")
                    .setCancelable(false)
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Logout Canceled", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .setPositiveButton("Yes! Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Logout Successful.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });

        UserRepo userRepo = new UserRepo(this);
        InputRepo inputRepo = new InputRepo(this);
        int totalInputs = inputRepo.getTotalInputs();
        int totalFarmers = userRepo.getTotalUsersByType(0);
        numberOfFarmers.setText(String.valueOf(totalFarmers));
        int totalLBC = userRepo.getTotalUsersByType(1);
        numberOfLBC.setText(String.valueOf(totalLBC));
        numberOfInputs.setText(String.valueOf(totalInputs));


        addLBC.setOnClickListener(view -> {
            startActivity(new Intent(this, AddLBC.class));
            finish();
        });

        connectFarmers.setOnClickListener(view -> {
            startActivity(new Intent(this, Farmers.class));
            finish();
        });

        provideInputs.setOnClickListener(view -> {
            startActivity(new Intent(this, Inputs.class));
            finish();
        });

        purchaseBeans.setOnClickListener(view -> {
            startActivity(new Intent(this, Purchases.class));
            finish();
        });
    }


    public void init(){
        logoutBtn = findViewById(R.id.logoutImv);

        purchaseBeans = findViewById(R.id.purchaseBeans);
        provideInputs = findViewById(R.id.provideInputs);
        connectFarmers = findViewById(R.id.connectFarmers);
        addLBC = findViewById(R.id.addLBC);


        numberOfLBC = findViewById(R.id.numberOfLBC);
        numberOfFarmers = findViewById(R.id.numberOfFarmers);
        numberOfInputs = findViewById(R.id.numberOfInputs);
        numberOfPurchase = findViewById(R.id.numberOfPurchase);
    }
}