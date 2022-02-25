package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.ya */
/* loaded from: classes3.dex */
public abstract class ViewAttachAttachedEvent extends ViewAttachEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static ViewAttachAttachedEvent m71a(@NonNull View view) {
        return new AutoValue_ViewAttachAttachedEvent(view);
    }
}
