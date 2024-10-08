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
                "userType INTEGER, " +
                "dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP);";


        String CREATE_FARM_TABLE = "CREATE TABLE  farms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "farmerId INTEGER, " +
                "size REAL, " +
                "stage TEXT, " +
                "location TEXT, " +
                "farmName TEXT, " +
                "image BLOB)";


        String CREATE_INPUT_TABLE = "CREATE TABLE  inputs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "farmerId INTEGER," +
                "farmName TEXT," +
                "type TEXT," +
                "qty TEXT," +
                "note TEXT," +
                "status TEXT DEFAULT 'Pending'," +
                "requestDate DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";

        String CREATE_BEANS = "CREATE TABLE beans(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "farmerId INTEGER," +
                "farmerName TEXT," +
                "farmerPhone TEXT," +
                "quantity REAL," +
                "price REAL," +
                "total REAL," +
                "address TEXT," +
                "status TEXT," +
                "addedDate DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";

        String CREATE_BOUGHT = "CREATE TABLE bought(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "lbcId INTEGER," +
                "farmerId INTEGER," +
                "lbcName TEXT," +
                "lbcPhone TEXT," +
                "farmerName TEXT," +
                "farmerPhone TEXT," +
                "farmerLocation TEXT," +
                "quantity TEXT," +
                "total TEXT," +
                "payerName TEXT," +
                "paymentType TEXT," +
                "dateBought DATETIME DEFAULT CURRENT_TIMESTAMP)";




        sqLiteDatabase.execSQL(CREATE_FARM_TABLE);
        sqLiteDatabase.execSQL(CREATE_INPUT_TABLE);
        sqLiteDatabase.execSQL(createUsersTable);
        sqLiteDatabase.execSQL(CREATE_BEANS);
        sqLiteDatabase.execSQL(CREATE_BOUGHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropUsersTable = "DROP TABLE IF EXISTS users;";
        String dropFarmTable = "DROP TABLE IF EXISTS farms;";
        String dropInputTable = "DROP TABLE IF EXISTS inputs;";
        String dropBeansTable = "DROP TABLE IF EXISTS inputs;";
        String dropBoughtTable = "DROP TABLE IF EXISTS bought;";

        // Drop existing tables
        sqLiteDatabase.execSQL(dropUsersTable);
        sqLiteDatabase.execSQL(dropFarmTable);
        sqLiteDatabase.execSQL(dropInputTable);
        sqLiteDatabase.execSQL(dropBeansTable);
        sqLiteDatabase.execSQL(dropBoughtTable);

        // Recreate the tables
        onCreate(sqLiteDatabase);
    }
}
