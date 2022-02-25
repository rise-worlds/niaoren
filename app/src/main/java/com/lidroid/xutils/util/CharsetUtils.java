package com.lidroid.xutils.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;
import p110z1.C5367in;

/* loaded from: classes.dex */
public class CharsetUtils {
    public static final String DEFAULT_ENCODING_CHARSET = "ISO-8859-1";
    public static final List<String> SUPPORT_CHARSET;

    private CharsetUtils() {
    }

    public static String toCharset(String str, String str2, int i) {
        try {
            return new String(str.getBytes(getEncoding(str, i)), str2);
        } catch (Throwable th) {
            LogUtils.m19212w(th);
            return str;
        }
    }

    public static String getEncoding(String str, int i) {
        for (String str2 : SUPPORT_CHARSET) {
            if (isCharset(str, str2, i)) {
                return str2;
            }
        }
        return "ISO-8859-1";
    }

    public static boolean isCharset(String str, String str2, int i) {
        try {
            if (str.length() > i) {
                str = str.substring(0, i);
            }
            return str.equals(new String(str.getBytes(str2), str2));
        } catch (Throwable unused) {
            return false;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        SUPPORT_CHARSET = arrayList;
        arrayList.add("ISO-8859-1");
        SUPPORT_CHARSET.add(C5367in.f22024b);
        SUPPORT_CHARSET.add("GBK");
        SUPPORT_CHARSET.add("GB18030");
        SUPPORT_CHARSET.add("US-ASCII");
        SUPPORT_CHARSET.add(HTTP.ASCII);
        SUPPORT_CHARSET.add("ISO-2022-KR");
        SUPPORT_CHARSET.add("ISO-8859-2");
        SUPPORT_CHARSET.add("ISO-2022-JP");
        SUPPORT_CHARSET.add("ISO-2022-JP-2");
        SUPPORT_CHARSET.add("UTF-8");
    }
}
