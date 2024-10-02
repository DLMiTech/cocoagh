package com.example.cocoagh.lbc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocoagh.R;
import com.example.cocoagh.adapters.MarketBeansAdapter;
import com.example.cocoagh.models.Beans;
import com.example.cocoagh.repo.BeansRepo;

import java.util.List;

public class LbcSearch extends AppCompatActivity {

    private ImageView backBtn;
    private EditText indexNumber;
    private Button search;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lbc_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backBtn.setOnClickListener(view -> {
            finish();
        });

        search.setOnClickListener(view -> {
            String mySearch = indexNumber.getText().toString();
            if (mySearch.isEmpty()){
                Toast.makeText(this, "Enter data to search.", Toast.LENGTH_SHORT).show();
            }else {
                try {

                    BeansRepo beansRepo = new BeansRepo(this);
                    List<Beans> beansList = beansRepo.searchBeans(mySearch);

                    // Create and set the adapter
                    MarketBeansAdapter adapter = new MarketBeansAdapter(this, beansList);
                    gridView.setAdapter(adapter);


                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void init(){
        backBtn = findViewById(R.id.backBtn);
        indexNumber = findViewById(R.id.indexInput);
        search = findViewById(R.id.searchBtn);
        gridView = findViewById(R.id.beans_grid_view);
    }
}