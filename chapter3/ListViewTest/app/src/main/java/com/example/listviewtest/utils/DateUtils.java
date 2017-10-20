package com.example.listviewtest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hsj on 2017/10/20.
 */

public final class DateUtils {
    //格式化时间
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        //设置正确的时区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 格式化得到当前时间
     * @return
     */
    public static String getCurrentTimeFormate(){
        return sdf.format(new Date());
    }
}
