package com.cyjh.mq.p049d;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.cyjh.mq.d.a */
/* loaded from: classes.dex */
public final class CLog {

    /* renamed from: a */
    private static final boolean f8868a = false;

    /* renamed from: b */
    private static final String f8869b = "IPC_ANDROID";

    /* renamed from: a */
    private static boolean m20461a() {
        return false;
    }

    /* renamed from: c */
    private static void m20458c() {
    }

    /* renamed from: d */
    private static void m20457d() {
    }

    /* renamed from: e */
    private static void m20456e() {
    }

    /* renamed from: f */
    private static void m20455f() {
    }

    /* renamed from: g */
    private static void m20454g() {
    }

    /* renamed from: h */
    private static void m20453h() {
    }

    /* renamed from: i */
    private static void m20452i() {
    }

    /* renamed from: j */
    private static void m20451j() {
    }

    /* renamed from: k */
    private static void m20450k() {
    }

    /* renamed from: l */
    private static void m20449l() {
    }

    /* renamed from: b */
    private static String m20459b() {
        StackTraceElement[] stackTrace;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(CLog.class.getName())) {
                return "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String m20460a(String str) {
        String str2 = Environment.getExternalStorageDirectory().getPath() + File.separator + "rootAbort" + File.separator;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis())) + ":" + str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2 + "save.log", true);
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();
            return "save.log";
        } catch (Exception unused) {
            return str2;
        }
    }
}
