package com.oneblog.blog.tools;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeStampTranslate {

    public String timeStamp2Time(Long timeStamp){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
        System.out.println("格式化结果：" + sd);
        return sd;
    }
}
