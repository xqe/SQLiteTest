package com.example.sqltest.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import static com.example.sqltest.sqlitetest.SQLiteHelper.KEY_ADDRESS;
import static com.example.sqltest.sqlitetest.SQLiteHelper.KEY_AGE;
import static com.example.sqltest.sqlitetest.SQLiteHelper.KEY_NAME;
import static com.example.sqltest.sqlitetest.SQLiteHelper.TABLE_NAME;

public class SQLiteManager {

    private SQLiteHelper sqLiteHelper;
    private static volatile SQLiteManager instance;

    private SQLiteManager(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    public static SQLiteManager getInstance(Context context) {
        if (instance == null) {
            synchronized (SQLiteManager.class) {
                if (instance == null) {
                    instance = new SQLiteManager(context);
                }
            }
        }
        return instance;
    }

    public List<DataBean> queryFromDB() {
        List<DataBean> result = null;
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        return result;
    }

    public int addDataToDB(DataBean dataBean){
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,dataBean.getName());
        cv.put(KEY_ADDRESS,dataBean.getAddress());
        cv.put(KEY_AGE,dataBean.getAge());
        int insertCount = (int) db.insert(TABLE_NAME,null,cv);
        String addCMD = "insert into " + TABLE_NAME
                + "(" + KEY_NAME  + ","+ KEY_ADDRESS +","+ KEY_AGE +")"
                + " values" +  "(" + dataBean.getName() + "," + dataBean.getAddress() + "," + dataBean.getAge() +");";
        //db.execSQL(addCMD);

        return insertCount;
    }

    public void deleteFromDB() {

    }

    public void updateToDB(DataBean dataBean,int key) {

    }

}
