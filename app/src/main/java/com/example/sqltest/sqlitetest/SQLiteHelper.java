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
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "test.db";
    static final String TABLE_NAME = "test_table_name";
    private static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_ADDRESS = "address";
    static final String KEY_WORK = "work";
    static final String KEY_AGE = "age";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.e(TAG, "SQLiteHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate");
        db.execSQL("drop table if exists " + TABLE_NAME);
        String createTableCMD =
                "create table if not exists " + TABLE_NAME
                + "( " + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " string,"
                + KEY_ADDRESS + " string,"
                + KEY_WORK + " integer);";
        db.execSQL(createTableCMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade:" + oldVersion + "," + newVersion);
        if (oldVersion > 3) {
            return;
        }
        try {
            db.beginTransaction();
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_WORK + " string; ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        Cursor cursor = null;
        db.beginTransaction();
        ArrayList<DataBean> list = new ArrayList<>();
        try{
            String sqlString = "select * from " + TABLE_NAME + " order by " + KEY_ID;
            cursor = db.rawQuery(sqlString, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    DataBean dataBean = new DataBean.Builder()
                            .age(cursor.getInt(cursor.getColumnIndex(KEY_AGE)))
                            .name(cursor.getString(cursor.getColumnIndex(KEY_NAME)))
                            .address(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)))
                            .build();
                    list.add(dataBean);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.endTransaction();
        }
        try {
            db.beginTransaction();
            Log.e(TAG, "onUpgrade: " + list.size());
            for (DataBean dataBean : list) {
                String updateSize = "UPDATE " + TABLE_NAME
                        + " SET " + KEY_WORK + " = 'Android'"
                        + " WHERE " +  KEY_ID + " = " + (list.indexOf(dataBean) + 1);
                db.execSQL(updateSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        if (oldVersion != newVersion) {
            db.beginTransaction();
            onCreate(db);
            db.setTransactionSuccessful();
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
