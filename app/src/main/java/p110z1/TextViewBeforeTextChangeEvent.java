package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abj */
/* loaded from: classes3.dex */
public abstract class TextViewBeforeTextChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract TextView mo4a();

    @NonNull
    /* renamed from: b */
    public abstract CharSequence mo3b();

    /* renamed from: c */
    public abstract int mo2c();

    /* renamed from: d */
    public abstract int mo1d();

    /* renamed from: e */
    public abstract int mo0e();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static TextViewBeforeTextChangeEvent m14450a(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        return new AutoValue_TextViewBeforeTextChangeEvent(textView, charSequence, i, i2, i3);
    }
}
