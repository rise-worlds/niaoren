package javax.mail.search;

/* loaded from: classes2.dex */
public abstract class ComparisonTerm extends SearchTerm {

    /* renamed from: EQ */
    public static final int f14660EQ = 3;

    /* renamed from: GE */
    public static final int f14661GE = 6;

    /* renamed from: GT */
    public static final int f14662GT = 5;

    /* renamed from: LE */
    public static final int f14663LE = 1;

    /* renamed from: LT */
    public static final int f14664LT = 2;

    /* renamed from: NE */
    public static final int f14665NE = 4;
    private static final long serialVersionUID = 1456646953666474308L;
    protected int comparison;

    public boolean equals(Object obj) {
        return (obj instanceof ComparisonTerm) && ((ComparisonTerm) obj).comparison == this.comparison;
    }

    public int hashCode() {
        return this.comparison;
    }
}
