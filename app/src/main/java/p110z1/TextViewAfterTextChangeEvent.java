package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abh */
/* loaded from: classes3.dex */
public abstract class TextViewAfterTextChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract TextView mo6a();

    @Nullable
    /* renamed from: b */
    public abstract Editable mo5b();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static TextViewAfterTextChangeEvent m14452a(@NonNull TextView textView, @Nullable Editable editable) {
        return new AutoValue_TextViewAfterTextChangeEvent(textView, editable);
    }
}
