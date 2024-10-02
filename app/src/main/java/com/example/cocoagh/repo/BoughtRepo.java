package com.example.cocoagh.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocoagh.models.Beans;
import com.example.cocoagh.models.Bought;
import com.example.cocoagh.res.DBAccess;

import java.util.ArrayList;
import java.util.List;

public class BoughtRepo extends DBAccess {
    public BoughtRepo(Context context) {
        super(context);
    }

    private static final String TABLE_NAME = "bought";

    public boolean addBought(Bought bought){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lbcId", bought.getLbcId());
        values.put("farmerId", bought.getFarmerId());
        values.put("lbcName", bought.getLbcName());
        values.put("lbcPhone", bought.getLbcPhone());
        values.put("farmerName", bought.getFarmerName());
        values.put("farmerPhone", bought.getFarmerPhone());
        values.put("farmerLocation", bought.getFarmerLocation());
        values.put("quantity", bought.getQuantity());
        values.put("total", bought.getTotal());
        values.put("payerName", bought.getPayerName());
        values.put("paymentType", bought.getPaymentType());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }



    @SuppressLint("Range")
    public List<Bought> getBoughtBeansByLBC(int lbcId) {
        List<Bought> boughtList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all farms by the given farmerId
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE lbcId = ?", new String[]{String.valueOf(lbcId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int lbcIdz = cursor.getInt(cursor.getColumnIndex("lbcId"));
                int farmerId = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String lbcName  = cursor.getString(cursor.getColumnIndex("lbcName"));
                String lbcPhone = cursor.getString(cursor.getColumnIndex("lbcPhone"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmerName"));
                String farmerPhone = cursor.getString(cursor.getColumnIndex("farmerPhone"));
                String farmerLocation = cursor.getString(cursor.getColumnIndex("farmerLocation"));
                String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
                String total = cursor.getString(cursor.getColumnIndex("total"));
                String payerName = cursor.getString(cursor.getColumnIndex("payerName"));
                String paymentType = cursor.getString(cursor.getColumnIndex("paymentType"));
                String dateBought = cursor.getString(cursor.getColumnIndex("dateBought"));


                Bought bought = new Bought(id, lbcIdz, farmerId, lbcName, lbcPhone, farmerName, farmerPhone, farmerLocation, quantity, total, payerName, paymentType, dateBought);
                boughtList.add(bought);

            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return boughtList;
    }




    @SuppressLint("Range")
    public List<Bought> getBoughtBeansForFarmer(int farmerId) {
        List<Bought> boughtList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all farms by the given farmerId
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE farmerId = ?", new String[]{String.valueOf(farmerId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int lbcIdz = cursor.getInt(cursor.getColumnIndex("lbcId"));
                int farmerIdz = cursor.getInt(cursor.getColumnIndex("farmerId"));
                String lbcName  = cursor.getString(cursor.getColumnIndex("lbcName"));
                String lbcPhone = cursor.getString(cursor.getColumnIndex("lbcPhone"));
                String farmerName = cursor.getString(cursor.getColumnIndex("farmerName"));
                String farmerPhone = cursor.getString(cursor.getColumnIndex("farmerPhone"));
                String farmerLocation = cursor.getString(cursor.getColumnIndex("farmerLocation"));
                String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
                String total = cursor.getString(cursor.getColumnIndex("total"));
                String payerName = cursor.getString(cursor.getColumnIndex("payerName"));
                String paymentType = cursor.getString(cursor.getColumnIndex("paymentType"));
                String dateBought = cursor.getString(cursor.getColumnIndex("dateBought"));


                Bought bought = new Bought(id, lbcIdz, farmerIdz, lbcName, lbcPhone, farmerName, farmerPhone, farmerLocation, quantity, total, payerName, paymentType, dateBought);
                boughtList.add(bought);

            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return boughtList;
    }
}
