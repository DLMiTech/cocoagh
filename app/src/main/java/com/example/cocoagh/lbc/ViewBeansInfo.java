package com.example.cocoagh.lbc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import com.example.cocoagh.repo.BeansRepo;

import java.util.Objects;

public class ViewBeansInfo extends AppCompatActivity {

    private TextView quantity, price, total, name, phone, location, status, time;
    private Button buy;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_beans_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        backBtn.setOnClickListener(view -> {
            finish();
        });

        Beans beans = (Beans) getIntent().getSerializableExtra("beans");

        if (beans != null){
            quantity.setText(String.valueOf(beans.getQuantity()));
            price.setText(String.valueOf(beans.getPrice()));
            total.setText(String.valueOf(beans.getTotal()));
            name.setText(beans.getFarmerName());
            phone.setText(beans.getPhone());
            location.setText(beans.getAddress());
            status.setText(beans.getStatus());
            time.setText(beans.getAddedDate());

            buy.setOnClickListener(view -> {
                if (Objects.equals(beans.getStatus(), "In Market")){
                    BeansRepo beansRepo = new BeansRepo(this);
                    Beans beans1 = beansRepo.getBeansById(beans.getId());
                    Intent intent = new Intent(this, BuyBeans.class);
                    intent.putExtra("beans", beans1);  // Pass the student object
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Beans out of market", Toast.LENGTH_SHORT).show();
                }

            });
        }


    }


    public void init(){
        backBtn = findViewById(R.id.backBtn);
        quantity = findViewById(R.id.textView60);
        price = findViewById(R.id.textView62);
        total = findViewById(R.id.textView64);
        name = findViewById(R.id.textView66);
        phone = findViewById(R.id.textView68);
        location = findViewById(R.id.textView70);
        status = findViewById(R.id.textView72);
        time = findViewById(R.id.textView71);
        buy = findViewById(R.id.buyBtn);
    }
}