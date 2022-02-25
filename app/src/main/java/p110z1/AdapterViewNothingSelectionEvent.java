package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.zk */
/* loaded from: classes3.dex */
public abstract class AdapterViewNothingSelectionEvent extends AdapterViewSelectionEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static AdapterViewSelectionEvent m38a(@NonNull AdapterView<?> adapterView) {
        return new AutoValue_AdapterViewNothingSelectionEvent(adapterView);
    }
}
