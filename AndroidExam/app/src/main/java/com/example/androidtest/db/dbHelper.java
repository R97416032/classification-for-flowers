package com.example.androidtest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public final static String CREATE_INFO="create table Info ("
            +"username text primary key ,"
            +"password text ,"
            +"ak text ,"
            +"sk text)";
    private Context mContent;
    public dbHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContent=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
