package p110z1;

import android.support.annotation.NonNull;
import android.view.View;

/* renamed from: z1.xm */
/* loaded from: classes3.dex */
final class AutoValue_ViewAttachAttachedEvent extends ViewAttachAttachedEvent {

    /* renamed from: a */
    private final View f23672a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewAttachAttachedEvent(View view) {
        if (view != null) {
            this.f23672a = view;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.ViewAttachEvent
    @NonNull
    /* renamed from: a */
    public View mo69a() {
        return this.f23672a;
    }

    public String toString() {
        return "ViewAttachAttachedEvent{view=" + this.f23672a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewAttachAttachedEvent) {
            return this.f23672a.equals(((ViewAttachAttachedEvent) obj).mo69a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23672a.hashCode() ^ 1000003;
    }
}
