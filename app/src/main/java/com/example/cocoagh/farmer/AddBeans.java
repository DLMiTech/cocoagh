package com.example.cocoagh.farmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.repo.BeansRepo;

public class AddBeans extends AppCompatActivity {

    private ImageView backBtn;
    private EditText quantityInput, priceInput, address;
    private Button submitBeansBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_beans);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int farmerId = sharedPreferences.getInt("user_id", -1); // Retrieve user ID
        String farmerName = sharedPreferences.getString("user_name", "");
        String farmerPhone = sharedPreferences.getString("user_phone", "");


        init();

        backBtn.setOnClickListener(view -> {
            finish();
        });

        submitBeansBtn.setOnClickListener(view -> {
            Log.d("SubmitButton", "Button clicked!");

            if (quantityInput.getText().toString().isEmpty() || priceInput.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter all input to add beans.", Toast.LENGTH_SHORT).show();
            } else {

                try {
                    int myFarmerId = farmerId;
                    String myFarmerName = farmerName;
                    String myFarmerPhone = farmerPhone;
                    double myQty = Double.parseDouble(quantityInput.getText().toString());
                    double myPrice = Double.parseDouble(priceInput.getText().toString());
                    String myAddress = address.getText().toString();

                    if (myQty < 10.0) {
                        Toast.makeText(this, "You can't add quantity less than 10 kg.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Beans beans = new Beans();
                    beans.setFarmerId(myFarmerId);
                    beans.setFarmerName(myFarmerName);
                    beans.setPhone(myFarmerPhone);
                    beans.setQuantity(myQty);
                    beans.setPrice(myPrice);
                    beans.setTotal();
                    beans.setAddress(myAddress);
                    beans.setStatus("In Market");

                    BeansRepo beansRepo = new BeansRepo(this);

                    boolean result = beansRepo.addBeans(beans);
                    if (result){
                        Toast.makeText(this, "Beans added to market successfully.", Toast.LENGTH_SHORT).show();
                        quantityInput.setText("");
                        priceInput.setText("");
                        address.setText("");
                    }else {
                        Toast.makeText(this, "Error adding beans", Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid quantity or price.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void init(){
        backBtn = findViewById(R.id.backBtn);

        quantityInput = findViewById(R.id.quantityInput);
        priceInput = findViewById(R.id.priceInput);
        address = findViewById(R.id.address);
        submitBeansBtn = findViewById(R.id.submitBeansBtn);
    }
}