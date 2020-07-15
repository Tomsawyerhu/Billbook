package com.example.billbook.utils;

import com.example.billbook.enums.SeasonTerm;

import java.util.Calendar;
import java.util.Date;

public class SeasonUtils {
    private Date date;
    public SeasonUtils(){
        date=new Date();
    }

    public SeasonTerm getBetween(){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        for(SeasonTerm seasonTerm:SeasonTerm.values()){
            if(seasonTerm.getMonth()==calender.get(Calendar.MONTH)+1&&calender.get(Calendar.DAY_OF_MONTH)+1>=seasonTerm.getStartDay()&&calender.get(Calendar.DAY_OF_MONTH)+1<=seasonTerm.getEndDay()){
                return seasonTerm;
            }
        }
        return null;
    }

}
