package com.agrithings.farmmange.util;


import android.util.Base64;

/**
 * Created by hsj on 2018/1/23.
 */

public final class Base64ByteUtils {

    /**
     * base64字符串转byte[]
     * @param base64Str
     * @return
     */
    public static byte[] base64String2ByteFun(String base64Str){
        byte[] asBytes = null;
        if(!ObjectUtils.isEmpty(base64Str)){
            asBytes = Base64.decode(base64Str,Base64.DEFAULT);
        }
        return  asBytes;
    }

    /**
     * byte[]转base64
     * @param b
     * @return
     */
    public static String byte2Base64StringFun(byte[] b){
        String base64 = null;
        if(!ObjectUtils.isEmpty(b)){
            return  Base64.encodeToString(b,Base64.DEFAULT);
        }
        return base64;
    }
}
