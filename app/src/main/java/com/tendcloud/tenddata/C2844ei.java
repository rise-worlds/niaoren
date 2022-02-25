package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ei */
/* loaded from: classes2.dex */
public class C2844ei {
    private C2844ei() {
    }

    /* renamed from: a */
    public static List m15839a() {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        try {
            for (File file : new File("/proc").listFiles()) {
                if (file != null && file.isDirectory()) {
                    try {
                        int parseInt = Integer.parseInt(file.getName());
                        C2808dn dnVar = new C2808dn(-1);
                        if ((dnVar.f13763b < 1000 || dnVar.f13763b > 9999) && !dnVar.f13764c.contains(":") && !dnVar.f13764c.contains("/")) {
                            arrayList.add(new C2808dn(parseInt));
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List m15838a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] listFiles = new File("/proc").listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file != null && file.isDirectory()) {
                        try {
                            C2808dn dnVar = new C2808dn(Integer.parseInt(file.getName()));
                            if (dnVar.f13762a && ((dnVar.f13763b < 1000 || dnVar.f13763b > 9999) && !dnVar.f13764c.contains(":") && !dnVar.f13764c.contains("/"))) {
                                arrayList.add(dnVar);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        } catch (Throwable unused2) {
        }
        return arrayList;
    }

    /* renamed from: b */
    public static boolean m15836b() {
        try {
            List<C2808dn> a = m15839a();
            int myPid = Process.myPid();
            for (C2808dn dnVar : a) {
                if (dnVar.f13765d == myPid && dnVar.f13762a) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static List m15835b(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 22) {
                return ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
            }
            List<C2808dn> a = m15839a();
            ArrayList arrayList = new ArrayList();
            for (C2808dn dnVar : a) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo(dnVar.f13764c, dnVar.f13765d, null);
                runningAppProcessInfo.uid = dnVar.f13763b;
                arrayList.add(runningAppProcessInfo);
            }
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m15837a(Context context, int i) {
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == i) {
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
