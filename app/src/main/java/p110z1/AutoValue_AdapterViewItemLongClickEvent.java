package p110z1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.zq */
/* loaded from: classes3.dex */
public final class AutoValue_AdapterViewItemLongClickEvent extends AdapterViewItemLongClickEvent {

    /* renamed from: a */
    private final AdapterView<?> f23811a;

    /* renamed from: b */
    private final View f23812b;

    /* renamed from: c */
    private final int f23813c;

    /* renamed from: d */
    private final long f23814d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewItemLongClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView != null) {
            this.f23811a = adapterView;
            if (view != null) {
                this.f23812b = view;
                this.f23813c = i;
                this.f23814d = j;
                return;
            }
            throw new NullPointerException("Null clickedView");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.AdapterViewItemLongClickEvent
    @NonNull
    /* renamed from: a */
    public AdapterView<?> mo23a() {
        return this.f23811a;
    }

    @Override // p110z1.AdapterViewItemLongClickEvent
    @NonNull
    /* renamed from: b */
    public View mo22b() {
        return this.f23812b;
    }

    @Override // p110z1.AdapterViewItemLongClickEvent
    /* renamed from: c */
    public int mo21c() {
        return this.f23813c;
    }

    @Override // p110z1.AdapterViewItemLongClickEvent
    /* renamed from: d */
    public long mo20d() {
        return this.f23814d;
    }

    public String toString() {
        return "AdapterViewItemLongClickEvent{view=" + this.f23811a + ", clickedView=" + this.f23812b + ", position=" + this.f23813c + ", id=" + this.f23814d + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemLongClickEvent)) {
            return false;
        }
        AdapterViewItemLongClickEvent zfVar = (AdapterViewItemLongClickEvent) obj;
        return this.f23811a.equals(zfVar.mo23a()) && this.f23812b.equals(zfVar.mo22b()) && this.f23813c == zfVar.mo21c() && this.f23814d == zfVar.mo20d();
    }

    public int hashCode() {
        long j = this.f23814d;
        return ((((((this.f23811a.hashCode() ^ 1000003) * 1000003) ^ this.f23812b.hashCode()) * 1000003) ^ this.f23813c) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
