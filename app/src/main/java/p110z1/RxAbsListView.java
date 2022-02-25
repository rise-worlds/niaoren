package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AbsListView;

/* renamed from: z1.aaj */
/* loaded from: classes3.dex */
public final class RxAbsListView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<AbsListViewScrollEvent> m14526a(@NonNull AbsListView absListView) {
        C5596xi.m126a(absListView, "absListView == null");
        return new AbsListViewScrollEventObservable(absListView);
    }

    private RxAbsListView() {
        throw new AssertionError("No instances.");
    }
}
