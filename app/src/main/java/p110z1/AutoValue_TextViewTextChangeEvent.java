package p110z1;

import android.support.annotation.NonNull;
import android.widget.TextView;

/* renamed from: z1.aab */
/* loaded from: classes3.dex */
final class AutoValue_TextViewTextChangeEvent extends TextViewTextChangeEvent {

    /* renamed from: a */
    private final TextView f15053a;

    /* renamed from: b */
    private final CharSequence f15054b;

    /* renamed from: c */
    private final int f15055c;

    /* renamed from: d */
    private final int f15056d;

    /* renamed from: e */
    private final int f15057e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewTextChangeEvent(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        if (textView != null) {
            this.f15053a = textView;
            if (charSequence != null) {
                this.f15054b = charSequence;
                this.f15055c = i;
                this.f15056d = i2;
                this.f15057e = i3;
                return;
            }
            throw new NullPointerException("Null text");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.TextViewTextChangeEvent
    @NonNull
    /* renamed from: a */
    public TextView mo14444a() {
        return this.f15053a;
    }

    @Override // p110z1.TextViewTextChangeEvent
    @NonNull
    /* renamed from: b */
    public CharSequence mo14442b() {
        return this.f15054b;
    }

    @Override // p110z1.TextViewTextChangeEvent
    /* renamed from: c */
    public int mo14441c() {
        return this.f15055c;
    }

    @Override // p110z1.TextViewTextChangeEvent
    /* renamed from: d */
    public int mo14440d() {
        return this.f15056d;
    }

    @Override // p110z1.TextViewTextChangeEvent
    /* renamed from: e */
    public int mo14439e() {
        return this.f15057e;
    }

    public String toString() {
        return "TextViewTextChangeEvent{view=" + this.f15053a + ", text=" + ((Object) this.f15054b) + ", start=" + this.f15055c + ", before=" + this.f15056d + ", count=" + this.f15057e + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewTextChangeEvent)) {
            return false;
        }
        TextViewTextChangeEvent aboVar = (TextViewTextChangeEvent) obj;
        return this.f15053a.equals(aboVar.mo14444a()) && this.f15054b.equals(aboVar.mo14442b()) && this.f15055c == aboVar.mo14441c() && this.f15056d == aboVar.mo14440d() && this.f15057e == aboVar.mo14439e();
    }

    public int hashCode() {
        return ((((((((this.f15053a.hashCode() ^ 1000003) * 1000003) ^ this.f15054b.hashCode()) * 1000003) ^ this.f15055c) * 1000003) ^ this.f15056d) * 1000003) ^ this.f15057e;
    }
}
