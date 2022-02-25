package p110z1;

import android.support.annotation.NonNull;
import android.widget.RatingBar;

/* renamed from: z1.zt */
/* loaded from: classes3.dex */
final class AutoValue_RatingBarChangeEvent extends RatingBarChangeEvent {

    /* renamed from: a */
    private final RatingBar f23820a;

    /* renamed from: b */
    private final float f23821b;

    /* renamed from: c */
    private final boolean f23822c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RatingBarChangeEvent(RatingBar ratingBar, float f, boolean z) {
        if (ratingBar != null) {
            this.f23820a = ratingBar;
            this.f23821b = f;
            this.f23822c = z;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.RatingBarChangeEvent
    @NonNull
    /* renamed from: a */
    public RatingBar mo15a() {
        return this.f23820a;
    }

    @Override // p110z1.RatingBarChangeEvent
    /* renamed from: b */
    public float mo14b() {
        return this.f23821b;
    }

    @Override // p110z1.RatingBarChangeEvent
    /* renamed from: c */
    public boolean mo13c() {
        return this.f23822c;
    }

    public String toString() {
        return "RatingBarChangeEvent{view=" + this.f23820a + ", rating=" + this.f23821b + ", fromUser=" + this.f23822c + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RatingBarChangeEvent)) {
            return false;
        }
        RatingBarChangeEvent aagVar = (RatingBarChangeEvent) obj;
        return this.f23820a.equals(aagVar.mo15a()) && Float.floatToIntBits(this.f23821b) == Float.floatToIntBits(aagVar.mo14b()) && this.f23822c == aagVar.mo13c();
    }

    public int hashCode() {
        return ((((this.f23820a.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f23821b)) * 1000003) ^ (this.f23822c ? 1231 : 1237);
    }
}
