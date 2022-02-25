package p110z1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;

/* renamed from: z1.zy */
/* loaded from: classes3.dex */
final class AutoValue_TextViewAfterTextChangeEvent extends TextViewAfterTextChangeEvent {

    /* renamed from: a */
    private final TextView f23831a;

    /* renamed from: b */
    private final Editable f23832b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewAfterTextChangeEvent(TextView textView, @Nullable Editable editable) {
        if (textView != null) {
            this.f23831a = textView;
            this.f23832b = editable;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.TextViewAfterTextChangeEvent
    @NonNull
    /* renamed from: a */
    public TextView mo6a() {
        return this.f23831a;
    }

    @Override // p110z1.TextViewAfterTextChangeEvent
    @Nullable
    /* renamed from: b */
    public Editable mo5b() {
        return this.f23832b;
    }

    public String toString() {
        return "TextViewAfterTextChangeEvent{view=" + this.f23831a + ", editable=" + ((Object) this.f23832b) + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewAfterTextChangeEvent)) {
            return false;
        }
        TextViewAfterTextChangeEvent abhVar = (TextViewAfterTextChangeEvent) obj;
        if (this.f23831a.equals(abhVar.mo6a())) {
            Editable editable = this.f23832b;
            if (editable == null) {
                if (abhVar.mo5b() == null) {
                    return true;
                }
            } else if (editable.equals(abhVar.mo5b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f23831a.hashCode() ^ 1000003) * 1000003;
        Editable editable = this.f23832b;
        return hashCode ^ (editable == null ? 0 : editable.hashCode());
    }
}
