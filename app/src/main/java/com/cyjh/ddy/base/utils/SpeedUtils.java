package com.cyjh.ddy.base.utils;

import android.content.pm.PackageManager;
import android.net.TrafficStats;
import com.blankj.utilcode.util.Utils;

/* renamed from: com.cyjh.ddy.base.utils.s */
/* loaded from: classes.dex */
public class SpeedUtils {

    /* renamed from: a */
    private static long f7137a;

    /* renamed from: b */
    private static long f7138b;

    /* renamed from: c */
    private static long f7139c;

    /* renamed from: d */
    private static long f7140d;

    /* renamed from: e */
    private static long f7141e;

    /* renamed from: f */
    private static long f7142f;

    /* renamed from: g */
    private static long f7143g;

    /* renamed from: h */
    private static long f7144h;

    /* renamed from: a */
    public static void m21742a() {
        f7137a = m21738e();
        f7138b = m21737f();
    }

    /* renamed from: b */
    public static int m21741b() {
        try {
            return Utils.m24103a().getPackageManager().getApplicationInfo(Utils.m24103a().getPackageName(), 1).uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: e */
    private static long m21738e() {
        if (f7139c == 0) {
            f7139c = System.currentTimeMillis();
        } else {
            f7140d = System.currentTimeMillis();
        }
        if (TrafficStats.getUidRxBytes(m21741b()) == -1) {
            return 0L;
        }
        return TrafficStats.getTotalRxBytes() / 1024;
    }

    /* renamed from: f */
    private static long m21737f() {
        if (f7141e == 0) {
            f7141e = System.currentTimeMillis();
        } else {
            f7142f = System.currentTimeMillis();
        }
        if (TrafficStats.getUidTxBytes(m21741b()) == -1) {
            return 0L;
        }
        return TrafficStats.getTotalTxBytes() / 1024;
    }

    /* renamed from: c */
    public static String m21740c() {
        long e = m21738e();
        f7137a = e;
        return String.format("%.0f", Float.valueOf((float) (e - f7137a)));
    }

    /* renamed from: d */
    public static String m21739d() {
        long f = m21737f();
        f7138b = f;
        return String.format("%.0f", Float.valueOf((float) (f - f7138b)));
    }
}
