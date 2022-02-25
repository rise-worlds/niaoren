package com.xuhao.didi.p082a.p088e;

import java.io.PrintStream;

/* renamed from: com.xuhao.didi.a.e.b */
/* loaded from: classes2.dex */
public class SLog {

    /* renamed from: a */
    private static boolean f14373a;

    /* renamed from: a */
    public static void m15175a(boolean z) {
        f14373a = z;
    }

    /* renamed from: a */
    public static boolean m15177a() {
        return f14373a;
    }

    /* renamed from: a */
    public static void m15176a(String str) {
        if (f14373a) {
            PrintStream printStream = System.err;
            printStream.println("OkSocket, " + str);
        }
    }

    /* renamed from: b */
    public static void m15174b(String str) {
        if (f14373a) {
            PrintStream printStream = System.out;
            printStream.println("OkSocket, " + str);
        }
    }

    /* renamed from: c */
    public static void m15173c(String str) {
        m15174b(str);
    }
}
