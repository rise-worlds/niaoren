package p110z1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: AppUtils.java */
/* renamed from: z1.adz */
/* loaded from: classes3.dex */
public final class adz {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a */
    private static Application f15415a = null;

    /* renamed from: b */
    private static final C3379a f15416b = new C3379a();

    /* renamed from: c */
    private static final String f15417c = "com.blankj.utilcode.util.PermissionUtils$PermissionActivity";

    /* compiled from: AppUtils.java */
    /* renamed from: z1.adz$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3380b {
        /* renamed from: a */
        void m14208a(Activity activity);
    }

    /* compiled from: AppUtils.java */
    /* renamed from: z1.adz$c */
    /* loaded from: classes3.dex */
    public interface AbstractC3381c {
        /* renamed from: a */
        void m14207a();

        /* renamed from: b */
        void m14206b();
    }

    private adz() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m14224a(Context context) {
        if (context == null) {
            m14225a(m14217f());
        } else {
            m14224a(context.getApplicationContext());
        }
    }

    /* renamed from: a */
    public static boolean m14223a(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            FileInputStream fileInputStream = new FileInputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static int m14221b(String str, String str2) {
        String readLine;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    fileInputStream.close();
                    return 1;
                }
            } while (!readLine.contains(str2));
            fileInputStream.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /* renamed from: a */
    public static void m14225a(Application application) {
        if (f15415a == null) {
            if (application == null) {
                f15415a = m14217f();
            } else {
                f15415a = application;
            }
            f15415a.registerActivityLifecycleCallbacks(f15416b);
        } else if (application != null && application.getClass() != f15415a.getClass()) {
            f15415a.unregisterActivityLifecycleCallbacks(f15416b);
            f15416b.f15418a.clear();
            f15415a = application;
            f15415a.registerActivityLifecycleCallbacks(f15416b);
        }
    }

    /* renamed from: a */
    public static Application m14226a() {
        Application application = f15415a;
        if (application != null) {
            return application;
        }
        Application f = m14217f();
        m14225a(f);
        return f;
    }

    /* renamed from: f */
    private static Application m14217f() {
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

    /* renamed from: b */
    static C3379a m14222b() {
        return f15416b;
    }

    /* renamed from: c */
    static LinkedList<Activity> m14220c() {
        return f15416b.f15418a;
    }

    /* renamed from: d */
    static Context m14219d() {
        if (!m14218e()) {
            return m14226a();
        }
        Activity b = f15416b.m14210b();
        return b == null ? m14226a() : b;
    }

    /* renamed from: e */
    static boolean m14218e() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) m14226a().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100) {
                return runningAppProcessInfo.processName.equals(m14226a().getPackageName());
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AppUtils.java */
    /* renamed from: z1.adz$a */
    /* loaded from: classes3.dex */
    public static class C3379a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: c */
        private AbstractC3380b f15420c;

        /* renamed from: a */
        final LinkedList<Activity> f15418a = new LinkedList<>();

        /* renamed from: b */
        final HashMap<Object, AbstractC3381c> f15419b = new HashMap<>();

        /* renamed from: d */
        private int f15421d = 0;

        /* renamed from: e */
        private int f15422e = 0;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        C3379a() {
        }

        /* renamed from: a */
        AbstractC3380b m14216a() {
            return this.f15420c;
        }

        /* renamed from: a */
        void m14212a(AbstractC3380b bVar) {
            this.f15420c = bVar;
        }

        /* renamed from: a */
        void m14213a(Object obj, AbstractC3381c cVar) {
            this.f15419b.put(obj, cVar);
        }

        /* renamed from: a */
        void m14214a(Object obj) {
            this.f15419b.remove(obj);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            m14215a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            m14215a(activity);
            if (this.f15421d <= 0) {
                m14211a(true);
            }
            int i = this.f15422e;
            if (i < 0) {
                this.f15422e = i + 1;
            } else {
                this.f15421d++;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            m14215a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.f15422e--;
                return;
            }
            this.f15421d--;
            if (this.f15421d <= 0) {
                m14211a(false);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.f15418a.remove(activity);
            AbstractC3380b bVar = this.f15420c;
            if (bVar != null) {
                bVar.m14208a(activity);
            }
        }

        /* renamed from: a */
        private void m14211a(boolean z) {
            AbstractC3381c next;
            if (!this.f15419b.isEmpty()) {
                Iterator<AbstractC3381c> it = this.f15419b.values().iterator();
                while (it.hasNext() && (next = it.next()) != null) {
                    if (z) {
                        next.m14207a();
                    } else {
                        next.m14206b();
                    }
                }
            }
        }

        /* renamed from: a */
        private void m14215a(Activity activity) {
            if (!adz.f15417c.equals(activity.getClass().getName())) {
                if (!this.f15418a.contains(activity)) {
                    this.f15418a.addLast(activity);
                } else if (!this.f15418a.getLast().equals(activity)) {
                    this.f15418a.remove(activity);
                    this.f15418a.addLast(activity);
                }
            }
        }

        /* renamed from: b */
        Activity m14210b() {
            Activity last;
            if (!this.f15418a.isEmpty() && (last = this.f15418a.getLast()) != null) {
                return last;
            }
            Activity c = m14209c();
            if (c != null) {
                m14215a(c);
            }
            return c;
        }

        /* renamed from: c */
        private Activity m14209c() {
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
    }
}
