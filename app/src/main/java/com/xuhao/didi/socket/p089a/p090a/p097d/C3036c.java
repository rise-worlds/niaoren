package com.xuhao.didi.socket.p089a.p090a.p097d;

/* compiled from: ThreadUtils.java */
/* renamed from: com.xuhao.didi.socket.a.a.d.c */
/* loaded from: classes2.dex */
public class C3036c {
    /* renamed from: a */
    public static void m15141a(long j) {
        long j2 = 0;
        long j3 = j;
        long j4 = 0;
        while (true) {
            long j5 = j2 - j4;
            if (j5 < j3) {
                j3 -= j5;
                try {
                    j4 = System.currentTimeMillis();
                    Thread.sleep(j3);
                    j2 = System.currentTimeMillis();
                } catch (InterruptedException unused) {
                    j2 = System.currentTimeMillis();
                }
            } else {
                return;
            }
        }
    }
}
