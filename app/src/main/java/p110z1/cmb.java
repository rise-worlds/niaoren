package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.Comparable;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Range;

/* compiled from: Ranges.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, m8860e = {"Lkotlin/ranges/ComparableRange;", TessBaseAPI.f9204e, "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.cmb */
/* loaded from: classes3.dex */
class cmb<T extends Comparable<? super T>> implements Range<T> {
    @NotNull

    /* renamed from: a */
    private final T f20834a;
    @NotNull

    /* renamed from: b */
    private final T f20835b;

    public cmb(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "start");
        cji.m5162f(t2, "endInclusive");
        this.f20834a = t;
        this.f20835b = t2;
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public boolean mo4668a(@NotNull T t) {
        cji.m5162f(t, SizeSelector.SIZE_KEY);
        return Range.C4986a.m4834a(this, t);
    }

    @Override // p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return Range.C4986a.m4835a(this);
    }

    @Override // p110z1.Range
    @NotNull
    /* renamed from: g */
    public T mo4665g() {
        return this.f20834a;
    }

    @Override // p110z1.Range
    @NotNull
    /* renamed from: i */
    public T mo4663i() {
        return this.f20835b;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmb) {
            if (!mo4667e() || !((cmb) obj).mo4667e()) {
                cmb cmbVar = (cmb) obj;
                if (!cji.m5184a(mo4665g(), cmbVar.mo4665g()) || !cji.m5184a(mo4663i(), cmbVar.mo4663i())) {
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
        return (mo4665g().hashCode() * 31) + mo4663i().hashCode();
    }

    @NotNull
    public String toString() {
        return mo4665g() + ".." + mo4663i();
    }
}
