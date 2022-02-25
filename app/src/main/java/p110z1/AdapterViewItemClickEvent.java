package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.zc */
/* loaded from: classes3.dex */
public abstract class AdapterViewItemClickEvent {
    @NonNull
    /* renamed from: a */
    public abstract AdapterView<?> mo27a();

    @NonNull
    /* renamed from: b */
    public abstract View mo26b();

    /* renamed from: c */
    public abstract int mo25c();

    /* renamed from: d */
    public abstract long mo24d();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static AdapterViewItemClickEvent m42a(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AutoValue_AdapterViewItemClickEvent(adapterView, view, i, j);
    }
}
