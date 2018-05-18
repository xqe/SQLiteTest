package com.example.sqltest.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String TAG = "SQLiteHelper";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test.db";
    static final String TABLE_NAME = "test_table_name";
    private static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_ADDRESS = "address";
    static final String KEY_AGE = "age";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.e(TAG, "SQLiteHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate");
        String createTableCMD =
                "create table if not exists " + TABLE_NAME
                + "( " + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " string,"
                + KEY_ADDRESS + " string,"
                + KEY_AGE + " integer);";
        db.execSQL(createTableCMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.e(TAG, "onOpen: ");
    }
}
