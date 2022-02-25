package p110z1;

/* renamed from: z1.nl */
/* loaded from: classes3.dex */
public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    

    /* renamed from: f */
    private static final ErrorCorrectionLevel[] f22599f;

    /* renamed from: e */
    public final int f22601e;

    static {
        ErrorCorrectionLevel nlVar = L;
        ErrorCorrectionLevel nlVar2 = M;
        ErrorCorrectionLevel nlVar3 = Q;
        f22599f = new ErrorCorrectionLevel[]{nlVar2, nlVar, H, nlVar3};
    }

    ErrorCorrectionLevel(int i) {
        this.f22601e = i;
    }

    /* renamed from: a */
    private int m1823a() {
        return this.f22601e;
    }

    /* renamed from: a */
    public static ErrorCorrectionLevel m1822a(int i) {
        if (i >= 0) {
            ErrorCorrectionLevel[] nlVarArr = f22599f;
            if (i < nlVarArr.length) {
                return nlVarArr[i];
            }
        }
        throw new IllegalArgumentException();
    }
}
