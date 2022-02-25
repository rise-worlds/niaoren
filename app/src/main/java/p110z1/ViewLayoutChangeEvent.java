package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.yo */
/* loaded from: classes3.dex */
public abstract class ViewLayoutChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract View mo63a();

    /* renamed from: b */
    public abstract int mo61b();

    /* renamed from: c */
    public abstract int mo60c();

    /* renamed from: d */
    public abstract int mo59d();

    /* renamed from: e */
    public abstract int mo58e();

    /* renamed from: f */
    public abstract int mo57f();

    /* renamed from: g */
    public abstract int mo56g();

    /* renamed from: h */
    public abstract int mo55h();

    /* renamed from: i */
    public abstract int mo54i();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static ViewLayoutChangeEvent m62a(@NonNull View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new AutoValue_ViewLayoutChangeEvent(view, i, i2, i3, i4, i5, i6, i7, i8);
    }
}
