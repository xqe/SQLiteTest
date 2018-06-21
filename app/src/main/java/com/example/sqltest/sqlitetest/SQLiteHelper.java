package com.example.sqltest.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String TAG = "SQLiteHelper";
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "test.db";
    static final String TABLE_NAME = "test_table_name";
    private static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_ADDRESS = "address";
    static final String KEY_WORK = "work";
    static final String KEY_AGE = "age";

    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME
            + "( " + KEY_ID + " integer primary key autoincrement,"
            + KEY_NAME + " string,"
            + KEY_ADDRESS + " string,"
            + KEY_AGE + " integer,"
            + KEY_WORK + " string);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.e(TAG, "SQLiteHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade===:" + oldVersion + "," + newVersion);
        try{
            db.beginTransaction();//需要批处理时则开启事务

            switch (newVersion) {
                case 1:
                    db.execSQL(CREATE_TABLE);
                    break;
                case 2:
                    String addColumn = "alter table " + TABLE_NAME + " add column " + KEY_AGE + " integer";
                    db.execSQL(addColumn);
                    break;
                default:
            }

            db.setTransactionSuccessful();
        }catch (Exception ex) {
            ex.printStackTrace();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

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
