package com.example.androidtest.tool;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class getPath {
    public static String get(Uri uri, Cursor cursor)
    {
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static String getPath(Uri uri, Cursor cursor, String path) {
        if(path.substring(path.length()-3).equals("jpg")) {
            path="/storage/self/primary/"+path.substring(path.length()-34);
        }
        else{
            path= getPath.get(uri,cursor);
        }
        return path;
    }
}
