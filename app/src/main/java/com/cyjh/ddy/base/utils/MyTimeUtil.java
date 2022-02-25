package com.cyjh.ddy.base.utils;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* renamed from: com.cyjh.ddy.base.utils.m */
/* loaded from: classes.dex */
public class MyTimeUtil {
    /* renamed from: a */
    public static String m21780a(int i) {
        int i2 = i / 24;
        int i3 = i % 24;
        if (i2 > 0 && i3 >= 0) {
            return i2 + "天" + i3;
        } else if (i2 != 0 || i3 <= 0) {
            return ResultTypeConstant.f7213z;
        } else {
            return i3 + "";
        }
    }
}
