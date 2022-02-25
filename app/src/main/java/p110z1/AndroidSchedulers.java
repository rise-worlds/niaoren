package p110z1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.Callable;

/* renamed from: z1.atf */
/* loaded from: classes3.dex */
public final class AndroidSchedulers {

    /* renamed from: a */
    private static final Scheduler f17495a = RxAndroidPlugins.m10014a(new Callable<Scheduler>() { // from class: z1.atf.1
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return C3886a.f17496a;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AndroidSchedulers.java */
    /* renamed from: z1.atf$a */
    /* loaded from: classes3.dex */
    public static final class C3886a {

        /* renamed from: a */
        static final Scheduler f17496a = new HandlerScheduler(new Handler(Looper.getMainLooper()), false);

        private C3886a() {
        }
    }

    /* renamed from: a */
    public static Scheduler m10005a() {
        return RxAndroidPlugins.m10013a(f17495a);
    }

    /* renamed from: a */
    public static Scheduler m10004a(Looper looper) {
        return m10003a(looper, false);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static Scheduler m10003a(Looper looper, boolean z) {
        if (looper != null) {
            if (Build.VERSION.SDK_INT < 16) {
                z = false;
            } else if (z && Build.VERSION.SDK_INT < 22) {
                Message obtain = Message.obtain();
                try {
                    obtain.setAsynchronous(true);
                } catch (NoSuchMethodError unused) {
                    z = false;
                }
                obtain.recycle();
            }
            return new HandlerScheduler(new Handler(looper), z);
        }
        throw new NullPointerException("looper == null");
    }

    private AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }
}
