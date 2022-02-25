package com.common.utils;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class StringUtil {
    public static String MD5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append(ResultTypeConstant.f7213z);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static double decimal(double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }

    public static double decimal(String str) {
        return new BigDecimal(str).setScale(2, 4).doubleValue();
    }

    public static String getLengthSize(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("####.0");
        if (f < 1000.0f) {
            return f + "米";
        }
        return decimalFormat.format(f / 1000.0f) + "公里";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isMobileNO(String str) {
        return Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$").matcher(str).matches();
    }
}
