package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.zf */
/* loaded from: classes3.dex */
public abstract class AdapterViewItemLongClickEvent {
    @NonNull
    /* renamed from: a */
    public abstract AdapterView<?> mo23a();

    @NonNull
    /* renamed from: b */
    public abstract View mo22b();

    /* renamed from: c */
    public abstract int mo21c();

    /* renamed from: d */
    public abstract long mo20d();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static AdapterViewItemLongClickEvent m41a(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AutoValue_AdapterViewItemLongClickEvent(adapterView, view, i, j);
    }
}
