package com.lidroid.xutils.util;

import android.webkit.MimeTypeMap;
import p110z1.Consts;

/* loaded from: classes.dex */
public class MimeTypeUtils {
    private MimeTypeUtils() {
    }

    public static String getMimeType(String str) {
        int lastIndexOf = str.lastIndexOf(Consts.f23430h);
        if (lastIndexOf == -1) {
            return "application/octet-stream";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1));
    }
}
