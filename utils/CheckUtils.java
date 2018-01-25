package com.agrithings.farmmange.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hsj on 2018/1/17.
 */

public final class CheckUtils {
    private static final String TAG = "CheckUtils";
    /**
     * 验证邮箱地址是否正确
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        if(ObjectUtils.isEmpty(email)){
            return false;
        }
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            Log.i(TAG, "校验证邮箱地址错误");
            flag = false;
        }

        return flag;
    }
    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        if (ObjectUtils.isEmpty(mobiles)){
            return false;
        }
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
            Log.i(TAG, "校验手机号码错误");
            flag = false;
        }
        return flag;
    }

    /**
     * 6-16位不含空格的密码
     * @param pw
     * @return
     */
    public static boolean passwordLeng_6_16(String pw){
        if (ObjectUtils.isEmpty(pw)){
            return  false;
        }
        boolean flag = false;
        pw = pw.trim();
        try{
         if(!TextUtils.isEmpty(pw)){
             if(!pw.contains(" ")){
                 if(pw.length() >= 6 && pw.length() <= 16){
                     flag = true;
                 }
             }
         }
        }catch(Exception e){
            Log.i(TAG, "校验6-16位不含空格的密码错误");
            flag = false;
        }
        return flag;
    }
}
