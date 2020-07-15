package com.example.billbook.enums;

import java.util.Calendar;

public enum SeasonTerm {
    LI_CHUN(0,Calendar.FEBRUARY,3,4),YU_SHUI(1,2,18,19),JING_ZHE(2,3,5,6),CHUN_FEN(3,3,20,21),QING_MING(4,4,4,6),GU_YU(5,4,19,20),LI_XIA(6,5,5,6),XIAO_MAN(7,5,20,22),MANG_ZHONG(8,6,5,6),XIA_ZHI(9,6,21,22),XIAO_SHU(10,7,7,8),DA_SHU(11,7,22,23),LI_QIU(12,8,6,9),CHU_SHU(13,8,22,24),BAI_LU(14,9,7,8),QIU_FEN(15,9,22,24),HAN_LU(16,10,7,9),SHUANG_JIANG(17,10,23,24),LI_DONG(18,11,7,8),XIAO_XUE(19,11,22,23),DA_XUE(20,12,7,8),DONG_ZHI(21,12,21,23),XIAO_HAN(22,1,5,6),DA_HAN(23,1,19,21);
    int index;
    int month;
    int startDay;
    int endDay;


    SeasonTerm(int i, int i1, int i2, int i3) {
        this.index=i;
        this.month=i1;
        this.startDay=i2;
        this.endDay=i3;
    }

    public int getIndex() {
        return index;
    }

    public int getMonth() {
        return month;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public static SeasonTerm getSeasonTermByIndex(int index){
        for(SeasonTerm seasonTerm:SeasonTerm.values()){
            if(seasonTerm.index==index){
                return seasonTerm;
            }
        }
        //默认返回立春
        return LI_CHUN;
    }
}
