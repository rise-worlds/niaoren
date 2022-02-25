package p110z1;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: z1.xp */
/* loaded from: classes3.dex */
final class AutoValue_ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChildViewRemoveEvent {

    /* renamed from: a */
    private final ViewGroup f23676a;

    /* renamed from: b */
    private final View f23677b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewGroupHierarchyChildViewRemoveEvent(ViewGroup viewGroup, View view) {
        if (viewGroup != null) {
            this.f23676a = viewGroup;
            if (view != null) {
                this.f23677b = view;
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
        return this.f23676a;
    }

    @Override // p110z1.ViewGroupHierarchyChangeEvent
    @NonNull
    /* renamed from: b */
    public View mo66b() {
        return this.f23677b;
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewRemoveEvent{view=" + this.f23676a + ", child=" + this.f23677b + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewGroupHierarchyChildViewRemoveEvent)) {
            return false;
        }
        ViewGroupHierarchyChildViewRemoveEvent ylVar = (ViewGroupHierarchyChildViewRemoveEvent) obj;
        return this.f23676a.equals(ylVar.mo67a()) && this.f23677b.equals(ylVar.mo66b());
    }

    public int hashCode() {
        return ((this.f23676a.hashCode() ^ 1000003) * 1000003) ^ this.f23677b.hashCode();
    }
}
