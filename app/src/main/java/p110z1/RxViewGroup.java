package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/* renamed from: z1.xz */
/* loaded from: classes3.dex */
public final class RxViewGroup {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<ViewGroupHierarchyChangeEvent> m77a(@NonNull ViewGroup viewGroup) {
        C5596xi.m126a(viewGroup, "viewGroup == null");
        return new ViewGroupHierarchyChangeEventObservable(viewGroup);
    }

    private RxViewGroup() {
        throw new AssertionError("No instances.");
    }
}
