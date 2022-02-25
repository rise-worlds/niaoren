package p110z1;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.p003v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.facade.template.ILogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.p */
/* loaded from: classes3.dex */
public final class _ARouter {

    /* renamed from: a */
    static ILogger f22783a = new DefaultLogger("ARouter::");

    /* renamed from: b */
    private static volatile boolean f22784b = false;

    /* renamed from: c */
    private static volatile boolean f22785c = false;

    /* renamed from: d */
    private static volatile boolean f22786d = false;

    /* renamed from: e */
    private static volatile _ARouter f22787e = null;

    /* renamed from: f */
    private static volatile boolean f22788f = false;

    /* renamed from: g */
    private static volatile ThreadPoolExecutor f22789g = DefaultPoolExecutor.m1225a();

    /* renamed from: h */
    private static Handler f22790h;

    /* renamed from: i */
    private static Context f22791i;

    /* renamed from: j */
    private static InterceptorService f22792j;

    private _ARouter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static synchronized boolean m1529a(Application application) {
        synchronized (_ARouter.class) {
            f22791i = application;
            LogisticsCenter.m2358a(f22791i, f22789g);
            f22783a.info("ARouter::", "ARouter init success!");
            f22788f = true;
            f22790h = new Handler(Looper.getMainLooper());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m1531a() {
        synchronized (_ARouter.class) {
            if (m1506k()) {
                f22788f = false;
                LogisticsCenter.m2359a();
                f22783a.info("ARouter::", "ARouter destroy success!");
            } else {
                f22783a.error("ARouter::", "Destroy can be used in debug mode only!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static _ARouter m1517b() {
        if (f22788f) {
            if (f22787e == null) {
                synchronized (_ARouter.class) {
                    if (f22787e == null) {
                        f22787e = new _ARouter();
                    }
                }
            }
            return f22787e;
        }
        throw new InitException("ARouterCore::Init::Invoke init(context) first!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static synchronized void m1514c() {
        synchronized (_ARouter.class) {
            f22785c = true;
            f22783a.info("ARouter::", "ARouter openDebug");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static synchronized void m1513d() {
        synchronized (_ARouter.class) {
            f22783a.showLog(true);
            f22783a.info("ARouter::", "ARouter openLog");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: e */
    public static synchronized void m1512e() {
        synchronized (_ARouter.class) {
            f22786d = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: f */
    public static boolean m1511f() {
        return f22786d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: g */
    public static void m1510g() {
        Log.i("ARouter::", "ARouter start attachBaseContext");
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            declaredField.set(invoke, new InstrumentationHook());
            Log.i("ARouter::", "ARouter hook instrumentation success!");
        } catch (Exception e) {
            Log.e("ARouter::", "ARouter hook instrumentation failed! [" + e.getMessage() + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public static synchronized void m1509h() {
        synchronized (_ARouter.class) {
            f22783a.showStackTrace(true);
            f22783a.info("ARouter::", "ARouter printStackTrace");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m1520a(ThreadPoolExecutor threadPoolExecutor) {
        synchronized (_ARouter.class) {
            f22789g = threadPoolExecutor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public static synchronized void m1508i() {
        synchronized (_ARouter.class) {
            f22784b = true;
            f22783a.info("ARouter::", "ARouter monitorMode on");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public static boolean m1507j() {
        return f22784b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public static boolean m1506k() {
        return f22785c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1526a(ILogger iLogger) {
        if (iLogger != null) {
            f22783a = iLogger;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1524a(Object obj) {
        AutowiredService autowiredService = (AutowiredService) ARouter.m1714a().m1707a("/arouter/service/autowired").navigation();
        if (autowiredService != null) {
            autowiredService.autowire(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Postcard m1522a(String str) {
        if (!C5599y.m75a((CharSequence) str)) {
            PathReplaceService pathReplaceService = (PathReplaceService) ARouter.m1714a().m1709a((Class<? extends Object>) PathReplaceService.class);
            if (pathReplaceService != null) {
                str = pathReplaceService.forString(str);
            }
            return m1521a(str, m1515b(str));
        }
        throw new HandlerException("ARouter::Parameter is invalid!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Postcard m1527a(Uri uri) {
        if (uri == null || C5599y.m75a((CharSequence) uri.toString())) {
            throw new HandlerException("ARouter::Parameter invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.m1714a().m1709a((Class<? extends Object>) PathReplaceService.class);
        if (pathReplaceService != null) {
            uri = pathReplaceService.forUri(uri);
        }
        return new Postcard(uri.getPath(), m1515b(uri.getPath()), uri, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Postcard m1521a(String str, String str2) {
        if (C5599y.m75a((CharSequence) str) || C5599y.m75a((CharSequence) str2)) {
            throw new HandlerException("ARouter::Parameter is invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.m1714a().m1709a((Class<? extends Object>) PathReplaceService.class);
        if (pathReplaceService != null) {
            str = pathReplaceService.forString(str);
        }
        return new Postcard(str, str2);
    }

    /* renamed from: b */
    private String m1515b(String str) {
        if (C5599y.m75a((CharSequence) str) || !str.startsWith("/")) {
            throw new HandlerException("ARouter::Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }
        try {
            String substring = str.substring(1, str.indexOf("/", 1));
            if (!C5599y.m75a((CharSequence) substring)) {
                return substring;
            }
            throw new HandlerException("ARouter::Extract the default group failed! There's nothing between 2 '/'!");
        } catch (Exception e) {
            ILogger iLogger = f22783a;
            iLogger.warning("ARouter::", "Failed to extract default group! " + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: l */
    public static void m1505l() {
        f22792j = (InterceptorService) ARouter.m1714a().m1707a("/arouter/service/interceptor").navigation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public <T> T m1525a(Class<? extends T> cls) {
        try {
            Postcard a = LogisticsCenter.m2352a(cls.getName());
            if (a == null) {
                a = LogisticsCenter.m2352a(cls.getSimpleName());
            }
            if (a == null) {
                return null;
            }
            LogisticsCenter.m2357a(a);
            return (T) a.getProvider();
        } catch (NoRouteFoundException e) {
            f22783a.warning("ARouter::", e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Object m1528a(final Context context, final Postcard postcard, final int i, final NavigationCallback navigationCallback) {
        try {
            LogisticsCenter.m2357a(postcard);
            if (navigationCallback != null) {
                navigationCallback.onFound(postcard);
            }
            if (postcard.isGreenChannel()) {
                return m1516b(context, postcard, i, navigationCallback);
            }
            f22792j.doInterceptions(postcard, new InterceptorCallback() { // from class: z1.p.2
                @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
                public void onContinue(Postcard postcard2) {
                    _ARouter.this.m1516b(context, postcard2, i, navigationCallback);
                }

                @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
                public void onInterrupt(Throwable th) {
                    NavigationCallback navigationCallback2 = navigationCallback;
                    if (navigationCallback2 != null) {
                        navigationCallback2.onInterrupt(postcard);
                    }
                    ILogger iLogger = _ARouter.f22783a;
                    iLogger.info("ARouter::", "Navigation failed, termination by interceptor : " + th.getMessage());
                }
            });
            return null;
        } catch (NoRouteFoundException e) {
            f22783a.warning("ARouter::", e.getMessage());
            if (m1506k()) {
                m1523a(new Runnable() { // from class: z1.p.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Context context2 = _ARouter.f22791i;
                        Toast.makeText(context2, "There's no route matched!\n Path = [" + postcard.getPath() + "]\n Group = [" + postcard.getGroup() + "]", 1).show();
                    }
                });
            }
            if (navigationCallback != null) {
                navigationCallback.onLost(postcard);
            } else {
                DegradeService degradeService = (DegradeService) ARouter.m1714a().m1709a((Class<? extends Object>) DegradeService.class);
                if (degradeService != null) {
                    degradeService.onLost(context, postcard);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: b */
    public Object m1516b(final Context context, final Postcard postcard, final int i, final NavigationCallback navigationCallback) {
        if (context == null) {
            context = f22791i;
        }
        switch (postcard.getType()) {
            case ACTIVITY:
                final Intent intent = new Intent(context, postcard.getDestination());
                intent.putExtras(postcard.getExtras());
                int flags = postcard.getFlags();
                if (-1 != flags) {
                    intent.setFlags(flags);
                } else if (!(context instanceof Activity)) {
                    intent.setFlags(268435456);
                }
                String action = postcard.getAction();
                if (!C5599y.m75a((CharSequence) action)) {
                    intent.setAction(action);
                }
                m1523a(new Runnable() { // from class: z1.p.3
                    @Override // java.lang.Runnable
                    public void run() {
                        _ARouter.this.m1530a(i, context, intent, postcard, navigationCallback);
                    }
                });
                return null;
            case PROVIDER:
                return postcard.getProvider();
            case BOARDCAST:
            case CONTENT_PROVIDER:
            case FRAGMENT:
                try {
                    Object newInstance = postcard.getDestination().getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (newInstance instanceof Fragment) {
                        ((Fragment) newInstance).setArguments(postcard.getExtras());
                    } else if (newInstance instanceof android.support.p003v4.app.Fragment) {
                        ((android.support.p003v4.app.Fragment) newInstance).setArguments(postcard.getExtras());
                    }
                    return newInstance;
                } catch (Exception e) {
                    ILogger iLogger = f22783a;
                    iLogger.error("ARouter::", "Fetch fragment instance error, " + C5599y.m73a(e.getStackTrace()));
                    break;
                }
        }
        return null;
    }

    /* renamed from: a */
    private void m1523a(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            f22790h.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1530a(int i, Context context, Intent intent, Postcard postcard, NavigationCallback navigationCallback) {
        if (i < 0) {
            ActivityCompat.startActivity(context, intent, postcard.getOptionsBundle());
        } else if (context instanceof Activity) {
            ActivityCompat.startActivityForResult((Activity) context, intent, i, postcard.getOptionsBundle());
        } else {
            f22783a.warning("ARouter::", "Must use [navigation(activity, ...)] to support [startActivityForResult]");
        }
        if (!(-1 == postcard.getEnterAnim() || -1 == postcard.getExitAnim() || !(context instanceof Activity))) {
            ((Activity) context).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
        }
        if (navigationCallback != null) {
            navigationCallback.onArrival(postcard);
        }
    }
}
