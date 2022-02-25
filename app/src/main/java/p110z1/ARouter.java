package p110z1;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.ILogger;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: z1.o */
/* loaded from: classes3.dex */
public final class ARouter {

    /* renamed from: a */
    public static final String f22666a = "NTeRQWvye18AkPd6G";

    /* renamed from: b */
    public static final String f22667b = "wmHzgD4lOj5o4241";

    /* renamed from: c */
    public static ILogger f22668c = null;

    /* renamed from: d */
    private static volatile ARouter f22669d = null;

    /* renamed from: e */
    private static volatile boolean f22670e = false;

    private ARouter() {
    }

    /* renamed from: a */
    public static void m1713a(Application application) {
        if (!f22670e) {
            f22668c = _ARouter.f22783a;
            _ARouter.f22783a.info("ARouter::", "ARouter init start.");
            f22670e = _ARouter.m1529a(application);
            if (f22670e) {
                _ARouter.m1505l();
            }
            _ARouter.f22783a.info("ARouter::", "ARouter init over.");
        }
    }

    /* renamed from: a */
    public static ARouter m1714a() {
        if (f22670e) {
            if (f22669d == null) {
                synchronized (ARouter.class) {
                    if (f22669d == null) {
                        f22669d = new ARouter();
                    }
                }
            }
            return f22669d;
        }
        throw new InitException("ARouter::Init::Invoke init(context) first!");
    }

    /* renamed from: b */
    public static synchronized void m1704b() {
        synchronized (ARouter.class) {
            _ARouter.m1514c();
        }
    }

    /* renamed from: c */
    public static boolean m1703c() {
        return _ARouter.m1506k();
    }

    /* renamed from: d */
    public static synchronized void m1702d() {
        synchronized (ARouter.class) {
            _ARouter.m1513d();
        }
    }

    /* renamed from: e */
    public static synchronized void m1701e() {
        synchronized (ARouter.class) {
            _ARouter.m1509h();
        }
    }

    /* renamed from: a */
    public static synchronized void m1705a(ThreadPoolExecutor threadPoolExecutor) {
        synchronized (ARouter.class) {
            _ARouter.m1520a(threadPoolExecutor);
        }
    }

    /* renamed from: f */
    public synchronized void m1700f() {
        _ARouter.m1531a();
        f22670e = false;
    }

    @Deprecated
    /* renamed from: g */
    public static synchronized void m1699g() {
        synchronized (ARouter.class) {
            _ARouter.m1512e();
        }
    }

    @Deprecated
    /* renamed from: h */
    public static boolean m1698h() {
        return _ARouter.m1511f();
    }

    @Deprecated
    /* renamed from: i */
    public static void m1697i() {
        _ARouter.m1510g();
    }

    /* renamed from: j */
    public static synchronized void m1696j() {
        synchronized (ARouter.class) {
            _ARouter.m1508i();
        }
    }

    /* renamed from: k */
    public static boolean m1695k() {
        return _ARouter.m1507j();
    }

    /* renamed from: a */
    public static void m1710a(ILogger iLogger) {
        _ARouter.m1526a(iLogger);
    }

    /* renamed from: a */
    public void m1708a(Object obj) {
        _ARouter.m1524a(obj);
    }

    /* renamed from: a */
    public Postcard m1707a(String str) {
        return _ARouter.m1517b().m1522a(str);
    }

    @Deprecated
    /* renamed from: a */
    public Postcard m1706a(String str, String str2) {
        return _ARouter.m1517b().m1521a(str, str2);
    }

    /* renamed from: a */
    public Postcard m1711a(Uri uri) {
        return _ARouter.m1517b().m1527a(uri);
    }

    /* renamed from: a */
    public <T> T m1709a(Class<? extends T> cls) {
        return (T) _ARouter.m1517b().m1525a((Class<? extends Object>) cls);
    }

    /* renamed from: a */
    public Object m1712a(Context context, Postcard postcard, int i, NavigationCallback navigationCallback) {
        return _ARouter.m1517b().m1528a(context, postcard, i, navigationCallback);
    }
}
