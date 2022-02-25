package p110z1;

import org.apache.tools.tar.TarConstants;

/* renamed from: z1.ja */
/* loaded from: classes3.dex */
final class DataMatrixSymbolInfo144 extends SymbolInfo {
    @Override // p110z1.SymbolInfo
    /* renamed from: a */
    public final int mo2293a() {
        return 10;
    }

    @Override // p110z1.SymbolInfo
    /* renamed from: a */
    public final int mo2292a(int i) {
        if (i <= 8) {
            return 156;
        }
        return TarConstants.PREFIXLEN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataMatrixSymbolInfo144() {
        super(false, 1558, 620, 22, 22, 36, -1, 62);
    }
}
