package p110z1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.zr */
/* loaded from: classes3.dex */
public final class AutoValue_AdapterViewItemSelectionEvent extends AdapterViewItemSelectionEvent {

    /* renamed from: a */
    private final AdapterView<?> f23815a;

    /* renamed from: b */
    private final View f23816b;

    /* renamed from: c */
    private final int f23817c;

    /* renamed from: d */
    private final long f23818d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewItemSelectionEvent(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView != null) {
            this.f23815a = adapterView;
            if (view != null) {
                this.f23816b = view;
                this.f23817c = i;
                this.f23818d = j;
                return;
            }
            throw new NullPointerException("Null selectedView");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.AdapterViewSelectionEvent
    @NonNull
    /* renamed from: d */
    public AdapterView<?> mo16d() {
        return this.f23815a;
    }

    @Override // p110z1.AdapterViewItemSelectionEvent
    @NonNull
    /* renamed from: a */
    public View mo19a() {
        return this.f23816b;
    }

    @Override // p110z1.AdapterViewItemSelectionEvent
    /* renamed from: b */
    public int mo18b() {
        return this.f23817c;
    }

    @Override // p110z1.AdapterViewItemSelectionEvent
    /* renamed from: c */
    public long mo17c() {
        return this.f23818d;
    }

    public String toString() {
        return "AdapterViewItemSelectionEvent{view=" + this.f23815a + ", selectedView=" + this.f23816b + ", position=" + this.f23817c + ", id=" + this.f23818d + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemSelectionEvent)) {
            return false;
        }
        AdapterViewItemSelectionEvent ziVar = (AdapterViewItemSelectionEvent) obj;
        return this.f23815a.equals(ziVar.mo16d()) && this.f23816b.equals(ziVar.mo19a()) && this.f23817c == ziVar.mo18b() && this.f23818d == ziVar.mo17c();
    }

    public int hashCode() {
        long j = this.f23818d;
        return ((((((this.f23815a.hashCode() ^ 1000003) * 1000003) ^ this.f23816b.hashCode()) * 1000003) ^ this.f23817c) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
