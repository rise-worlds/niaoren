package p110z1;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: z1.xo */
/* loaded from: classes3.dex */
final class AutoValue_ViewGroupHierarchyChildViewAddEvent extends ViewGroupHierarchyChildViewAddEvent {

    /* renamed from: a */
    private final ViewGroup f23674a;

    /* renamed from: b */
    private final View f23675b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewGroupHierarchyChildViewAddEvent(ViewGroup viewGroup, View view) {
        if (viewGroup != null) {
            this.f23674a = viewGroup;
            if (view != null) {
                this.f23675b = view;
                return;
            }
            throw new NullPointerException("Null child");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.ViewGroupHierarchyChangeEvent
    @NonNull
    /* renamed from: a */
    public ViewGroup mo67a() {
        return this.f23674a;
    }

    @Override // p110z1.ViewGroupHierarchyChangeEvent
    @NonNull
    /* renamed from: b */
    public View mo66b() {
        return this.f23675b;
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewAddEvent{view=" + this.f23674a + ", child=" + this.f23675b + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewGroupHierarchyChildViewAddEvent)) {
            return false;
        }
        ViewGroupHierarchyChildViewAddEvent ykVar = (ViewGroupHierarchyChildViewAddEvent) obj;
        return this.f23674a.equals(ykVar.mo67a()) && this.f23675b.equals(ykVar.mo66b());
    }

    public int hashCode() {
        return ((this.f23674a.hashCode() ^ 1000003) * 1000003) ^ this.f23675b.hashCode();
    }
}
