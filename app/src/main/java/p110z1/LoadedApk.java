package p110z1;

import android.app.Application;
import android.app.IServiceConnection;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IInterface;
import java.lang.ref.WeakReference;
import mirror.MethodParams;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.csg */
/* loaded from: classes3.dex */
public class LoadedApk {
    public static Class Class = RefClass.load(LoadedApk.class, "android.app.LoadedApk");
    @MethodParams({Context.class, ServiceConnection.class})
    public static RefMethod<IServiceConnection> forgetServiceDispatcher;
    public static RefMethod<ClassLoader> getClassLoader;
    @MethodParams({ServiceConnection.class, Context.class, Handler.class, int.class})
    public static RefMethod<IServiceConnection> getServiceDispatcher;
    public static RefObject<ApplicationInfo> mApplicationInfo;
    public static RefObject<ClassLoader> mClassLoader;
    public static RefBoolean mSecurityViolation;
    @MethodParams({boolean.class, Instrumentation.class})
    public static RefMethod<Application> makeApplication;

    /* compiled from: LoadedApk.java */
    /* renamed from: z1.csg$a */
    /* loaded from: classes3.dex */
    public static class C5120a {
        public static Class Class = RefClass.load(C5120a.class, "android.app.LoadedApk$ReceiverDispatcher");
        public static RefMethod<IInterface> getIIntentReceiver;
        public static RefObject<IIntentReceiver> mIIntentReceiver;
        public static RefObject<BroadcastReceiver> mReceiver;

        /* compiled from: LoadedApk.java */
        /* renamed from: z1.csg$a$a */
        /* loaded from: classes3.dex */
        public static class C5121a {
            public static Class Class = RefClass.load(C5121a.class, "android.app.LoadedApk$ReceiverDispatcher$InnerReceiver");
            public static RefObject<WeakReference> mDispatcher;
        }
    }

    /* compiled from: LoadedApk.java */
    /* renamed from: z1.csg$b */
    /* loaded from: classes3.dex */
    public static class C5122b {
        public static Class Class = RefClass.load(C5122b.class, "android.app.LoadedApk$ServiceDispatcher");
        public static RefObject<ServiceConnection> mConnection;
        public static RefObject<Context> mContext;

        /* compiled from: LoadedApk.java */
        /* renamed from: z1.csg$b$a */
        /* loaded from: classes3.dex */
        public static class C5123a {
            public static Class Class = RefClass.load(C5123a.class, "android.app.LoadedApk$ServiceDispatcher$InnerConnection");
            public static RefObject<WeakReference> mDispatcher;
        }
    }
}
