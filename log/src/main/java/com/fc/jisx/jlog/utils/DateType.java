package com.fc.jisx.jlog.utils;

/**
 * 日期枚举类
 * C开头代表包含中文，区别于N开头的类型
 * Created by jsx on 2016/4/11.
 */
public enum  DateType {


    C_YMdHms("yyyy年MM月dd日 HH时mm分ss秒"),
    C_YMdHm("yyyy年MM月dd日 HH时mm分"),
    C_YMd("yyyy年MM月dd日"),
    C_YM("yyyy年MM月"),
    C_Md("MM月dd日"),
    C_d("dd日"),
    C_Hms("HH时mm分ss秒"),
    C_Hm("HH时mm分"),
    N_YMdHms("yyyy-MM-dd HH:mm:ss"),
    N_YMdHm("yyyy-MM-dd HH:mm"),
    N_Y("yyyy"),
    N_M("MM"),
    N_d("dd"),
    N_YMD("yyyy-MM-dd"),
    N_MD("MM-dd"),
    N_Hms("HH:mm:ss"),
    N_Hm("HH:mm"),
    YMd("yyyyMMdd");

    private String type;
    private DateType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
