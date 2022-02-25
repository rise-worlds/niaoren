package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* renamed from: z1.aau */
/* loaded from: classes3.dex */
public final class RxSeekBar {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static InitialValueObservable<Integer> m14488a(@NonNull SeekBar seekBar) {
        C5596xi.m126a(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, null);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static InitialValueObservable<Integer> m14487b(@NonNull SeekBar seekBar) {
        C5596xi.m126a(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, true);
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static InitialValueObservable<Integer> m14486c(@NonNull SeekBar seekBar) {
        C5596xi.m126a(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, false);
    }

    @CheckResult
    @NonNull
    /* renamed from: d */
    public static InitialValueObservable<SeekBarChangeEvent> m14485d(@NonNull SeekBar seekBar) {
        C5596xi.m126a(seekBar, "view == null");
        return new SeekBarChangeEventObservable(seekBar);
    }

    private RxSeekBar() {
        throw new AssertionError("No instances.");
    }
}
