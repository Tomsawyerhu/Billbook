package com.example.billbook.utils;

import android.os.Build;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class TimeUtils {
    public static final String STR_CANNOT_BE_PARSED="字符串不能被解析";

    String pattern="%d{4}-%d{2}-%d{2} %d{2}:%d{2}:%d{2}";
    String timePattern="yyyy-MM-dd HH:mm:ss";

    public TimeUtils(){}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime toLocalDateTime(String str) throws Exception {
        if(!str.matches(pattern)){
            throw new Exception(STR_CANNOT_BE_PARSED);
        }else{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern(timePattern);
            return LocalDateTime.parse(str,formatter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int isBeforeOrEqualOrAfterOrBetween(LocalDateTime t1, LocalDateTime t2, @Nullable LocalDateTime t3){
        if(t3==null){
            if(t1.isBefore(t2)){
                return -1;
            }
            else if(t2.isEqual(t2)){
                return 0;
            }
            else{
                return 1;
            }
        }else{
            if((t1.isBefore(t3)&&t1.isAfter(t2))||(t1.isBefore(t2)&&t1.isAfter(t3))){
                return 2;
            }else{
                return -2;
            }
        }
    }

    public String getDay(String rawStr){
        if(!rawStr.matches(pattern)){
            return STR_CANNOT_BE_PARSED;
        }else{
            return rawStr.split(" ")[0].split("-")[2]+"日";
        }

    }

}
