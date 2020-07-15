package com.example.billbook.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.billbook.annotation.Database;

@Database(name = "billbook")
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context,name,cursorFactory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase db){
        db.execSQL("create table if not exists billrecord(" +
                "id integer primary key autoincrement," +
                "time varchar(255) not null," +
                "value int(11) not null," +
                "type int(11) not null,"+
                "note varchar(255) default null)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
