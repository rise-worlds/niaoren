package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hb */
/* loaded from: classes2.dex */
public class C2933hb {

    /* renamed from: a */
    private static List f14142a = new ArrayList();

    /* renamed from: b */
    private static Handler f14143b;

    /* renamed from: c */
    private static HandlerThread f14144c;

    static {
        try {
            f14144c = new HandlerThread("excHandlerThread");
            f14144c.start();
            f14143b = new Handler(f14144c.getLooper());
        } catch (Throwable unused) {
        }
    }

    public static void postSDKError(Throwable th) {
        try {
            f14143b.post(new RunnableC2934hc(th));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static void m15522a(boolean z, Map map) {
        try {
            f14143b.post(new RunnableC2935hd(map, z));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m15521b(boolean z, String str) {
        if (!z || !str.contains(C2663aa.f13466i)) {
            return z || !str.contains("api/q/a");
        }
        return false;
    }

    /* renamed from: a */
    public static final String m15524a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.toString());
        sb.append("\r\n");
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i = 50;
            if (stackTrace.length <= 50) {
                i = stackTrace.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("\t");
                sb.append(stackTrace[i2]);
                sb.append("\r\n");
            }
            Throwable cause = th.getCause();
            if (cause != null) {
                m15525a(sb, stackTrace, cause, 1);
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static final void m15525a(StringBuilder sb, StackTraceElement[] stackTraceElementArr, Throwable th, int i) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            for (int length2 = stackTraceElementArr.length - 1; length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2]); length2--) {
                length--;
            }
            int i2 = 50;
            if (length <= 50) {
                i2 = length;
            }
            sb.append("Caused by : ");
            sb.append(th);
            sb.append("\r\n");
            for (int i3 = 0; i3 <= i2; i3++) {
                sb.append("\t");
                sb.append(stackTrace[i3]);
                sb.append("\r\n");
            }
            if (i < 5 && th.getCause() != null) {
                m15525a(sb, stackTrace, th, i + 1);
            }
        } catch (Throwable unused) {
        }
    }
}
