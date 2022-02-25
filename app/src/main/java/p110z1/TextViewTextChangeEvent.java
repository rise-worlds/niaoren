package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abo */
/* loaded from: classes3.dex */
public abstract class TextViewTextChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract TextView mo14444a();

    @NonNull
    /* renamed from: b */
    public abstract CharSequence mo14442b();

    /* renamed from: c */
    public abstract int mo14441c();

    /* renamed from: d */
    public abstract int mo14440d();

    /* renamed from: e */
    public abstract int mo14439e();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static TextViewTextChangeEvent m14443a(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        return new AutoValue_TextViewTextChangeEvent(textView, charSequence, i, i2, i3);
    }
}
