package p110z1;

/* renamed from: z1.gh */
/* loaded from: classes3.dex */
public abstract class Binarizer {

    /* renamed from: a */
    public final LuminanceSource f21780a;

    /* renamed from: a */
    public abstract Binarizer mo2433a(LuminanceSource odVar);

    /* renamed from: a */
    public abstract BitArray mo2442a(int i, BitArray huVar) throws NotFoundException;

    /* renamed from: a */
    public abstract BitMatrix mo2435a() throws NotFoundException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Binarizer(LuminanceSource odVar) {
        this.f21780a = odVar;
    }

    /* renamed from: b */
    private LuminanceSource m2732b() {
        return this.f21780a;
    }

    /* renamed from: c */
    private int m2731c() {
        return this.f21780a.f22688a;
    }

    /* renamed from: d */
    private int m2730d() {
        return this.f21780a.f22689b;
    }
}
