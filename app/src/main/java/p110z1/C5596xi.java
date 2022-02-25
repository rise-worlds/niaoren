package p110z1;

import android.os.Looper;
import android.support.annotation.RestrictTo;

/* compiled from: Preconditions.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: z1.xi */
/* loaded from: classes3.dex */
public final class C5596xi {
    /* renamed from: a */
    public static void m126a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    /* renamed from: a */
    public static boolean m125a(Observer<?> assVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        assVar.onSubscribe(Disposables.m9995a());
        assVar.onError(new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName()));
        return false;
    }

    private C5596xi() {
        throw new AssertionError("No instances.");
    }
}
