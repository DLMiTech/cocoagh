package com.example.cocoagh.flagments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.AddBeans;
import com.example.cocoagh.farmer.ViewFarms;
import com.example.cocoagh.models.Farms;
import com.example.cocoagh.repo.FarmRepo;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class Farm_f extends Fragment {

    private static final int ARG_USERID = -1;
    private static final String ARG_NAME = "name";
    private EditText location, hectares;
    private Button saveFarm;

    private ImageButton chooseImg;
    private ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;


    private String selectedStage;
    String[] positions = {"Growing Cocoa Trees", "Harvesting Cocoa Trees"};

    public Farm_f() {
        // Required empty public constructor
    }

    public static Farm_f newInstance(Integer userId, String userName) {
        Farm_f fragment = new Farm_f();
        Bundle args = new Bundle();
        args.putInt(String.valueOf(ARG_USERID), userId);
        args.putString(ARG_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_farm_f, container, false);

        TextView addBeansBtn = view.findViewById(R.id.addBeansTxt);

        addBeansBtn.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AddBeans.class));
        });

        AutoCompleteTextView farmStatus = view.findViewById(R.id.farmStatsInput);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(requireActivity(), R.layout.list_items, positions);
        farmStatus.setAdapter(adapterItems);

        location = view.findViewById(R.id.farmLocationInput);
        hectares = view.findViewById(R.id.farmHectaresInput);
        saveFarm = view.findViewById(R.id.saveCandidateBtn);
        chooseImg = view.findViewById(R.id.chooseImageBtn);
        imageView = view.findViewById(R.id.candidateImage);



        farmStatus.setOnItemClickListener((adapterView, f_view, i, l) -> {
            selectedStage = adapterView.getItemAtPosition(i).toString();
        });

        chooseImage();

        saveFarm.setOnClickListener(view1 -> {
            String myStage = selectedStage;

            // Check if the image is selected
            if (imageView.getDrawable() == null) {
                Toast.makeText(getActivity(), "Please select an image for candidate.", Toast.LENGTH_SHORT).show();
                return;
            } else if (location.getText().toString().isEmpty() || hectares.getText().toString().isEmpty() || myStage == null || myStage.isEmpty()) {
                Toast.makeText(getActivity(), "Please all inputs are required to add farm.", Toast.LENGTH_SHORT).show();
                return;
            }


            assert getArguments() != null;
            int id = getArguments().getInt(String.valueOf(ARG_USERID));
            String name = getArguments().getString(ARG_NAME);
            String myLocation = location.getText().toString();
            int mySize = Integer.parseInt(hectares.getText().toString());
            byte[] myFarmImage = imageViewToByte(imageView);

            Farms farms = new Farms();
            farms.setFarmerId(id);
            farms.setFarmName(name);
            farms.setLocation(myLocation);
            farms.setSize(mySize);
            farms.setImage(myFarmImage);
            farms.setStage(myStage);

            try {
                FarmRepo farmRepo = new FarmRepo(getActivity());

                boolean result = farmRepo.addFarm(farms);
                if (result){
                    Toast.makeText(getActivity(), "Farm information added successfully", Toast.LENGTH_SHORT).show();
                    location.setText("");
                    hectares.setText("");
                    selectedStage = null;
                    imageView.setImageResource(R.drawable.d_cocoa);
                }else {
                    Toast.makeText(getActivity(), "Error adding new farm.", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Log.e("Database Error", e.getMessage());
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }


        });

        return view;

    }





    private byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    // Open Gallery if permission is granted
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(getActivity(), "You don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void chooseImage(){
        chooseImg.setOnClickListener(view -> {
            // Request the permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_MEDIA_IMAGES)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                            REQUEST_CODE_GALLERY);
                } else {
                    openGallery();
                }
            } else if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            } else {
                openGallery();
            }
        });
    }

}