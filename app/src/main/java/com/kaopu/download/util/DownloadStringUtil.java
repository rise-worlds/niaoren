package com.kaopu.download.util;

import p110z1.Consts;

/* loaded from: classes.dex */
public class DownloadStringUtil {
    public static String getFileName(String str, boolean z) {
        if (str == null || -1 == str.lastIndexOf("/") || -1 == str.lastIndexOf(Consts.f23430h)) {
            return null;
        }
        if (!z) {
            return str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(Consts.f23430h));
        }
        return str.substring(str.lastIndexOf("/") + 1);
    }
}
