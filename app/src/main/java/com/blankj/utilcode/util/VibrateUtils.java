package com.blankj.utilcode.util;

import android.os.Vibrator;
import android.support.annotation.RequiresPermission;

/* renamed from: com.blankj.utilcode.util.be */
/* loaded from: classes.dex */
public final class VibrateUtils {

    /* renamed from: a */
    private static Vibrator f6833a;

    private VibrateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission("android.permission.VIBRATE")
    /* renamed from: a */
    public static void m22988a(long j) {
        Vibrator b = m22986b();
        if (b != null) {
            b.vibrate(j);
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    /* renamed from: a */
    public static void m22987a(long[] jArr, int i) {
        Vibrator b = m22986b();
        if (b != null) {
            b.vibrate(jArr, i);
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    /* renamed from: a */
    public static void m22989a() {
        Vibrator b = m22986b();
        if (b != null) {
            b.cancel();
        }
    }

    /* renamed from: b */
    private static Vibrator m22986b() {
        if (f6833a == null) {
            f6833a = (Vibrator) Utils.m24103a().getSystemService("vibrator");
        }
        return f6833a;
    }
}
