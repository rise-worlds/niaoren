package com.cyjh.ddy.base.utils;

/* renamed from: com.cyjh.ddy.base.utils.g */
/* loaded from: classes.dex */
public class IPUtil {

    /* renamed from: a */
    private static String f7126a = "?";

    /* renamed from: a */
    public static void m21825a() {
        new Thread(new Runnable() { // from class: com.cyjh.ddy.base.utils.IPUtil$1
            @Override // java.lang.Runnable
            public void run() {
                IPUtil.m21822d();
            }
        }).start();
    }

    /* renamed from: b */
    public static String m21824b() {
        String str;
        synchronized (f7126a) {
            str = f7126a;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.net.HttpURLConnection, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x010d -> B:53:0x0115). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0112 -> B:53:0x0115). Please submit an issue!!! */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m21822d() {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.base.utils.IPUtil.m21822d():void");
    }
}
