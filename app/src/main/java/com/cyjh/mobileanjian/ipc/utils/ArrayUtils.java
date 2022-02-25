package com.cyjh.mobileanjian.ipc.utils;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.a */
/* loaded from: classes.dex */
public final class ArrayUtils {
    /* renamed from: a */
    public static boolean m20671a(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    /* renamed from: b */
    public static boolean m20670b(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }
}
