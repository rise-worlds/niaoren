package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.ServiceConnection;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.blankj.utilcode.util.au */
/* loaded from: classes.dex */
public final class ServiceUtils {
    private ServiceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Set m23286a() {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningServices(Integer.MAX_VALUE);
        HashSet hashSet = new HashSet();
        if (runningServices == null || runningServices.size() == 0) {
            return null;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            hashSet.add(runningServiceInfo.service.getClassName());
        }
        return hashSet;
    }

    /* renamed from: a */
    public static void m23282a(String str) {
        try {
            m23284a(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m23284a(Class<?> cls) {
        Utils.m24103a().startService(new Intent(Utils.m24103a(), cls));
    }

    /* renamed from: b */
    public static boolean m23279b(String str) {
        try {
            return m23280b(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m23280b(Class<?> cls) {
        return Utils.m24103a().stopService(new Intent(Utils.m24103a(), cls));
    }

    /* renamed from: a */
    public static void m23281a(String str, ServiceConnection serviceConnection, int i) {
        try {
            m23283a(Class.forName(str), serviceConnection, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m23283a(Class<?> cls, ServiceConnection serviceConnection, int i) {
        Utils.m24103a().bindService(new Intent(Utils.m24103a(), cls), serviceConnection, i);
    }

    /* renamed from: a */
    public static void m23285a(ServiceConnection serviceConnection) {
        Utils.m24103a().unbindService(serviceConnection);
    }

    /* renamed from: c */
    public static boolean m23278c(Class<?> cls) {
        return m23277c(cls.getName());
    }

    /* renamed from: c */
    public static boolean m23277c(String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (str.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
