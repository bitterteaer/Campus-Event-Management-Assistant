package com.xxx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PastDayUtile {
    public static String getPastDay (Integer beforeNum){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - beforeNum);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String pastDay = simpleDateFormat.format(calendar.getTime());
        return pastDay;
    }
}
