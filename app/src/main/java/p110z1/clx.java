package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Ranges.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0096\u0002J\u0013\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\t¨\u0006\u0019"}, m8860e = {"Lkotlin/ranges/ClosedDoubleRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "start", "endInclusive", "(DD)V", "_endInclusive", "_start", "getEndInclusive", "()Ljava/lang/Double;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "equals", "other", "", "hashCode", "", "isEmpty", "lessThanOrEquals", "a", "b", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.clx */
/* loaded from: classes3.dex */
final class clx implements clz<Double> {

    /* renamed from: a */
    private final double f20827a;

    /* renamed from: b */
    private final double f20828b;

    /* renamed from: a */
    public boolean m4848a(double d, double d2) {
        return d <= d2;
    }

    public clx(double d, double d2) {
        this.f20827a = d;
        this.f20828b = d2;
    }

    @Override // p110z1.clz, p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(Comparable comparable) {
        return m4849a(((Number) comparable).doubleValue());
    }

    @Override // p110z1.clz
    /* renamed from: a */
    public /* synthetic */ boolean mo4842a(Double d, Double d2) {
        return m4848a(d.doubleValue(), d2.doubleValue());
    }

    @NotNull
    /* renamed from: a */
    public Double mo4665g() {
        return Double.valueOf(this.f20827a);
    }

    @NotNull
    /* renamed from: b */
    public Double mo4663i() {
        return Double.valueOf(this.f20828b);
    }

    /* renamed from: a */
    public boolean m4849a(double d) {
        return d >= this.f20827a && d <= this.f20828b;
    }

    @Override // p110z1.clz, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return this.f20827a > this.f20828b;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof clx) {
            if (!mo4667e() || !((clx) obj).mo4667e()) {
                clx clxVar = (clx) obj;
                if (!(this.f20827a == clxVar.f20827a && this.f20828b == clxVar.f20828b)) {
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
        return (Double.valueOf(this.f20827a).hashCode() * 31) + Double.valueOf(this.f20828b).hashCode();
    }

    @NotNull
    public String toString() {
        return this.f20827a + ".." + this.f20828b;
    }
}
