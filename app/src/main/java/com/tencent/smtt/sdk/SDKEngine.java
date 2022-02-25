package com.tencent.smtt.sdk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.d */
/* loaded from: classes2.dex */
public class SDKEngine {

    /* renamed from: a */
    static int f13137a = 0;

    /* renamed from: b */
    static boolean f13138b = false;

    /* renamed from: e */
    private static SDKEngine f13139e = null;

    /* renamed from: h */
    private static int f13140h = 0;

    /* renamed from: i */
    private static int f13141i = 3;

    /* renamed from: k */
    private static String f13142k;

    /* renamed from: c */
    private TbsWizard f13143c = null;

    /* renamed from: d */
    private TbsWizard f13144d = null;

    /* renamed from: f */
    private boolean f13145f = false;

    /* renamed from: g */
    private boolean f13146g = false;

    /* renamed from: j */
    private File f13147j = null;

    private SDKEngine() {
    }

    /* renamed from: a */
    public static SDKEngine m16828a(boolean z) {
        if (f13139e == null && z) {
            synchronized (SDKEngine.class) {
                if (f13139e == null) {
                    f13139e = new SDKEngine();
                }
            }
        }
        return f13139e;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ff A[Catch: Throwable -> 0x0171, TryCatch #1 {, blocks: (B:4:0x0004, B:8:0x0043, B:17:0x0065, B:19:0x009a, B:65:0x01a8, B:67:0x01db, B:70:0x01e0, B:71:0x01f0, B:23:0x00a0, B:25:0x00a6, B:27:0x00b3, B:29:0x00ca, B:33:0x00d5, B:37:0x00e0, B:39:0x00f3, B:45:0x00ff, B:48:0x0108, B:52:0x0113, B:53:0x011c, B:55:0x011f, B:56:0x0122, B:58:0x0128, B:59:0x012e, B:60:0x0133, B:62:0x014d, B:63:0x015e, B:64:0x016d), top: B:76:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0108 A[Catch: Throwable -> 0x0171, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0004, B:8:0x0043, B:17:0x0065, B:19:0x009a, B:65:0x01a8, B:67:0x01db, B:70:0x01e0, B:71:0x01f0, B:23:0x00a0, B:25:0x00a6, B:27:0x00b3, B:29:0x00ca, B:33:0x00d5, B:37:0x00e0, B:39:0x00f3, B:45:0x00ff, B:48:0x0108, B:52:0x0113, B:53:0x011c, B:55:0x011f, B:56:0x0122, B:58:0x0128, B:59:0x012e, B:60:0x0133, B:62:0x014d, B:63:0x015e, B:64:0x016d), top: B:76:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0111  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m16830a(android.content.Context r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SDKEngine.m16830a(android.content.Context, boolean, boolean):void");
    }

    /* renamed from: a */
    public TbsWizard m16832a() {
        if (this.f13145f) {
            return this.f13143c;
        }
        return null;
    }

    /* renamed from: b */
    public boolean m16827b() {
        return this.f13145f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public TbsWizard m16824c() {
        return this.f13143c;
    }

    /* renamed from: d */
    public static int m16823d() {
        return f13140h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16831a(int i) {
        f13140h = i;
    }

    /* renamed from: e */
    public String m16822e() {
        return (this.f13143c == null || QbSdk.f12789a) ? "system webview get nothing..." : this.f13143c.m16633a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean m16821f() {
        if (f13138b) {
            if (f13142k == null) {
                return false;
            }
            int i = m16818i();
            if (i == 0) {
                m16826b(1);
            } else {
                int i2 = i + 1;
                if (i2 > f13141i) {
                    return false;
                }
                m16826b(i2);
            }
        }
        return f13138b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m16825b(boolean z) {
        f13138b = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean m16820g() {
        return this.f13146g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16829a(String str) {
        f13142k = str;
    }

    /* renamed from: i */
    private int m16818i() {
        Throwable th;
        Exception e;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                File file = new File(this.f13147j, "count.prop");
                if (!file.exists()) {
                    return 0;
                }
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream2);
                    int intValue = Integer.valueOf(properties.getProperty(f13142k, "1")).intValue();
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return intValue;
                } catch (Exception e3) {
                    e = e3;
                    bufferedInputStream = bufferedInputStream2;
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e6) {
            e = e6;
        }
    }

    /* renamed from: b */
    private void m16826b(int i) {
        String valueOf = String.valueOf(i);
        Properties properties = new Properties();
        properties.setProperty(f13142k, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.f13147j, "count.prop")), (String) null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: h */
    public boolean m16819h() {
        return QbSdk.useSoftWare();
    }
}
