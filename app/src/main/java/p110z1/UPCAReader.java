package p110z1;

import java.util.Map;

/* renamed from: z1.lw */
/* loaded from: classes3.dex */
public final class UPCAReader extends UPCEANReader {

    /* renamed from: a */
    private final UPCEANReader f22383a = new EAN13Reader();

    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    public final C5422ol mo2071a(int i, BitArray huVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return m2083a(this.f22383a.mo2071a(i, huVar, iArr, map));
    }

    @Override // p110z1.UPCEANReader, p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return m2083a(this.f22383a.mo2072a(i, huVar, map));
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, FormatException {
        return m2083a(this.f22383a.mo1637a(htVar));
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        return m2083a(this.f22383a.mo1636a(htVar, map));
    }

    @Override // p110z1.UPCEANReader
    /* renamed from: b */
    final BarcodeFormat mo2062b() {
        return BarcodeFormat.UPC_A;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    public final int mo2063a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        return this.f22383a.mo2063a(huVar, iArr, sb);
    }

    /* renamed from: a */
    private static C5422ol m2083a(C5422ol olVar) throws FormatException {
        String str = olVar.f22707a;
        if (str.charAt(0) == '0') {
            C5422ol olVar2 = new C5422ol(str.substring(1), null, olVar.f22710d, BarcodeFormat.UPC_A);
            if (olVar.f22712f != null) {
                olVar2.m1634a(olVar.f22712f);
            }
            return olVar2;
        }
        throw FormatException.m2059a();
    }
}
