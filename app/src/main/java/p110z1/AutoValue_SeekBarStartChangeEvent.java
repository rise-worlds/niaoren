package p110z1;

import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* renamed from: z1.zw */
/* loaded from: classes3.dex */
final class AutoValue_SeekBarStartChangeEvent extends SeekBarStartChangeEvent {

    /* renamed from: a */
    private final SeekBar f23829a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SeekBarStartChangeEvent(SeekBar seekBar) {
        if (seekBar != null) {
            this.f23829a = seekBar;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.SeekBarChangeEvent
    @NonNull
    /* renamed from: a */
    public SeekBar mo7a() {
        return this.f23829a;
    }

    public String toString() {
        return "SeekBarStartChangeEvent{view=" + this.f23829a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SeekBarStartChangeEvent) {
            return this.f23829a.equals(((SeekBarStartChangeEvent) obj).mo7a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23829a.hashCode() ^ 1000003;
    }
}
