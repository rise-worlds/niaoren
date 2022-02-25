package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abl */
/* loaded from: classes3.dex */
public abstract class TextViewEditorActionEvent {
    @NonNull
    /* renamed from: a */
    public abstract TextView mo14448a();

    /* renamed from: b */
    public abstract int mo14446b();

    @Nullable
    /* renamed from: c */
    public abstract KeyEvent mo14445c();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static TextViewEditorActionEvent m14447a(@NonNull TextView textView, int i, @Nullable KeyEvent keyEvent) {
        return new AutoValue_TextViewEditorActionEvent(textView, i, keyEvent);
    }
}
