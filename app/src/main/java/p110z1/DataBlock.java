package p110z1;

import p110z1.Version;

/* renamed from: z1.ir */
/* loaded from: classes3.dex */
final class DataBlock {

    /* renamed from: a */
    final int f22036a;

    /* renamed from: b */
    final byte[] f22037b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataBlock(int i, byte[] bArr) {
        this.f22036a = i;
        this.f22037b = bArr;
    }

    /* renamed from: a */
    private int m2407a() {
        return this.f22036a;
    }

    /* renamed from: b */
    private byte[] m2405b() {
        return this.f22037b;
    }

    /* renamed from: a */
    private static DataBlock[] m2406a(byte[] bArr, Version iuVar) {
        Version.C5373b bVar = iuVar.f22059f;
        Version.C5372a[] aVarArr = bVar.f22064b;
        int i = 0;
        for (Version.C5372a aVar : aVarArr) {
            i += aVar.f22061a;
        }
        DataBlock[] irVarArr = new DataBlock[i];
        int length = aVarArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Version.C5372a aVar2 = aVarArr[i2];
            int i4 = i3;
            for (int i5 = 0; i5 < aVar2.f22061a; i5++) {
                int i6 = aVar2.f22062b;
                i4++;
                irVarArr[i4] = new DataBlock(i6, new byte[bVar.f22063a + i6]);
            }
            i2++;
            i3 = i4;
        }
        int length2 = irVarArr[0].f22037b.length - bVar.f22063a;
        int i7 = length2 - 1;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i7) {
            int i10 = i9;
            for (int i11 = 0; i11 < i3; i11++) {
                i10++;
                irVarArr[i11].f22037b[i8] = bArr[i10];
            }
            i8++;
            i9 = i10;
        }
        boolean z = iuVar.f22054a == 24;
        int i12 = z ? 8 : i3;
        int i13 = i9;
        for (int i14 = 0; i14 < i12; i14++) {
            i13++;
            irVarArr[i14].f22037b[i7] = bArr[i13];
        }
        int length3 = irVarArr[0].f22037b.length;
        while (length2 < length3) {
            for (int i15 = 0; i15 < i3; i15++) {
                int i16 = z ? (i15 + 8) % i3 : i15;
                i13++;
                irVarArr[i16].f22037b[(!z || i16 <= 7) ? length2 : length2 - 1] = bArr[i13];
            }
            length2++;
        }
        if (i13 == bArr.length) {
            return irVarArr;
        }
        throw new IllegalArgumentException();
    }
}
