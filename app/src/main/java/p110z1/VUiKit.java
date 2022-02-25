package p110z1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import org.jdeferred.android.AndroidDeferredManager;

/* renamed from: z1.api */
/* loaded from: classes3.dex */
public class VUiKit {

    /* renamed from: a */
    private static final AndroidDeferredManager f17146a = new AndroidDeferredManager();

    /* renamed from: b */
    private static final Handler f17147b = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static AndroidDeferredManager m11713a() {
        return f17146a;
    }

    /* renamed from: a */
    public static int m11710a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    public static void m11709a(Runnable runnable) {
        f17147b.post(runnable);
    }

    /* renamed from: a */
    public static void m11711a(long j, Runnable runnable) {
        f17147b.postDelayed(runnable, j);
    }

    /* renamed from: a */
    public static void m11712a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
