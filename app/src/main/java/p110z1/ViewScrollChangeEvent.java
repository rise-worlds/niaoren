package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.ys */
/* loaded from: classes3.dex */
public abstract class ViewScrollChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract View mo53a();

    /* renamed from: b */
    public abstract int mo51b();

    /* renamed from: c */
    public abstract int mo50c();

    /* renamed from: d */
    public abstract int mo49d();

    /* renamed from: e */
    public abstract int mo48e();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static ViewScrollChangeEvent m52a(@NonNull View view, int i, int i2, int i3, int i4) {
        return new AutoValue_ViewScrollChangeEvent(view, i, i2, i3, i4);
    }
}
