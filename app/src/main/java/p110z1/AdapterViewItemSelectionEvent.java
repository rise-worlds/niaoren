package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.zi */
/* loaded from: classes3.dex */
public abstract class AdapterViewItemSelectionEvent extends AdapterViewSelectionEvent {
    @NonNull
    /* renamed from: a */
    public abstract View mo19a();

    /* renamed from: b */
    public abstract int mo18b();

    /* renamed from: c */
    public abstract long mo17c();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static AdapterViewSelectionEvent m40a(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AutoValue_AdapterViewItemSelectionEvent(adapterView, view, i, j);
    }
}
