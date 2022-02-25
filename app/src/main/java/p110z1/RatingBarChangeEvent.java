package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.aag */
/* loaded from: classes3.dex */
public abstract class RatingBarChangeEvent {
    @NonNull
    /* renamed from: a */
    public abstract RatingBar mo15a();

    /* renamed from: b */
    public abstract float mo14b();

    /* renamed from: c */
    public abstract boolean mo13c();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static RatingBarChangeEvent m14529a(@NonNull RatingBar ratingBar, float f, boolean z) {
        return new AutoValue_RatingBarChangeEvent(ratingBar, f, z);
    }
}
