package com.example.cocoagh.lbc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Bought;
import com.example.cocoagh.repo.BeansRepo;
import com.example.cocoagh.repo.BoughtRepo;

public class BuyBeans extends AppCompatActivity {

    private ImageView backBtn;
    private TextView quantity, total, name, phone, location;
    private Button buyNow;
    private EditText paymentInput, buyerName;

    private String paymentType;
    String[] types = {"Cash", "MoMo", "Bank"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_beans);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        backBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, DashboardLBC.class));
            finish();
        });

        AutoCompleteTextView payment = findViewById(R.id.paymentInput);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.list_items, types);
        payment.setAdapter(adapterItems);

        Beans beans = (Beans) getIntent().getSerializableExtra("beans");

        payment.setOnItemClickListener((adapterView, f_view, i, l) -> {
            paymentType = adapterView.getItemAtPosition(i).toString();
        });

        if (beans != null){
            quantity.setText(String.valueOf(beans.getQuantity()));
            total.setText(String.valueOf("GHC "+beans.getTotal()));
            name.setText(beans.getFarmerName());
            phone.setText(beans.getPhone());
            location.setText(beans.getAddress());

            buyNow.setOnClickListener(view -> {
                String myFullName = buyerName.getText().toString();
                String myPaymentType = paymentType;

                if (myFullName.isEmpty() || myPaymentType == null || myPaymentType.isEmpty()){
                    Toast.makeText(this, "Enter name and select payment type", Toast.LENGTH_SHORT).show();
                } else if (myPaymentType.equals("MoMo") || myPaymentType.equals("Bank")) {
                    Toast.makeText(this, "Please only cash payment is allowed for now.", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    int lbcId = sharedPreferences.getInt("user_id", -1); // Retrieve user ID
                    String lbcName = sharedPreferences.getString("user_name", "");
                    String lbcPhone = sharedPreferences.getString("user_phone", "");

                    Bought bought = new Bought();
                    bought.setLbcId(lbcId);
                    bought.setLbcName(lbcName);
                    bought.setLbcPhone(lbcPhone);
                    bought.setFarmerId(beans.getFarmerId());
                    bought.setFarmerName(beans.getFarmerName());
                    bought.setFarmerPhone(beans.getPhone());
                    bought.setFarmerLocation(beans.getAddress());
                    bought.setQuantity(String.valueOf(beans.getQuantity()));
                    bought.setTotal(String.valueOf(beans.getTotal()));
                    bought.setPayerName(myFullName);
                    bought.setPaymentType(myPaymentType);


                    try {
                        BoughtRepo boughtRepo = new BoughtRepo(this);
                        BeansRepo beansRepo = new BeansRepo(this);
                        beansRepo.updateBeansStatus(beans.getId(), "Bought from market");
                        boolean result = boughtRepo.addBought(bought);
                        if (result){
                            Toast.makeText(this, "Beans bought successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, BoughtSuccess.class));
                            finish();
                        }else {
                            Toast.makeText(this, "Error buying beans.", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }


    public void init(){
        backBtn = findViewById(R.id.backBtn);
        quantity = findViewById(R.id.textView75);
        total = findViewById(R.id.textView77);

        name = findViewById(R.id.textView82);
        phone = findViewById(R.id.textView83);
        location = findViewById(R.id.textView84);

        paymentInput = findViewById(R.id.paymentInput);
        buyerName = findViewById(R.id.buyerInput);

        buyNow = findViewById(R.id.buyNowBtn);
    }
}