package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AbsListView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.yz */
/* loaded from: classes3.dex */
public abstract class AbsListViewScrollEvent {
    @NonNull
    /* renamed from: a */
    public abstract AbsListView mo32a();

    /* renamed from: b */
    public abstract int mo31b();

    /* renamed from: c */
    public abstract int mo30c();

    /* renamed from: d */
    public abstract int mo29d();

    /* renamed from: e */
    public abstract int mo28e();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static AbsListViewScrollEvent m47a(AbsListView absListView, int i, int i2, int i3, int i4) {
        return new AutoValue_AbsListViewScrollEvent(absListView, i, i2, i3, i4);
    }
}
