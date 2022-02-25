package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abf */
/* loaded from: classes3.dex */
public abstract class SeekBarStartChangeEvent extends SeekBarChangeEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static SeekBarStartChangeEvent m14454a(@NonNull SeekBar seekBar) {
        return new AutoValue_SeekBarStartChangeEvent(seekBar);
    }
}
