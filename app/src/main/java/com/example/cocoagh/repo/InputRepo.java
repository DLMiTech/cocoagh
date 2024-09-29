package com.example.cocoagh.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocoagh.models.Farms;
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.res.DBAccess;

import java.util.ArrayList;
import java.util.List;

public class InputRepo extends DBAccess {
    public InputRepo(Context context) {
        super(context);
    }
    private static final String TABLE_NAME = "inputs";


    public boolean addInput(Inputs inputs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farmerId", inputs.getFarmerId());
        values.put("farmName", inputs.getFarmName());
        values.put("type", inputs.getInputType());
        values.put("qty", inputs.getInputQty());
        values.put("note", inputs.getInputNote());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }


    public void deleteInput(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    public int getTotalInputs() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM inputs", null);

        int totalCount = 0;
        if (cursor.moveToFirst()) {
            totalCount = cursor.getInt(0); // Get the count from the first column
        }

        cursor.close();
        db.close();
        return totalCount; // Return the total count of users
    }


    public void updateInputStatus(int id, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);

        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    public void updateInput(Inputs inputs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farmerId", inputs.getFarmerId());
        values.put("farmName", inputs.getFarmName());
        values.put("type", inputs.getInputType());
        values.put("qty", inputs.getInputQty());
        values.put("note", inputs.getInputNote());

        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(inputs.getId())});
        db.close();
    }



    @SuppressLint("Range")
    public List<Inputs> getAllInput() {
        List<Inputs> inputsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int farmerId = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmName"));
                String inputType = cursor.getString(cursor.getColumnIndex("type"));
                String inputQty = cursor.getString(cursor.getColumnIndex("qty"));
                String inputNote = cursor.getString(cursor.getColumnIndex("note"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                String requestDate = cursor.getString(cursor.getColumnIndex("requestDate"));

                Inputs inputs = new Inputs(id, farmerId, farmerName, inputType, inputQty, inputNote, status, requestDate);
                inputsList.add(inputs);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return inputsList;
    }




    @SuppressLint("Range")
    public List<Inputs> getInputByFarmerId(int farmerId) {
        List<Inputs> inputsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all farms by the given farmerId
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE farmerId = ?", new String[]{String.valueOf(farmerId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int farmerIdz = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmName"));
                String inputType = cursor.getString(cursor.getColumnIndex("type"));
                String inputQty = cursor.getString(cursor.getColumnIndex("qty"));
                String inputNote = cursor.getString(cursor.getColumnIndex("note"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                String requestDate = cursor.getString(cursor.getColumnIndex("requestDate"));

                // Create a new Farms object and add it to the list
                Inputs inputs = new Inputs(id, farmerIdz, farmerName, inputType, inputQty, inputNote, status, requestDate);
                inputsList.add(inputs);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return inputsList;
    }

}
