package com.cyjh.ddy.base.utils;

import android.content.Context;
import android.os.Build;

/* renamed from: com.cyjh.ddy.base.utils.c */
/* loaded from: classes.dex */
public class CocUtil {
    /* renamed from: a */
    public static boolean m21868a(Context context) {
        return Build.CPU_ABI.equalsIgnoreCase("x86") || Build.PRODUCT.equals("android_x86");
    }
}
