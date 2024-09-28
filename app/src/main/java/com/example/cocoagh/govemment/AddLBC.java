package com.example.cocoagh.govemment;

import android.content.Intent;
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
import com.example.cocoagh.models.Users;
import com.example.cocoagh.repo.UserRepo;

public class AddLBC extends AppCompatActivity {

    private ImageView backBtn, viewLBC;
    private EditText name, username, email, phone, password;
    private Button saveLBC;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lbc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, DashboardG.class));
            finish();
        });
        
        saveLBC.setOnClickListener(view -> {
            String myName = name.getText().toString();
            String myUsername = username.getText().toString();
            String myEmail = email.getText().toString();
            String myPhone = phone.getText().toString();
            String myPassword = password.getText().toString();

            if (myName.isEmpty() || myUsername.isEmpty() || myEmail.isEmpty() || myPhone.isEmpty() || myPassword.isEmpty()){
                Toast.makeText(this, "Please enter all inputs", Toast.LENGTH_SHORT).show();
            } else if (myPassword.length() < 6) {
                Toast.makeText(this, "Enter a strong password", Toast.LENGTH_SHORT).show();
            } else {
                Users users = new Users();
                users.setName(myName);
                users.setUsername(myUsername);
                users.setPhone(myPhone);
                users.setPassword(myPassword);
                users.setUserType(2);

                Toast.makeText(this, "UserType"+users.getUserType(), Toast.LENGTH_SHORT).show();

                UserRepo userRepo = new UserRepo(this);
                try {
                    boolean result = userRepo.registerUser(users);
                    if (result){
                        Toast.makeText(this, "LBC added successfully.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Error adding LBC", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
            Toast.makeText(this, "Add LBC", Toast.LENGTH_SHORT).show();
        });
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        viewLBC = findViewById(R.id.viewLBC);
        saveLBC = findViewById(R.id.addLBCBtn);
    }
}