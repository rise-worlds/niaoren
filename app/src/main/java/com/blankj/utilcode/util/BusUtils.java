package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class BusUtils {

    /* renamed from: a */
    private static final Object f6466a = new Object();

    /* renamed from: b */
    private static final Map<Class, Set<Object>> f6467b = new HashMap();

    /* renamed from: c */
    private static final Map<String, Set<C0926a>> f6468c = new HashMap();

    /* renamed from: d */
    private static final Set<C0931d> f6469d = new HashSet();

    /* renamed from: e */
    private static ConcurrentHashMap<String, AbstractC0930c> f6470e = new ConcurrentHashMap<>();

    /* renamed from: f */
    private static Map<String, C0927b> f6471f = new HashMap();

    /* renamed from: g */
    private static C0927b f6472g = null;

    /* renamed from: h */
    private static final int f6473h = 0;

    /* renamed from: i */
    private static final int f6474i = 1;

    /* renamed from: j */
    private static final int f6475j = 2;

    /* renamed from: k */
    private static final String f6476k = "MESSENGER_UTILS";

    /* renamed from: com.blankj.utilcode.util.BusUtils$c */
    /* loaded from: classes.dex */
    public interface AbstractC0930c {
        /* renamed from: a */
        void m24206a(Bundle bundle);
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    /* renamed from: com.blankj.utilcode.util.BusUtils$e */
    /* loaded from: classes.dex */
    public @interface AbstractC0932e {
        /* renamed from: a */
        String m24205a() default "";

        /* renamed from: b */
        int m24204b() default 0;
    }

    /* renamed from: a */
    private static void m24238a(Object obj, String str, Object[] objArr) {
    }

    /* renamed from: c */
    private static Object m24226c(String str, Object[] objArr) {
        return f6466a;
    }

    /* renamed from: a */
    public static <T> T m24233a(String str, Object... objArr) {
        if (str == null || str.length() == 0) {
            return null;
        }
        T t = (T) m24226c(str, objArr);
        LogUtils.m23720e("BusUtils");
        if (!f6466a.equals(t)) {
            return t;
        }
        LogUtils.m23720e("BusUtils", "static bus of <" + str + "> didn't exist.");
        return null;
    }

    /* renamed from: a */
    public static void m24239a(Object obj) {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Set<Object> set = f6467b.get(cls);
            if (set == null) {
                set = new HashSet<>();
                f6467b.put(cls, set);
            }
            set.add(obj);
        }
    }

    /* renamed from: b */
    public static void m24229b(String str, Object... objArr) {
        if (!(str == null || str.length() == 0)) {
            for (C0926a aVar : f6468c.get(str)) {
                Set<Object> set = f6467b.get(aVar.f6481a);
                if (set == null || set.isEmpty()) {
                    Log.e("BusUtils", "bus of <" + str + "{" + aVar + "}> in didn't exist.");
                } else {
                    for (Object obj : set) {
                        m24238a(obj, str, objArr);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public static void m24231b(Object obj) {
        if (obj != null) {
            Set<Object> set = f6467b.get(obj.getClass());
            if (set == null || set.contains(obj)) {
                Log.i("BusUtils", "Subscriber to unregister was not registered before: " + obj);
                return;
            }
            set.remove(obj);
        }
    }

    /* renamed from: a */
    private static void m24234a(String str, Class cls, int i) {
        Set<C0926a> set = f6468c.get(str);
        if (set == null) {
            set = new TreeSet<>();
            f6468c.put(str, set);
        }
        set.add(new C0926a(cls, i));
    }

    /* renamed from: com.blankj.utilcode.util.BusUtils$d */
    /* loaded from: classes.dex */
    static class C0931d {

        /* renamed from: a */
        String f6491a;

        /* renamed from: b */
        Object[] f6492b;

        C0931d(String str, Object[] objArr) {
            this.f6491a = str;
            this.f6492b = objArr;
        }
    }

    /* renamed from: com.blankj.utilcode.util.BusUtils$a */
    /* loaded from: classes.dex */
    static class C0926a implements Comparable<C0926a> {

        /* renamed from: a */
        private Class f6481a;

        /* renamed from: b */
        private int f6482b;

        C0926a(Class cls, int i) {
            this.f6481a = cls;
            this.f6482b = i;
        }

        /* renamed from: a */
        public int compareTo(@NonNull C0926a aVar) {
            if (aVar != null) {
                int i = aVar.f6482b;
                int i2 = this.f6482b;
                return i != i2 ? i - i2 : aVar.hashCode() - this.f6481a.hashCode();
            }
            throw new NullPointerException("Argument 'o' of type Bus (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }

        public String toString() {
            return this.f6481a.getName() + ": " + this.f6482b;
        }
    }

    /* renamed from: a */
    public static void m24240a() {
        if (m24223e()) {
            Utils.m24103a().startService(new Intent(Utils.m24103a(), ServerService.class));
            return;
        }
        m24232b();
    }

    /* renamed from: a */
    public static void m24237a(String str) {
        if (f6471f.containsKey(str)) {
            Log.i("BusUtils", "registerClient: client registered: " + str);
            return;
        }
        C0927b bVar = new C0927b(str);
        if (bVar.m24212a()) {
            f6471f.put(str, bVar);
            return;
        }
        Log.e("BusUtils", "registerClient: client bind failed: " + str);
    }

    /* renamed from: b */
    public static void m24230b(String str) {
        if (f6471f.containsKey(str)) {
            f6471f.get(str).m24209b();
            return;
        }
        Log.i("BusUtils", "unregisterClient: client didn't register: " + str);
    }

    /* renamed from: b */
    public static void m24232b() {
        C0927b bVar = new C0927b(null);
        if (bVar.m24212a()) {
            f6472g = bVar;
        } else {
            Log.e("BusUtils", "bind service failed.");
        }
    }

    /* renamed from: c */
    public static void m24228c() {
        C0927b bVar = f6472g;
        if (bVar != null) {
            bVar.m24209b();
        }
    }

    /* renamed from: a */
    public static void m24235a(@NonNull String str, @NonNull AbstractC0930c cVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cVar != null) {
            f6470e.put(str, cVar);
        } else {
            throw new NullPointerException("Argument 'callback' of type MessageCallback (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m24227c(@NonNull String str) {
        if (str != null) {
            f6470e.remove(str);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24236a(@NonNull String str, @NonNull Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bundle != null) {
            bundle.putString(f6476k, str);
            C0927b bVar = f6472g;
            if (bVar != null) {
                bVar.m24211a(bundle);
            } else {
                Intent intent = new Intent(Utils.m24103a(), ServerService.class);
                intent.putExtras(bundle);
                Utils.m24103a().startService(intent);
            }
            for (C0927b bVar2 : f6471f.values()) {
                bVar2.m24211a(bundle);
            }
        } else {
            throw new NullPointerException("Argument 'data' of type Bundle (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    private static boolean m24223e() {
        return Utils.m24103a().getPackageName().equals(Utils.m24093f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static boolean m24221f(@NonNull String str) {
        if (str != null) {
            try {
                return Utils.m24103a().getPackageManager().getApplicationInfo(str, 0) != null;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static boolean m24220g(@NonNull String str) {
        if (str != null) {
            try {
                ApplicationInfo applicationInfo = Utils.m24103a().getPackageManager().getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    return false;
                }
                int i = applicationInfo.uid;
                ActivityManager activityManager = (ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
                if (activityManager != null) {
                    List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                    if (runningTasks != null && runningTasks.size() > 0) {
                        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                            if (str.equals(runningTaskInfo.baseActivity.getPackageName())) {
                                return true;
                            }
                        }
                    }
                    List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null && runningServices.size() > 0) {
                        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                            if (i == runningServiceInfo.uid) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.BusUtils$b */
    /* loaded from: classes.dex */
    public static class C0927b {

        /* renamed from: a */
        String f6483a;

        /* renamed from: b */
        Messenger f6484b;

        /* renamed from: c */
        LinkedList<Bundle> f6485c = new LinkedList<>();
        @SuppressLint({"HandlerLeak"})

        /* renamed from: d */
        Handler f6486d = new Handler() { // from class: com.blankj.utilcode.util.BusUtils.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String string;
                AbstractC0930c cVar;
                Bundle data = message.getData();
                if (data != null && (string = data.getString(BusUtils.f6476k)) != null && (cVar = (AbstractC0930c) BusUtils.f6470e.get(string)) != null) {
                    cVar.m24206a(data);
                }
            }
        };

        /* renamed from: e */
        Messenger f6487e = new Messenger(this.f6486d);

        /* renamed from: f */
        ServiceConnection f6488f = new ServiceConnection() { // from class: com.blankj.utilcode.util.BusUtils.b.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("BusUtils", "client service connected " + componentName);
                C0927b.this.f6484b = new Messenger(iBinder);
                Message obtain = Message.obtain(C0927b.this.f6486d, 0, Utils.m24093f().hashCode(), 0);
                obtain.replyTo = C0927b.this.f6487e;
                try {
                    C0927b.this.f6484b.send(obtain);
                } catch (RemoteException e) {
                    Log.e("BusUtils", "onServiceConnected: ", e);
                }
                C0927b.this.m24207c();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.w("BusUtils", "client service disconnected:" + componentName);
                C0927b bVar = C0927b.this;
                bVar.f6484b = null;
                if (!bVar.m24212a()) {
                    Log.e("BusUtils", "client service rebind failed: " + componentName);
                }
            }
        };

        C0927b(String str) {
            this.f6483a = str;
        }

        /* renamed from: a */
        boolean m24212a() {
            if (TextUtils.isEmpty(this.f6483a)) {
                return Utils.m24103a().bindService(new Intent(Utils.m24103a(), ServerService.class), this.f6488f, 1);
            } else if (!BusUtils.m24221f(this.f6483a)) {
                Log.e("BusUtils", "bind: the app is not installed -> " + this.f6483a);
                return false;
            } else if (BusUtils.m24220g(this.f6483a)) {
                Intent intent = new Intent(this.f6483a + ".messenger");
                intent.setPackage(this.f6483a);
                return Utils.m24103a().bindService(intent, this.f6488f, 1);
            } else {
                Log.e("BusUtils", "bind: the app is not running -> " + this.f6483a);
                return false;
            }
        }

        /* renamed from: b */
        void m24209b() {
            Message obtain = Message.obtain(this.f6486d, 1);
            obtain.replyTo = this.f6487e;
            try {
                this.f6484b.send(obtain);
            } catch (RemoteException e) {
                Log.e("BusUtils", "unbind: ", e);
            }
            Utils.m24103a().unbindService(this.f6488f);
        }

        /* renamed from: a */
        void m24211a(Bundle bundle) {
            if (this.f6484b == null) {
                this.f6485c.addFirst(bundle);
                Log.i("BusUtils", "save the bundle " + bundle);
                return;
            }
            m24207c();
            if (!m24208b(bundle)) {
                this.f6485c.addFirst(bundle);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m24207c() {
            if (!this.f6485c.isEmpty()) {
                for (int size = this.f6485c.size() - 1; size >= 0; size--) {
                    if (m24208b(this.f6485c.get(size))) {
                        this.f6485c.remove(size);
                    }
                }
            }
        }

        /* renamed from: b */
        private boolean m24208b(Bundle bundle) {
            Message obtain = Message.obtain(this.f6486d, 2);
            obtain.setData(bundle);
            obtain.replyTo = this.f6487e;
            try {
                this.f6484b.send(obtain);
                return true;
            } catch (RemoteException e) {
                Log.e("BusUtils", "send2Server: ", e);
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ServerService extends Service {

        /* renamed from: a */
        private final ConcurrentHashMap<Integer, Messenger> f6477a = new ConcurrentHashMap<>();
        @SuppressLint({"HandlerLeak"})

        /* renamed from: b */
        private final Handler f6478b = new Handler() { // from class: com.blankj.utilcode.util.BusUtils.ServerService.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        ServerService.this.f6477a.put(Integer.valueOf(message.arg1), message.replyTo);
                        return;
                    case 1:
                        ServerService.this.f6477a.remove(Integer.valueOf(message.arg1));
                        return;
                    case 2:
                        ServerService.this.m24219a(message);
                        ServerService.this.m24216b(message);
                        return;
                    default:
                        super.handleMessage(message);
                        return;
                }
            }
        };

        /* renamed from: c */
        private final Messenger f6479c = new Messenger(this.f6478b);

        @Override // android.app.Service
        @Nullable
        public IBinder onBind(Intent intent) {
            return this.f6479c.getBinder();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            Bundle extras;
            if (!(intent == null || (extras = intent.getExtras()) == null)) {
                Message obtain = Message.obtain(this.f6478b, 2);
                obtain.replyTo = this.f6479c;
                obtain.setData(extras);
                m24219a(obtain);
                m24216b(obtain);
            }
            return 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m24219a(Message message) {
            for (Messenger messenger : this.f6477a.values()) {
                if (messenger != null) {
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m24216b(Message message) {
            String string;
            AbstractC0930c cVar;
            Bundle data = message.getData();
            if (data != null && (string = data.getString(BusUtils.f6476k)) != null && (cVar = (AbstractC0930c) BusUtils.f6470e.get(string)) != null) {
                cVar.m24206a(data);
            }
        }
    }
}
