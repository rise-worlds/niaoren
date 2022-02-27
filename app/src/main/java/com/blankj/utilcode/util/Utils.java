package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class Utils {

    /* renamed from: b */
    private static final String f6604b = "com.blankj.utilcode.util.PermissionUtils$PermissionActivity";
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: e */
    private static Application f6607e;

    /* renamed from: c */
    private static final C0952a f6605c = new C0952a();

    /* renamed from: d */
    private static final ExecutorService f6606d = Executors.newFixedThreadPool(3);

    /* renamed from: a */
    static final Handler f6603a = new Handler(Looper.getMainLooper());

    /* renamed from: com.blankj.utilcode.util.Utils$b */
    /* loaded from: classes.dex */
    public interface AbstractC0953b<T> {
        /* renamed from: a */
        void m24075a(T t);
    }

    /* renamed from: com.blankj.utilcode.util.Utils$c */
    /* loaded from: classes.dex */
    public interface AbstractC0954c {
        /* renamed from: a */
        void mo22994a(Activity activity);
    }

    /* renamed from: com.blankj.utilcode.util.Utils$d */
    /* loaded from: classes.dex */
    public interface AbstractC0955d {
        /* renamed from: a */
        void m24074a();

        /* renamed from: b */
        void m24073b();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m24101a(Context context) {
        if (context == null) {
            m24102a(m24088k());
        } else {
            m24102a((Application) context.getApplicationContext());
        }
    }

    /* renamed from: a */
    public static void m24102a(Application application) {
        if (f6607e == null) {
            if (application == null) {
                f6607e = m24088k();
            } else {
                f6607e = application;
            }
            f6607e.registerActivityLifecycleCallbacks(f6605c);
        } else if (application != null && application.getClass() != f6607e.getClass()) {
            f6607e.unregisterActivityLifecycleCallbacks(f6605c);
            f6605c.f6608a.clear();
            f6607e = application;
            f6607e.registerActivityLifecycleCallbacks(f6605c);
        }
    }

    /* renamed from: a */
    public static Application m24103a() {
        Application application = f6607e;
        if (application != null) {
            return application;
        }
        Application k = m24088k();
        m24102a(k);
        return k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static C0952a m24097b() {
        return f6605c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static LinkedList<Activity> m24096c() {
        return f6605c.f6608a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static Context m24095d() {
        if (!m24094e()) {
            return m24103a();
        }
        Activity a = f6605c.m24086a();
        return a == null ? m24103a() : a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static boolean m24094e() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(m24103a().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T> AbstractRunnableC0956e<T> m24100a(AbstractRunnableC0956e<T> eVar) {
        f6606d.execute(eVar);
        return eVar;
    }

    /* renamed from: a */
    public static void m24099a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f6603a.post(runnable);
        }
    }

    /* renamed from: a */
    public static void m24098a(Runnable runnable, long j) {
        f6603a.postDelayed(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static String m24093f() {
        String h = m24091h();
        if (!TextUtils.isEmpty(h)) {
            return h;
        }
        String i = m24090i();
        return !TextUtils.isEmpty(i) ? i : m24089j();
    }

    /* renamed from: h */
    private static String m24091h() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String trim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return trim;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: i */
    private static String m24090i() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() == 0) {
            return "";
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && runningAppProcessInfo.processName != null) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    /* renamed from: j */
    private static String m24089j() {
        try {
            Application a = m24103a();
            Field field = a.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(a);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", new Class[0]).invoke(obj2, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: k */
    private static Application m24088k() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (invoke != null) {
                return (Application) invoke;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public static void m24087l() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("Utils", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.Utils$a */
    /* loaded from: classes.dex */
    public static class C0952a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a */
        final LinkedList<Activity> f6608a = new LinkedList<>();

        /* renamed from: b */
        final Map<Object, AbstractC0955d> f6609b = new HashMap();

        /* renamed from: c */
        final Map<Activity, Set<AbstractC0954c>> f6610c = new HashMap();

        /* renamed from: d */
        private int f6611d = 0;

        /* renamed from: e */
        private int f6612e = 0;

        /* renamed from: f */
        private boolean f6613f = false;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        C0952a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Utils.m24087l();
            m24078c(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (!this.f6613f) {
                m24078c(activity);
            }
            int i = this.f6612e;
            if (i < 0) {
                this.f6612e = i + 1;
                m24079b(activity);
                return;
            }
            this.f6611d++;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            m24078c(activity);
            if (this.f6613f) {
                this.f6613f = false;
                m24081a(true);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.f6612e--;
                return;
            }
            this.f6611d--;
            if (this.f6611d <= 0) {
                this.f6613f = true;
                m24081a(false);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.f6608a.remove(activity);
            m24077d(activity);
            m24076e(activity);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public Activity m24086a() {
            if (!this.f6608a.isEmpty()) {
                for (int size = this.f6608a.size() - 1; size >= 0; size--) {
                    Activity activity = this.f6608a.get(size);
                    if (!(activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()))) {
                        return activity;
                    }
                }
            }
            Activity b = m24080b();
            if (b != null) {
                m24078c(b);
            }
            return b;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void m24082a(Object obj, AbstractC0955d dVar) {
            this.f6609b.put(obj, dVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void m24083a(Object obj) {
            this.f6609b.remove(obj);
        }

        /* renamed from: a */
        void m24085a(Activity activity) {
            if (activity != null) {
                this.f6610c.remove(activity);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void m24084a(Activity activity, AbstractC0954c cVar) {
            Set<AbstractC0954c> set;
            if (activity != null && cVar != null) {
                if (!this.f6610c.containsKey(activity)) {
                    set = new HashSet<>();
                    this.f6610c.put(activity, set);
                } else {
                    set = this.f6610c.get(activity);
                    if (set.contains(cVar)) {
                        return;
                    }
                }
                set.add(cVar);
            }
        }

        /* renamed from: b */
        private void m24079b(Activity activity) {
            Resources resources = Utils.m24103a().getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                configuration.setLocales(activity.getResources().getConfiguration().getLocales());
            } else if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(activity.getResources().getConfiguration().locale);
            }
            if (Build.VERSION.SDK_INT >= 17) {
                Utils.m24103a().createConfigurationContext(configuration);
            } else {
                resources.updateConfiguration(configuration, displayMetrics);
            }
        }

        /* renamed from: a */
        private void m24081a(boolean z) {
            AbstractC0955d next;
            if (!this.f6609b.isEmpty()) {
                Iterator<AbstractC0955d> it = this.f6609b.values().iterator();
                while (it.hasNext() && (next = it.next()) != null) {
                    if (z) {
                        next.m24074a();
                    } else {
                        next.m24073b();
                    }
                }
            }
        }

        /* renamed from: c */
        private void m24078c(Activity activity) {
            if (!Utils.f6604b.equals(activity.getClass().getName())) {
                if (!this.f6608a.contains(activity)) {
                    this.f6608a.addLast(activity);
                } else if (!this.f6608a.getLast().equals(activity)) {
                    this.f6608a.remove(activity);
                    this.f6608a.addLast(activity);
                }
            }
        }

        /* renamed from: d */
        private void m24077d(Activity activity) {
            Iterator<Map.Entry<Activity, Set<AbstractC0954c>>> it = this.f6610c.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Activity, Set<AbstractC0954c>> next = it.next();
                if (next.getKey() == activity) {
                    for (AbstractC0954c cVar : next.getValue()) {
                        cVar.mo22994a(activity);
                    }
                    it.remove();
                }
            }
        }

        /* renamed from: b */
        private Activity m24080b() {
            Map map;
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivityList");
                declaredField.setAccessible(true);
                map = (Map) declaredField.get(invoke);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
            if (map == null) {
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField(ServiceManagerNative.ACTIVITY);
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
            return null;
        }

        /* renamed from: e */
        private static void m24076e(Activity activity) {
            InputMethodManager inputMethodManager;
            if (!(activity == null || (inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method")) == null)) {
                for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
                    try {
                        Field declaredField = InputMethodManager.class.getDeclaredField(str);
                        if (declaredField != null) {
                            if (!declaredField.isAccessible()) {
                                declaredField.setAccessible(true);
                            }
                            Object obj = declaredField.get(inputMethodManager);
                            if ((obj instanceof View) && ((View) obj).getRootView() == activity.getWindow().getDecorView().getRootView()) {
                                declaredField.set(inputMethodManager, null);
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class FileProvider4UtilCode extends FileProvider {
        @Override // android.support.v4.content.FileProvider, android.content.ContentProvider
        public boolean onCreate() {
            Utils.m24101a(getContext());
            return true;
        }
    }

    /* renamed from: com.blankj.utilcode.util.Utils$e */
    /* loaded from: classes.dex */
    public static abstract class AbstractRunnableC0956e<Result> implements Runnable {

        /* renamed from: a */
        private static final int f6614a = 0;

        /* renamed from: b */
        private static final int f6615b = 1;

        /* renamed from: c */
        private static final int f6616c = 2;

        /* renamed from: d */
        private static final int f6617d = 3;

        /* renamed from: e */
        private volatile int f6618e = 0;

        /* renamed from: f */
        private AbstractC0953b<Result> f6619f;

        /* renamed from: b */
        abstract Result mo23263b();

        public AbstractRunnableC0956e(AbstractC0953b<Result> bVar) {
            this.f6619f = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                final Result b = mo23263b();
                if (this.f6618e == 0) {
                    this.f6618e = 1;
                    Utils.f6603a.post(new Runnable() { // from class: com.blankj.utilcode.util.Utils.e.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractRunnableC0956e.this.f6619f.m24075a(b);
                        }
                    });
                }
            } catch (Throwable unused) {
                if (this.f6618e == 0) {
                    this.f6618e = 3;
                }
            }
        }

        /* renamed from: c */
        public void m24071c() {
            this.f6618e = 2;
        }

        /* renamed from: d */
        public boolean m24070d() {
            return this.f6618e != 0;
        }

        /* renamed from: e */
        public boolean m24069e() {
            return this.f6618e == 2;
        }
    }
}
