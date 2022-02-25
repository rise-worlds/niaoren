package com.tendcloud.tenddata;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import java.io.File;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dp */
/* loaded from: classes2.dex */
public class C2810dp {

    /* renamed from: a */
    private static volatile C2810dp f13767a;

    /* renamed from: b */
    private PackageInfo f13768b = null;

    private C2810dp() {
    }

    /* renamed from: a */
    public static C2810dp m16048a() {
        if (f13767a == null) {
            synchronized (C2810dp.class) {
                if (f13767a == null) {
                    f13767a = new C2810dp();
                }
            }
        }
        return f13767a;
    }

    /* renamed from: i */
    private synchronized boolean m16039i(Context context) {
        try {
            if (this.f13768b == null) {
                this.f13768b = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            }
        } catch (Throwable unused) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public String m16047a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: b */
    public int m16046b(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            if (!m16039i(context)) {
                return -1;
            }
            return this.f13768b.versionCode;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: c */
    public String m16045c(Context context) {
        if (context == null) {
            return "unknown";
        }
        try {
            return !m16039i(context) ? "unknown" : this.f13768b.versionName;
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: d */
    public long m16044d(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            if (m16039i(context) && C2855es.m15807a(9)) {
                return this.f13768b.firstInstallTime;
            }
        } catch (Throwable unused) {
        }
        return -1L;
    }

    /* renamed from: e */
    public long m16043e(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            if (m16039i(context) && C2855es.m15807a(9)) {
                return this.f13768b.lastUpdateTime;
            }
        } catch (Throwable unused) {
        }
        return -1L;
    }

    /* renamed from: f */
    public long m16042f(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            return new File(context.getApplicationInfo().sourceDir).length();
        } catch (Throwable unused) {
            return -1L;
        }
    }

    /* renamed from: g */
    public String m16041g(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (!m16039i(context)) {
                return null;
            }
            Signature[] signatureArr = this.f13768b.signatures;
            if (signatureArr.length < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(signatureArr[0].toCharsString());
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: h */
    public String m16040h(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        } catch (Throwable unused) {
            return null;
        }
    }
}
