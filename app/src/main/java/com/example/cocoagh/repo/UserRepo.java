package com.example.cocoagh.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.cocoagh.R;
import com.example.cocoagh.farmer.DashboardF;
import com.example.cocoagh.lbc.DashboardLBC;
import com.example.cocoagh.models.Users;
import com.example.cocoagh.res.DBAccess;

import java.util.ArrayList;
import java.util.List;

public class UserRepo extends DBAccess {
    public UserRepo(Context context) {
        super(context);
    }

    public boolean userExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "users",
                new String[]{"id"},
                "username=?",
                new String[]{username},
                null, null, null
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    // Method to register a user
    public boolean registerUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("username", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("password", user.getPassword());

        long result = db.insert("users", null, values);
        return result != -1; // Return true if insert was successful
    }




    @SuppressLint("Range")
    public void loginUser(String username, String password, Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "users",
                new String[]{"id", "name", "username", "phone", "userType"},
                "username=? AND password=?",
                new String[]{username, password},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Get user information
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String userUsername = cursor.getString(cursor.getColumnIndex("username"));
            String userPhone = cursor.getString(cursor.getColumnIndex("phone"));
            int userType = cursor.getInt(cursor.getColumnIndex("userType"));

            // Close the cursor
            cursor.close();

            // Create an Intent to start the appropriate activity
            Intent intent;
            if (userType == 0) {
                // Start Activity 1
                intent = new Intent(context, DashboardF.class);
            } else {
                // Start Activity 2
                intent = new Intent(context, DashboardLBC.class);
            }

            // Assuming you get the user ID and name after login
            SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("user_id", id); // Save user ID
            editor.putString("user_name", name); // Save user name
            editor.apply();


            // Pass the user information to the new activity
            intent.putExtra("id", id);
            intent.putExtra("username", userUsername);
            intent.putExtra("name", name);
            intent.putExtra("phone", userPhone);
            intent.putExtra("userType", userType);

            // Start the activity
            context.startActivity(intent);

            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor != null) {
                cursor.close();
            }
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.alert_w)
                    .setTitle("CoCoa GH")
                    .setMessage("Login failed. Please check your credentials.")
                    .setCancelable(false)
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Handle "Try Again" action
                        }
                    })
                    .show();
        }
    }




    // Method to delete a user
    public boolean deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("users", "id=?", new String[]{String.valueOf(userId)});
        return result > 0; // Return true if delete was successful
    }

    // Method to update a user
    public boolean updateUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("username", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("password", user.getPassword());
        values.put("userType", user.getUserType());

        int result = db.update("users", values, "id=?", new String[]{String.valueOf(user.getId())});
        return result > 0; // Return true if update was successful
    }


    public int getTotalUsersByType(int userType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users WHERE userType = ?", new String[]{String.valueOf(userType)});

        int totalCount = 0;
        if (cursor.moveToFirst()) {
            totalCount = cursor.getInt(0); // Get the count from the first column
        }

        cursor.close();
        db.close();
        return totalCount; // Return the total count of users
    }




    // Method to get all users
    @SuppressLint("Range")
    public List<Users> getAllUsers() {
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users",
                new String[]{"id", "name", "username", "phone", "password", "userType", "dateCreated"},
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Users user = new Users();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setUserType(cursor.getInt(cursor.getColumnIndex("userType")));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return userList;
    }


    @SuppressLint("Range")
    public List<Users> getUsersByType(int userType) {
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all users by the given userType
        Cursor cursor = db.query("users",
                new String[]{"id", "name", "username", "phone", "password", "userType", "dateCreated"},
                "userType = ?",
                new String[]{String.valueOf(userType)},
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Users user = new Users();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setUserType(cursor.getInt(cursor.getColumnIndex("userType")));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return userList;
    }



    // Method to get one user by ID
    @SuppressLint("Range")
    public Users getOneUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users",
                new String[]{"id", "name", "username", "phone", "password", "userType", "dateCreated"},
                "id=?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Users user = new Users();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setUserType(cursor.getInt(cursor.getColumnIndex("userType")));
            cursor.close();
            return user;
        }
        return null;
    }
}
