package p110z1;

import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* renamed from: z1.zv */
/* loaded from: classes3.dex */
final class AutoValue_SeekBarProgressChangeEvent extends SeekBarProgressChangeEvent {

    /* renamed from: a */
    private final SeekBar f23826a;

    /* renamed from: b */
    private final int f23827b;

    /* renamed from: c */
    private final boolean f23828c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SeekBarProgressChangeEvent(SeekBar seekBar, int i, boolean z) {
        if (seekBar != null) {
            this.f23826a = seekBar;
            this.f23827b = i;
            this.f23828c = z;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.SeekBarChangeEvent
    @NonNull
    /* renamed from: a */
    public SeekBar mo7a() {
        return this.f23826a;
    }

    @Override // p110z1.SeekBarProgressChangeEvent
    /* renamed from: b */
    public int mo9b() {
        return this.f23827b;
    }

    @Override // p110z1.SeekBarProgressChangeEvent
    /* renamed from: c */
    public boolean mo8c() {
        return this.f23828c;
    }

    public String toString() {
        return "SeekBarProgressChangeEvent{view=" + this.f23826a + ", progress=" + this.f23827b + ", fromUser=" + this.f23828c + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SeekBarProgressChangeEvent)) {
            return false;
        }
        SeekBarProgressChangeEvent abeVar = (SeekBarProgressChangeEvent) obj;
        return this.f23826a.equals(abeVar.mo7a()) && this.f23827b == abeVar.mo9b() && this.f23828c == abeVar.mo8c();
    }

    public int hashCode() {
        return ((((this.f23826a.hashCode() ^ 1000003) * 1000003) ^ this.f23827b) * 1000003) ^ (this.f23828c ? 1231 : 1237);
    }
}
