package com.example.cocoagh.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;

public class ForgotActivity extends AppCompatActivity {

    private EditText phone;
    private Button resetOPT;
    private TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resetInit();

        signIn.setOnClickListener(view -> {
            startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
            finish();
        });

        resetOPT.setOnClickListener(view -> {
            String myPhone = phone.getText().toString();
            if (myPhone.isEmpty()){
                Toast.makeText(this, "Enter phone number to reset password.", Toast.LENGTH_SHORT).show();
            } else if (myPhone.length() > 9) {
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT);
            }else {
                Toast.makeText(this, "PASS", Toast.LENGTH_SHORT);
            }
        });
    }

    private void resetInit(){
        phone = findViewById(R.id.phoneInput);
        resetOPT = findViewById(R.id.resetPasswordBtn);
        signIn = findViewById(R.id.signInTxt);
    }
}