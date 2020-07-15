package com.example.billbook.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PictureUrl {
    public static final Map<String,String> seasonGifUrls=new HashMap<String, String>(){{
        put("0","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/lichun.gif");
        put("1","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/yushui.gif");
        put("2","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/jingzhe.gif");
        put("3","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/chunfen.gif");
        put("4","");
        put("5","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/guyu.gif");
        put("6","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/lixia.gif");
        put("7","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/xiaohan.gif");
        put("8","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/mangzhong.gif");
        put("9","");
        put("10","");
        put("11","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/dashu.gif");
        put("12","");
        put("13","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/chushu.gif");
        put("14","");
        put("15","");
        put("16","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/hanlu.gif");
        put("17","");
        put("18","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/lidong.gif");
        put("19","");
        put("20","");
        put("21","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/dongzhi.gif");
        put("22","https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/xiaohan.gif");
        put("23","");
    }};

    public static final String defaultGifUrl="https://billbook.oss-cn-hangzhou.aliyuncs.com/image/season/default.jpg";

    public static final ArrayList<String> bgJpgUrls=new ArrayList<String>(){
        {
            add("https://billbook.oss-cn-hangzhou.aliyuncs.com/image/bg/bg1.jpg");
        }
    };
}
