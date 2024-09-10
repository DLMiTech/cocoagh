package com.example.cocoagh.res;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAccess extends SQLiteOpenHelper {
    private static final String DB_NAME = "cocoa_db";
    private static final int DB_VERSION = 1;

    public DBAccess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createUsersTable = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "username TEXT UNIQUE, " +
                "phone TEXT, " +
                "password TEXT, " +
                "userType INTEGER DEFAULT 0, " +
                "dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP);";

        String CREATE_FARM_TABLE = "CREATE TABLE  farms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "farmerId INTEGER, " +
                "size REAL, " +
                "stage TEXT, " +
                "location TEXT, " +
                "farmName TEXT, " +
                "image BLOB)";


        sqLiteDatabase.execSQL(CREATE_FARM_TABLE);
        sqLiteDatabase.execSQL(createUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropUsersTable = "DROP TABLE IF EXISTS users;";
        String dropFarmTable = "DROP TABLE IF EXISTS farms;";

        // Drop existing tables
        sqLiteDatabase.execSQL(dropUsersTable);
        sqLiteDatabase.execSQL(dropFarmTable);

        // Recreate the tables
        onCreate(sqLiteDatabase);
    }
}
