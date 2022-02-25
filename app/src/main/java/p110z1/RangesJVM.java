package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0096\u0002J\u0013\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\t¨\u0006\u0019"}, m8860e = {"Lkotlin/ranges/ClosedFloatRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "start", "endInclusive", "(FF)V", "_endInclusive", "_start", "getEndInclusive", "()Ljava/lang/Float;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "equals", "other", "", "hashCode", "", "isEmpty", "lessThanOrEquals", "a", "b", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.cly */
/* loaded from: classes3.dex */
final class RangesJVM implements clz<Float> {

    /* renamed from: a */
    private final float f20829a;

    /* renamed from: b */
    private final float f20830b;

    /* renamed from: a */
    public boolean m4844a(float f, float f2) {
        return f <= f2;
    }

    public RangesJVM(float f, float f2) {
        this.f20829a = f;
        this.f20830b = f2;
    }

    @Override // p110z1.clz, p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(Comparable comparable) {
        return m4845a(((Number) comparable).floatValue());
    }

    @Override // p110z1.clz
    /* renamed from: a */
    public /* synthetic */ boolean mo4842a(Float f, Float f2) {
        return m4844a(f.floatValue(), f2.floatValue());
    }

    @NotNull
    /* renamed from: a */
    public Float mo4665g() {
        return Float.valueOf(this.f20829a);
    }

    @NotNull
    /* renamed from: b */
    public Float mo4663i() {
        return Float.valueOf(this.f20830b);
    }

    /* renamed from: a */
    public boolean m4845a(float f) {
        return f >= this.f20829a && f <= this.f20830b;
    }

    @Override // p110z1.clz, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return this.f20829a > this.f20830b;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof RangesJVM) {
            if (!mo4667e() || !((RangesJVM) obj).mo4667e()) {
                RangesJVM clyVar = (RangesJVM) obj;
                if (!(this.f20829a == clyVar.f20829a && this.f20830b == clyVar.f20830b)) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (Float.valueOf(this.f20829a).hashCode() * 31) + Float.valueOf(this.f20830b).hashCode();
    }

    @NotNull
    public String toString() {
        return this.f20829a + ".." + this.f20830b;
    }
}
