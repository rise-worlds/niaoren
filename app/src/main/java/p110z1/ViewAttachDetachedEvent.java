package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.yb */
/* loaded from: classes3.dex */
public abstract class ViewAttachDetachedEvent extends ViewAttachEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static ViewAttachDetachedEvent m70a(@NonNull View view) {
        return new AutoValue_ViewAttachDetachedEvent(view);
    }
}
