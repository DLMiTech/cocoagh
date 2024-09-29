package com.example.cocoagh.farmer;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
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
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.repo.InputRepo;

public class RequestInputs extends AppCompatActivity {

    private ImageView backBtn;
    private EditText inputType, inputQty, inputNote;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_inputs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int farmerId = sharedPreferences.getInt("user_id", -1); // Retrieve user ID
        String farmerName = sharedPreferences.getString("user_name", ""); // Retrieve user name



        backBtn.setOnClickListener(view -> {
            finish();
        });
        
        submit.setOnClickListener(view -> {
            String myInputType = inputType.getText().toString();
            String myInputQty = inputQty.getText().toString();
            String myInputNote = inputNote.getText().toString();
            
            if (myInputType.isEmpty() || myInputQty.isEmpty()){
                Toast.makeText(this, "Please enter input type and quantity", Toast.LENGTH_SHORT).show();
            }else {
                Inputs inputs = new Inputs();
                inputs.setFarmerId(farmerId);
                inputs.setFarmName(farmerName);
                inputs.setInputType(myInputType);
                inputs.setInputQty(myInputQty);
                inputs.setInputNote(myInputNote);

//                Toast.makeText(this, "ID" + inputs.getFarmerId()+", "+inputs.getFarmName(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "Input" + inputs.getInputType()+", "+inputs.getInputQty()+ ", "+inputs.getInputNote(), Toast.LENGTH_SHORT).show();


                try {
                    InputRepo inputRepo = new InputRepo(this);
                    boolean result = inputRepo.addInput(inputs);
                    if (result) {
                        inputType.setText("");
                        inputQty.setText("");
                        inputNote.setText("");
                        Toast.makeText(this, "Input requested successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error inserting input", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLiteException e) {
                    Log.e("Database Error", e.getMessage());
                    Toast.makeText(this, "Database error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);

        inputType = findViewById(R.id.inputType);
        inputQty = findViewById(R.id.inputQty);
        inputNote = findViewById(R.id.inputNote);

        submit = findViewById(R.id.submitInputBtn);
    }
}