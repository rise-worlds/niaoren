package com.lody.virtual.helper.utils;

import android.util.Base64;

/* loaded from: classes.dex */
public class EncodeUtils {
    public static String decodeBase64(String str) {
        return new String(Base64.decode(str, 0));
    }
}
