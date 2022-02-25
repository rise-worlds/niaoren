package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

/* renamed from: com.blankj.utilcode.util.af */
/* loaded from: classes.dex */
public final class MetaDataUtils {
    private MetaDataUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m23661a(@NonNull String str) {
        if (str != null) {
            try {
                return String.valueOf(Utils.m24103a().getPackageManager().getApplicationInfo(Utils.m24103a().getPackageName(), 128).metaData.get(str));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23665a(@NonNull Activity activity, @NonNull String str) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            return m23662a((Class<? extends Activity>) activity.getClass(), str);
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23662a(@NonNull Class<? extends Activity> cls, @NonNull String str) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            try {
                return String.valueOf(Utils.m24103a().getPackageManager().getActivityInfo(new ComponentName(Utils.m24103a(), cls), 128).metaData.get(str));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23664a(@NonNull Service service, @NonNull String str) {
        if (service == null) {
            throw new NullPointerException("Argument 'service' of type Service (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            return m23660b(service.getClass(), str);
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m23660b(@NonNull Class<? extends Service> cls, @NonNull String str) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Service> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            try {
                return String.valueOf(Utils.m24103a().getPackageManager().getServiceInfo(new ComponentName(Utils.m24103a(), cls), 128).metaData.get(str));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23663a(@NonNull BroadcastReceiver broadcastReceiver, @NonNull String str) {
        if (broadcastReceiver == null) {
            throw new NullPointerException("Argument 'receiver' of type BroadcastReceiver (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            return m23663a(broadcastReceiver, str);
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static String m23659c(@NonNull Class<? extends BroadcastReceiver> cls, @NonNull String str) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends BroadcastReceiver> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            try {
                return String.valueOf(Utils.m24103a().getPackageManager().getReceiverInfo(new ComponentName(Utils.m24103a(), cls), 128).metaData.get(str));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            throw new NullPointerException("Argument 'key' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }
}
