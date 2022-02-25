package p110z1;

import android.support.annotation.NonNull;
import android.widget.AbsListView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.zo */
/* loaded from: classes3.dex */
public final class AutoValue_AbsListViewScrollEvent extends AbsListViewScrollEvent {

    /* renamed from: a */
    private final AbsListView f23802a;

    /* renamed from: b */
    private final int f23803b;

    /* renamed from: c */
    private final int f23804c;

    /* renamed from: d */
    private final int f23805d;

    /* renamed from: e */
    private final int f23806e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AbsListViewScrollEvent(AbsListView absListView, int i, int i2, int i3, int i4) {
        if (absListView != null) {
            this.f23802a = absListView;
            this.f23803b = i;
            this.f23804c = i2;
            this.f23805d = i3;
            this.f23806e = i4;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.AbsListViewScrollEvent
    @NonNull
    /* renamed from: a */
    public AbsListView mo32a() {
        return this.f23802a;
    }

    @Override // p110z1.AbsListViewScrollEvent
    /* renamed from: b */
    public int mo31b() {
        return this.f23803b;
    }

    @Override // p110z1.AbsListViewScrollEvent
    /* renamed from: c */
    public int mo30c() {
        return this.f23804c;
    }

    @Override // p110z1.AbsListViewScrollEvent
    /* renamed from: d */
    public int mo29d() {
        return this.f23805d;
    }

    @Override // p110z1.AbsListViewScrollEvent
    /* renamed from: e */
    public int mo28e() {
        return this.f23806e;
    }

    public String toString() {
        return "AbsListViewScrollEvent{view=" + this.f23802a + ", scrollState=" + this.f23803b + ", firstVisibleItem=" + this.f23804c + ", visibleItemCount=" + this.f23805d + ", totalItemCount=" + this.f23806e + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbsListViewScrollEvent)) {
            return false;
        }
        AbsListViewScrollEvent yzVar = (AbsListViewScrollEvent) obj;
        return this.f23802a.equals(yzVar.mo32a()) && this.f23803b == yzVar.mo31b() && this.f23804c == yzVar.mo30c() && this.f23805d == yzVar.mo29d() && this.f23806e == yzVar.mo28e();
    }

    public int hashCode() {
        return ((((((((this.f23802a.hashCode() ^ 1000003) * 1000003) ^ this.f23803b) * 1000003) ^ this.f23804c) * 1000003) ^ this.f23805d) * 1000003) ^ this.f23806e;
    }
}
