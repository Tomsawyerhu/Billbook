package com.example.billbook.data.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.billbook.annotation.Database;
import com.example.billbook.data.database.DatabaseHelper;

public abstract class TableOperationHelper {
    private DatabaseHelper helper;

    TableOperationHelper(Context context){
        Class databaseHelper=DatabaseHelper.class;
        Database database=(Database) databaseHelper.getAnnotation(Database.class);
        String name=database.name();
        helper=new DatabaseHelper(context,name,null,1);
    }

    public SQLiteDatabase getDataBase(){
        return helper.getWritableDatabase();
    }
}
