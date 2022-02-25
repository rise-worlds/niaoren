package p110z1;

/* renamed from: z1.oc */
/* loaded from: classes3.dex */
public final class QRCode {

    /* renamed from: a */
    public static final int f22682a = 8;

    /* renamed from: b */
    Mode f22683b;

    /* renamed from: c */
    ErrorCorrectionLevel f22684c;

    /* renamed from: d */
    C5411np f22685d;

    /* renamed from: e */
    int f22686e = -1;

    /* renamed from: f */
    public ByteMatrix f22687f;

    /* renamed from: b */
    private static boolean m1660b(int i) {
        return i >= 0 && i < 8;
    }

    /* renamed from: a */
    private Mode m1667a() {
        return this.f22683b;
    }

    /* renamed from: b */
    private ErrorCorrectionLevel m1661b() {
        return this.f22684c;
    }

    /* renamed from: c */
    private C5411np m1659c() {
        return this.f22685d;
    }

    /* renamed from: d */
    private int m1658d() {
        return this.f22686e;
    }

    /* renamed from: e */
    private ByteMatrix m1657e() {
        return this.f22687f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f22683b);
        sb.append("\n ecLevel: ");
        sb.append(this.f22684c);
        sb.append("\n version: ");
        sb.append(this.f22685d);
        sb.append("\n maskPattern: ");
        sb.append(this.f22686e);
        if (this.f22687f == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f22687f);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    /* renamed from: a */
    private void m1664a(Mode nnVar) {
        this.f22683b = nnVar;
    }

    /* renamed from: a */
    private void m1665a(ErrorCorrectionLevel nlVar) {
        this.f22684c = nlVar;
    }

    /* renamed from: a */
    private void m1663a(C5411np npVar) {
        this.f22685d = npVar;
    }

    /* renamed from: a */
    private void m1666a(int i) {
        this.f22686e = i;
    }

    /* renamed from: a */
    private void m1662a(ByteMatrix nyVar) {
        this.f22687f = nyVar;
    }
}
