package p110z1;

import android.util.Log;

/* renamed from: z1.ahu */
/* loaded from: classes3.dex */
public class FileDownloadLog {

    /* renamed from: a */
    public static boolean f15845a = false;

    /* renamed from: b */
    private static final String f15846b = "FileDownloader.";

    /* renamed from: a */
    public static void m13213a(Object obj, Throwable th, String str, Object... objArr) {
        m13216a(6, obj, th, str, objArr);
    }

    /* renamed from: a */
    public static void m13214a(Object obj, String str, Object... objArr) {
        m13217a(6, obj, str, objArr);
    }

    /* renamed from: b */
    public static void m13212b(Object obj, String str, Object... objArr) {
        m13217a(4, obj, str, objArr);
    }

    /* renamed from: c */
    public static void m13211c(Object obj, String str, Object... objArr) {
        m13217a(3, obj, str, objArr);
    }

    /* renamed from: d */
    public static void m13210d(Object obj, String str, Object... objArr) {
        m13217a(5, obj, str, objArr);
    }

    /* renamed from: e */
    public static void m13209e(Object obj, String str, Object... objArr) {
        m13217a(2, obj, str, objArr);
    }

    /* renamed from: a */
    private static void m13217a(int i, Object obj, String str, Object... objArr) {
        m13216a(i, obj, null, str, objArr);
    }

    /* renamed from: a */
    private static void m13216a(int i, Object obj, Throwable th, String str, Object... objArr) {
        if ((i >= 5) || f15845a) {
            Log.println(i, m13215a(obj), FileDownloadUtils.m13182a(str, objArr));
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static String m13215a(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(f15846b);
        sb.append((obj instanceof Class ? (Class) obj : obj.getClass()).getSimpleName());
        return sb.toString();
    }
}
