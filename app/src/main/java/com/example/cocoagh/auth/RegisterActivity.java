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
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.UserRepo;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullName, username, phone, password;
    private Button signUp;
    private TextView signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUpInit();

        signIn.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        signUp.setOnClickListener(view -> {
            String myFullName = fullName.getText().toString();
            String myUsername = username.getText().toString();
            String myPhone = phone.getText().toString();
            String myPassword = password.getText().toString();

            if (myFullName.isEmpty() || myUsername.isEmpty() || myPhone.isEmpty() || myPassword.isEmpty()){
                Toast.makeText(this, "All input are required to register.", Toast.LENGTH_SHORT).show();
            } else if (myPhone.length() < 9) {
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            } else if (myPassword.length() < 6) {
                Toast.makeText(this, "Password must have more than 6 charters.", Toast.LENGTH_SHORT).show();
            }else {
                Users users = new Users();
                UserRepo userRepo = new UserRepo(this);
                if (userRepo.userExists(myUsername)){
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.alert_w)
                            .setTitle("CoCoa GH")
                            .setMessage("Username "+myUsername+" already used!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }else {
                    users.setName(myFullName);
                    users.setUsername(myUsername);
                    users.setPhone(myPhone);
                    users.setPassword(myPassword);
                    if (userRepo.registerUser(users)){
                        new AlertDialog.Builder(this)
                                .setIcon(R.drawable.alert_g)
                                .setTitle("CoCoa GH")
                                .setMessage("Registration successfully.")
                                .setCancelable(false)
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {}
                                })

                                .setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                })
                                .show();
                    }else {
                        new AlertDialog.Builder(this)
                                .setIcon(R.drawable.alert_r)
                                .setTitle("CoCoa GH")
                                .setMessage("Error registering user, Try again.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                }

            }
        });
    }


    public void signUpInit(){
        fullName = findViewById(R.id.fullNameInput);
        username = findViewById(R.id.usernameInput);
        phone = findViewById(R.id.phoneInput);
        password = findViewById(R.id.passwordInput);
        signUp = findViewById(R.id.signUpBtn);
        signIn = findViewById(R.id.signInTxt);
    }
}