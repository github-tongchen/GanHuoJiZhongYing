package com.tongchen.ganhuojizhongying.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TongChen on 2017/7/13.
 * <p>
 * Description:
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GanHuo.db";
    private static final int DATABASE_VERSION = 1;

    private static DBHelper instance = null;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
