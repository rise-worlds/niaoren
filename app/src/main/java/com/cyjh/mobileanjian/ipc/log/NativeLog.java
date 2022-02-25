package com.cyjh.mobileanjian.ipc.log;

/* loaded from: classes.dex */
public class NativeLog {

    /* renamed from: a */
    private static StringBuffer f8275a = new StringBuffer();

    public static void appendLog(String str) {
        if (f8275a.length() > 0) {
            f8275a.append("@_@");
        }
        f8275a.append(str);
    }

    public static String getExtraLog() {
        return f8275a.toString();
    }

    public static void reset() {
        f8275a.setLength(0);
    }
}
