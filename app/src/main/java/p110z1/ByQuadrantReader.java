package p110z1;

import java.util.Map;

/* renamed from: z1.jr */
/* loaded from: classes3.dex */
public final class ByQuadrantReader implements Reader {

    /* renamed from: a */
    private final Reader f22168a;

    private ByQuadrantReader(Reader ojVar) {
        this.f22168a = ojVar;
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, ChecksumException, FormatException {
        return mo1636a(htVar, null);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int a = htVar.m2561a() / 2;
        int b = htVar.m2558b() / 2;
        try {
            try {
                try {
                    try {
                        return this.f22168a.mo1636a(htVar.m2560a(0, 0, a, b), map);
                    } catch (NotFoundException unused) {
                        int i = a / 2;
                        int i2 = b / 2;
                        C5422ol a2 = this.f22168a.mo1636a(htVar.m2560a(i, i2, a, b), map);
                        m2254a(a2.f22710d, i, i2);
                        return a2;
                    }
                } catch (NotFoundException unused2) {
                    C5422ol a3 = this.f22168a.mo1636a(htVar.m2560a(a, b, a, b), map);
                    m2254a(a3.f22710d, a, b);
                    return a3;
                }
            } catch (NotFoundException unused3) {
                C5422ol a4 = this.f22168a.mo1636a(htVar.m2560a(0, b, a, b), map);
                m2254a(a4.f22710d, 0, b);
                return a4;
            }
        } catch (NotFoundException unused4) {
            C5422ol a5 = this.f22168a.mo1636a(htVar.m2560a(a, 0, a, b), map);
            m2254a(a5.f22710d, a, 0);
            return a5;
        }
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        this.f22168a.mo1638a();
    }

    /* renamed from: a */
    private static void m2254a(ResultPoint[] onVarArr, int i, int i2) {
        if (onVarArr != null) {
            for (int i3 = 0; i3 < onVarArr.length; i3++) {
                ResultPoint onVar = onVarArr[i3];
                onVarArr[i3] = new ResultPoint(onVar.f22726c + i, onVar.f22727d + i2);
            }
        }
    }
}
