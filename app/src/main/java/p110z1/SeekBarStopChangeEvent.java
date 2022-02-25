package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.abg */
/* loaded from: classes3.dex */
public abstract class SeekBarStopChangeEvent extends SeekBarChangeEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static SeekBarStopChangeEvent m14453a(@NonNull SeekBar seekBar) {
        return new AutoValue_SeekBarStopChangeEvent(seekBar);
    }
}
