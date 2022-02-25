package p110z1;

import android.support.annotation.NonNull;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.zs */
/* loaded from: classes3.dex */
public final class AutoValue_AdapterViewNothingSelectionEvent extends AdapterViewNothingSelectionEvent {

    /* renamed from: a */
    private final AdapterView<?> f23819a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewNothingSelectionEvent(AdapterView<?> adapterView) {
        if (adapterView != null) {
            this.f23819a = adapterView;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.AdapterViewSelectionEvent
    @NonNull
    /* renamed from: d */
    public AdapterView<?> mo16d() {
        return this.f23819a;
    }

    public String toString() {
        return "AdapterViewNothingSelectionEvent{view=" + this.f23819a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdapterViewNothingSelectionEvent) {
            return this.f23819a.equals(((AdapterViewNothingSelectionEvent) obj).mo16d());
        }
        return false;
    }

    public int hashCode() {
        return this.f23819a.hashCode() ^ 1000003;
    }
}
