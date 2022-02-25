package p110z1;

/* renamed from: z1.ln */
/* loaded from: classes3.dex */
public final class EAN8Reader extends UPCEANReader {

    /* renamed from: a */
    private final int[] f22362a = new int[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    public final int mo2063a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f22362a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = huVar.f21908b;
        int i2 = iArr[1];
        int i3 = 0;
        while (i3 < 4 && i2 < i) {
            sb.append((char) (m2064a(huVar, iArr2, i2, f22397e) + 48));
            int i4 = i2;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            i3++;
            i2 = i4;
        }
        int i6 = m2066a(huVar, i2, true, f22395c)[1];
        int i7 = 0;
        while (i7 < 4 && i6 < i) {
            sb.append((char) (m2064a(huVar, iArr2, i6, f22397e) + 48));
            int i8 = i6;
            for (int i9 : iArr2) {
                i8 += i9;
            }
            i7++;
            i6 = i8;
        }
        return i6;
    }

    @Override // p110z1.UPCEANReader
    /* renamed from: b */
    final BarcodeFormat mo2062b() {
        return BarcodeFormat.EAN_8;
    }
}
