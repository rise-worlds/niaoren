package p110z1;

import android.support.annotation.NonNull;
import android.widget.TextView;

/* renamed from: z1.zz */
/* loaded from: classes3.dex */
final class AutoValue_TextViewBeforeTextChangeEvent extends TextViewBeforeTextChangeEvent {

    /* renamed from: a */
    private final TextView f23833a;

    /* renamed from: b */
    private final CharSequence f23834b;

    /* renamed from: c */
    private final int f23835c;

    /* renamed from: d */
    private final int f23836d;

    /* renamed from: e */
    private final int f23837e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewBeforeTextChangeEvent(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        if (textView != null) {
            this.f23833a = textView;
            if (charSequence != null) {
                this.f23834b = charSequence;
                this.f23835c = i;
                this.f23836d = i2;
                this.f23837e = i3;
                return;
            }
            throw new NullPointerException("Null text");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.TextViewBeforeTextChangeEvent
    @NonNull
    /* renamed from: a */
    public TextView mo4a() {
        return this.f23833a;
    }

    @Override // p110z1.TextViewBeforeTextChangeEvent
    @NonNull
    /* renamed from: b */
    public CharSequence mo3b() {
        return this.f23834b;
    }

    @Override // p110z1.TextViewBeforeTextChangeEvent
    /* renamed from: c */
    public int mo2c() {
        return this.f23835c;
    }

    @Override // p110z1.TextViewBeforeTextChangeEvent
    /* renamed from: d */
    public int mo1d() {
        return this.f23836d;
    }

    @Override // p110z1.TextViewBeforeTextChangeEvent
    /* renamed from: e */
    public int mo0e() {
        return this.f23837e;
    }

    public String toString() {
        return "TextViewBeforeTextChangeEvent{view=" + this.f23833a + ", text=" + ((Object) this.f23834b) + ", start=" + this.f23835c + ", count=" + this.f23836d + ", after=" + this.f23837e + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewBeforeTextChangeEvent)) {
            return false;
        }
        TextViewBeforeTextChangeEvent abjVar = (TextViewBeforeTextChangeEvent) obj;
        return this.f23833a.equals(abjVar.mo4a()) && this.f23834b.equals(abjVar.mo3b()) && this.f23835c == abjVar.mo2c() && this.f23836d == abjVar.mo1d() && this.f23837e == abjVar.mo0e();
    }

    public int hashCode() {
        return ((((((((this.f23833a.hashCode() ^ 1000003) * 1000003) ^ this.f23834b.hashCode()) * 1000003) ^ this.f23835c) * 1000003) ^ this.f23836d) * 1000003) ^ this.f23837e;
    }
}
