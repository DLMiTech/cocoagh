package com.example.cocoagh.auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.DashboardF;
import com.example.cocoagh.govemment.DashboardG;
import com.example.cocoagh.lbc.DashboardLBC;
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.UserRepo;

public class LoginActivity extends AppCompatActivity {

    private TextView forgotPassword, signUp;
    private EditText username, password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signInInit();

        signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        forgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
            finish();
        });


        signIn.setOnClickListener(view -> {
            String adminUsername = "admin";
            String adminPassword = "admin@1234";
            String myUsername = username.getText().toString();
            String myPassword = password.getText().toString();

            if (myUsername.isEmpty() || myPassword.isEmpty()){
                Toast.makeText(this, "Enter username and password to sign in.", Toast.LENGTH_SHORT).show();
            } else if (myUsername.equals(adminUsername) && myPassword.equals(adminPassword)) {
                startActivity(new Intent(this, DashboardG.class));
                finish();
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            } else {

                UserRepo userRepo = new UserRepo(this);
                userRepo.loginUser(myUsername, myPassword, this);

            }
        });
    }

    public void signInInit(){
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        forgotPassword =  findViewById(R.id.forgotPasswordTxt);
        signUp = findViewById(R.id.signUpTxt);
        signIn = findViewById(R.id.signInBtn);
    }
}