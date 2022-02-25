package p110z1;

import android.support.annotation.NonNull;
import android.view.View;

/* renamed from: z1.xn */
/* loaded from: classes3.dex */
final class AutoValue_ViewAttachDetachedEvent extends ViewAttachDetachedEvent {

    /* renamed from: a */
    private final View f23673a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewAttachDetachedEvent(View view) {
        if (view != null) {
            this.f23673a = view;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.ViewAttachEvent
    @NonNull
    /* renamed from: a */
    public View mo69a() {
        return this.f23673a;
    }

    public String toString() {
        return "ViewAttachDetachedEvent{view=" + this.f23673a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewAttachDetachedEvent) {
            return this.f23673a.equals(((ViewAttachDetachedEvent) obj).mo69a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23673a.hashCode() ^ 1000003;
    }
}
