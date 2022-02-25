package p110z1;

import android.os.Handler;
import android.os.Looper;

/* renamed from: z1.dcc */
/* loaded from: classes3.dex */
public class UiThreadHandlerUtils {

    /* renamed from: a */
    private static final Handler f21292a = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    private static final Object f21293b = new Object();

    /* renamed from: a */
    public static final Handler m3233a() {
        return f21292a;
    }

    /* renamed from: a */
    public static final boolean m3232a(Runnable runnable) {
        Handler handler = f21292a;
        if (handler == null) {
            return false;
        }
        return handler.post(runnable);
    }

    /* renamed from: a */
    public static final boolean m3231a(Runnable runnable, long j) {
        Handler handler = f21292a;
        if (handler == null) {
            return false;
        }
        return handler.postDelayed(runnable, j);
    }

    /* renamed from: b */
    public static final boolean m3229b(Runnable runnable, long j) {
        Handler handler = f21292a;
        if (handler == null) {
            return false;
        }
        handler.removeCallbacks(runnable, f21293b);
        return f21292a.postDelayed(runnable, j);
    }

    /* renamed from: b */
    public static void m3230b(Runnable runnable) {
        Handler handler = f21292a;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
