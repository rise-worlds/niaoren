package p110z1;

import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* renamed from: z1.zx */
/* loaded from: classes3.dex */
final class AutoValue_SeekBarStopChangeEvent extends SeekBarStopChangeEvent {

    /* renamed from: a */
    private final SeekBar f23830a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SeekBarStopChangeEvent(SeekBar seekBar) {
        if (seekBar != null) {
            this.f23830a = seekBar;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.SeekBarChangeEvent
    @NonNull
    /* renamed from: a */
    public SeekBar mo7a() {
        return this.f23830a;
    }

    public String toString() {
        return "SeekBarStopChangeEvent{view=" + this.f23830a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SeekBarStopChangeEvent) {
            return this.f23830a.equals(((SeekBarStopChangeEvent) obj).mo7a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23830a.hashCode() ^ 1000003;
    }
}
