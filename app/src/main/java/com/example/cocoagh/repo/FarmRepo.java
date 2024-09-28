package com.example.cocoagh.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocoagh.models.Farms;
import com.example.cocoagh.res.DBAccess;

import java.util.ArrayList;
import java.util.List;

public class FarmRepo extends DBAccess {
    public FarmRepo(Context context) {
        super(context);
    }
    private static final String TABLE_NAME = "farms";

    // Method to add a farm
    public boolean addFarm(Farms farms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farmerId", farms.getFarmerId());
        values.put("size", farms.getSize());
        values.put("stage", farms.getStage());
        values.put("location", farms.getLocation());
        values.put("farmName", farms.getFarmName());
//        values.put("image", farms.getImage());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }


    // Method to delete a farm by id
    public void deleteFarm(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    // Method to update a farm
    public void updateFarm(Farms farms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farmerId", farms.getFarmerId());
        values.put("size", farms.getSize());
        values.put("stage", farms.getStage());
        values.put("location", farms.getLocation());
        values.put("farmName", farms.getFarmName());
//        values.put("image", farms.getImage());
        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(farms.getId())});
        db.close();
    }



    @SuppressLint("Range")
    public List<Farms> getAllFarms() {
        List<Farms> farmsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int farmerId = cursor.getInt(cursor.getColumnIndex("farmerId"));
                double farmSize = cursor.getDouble(cursor.getColumnIndex("size"));
                String farmStage = cursor.getString(cursor.getColumnIndex("stage"));
                String farmLocation = cursor.getString(cursor.getColumnIndex("location"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmName"));

                Farms farms = new Farms(id, farmerId, farmSize, farmStage, farmLocation, farmerName, image);
                farmsList.add(farms);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return farmsList;
    }



    @SuppressLint("Range")
    public List<Farms> getFarmsByFarmerId(int farmerId) {
        List<Farms> farmsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all farms by the given farmerId
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE farmerId = ?", new String[]{String.valueOf(farmerId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                double farmSize = cursor.getDouble(cursor.getColumnIndex("size"));
                String farmStage = cursor.getString(cursor.getColumnIndex("stage"));
                String farmLocation = cursor.getString(cursor.getColumnIndex("location"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmName"));

                // Create a new Farms object and add it to the list
                Farms farms = new Farms(id, farmerId, farmSize, farmStage, farmLocation, farmerName, image);
                farmsList.add(farms);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return farmsList;
    }

}
