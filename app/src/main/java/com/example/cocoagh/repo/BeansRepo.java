package com.example.cocoagh.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Inputs;
import com.example.cocoagh.res.DBAccess;

import java.util.ArrayList;
import java.util.List;

public class BeansRepo extends DBAccess {
    public BeansRepo(Context context) {
        super(context);
    }

    private static final String TABLE_NAME = "beans";

    public boolean addBeans(Beans beans){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farmerId", beans.getFarmerId());
        values.put("farmerName", beans.getFarmerName());
        values.put("farmerPhone", beans.getPhone());
        values.put("quantity", beans.getQuantity());
        values.put("price", beans.getPrice());
        values.put("total", beans.getTotal());
        values.put("address", beans.getAddress());
        values.put("status", beans.getStatus());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }


    public void deleteBeans(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    @SuppressLint("Range")
    public List<Beans> getAllBeans() {
        List<Beans> beansList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int farmerId = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmerName"));
                String farmerPhone = cursor.getString(cursor.getColumnIndex("farmerPhone"));
                double quantity = cursor.getDouble(cursor.getColumnIndex("quantity"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                double total = cursor.getDouble(cursor.getColumnIndex("total"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String addedDate = cursor.getString(cursor.getColumnIndex("addedDate"));
                String status = cursor.getString(cursor.getColumnIndex("status"));

                Beans beans = new Beans(id, farmerId, quantity, price, total, address, farmerPhone, farmerName, addedDate, status);
                beansList.add(beans);


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return beansList;
    }



    @SuppressLint("Range")
    public List<Beans> getBeansByFarmerId(int farmerId) {
        List<Beans> beansList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all farms by the given farmerId
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE farmerId = ?", new String[]{String.valueOf(farmerId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int farmerIdz = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmerName"));
                String farmerPhone = cursor.getString(cursor.getColumnIndex("farmerPhone"));
                double quantity = cursor.getDouble(cursor.getColumnIndex("quantity"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                double total = cursor.getDouble(cursor.getColumnIndex("total"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String addedDate = cursor.getString(cursor.getColumnIndex("addedDate"));
                String status = cursor.getString(cursor.getColumnIndex("status"));

                Beans beans = new Beans(id, farmerId, quantity, price, total, address, farmerPhone, farmerName, addedDate, status);
                beansList.add(beans);

            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return beansList;
    }
}
