package com.agrithings.farmmange.util;

import android.util.Log;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hsj on 2017/10/20.
 */

public final class DateUtils {
    private static final String TAG = "DateUtils";

    //一天的毫秒数
    public final static long TIMEM_OF_A_DAY = 1*24*60*60*1000;

    // 创建 Calendar 对象
    public static Calendar calendar = Calendar.getInstance();


    public static class DataUtilsException extends Exception{
        public DataUtilsException() {
        }

        public DataUtilsException(String message) {
            super(message);
        }

        public DataUtilsException(String message, Throwable cause) {
            super(message, cause);
        }

        public DataUtilsException(Throwable cause) {
            super(cause);
        }
    }
    //格式化时间
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        //设置正确的时区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 格式化得到当前时间-yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTimeFormate(){
        return sdf.format(new Date());
    }

    /**
     * 时间戳得到格式化时间-yyyy-MM-dd HH:mm:ss
     * @param timeM
     * @return
     */
    public static String getTimeFormate(Long timeM) throws DataUtilsException {
        return sdf.format(new Date(checkTimeM(timeM)));
    }

    /**
     * 格式化时间-yyyy-MM-dd HH:mm:ss-得到时间戳
     * @param dateString
     * @return
     * @throws DataUtilsException
     */
    public static Long getTimeM(String dateString ) throws Exception {
        if(ObjectUtils.isEmpty(dateString)){
            throw  new DataUtilsException("dateString不能为空！");
        }
        Date date = sdf.parse(dateString);
        return date.getTime();
    }

    /**
     * 时间戳得到-年
     * @param timeM
     * @return
     * @throws DataUtilsException
     */
    public static int getYear(Long timeM) throws DataUtilsException {
        // 或者用 Date 来初始化 Calendar 对象
        calendar.setTime(new Date(checkTimeM(timeM)));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 时间戳得到-月
     *           显示月份 (从0开始, 实际显示要加一)
     * @param timeM
     * @return
     * @throws DataUtilsException
     */
    public static int getMonth(Long timeM) throws DataUtilsException {
        // 或者用 Date 来初始化 Calendar 对象
        calendar.setTime(new Date(checkTimeM(timeM)));
        return calendar.get(Calendar.MONTH)+1;
    }

    /**
     * 时间戳得到-本月第几天
     * @param timeM
     * @return
     * @throws DataUtilsException
     */
    public static int getDayOfMonth(Long timeM) throws DataUtilsException {
        // 或者用 Date 来初始化 Calendar 对象
        calendar.setTime(new Date(checkTimeM(timeM)));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据 年 月 获取对应的月份天数
     * @param year
     * @param month
     * @return
     * @throws DataUtilsException
     */
    public static int getDaysByMonthAndYear(int year, int month) throws DataUtilsException {
        if(year < 1970 || (month < 1 || month > 12)){
            throw  new DataUtilsException("年份必须等于大于1970，月份范围1-12！");
        }
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据天数获取-时间戳
     * @param days
     * @return
     */
    public static Long getTimeMByDays(int days) throws DataUtilsException {
        if(days < 0){
            throw  new DataUtilsException("天数必须大于0！");
        }
        return days*TIMEM_OF_A_DAY;
    }

    /**
     * 根据时间戳获取-天数
     * @param timem
     * @return
     */
    public static double getDayByTimem(long timem) throws DataUtilsException {
        if(timem < 0){
            throw  new DataUtilsException("时间戳必须大于0！");
        }
        return divid(timem,TIMEM_OF_A_DAY);
    }

    /**
     * 根据年份和月份得到每个月月初的时间戳-2017-12-01 00:00:00
     * @param month
     * @return
     * @throws DataUtilsException
     */
    public static long geTimemEveryMonthStart(int year,int month) throws Exception {
        if(year < 1970 || (month < 1 || month > 12)){
            throw  new DataUtilsException("年份必须等于大于1970，月份范围1-12！");
        }
        String dateString = String.format("%s-%s-01 00:00:00",year,month);
        return getTimeM(dateString);
    }

    /**
     * 两个数相除获取两位小数
     * @param a
     * @param b
     * @return
     * @throws DataUtilsException
     */
    public static double divid(long a,long b) throws DataUtilsException {
        if(a <= 0 || b <= 0){
            throw  new DataUtilsException("参数必须大于0！");
        }
        return   new BigDecimal((double) a/b).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 检验时间戳
     * @param timeM
     * @return
     * @throws DataUtilsException
     */
    public static Long checkTimeM(Long timeM) throws DataUtilsException {
        if(timeM <= 0){
            throw  new DataUtilsException("时间戳必须大于0！");
        }
        return timeM;
    }

    public static void main(String[] args) throws Exception {
        Log.i(TAG,"timeM="+getTimeM("2016-08-12 12:00:00"));
    }

}
