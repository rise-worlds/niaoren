package p110z1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.zp */
/* loaded from: classes3.dex */
public final class AutoValue_AdapterViewItemClickEvent extends AdapterViewItemClickEvent {

    /* renamed from: a */
    private final AdapterView<?> f23807a;

    /* renamed from: b */
    private final View f23808b;

    /* renamed from: c */
    private final int f23809c;

    /* renamed from: d */
    private final long f23810d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView != null) {
            this.f23807a = adapterView;
            if (view != null) {
                this.f23808b = view;
                this.f23809c = i;
                this.f23810d = j;
                return;
            }
            throw new NullPointerException("Null clickedView");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.AdapterViewItemClickEvent
    @NonNull
    /* renamed from: a */
    public AdapterView<?> mo27a() {
        return this.f23807a;
    }

    @Override // p110z1.AdapterViewItemClickEvent
    @NonNull
    /* renamed from: b */
    public View mo26b() {
        return this.f23808b;
    }

    @Override // p110z1.AdapterViewItemClickEvent
    /* renamed from: c */
    public int mo25c() {
        return this.f23809c;
    }

    @Override // p110z1.AdapterViewItemClickEvent
    /* renamed from: d */
    public long mo24d() {
        return this.f23810d;
    }

    public String toString() {
        return "AdapterViewItemClickEvent{view=" + this.f23807a + ", clickedView=" + this.f23808b + ", position=" + this.f23809c + ", id=" + this.f23810d + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemClickEvent)) {
            return false;
        }
        AdapterViewItemClickEvent zcVar = (AdapterViewItemClickEvent) obj;
        return this.f23807a.equals(zcVar.mo27a()) && this.f23808b.equals(zcVar.mo26b()) && this.f23809c == zcVar.mo25c() && this.f23810d == zcVar.mo24d();
    }

    public int hashCode() {
        long j = this.f23810d;
        return ((((((this.f23807a.hashCode() ^ 1000003) * 1000003) ^ this.f23808b.hashCode()) * 1000003) ^ this.f23809c) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
