package p110z1;

/* renamed from: z1.kx */
/* loaded from: classes3.dex */
public class DataCharacter {

    /* renamed from: a */
    public final int f22285a;

    /* renamed from: b */
    public final int f22286b;

    public DataCharacter(int i, int i2) {
        this.f22285a = i;
        this.f22286b = i2;
    }

    /* renamed from: a */
    private int m2142a() {
        return this.f22285a;
    }

    /* renamed from: b */
    private int m2141b() {
        return this.f22286b;
    }

    public final String toString() {
        return this.f22285a + "(" + this.f22286b + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DataCharacter)) {
            return false;
        }
        DataCharacter kxVar = (DataCharacter) obj;
        return this.f22285a == kxVar.f22285a && this.f22286b == kxVar.f22286b;
    }

    public final int hashCode() {
        return this.f22285a ^ this.f22286b;
    }
}
