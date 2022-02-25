package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Adapter;

/* renamed from: z1.aak */
/* loaded from: classes3.dex */
public final class RxAdapter {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static <T extends Adapter> InitialValueObservable<T> m14525a(@NonNull T t) {
        C5596xi.m126a(t, "adapter == null");
        return new AdapterDataChangeObservable(t);
    }

    private RxAdapter() {
        throw new AssertionError("No instances.");
    }
}
