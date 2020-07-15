package com.example.billbook.data.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.example.billbook.data.model.BillRecord;
import com.example.billbook.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

public class BillOperationHelper extends TableOperationHelper {
    private static final String TABLE_NAME="billrecord";
    private SQLiteDatabase database;

    private TimeUtils timeUtils=new TimeUtils();

    public BillOperationHelper(Context context) {
        super(context);
        this.database=super.getDataBase();
    }

    public void insert(BillRecord record){
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",record.getTime());
        contentValues.put("value",record.getValue());
        contentValues.put("type",record.getType());
        contentValues.put("note",record.getNote());
        long size=database.insert(TABLE_NAME,null,contentValues);
        Log.d("size","size"+size);
    }

    public List<BillRecord> getRecordsByType(int t){
        List<BillRecord> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME+" where type=?",new String[]{String.valueOf(t)});
        while(cursor.moveToNext()){
            BillRecord record=new BillRecord();
            fillRecord(cursor,record);
            list.add(record);
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<BillRecord> getRecordsBetween(LocalDateTime start, LocalDateTime end) throws Exception {
        List<BillRecord> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
        while(cursor.moveToNext()){
            String time = cursor.getString(1);
            LocalDateTime localDateTime =timeUtils.toLocalDateTime(time);
            if(timeUtils.isBeforeOrEqualOrAfterOrBetween(localDateTime,start,end)==2){
                BillRecord record=new BillRecord();
                fillRecord(cursor,record);
                list.add(record);
            }
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<BillRecord> getRecordsBefore(LocalDateTime t) throws Exception {
        List<BillRecord> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
        while(cursor.moveToNext()){
            String time = cursor.getString(1);
            LocalDateTime localDateTime =timeUtils.toLocalDateTime(time);
            if(timeUtils.isBeforeOrEqualOrAfterOrBetween(localDateTime,t,null)==-1){
                BillRecord record=new BillRecord();
                fillRecord(cursor,record);
                list.add(record);
            }
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<BillRecord> getRecordsAfter(LocalDateTime t) throws Exception {
        List<BillRecord> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
        while(cursor.moveToNext()){
            String time = cursor.getString(1);
            LocalDateTime localDateTime =timeUtils.toLocalDateTime(time);
            if(timeUtils.isBeforeOrEqualOrAfterOrBetween(localDateTime,t,null)==1){
                BillRecord record=new BillRecord();
                fillRecord(cursor,record);
                list.add(record);
            }
        }
        return list;
    }

    public void updateById(int id,BillRecord record){
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",record.getTime());
        contentValues.put("value",record.getValue());
        contentValues.put("type",record.getType());
        contentValues.put("note",record.getNote());
        database.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(id)});
    }


    public void deleteById(int id){
        database.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }

    public void clear(){
        database.delete(TABLE_NAME,"",null);
    }

    private void fillRecord(Cursor cursor,BillRecord record){
        int id= Integer.parseInt(cursor.getString(0));
        String time = cursor.getString(1);
        int value=Integer.parseInt(cursor.getString(2));
        int type=Integer.parseInt(cursor.getString(3));
        String note=cursor.getString(4);

        record.setId(id);
        record.setTime(time);
        record.setValue(value);
        record.setType(type);
        record.setNote(note);
    }

}
