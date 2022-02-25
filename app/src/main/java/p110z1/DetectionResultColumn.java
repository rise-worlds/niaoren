package p110z1;

import java.util.Formatter;

/* renamed from: z1.mo */
/* loaded from: classes3.dex */
class DetectionResultColumn {

    /* renamed from: c */
    private static final int f22487c = 5;

    /* renamed from: a */
    final BoundingBox f22488a;

    /* renamed from: b */
    final Codeword[] f22489b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn(BoundingBox mkVar) {
        this.f22488a = new BoundingBox(mkVar);
        this.f22489b = new Codeword[(mkVar.f22437i - mkVar.f22436h) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Codeword m1975a(int i) {
        Codeword mlVar;
        Codeword mlVar2;
        Codeword c = m1971c(i);
        if (c != null) {
            return c;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int b = m1972b(i) - i2;
            if (b >= 0 && (mlVar2 = this.f22489b[b]) != null) {
                return mlVar2;
            }
            int b2 = m1972b(i) + i2;
            Codeword[] mlVarArr = this.f22489b;
            if (b2 < mlVarArr.length && (mlVar = mlVarArr[b2]) != null) {
                return mlVar;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m1972b(int i) {
        return i - this.f22488a.f22436h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1974a(int i, Codeword mlVar) {
        this.f22489b[m1972b(i)] = mlVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final Codeword m1971c(int i) {
        return this.f22489b[m1972b(i)];
    }

    /* renamed from: a */
    private BoundingBox m1976a() {
        return this.f22488a;
    }

    /* renamed from: b */
    private Codeword[] m1973b() {
        return this.f22489b;
    }

    public String toString() {
        Codeword[] mlVarArr;
        Formatter formatter = new Formatter();
        try {
            int i = 0;
            for (Codeword mlVar : this.f22489b) {
                if (mlVar == null) {
                    i++;
                    formatter.format("%3d:    |   %n", Integer.valueOf(i));
                } else {
                    i++;
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(mlVar.f22443e), Integer.valueOf(mlVar.f22442d));
                }
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } finally {
            try {
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
