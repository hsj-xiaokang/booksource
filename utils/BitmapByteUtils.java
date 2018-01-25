package com.agrithings.farmmange.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by hsj on 2018/1/23.
 */

public final class BitmapByteUtils {
    private static final String TAG = "BitmapByteUtils";

    /**
     * Bitmap2Bytes
     * @param bm
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
                 if(ObjectUtils.isEmpty(bm)){
                     return null;
                 }
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                 return baos.toByteArray();
    }

    /**
     * Bytes2Bimap
      * @param b
     * @return
     */
    public static Bitmap Bytes2Bimap(byte[] b) {
                 if (b.length != 0) {
                         return BitmapFactory.decodeByteArray(b, 0, b.length);
                     } else {
                         return null;
                     }
             }
}
