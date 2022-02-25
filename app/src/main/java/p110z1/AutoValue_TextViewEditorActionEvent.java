package p110z1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: z1.aaa */
/* loaded from: classes3.dex */
final class AutoValue_TextViewEditorActionEvent extends TextViewEditorActionEvent {

    /* renamed from: a */
    private final TextView f15050a;

    /* renamed from: b */
    private final int f15051b;

    /* renamed from: c */
    private final KeyEvent f15052c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewEditorActionEvent(TextView textView, int i, @Nullable KeyEvent keyEvent) {
        if (textView != null) {
            this.f15050a = textView;
            this.f15051b = i;
            this.f15052c = keyEvent;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.TextViewEditorActionEvent
    @NonNull
    /* renamed from: a */
    public TextView mo14448a() {
        return this.f15050a;
    }

    @Override // p110z1.TextViewEditorActionEvent
    /* renamed from: b */
    public int mo14446b() {
        return this.f15051b;
    }

    @Override // p110z1.TextViewEditorActionEvent
    @Nullable
    /* renamed from: c */
    public KeyEvent mo14445c() {
        return this.f15052c;
    }

    public String toString() {
        return "TextViewEditorActionEvent{view=" + this.f15050a + ", actionId=" + this.f15051b + ", keyEvent=" + this.f15052c + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewEditorActionEvent)) {
            return false;
        }
        TextViewEditorActionEvent ablVar = (TextViewEditorActionEvent) obj;
        if (this.f15050a.equals(ablVar.mo14448a()) && this.f15051b == ablVar.mo14446b()) {
            KeyEvent keyEvent = this.f15052c;
            if (keyEvent == null) {
                if (ablVar.mo14445c() == null) {
                    return true;
                }
            } else if (keyEvent.equals(ablVar.mo14445c())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f15050a.hashCode() ^ 1000003) * 1000003) ^ this.f15051b) * 1000003;
        KeyEvent keyEvent = this.f15052c;
        return hashCode ^ (keyEvent == null ? 0 : keyEvent.hashCode());
    }
}
