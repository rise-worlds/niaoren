package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abe */
/* loaded from: classes3.dex */
public abstract class SeekBarProgressChangeEvent extends SeekBarChangeEvent {
    /* renamed from: b */
    public abstract int mo9b();

    /* renamed from: c */
    public abstract boolean mo8c();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static SeekBarProgressChangeEvent m14455a(@NonNull SeekBar seekBar, int i, boolean z) {
        return new AutoValue_SeekBarProgressChangeEvent(seekBar, i, z);
    }
}
