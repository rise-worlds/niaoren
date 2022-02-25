package com.tendcloud.tenddata;

import android.os.SystemClock;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ix */
/* loaded from: classes2.dex */
public class C2991ix extends AbstractC2984iq {
    public C2991ix() {
        m15410a("bootTime", Long.valueOf(m15390c()));
        m15410a("activeTime", Long.valueOf(SystemClock.elapsedRealtime()));
        m15410a("freeDiskSpace", Integer.valueOf(m15391b()));
        m15410a("batteryLevel", Integer.valueOf(C2821dv.m15932d(C2664ab.f13513g)));
        m15410a("batteryState", Integer.valueOf(C2821dv.m15930e(C2664ab.f13513g)));
    }

    /* renamed from: b */
    public static int m15391b() {
        try {
            int[] s = C2821dv.m15903s();
            if (s != null) {
                return s[1];
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: c */
    public static long m15390c() {
        try {
            return System.currentTimeMillis() - SystemClock.elapsedRealtime();
        } catch (Throwable unused) {
            return -1L;
        }
    }
}
