package p110z1;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import mirror.MethodParams;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefMethod;
import mirror.RefObject;
import mirror.RefStaticInt;
import mirror.RefStaticMethod;
import mirror.RefStaticObject;

/* renamed from: z1.crh */
/* loaded from: classes3.dex */
public class ActivityThread {
    public static Class<?> TYPE = RefClass.load(ActivityThread.class, "android.app.ActivityThread");
    public static RefStaticMethod currentActivityThread;
    public static RefMethod<IBinder> getApplicationThread;
    public static RefMethod<Handler> getHandler;
    public static RefMethod<String> getProcessName;
    public static RefMethod<Object> installProvider;
    public static RefObject<Map<IBinder, Object>> mActivities;
    public static RefObject<Object> mBoundApplication;

    /* renamed from: mH */
    public static RefObject<Handler> f21100mH;
    public static RefObject<Application> mInitialApplication;
    public static RefObject<Instrumentation> mInstrumentation;
    public static RefObject<Map<String, WeakReference<?>>> mPackages;
    public static RefObject<Map> mProviderMap;
    @MethodParams({IBinder.class, List.class})
    public static RefMethod<Void> performNewIntents;
    public static RefStaticObject<IInterface> sPackageManager;
    @MethodParams({IBinder.class, String.class, int.class, int.class, Intent.class})
    public static RefMethod<Void> sendActivityResult;

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$a */
    /* loaded from: classes3.dex */
    public static class C5105a {
        public static Class<?> TYPE = RefClass.load(C5105a.class, "android.app.ActivityThread$ActivityClientRecord");
        public static RefObject<Activity> activity;
        public static RefObject<ActivityInfo> activityInfo;
        public static RefObject<Intent> intent;
        public static RefObject<Boolean> isTopResumedActivity;
        public static RefObject<IBinder> token;
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$b */
    /* loaded from: classes3.dex */
    public static class C5106b {
        public static Class<?> TYPE = RefClass.load(C5106b.class, "android.app.ActivityThread$AppBindData");
        public static RefObject<ApplicationInfo> appInfo;
        public static RefObject<Object> info;
        public static RefObject<ComponentName> instrumentationName;
        public static RefObject<String> processName;
        public static RefObject<List<ProviderInfo>> providers;
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$c */
    /* loaded from: classes3.dex */
    public static class C5107c {
        public static Class<?> TYPE = RefClass.load(C5107c.class, "android.app.ActivityThread$CreateServiceData");
        public static RefObject<Object> compatInfo;
        public static RefObject<ServiceInfo> info;
        public static RefObject<Intent> intent;
        public static RefObject<IBinder> token;
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$d */
    /* loaded from: classes3.dex */
    public static class C5108d {
        public static RefStaticInt EXECUTE_TRANSACTION;
        public static RefStaticInt LAUNCH_ACTIVITY;
        public static RefStaticInt SCHEDULE_CRASH;
        public static Class<?> TYPE = RefClass.load(C5108d.class, "android.app.ActivityThread$H");
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$e */
    /* loaded from: classes3.dex */
    public static class C5109e {
        public static Class<?> TYPE = RefClass.load(C5109e.class, "android.app.ActivityThread$ProviderClientRecord");
        @MethodReflectParams({"android.app.ActivityThread", "java.lang.String", "android.content.IContentProvider", "android.content.ContentProvider"})
        public static RefConstructor<?> ctor;
        public static RefObject<String> mName;
        public static RefObject<IInterface> mProvider;
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$f */
    /* loaded from: classes3.dex */
    public static class C5110f {
        public static Class<?> TYPE = RefClass.load(C5110f.class, "android.app.ActivityThread$ProviderClientRecord");
        public static RefObject<Object> mHolder;
        public static RefObject<IInterface> mProvider;
    }

    /* compiled from: ActivityThread.java */
    /* renamed from: z1.crh$g */
    /* loaded from: classes3.dex */
    public static class C5111g {
        public static Class<?> TYPE = RefClass.load(C5111g.class, "android.app.ActivityThread$ProviderKey");
        @MethodParams({String.class, int.class})
        public static RefConstructor<?> ctor;
    }

    public static Object installProvider(Object obj, Context context, ProviderInfo providerInfo, Object obj2) throws Throwable {
        return Build.VERSION.SDK_INT <= 15 ? installProvider.callWithException(obj, context, obj2, providerInfo, false, true) : installProvider.callWithException(obj, context, obj2, providerInfo, false, true, true);
    }
}
