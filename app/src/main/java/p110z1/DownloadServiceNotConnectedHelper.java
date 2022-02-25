package p110z1;

import android.app.Notification;

/* renamed from: z1.ahr */
/* loaded from: classes3.dex */
public class DownloadServiceNotConnectedHelper {

    /* renamed from: a */
    private static final String f15837a = ", but the download service isn't connected yet.";

    /* renamed from: b */
    private static final String f15838b = "\nYou can use FileDownloader#isServiceConnected() to check whether the service has been connected, \nbesides you can use following functions easier to control your code invoke after the service has been connected: \n1. FileDownloader#bindService(Runnable)\n2. FileDownloader#insureServiceBind()\n3. FileDownloader#insureServiceBindAsync()";

    /* renamed from: a */
    public static boolean m13242a(String str, String str2, boolean z) {
        m13241a("request start the task([%s], [%s], [%B]) in the download service", str, str2, Boolean.valueOf(z));
        return false;
    }

    /* renamed from: a */
    public static boolean m13245a(int i) {
        m13241a("request pause the task[%d] in the download service", Integer.valueOf(i));
        return false;
    }

    /* renamed from: a */
    public static boolean m13243a(String str, String str2) {
        m13241a("request check the task([%s], [%s]) is downloading in the download service", str, str2);
        return false;
    }

    /* renamed from: b */
    public static long m13238b(int i) {
        m13241a("request get the downloaded so far byte for the task[%d] in the download service", Integer.valueOf(i));
        return 0L;
    }

    /* renamed from: c */
    public static long m13236c(int i) {
        m13241a("request get the total byte for the task[%d] in the download service", Integer.valueOf(i));
        return 0L;
    }

    /* renamed from: d */
    public static byte m13235d(int i) {
        m13241a("request get the status for the task[%d] in the download service", Integer.valueOf(i));
        return (byte) 0;
    }

    /* renamed from: a */
    public static void m13246a() {
        m13241a("request pause all tasks in the download service", new Object[0]);
    }

    /* renamed from: b */
    public static boolean m13239b() {
        m13241a("request check the download service is idle", new Object[0]);
        return true;
    }

    /* renamed from: a */
    public static void m13244a(int i, Notification notification) {
        m13241a("request set the download service as the foreground service([%d],[%s]),", Integer.valueOf(i), notification);
    }

    /* renamed from: a */
    public static void m13240a(boolean z) {
        m13241a("request cancel the foreground status[%B] for the download service", Boolean.valueOf(z));
    }

    /* renamed from: e */
    public static boolean m13234e(int i) {
        m13241a("request set the max network thread count[%d] in the download service", Integer.valueOf(i));
        return false;
    }

    /* renamed from: f */
    public static boolean m13233f(int i) {
        m13241a("request clear the task[%d] data in the database", Integer.valueOf(i));
        return false;
    }

    /* renamed from: c */
    public static boolean m13237c() {
        m13241a("request clear all tasks data in the database", new Object[0]);
        return false;
    }

    /* renamed from: a */
    private static void m13241a(String str, Object... objArr) {
        FileDownloadLog.m13210d(DownloadServiceNotConnectedHelper.class, str + f15837a + f15838b, objArr);
    }
}
