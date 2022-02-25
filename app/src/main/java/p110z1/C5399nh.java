package p110z1;

import p110z1.C5411np;

/* compiled from: DataBlock.java */
/* renamed from: z1.nh */
/* loaded from: classes3.dex */
final class C5399nh {

    /* renamed from: a */
    final int f22580a;

    /* renamed from: b */
    final byte[] f22581b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5399nh(int i, byte[] bArr) {
        this.f22580a = i;
        this.f22581b = bArr;
    }

    /* renamed from: a */
    private static C5399nh[] m1841a(byte[] bArr, C5411np npVar, ErrorCorrectionLevel nlVar) {
        if (bArr.length == npVar.f22624c) {
            C5411np.C5413b a = npVar.m1809a(nlVar);
            C5411np.C5412a[] aVarArr = a.f22629b;
            int i = 0;
            for (C5411np.C5412a aVar : aVarArr) {
                i += aVar.f22626a;
            }
            C5399nh[] nhVarArr = new C5399nh[i];
            int length = aVarArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                C5411np.C5412a aVar2 = aVarArr[i2];
                int i4 = i3;
                for (int i5 = 0; i5 < aVar2.f22626a; i5++) {
                    int i6 = aVar2.f22627b;
                    i4++;
                    nhVarArr[i4] = new C5399nh(i6, new byte[a.f22628a + i6]);
                }
                i2++;
                i3 = i4;
            }
            int length2 = nhVarArr[0].f22581b.length;
            int length3 = nhVarArr.length - 1;
            while (length3 >= 0 && nhVarArr[length3].f22581b.length != length2) {
                length3--;
            }
            int i7 = length3 + 1;
            int i8 = length2 - a.f22628a;
            int i9 = 0;
            int i10 = 0;
            while (i9 < i8) {
                int i11 = i10;
                for (int i12 = 0; i12 < i3; i12++) {
                    i11++;
                    nhVarArr[i12].f22581b[i9] = bArr[i11];
                }
                i9++;
                i10 = i11;
            }
            for (int i13 = i7; i13 < i3; i13++) {
                i10++;
                nhVarArr[i13].f22581b[i8] = bArr[i10];
            }
            int length4 = nhVarArr[0].f22581b.length;
            while (i8 < length4) {
                int i14 = 0;
                while (i14 < i3) {
                    i10++;
                    nhVarArr[i14].f22581b[i14 < i7 ? i8 : i8 + 1] = bArr[i10];
                    i14++;
                }
                i8++;
                i10 = i10;
            }
            return nhVarArr;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    private int m1842a() {
        return this.f22580a;
    }

    /* renamed from: b */
    private byte[] m1840b() {
        return this.f22581b;
    }
}
