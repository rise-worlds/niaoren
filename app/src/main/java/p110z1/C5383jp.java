package p110z1;

import com.tencent.smtt.sdk.TbsListener;

/* compiled from: Decoder.java */
/* renamed from: z1.jp */
/* loaded from: classes3.dex */
public final class C5383jp {

    /* renamed from: a */
    private static final int f22162a = 0;

    /* renamed from: b */
    private static final int f22163b = 1;

    /* renamed from: c */
    private static final int f22164c = 2;

    /* renamed from: d */
    private final ReedSolomonDecoder f22165d = new ReedSolomonDecoder(GenericGF.f21931h);

    /* renamed from: b */
    private DecoderResult m2257b(BitMatrix hyVar) throws ChecksumException, FormatException {
        return m2259a(hyVar);
    }

    /* renamed from: a */
    public final DecoderResult m2259a(BitMatrix hyVar) throws FormatException, ChecksumException {
        byte[] bArr;
        C5381jn jnVar = new C5381jn(hyVar);
        byte[] bArr2 = new byte[TbsListener.ErrorCode.NEEDDOWNLOAD_5];
        int i = jnVar.f22144b.f21921b;
        int i2 = jnVar.f22144b.f21920a;
        for (int i3 = 0; i3 < i; i3++) {
            int[] iArr = C5381jn.f22143a[i3];
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = iArr[i4];
                if (i5 >= 0 && jnVar.f22144b.m2519a(i4, i3)) {
                    int i6 = i5 / 6;
                    bArr2[i6] = (byte) (((byte) (1 << (5 - (i5 % 6)))) | bArr2[i6]);
                }
            }
        }
        m2258a(bArr2, 0, 10, 10, 0);
        int i7 = bArr2[0] & 15;
        switch (i7) {
            case 2:
            case 3:
            case 4:
                m2258a(bArr2, 20, 84, 40, 1);
                m2258a(bArr2, 20, 84, 40, 2);
                bArr = new byte[94];
                break;
            case 5:
                m2258a(bArr2, 20, 68, 56, 1);
                m2258a(bArr2, 20, 68, 56, 2);
                bArr = new byte[78];
                break;
            default:
                throw FormatException.m2059a();
        }
        System.arraycopy(bArr2, 0, bArr, 0, 10);
        System.arraycopy(bArr2, 20, bArr, 10, bArr.length - 10);
        return C5382jo.m2266a(bArr, i7);
    }

    /* renamed from: a */
    private void m2258a(byte[] bArr, int i, int i2, int i3, int i4) throws ChecksumException {
        int i5 = i2 + i3;
        int i6 = i4 == 0 ? 1 : 2;
        int[] iArr = new int[i5 / i6];
        for (int i7 = 0; i7 < i5; i7++) {
            if (i4 == 0 || i7 % 2 == i4 - 1) {
                iArr[i7 / i6] = bArr[i7 + i] & 255;
            }
        }
        try {
            this.f22165d.m2470a(iArr, i3 / i6);
            for (int i8 = 0; i8 < i2; i8++) {
                if (i4 == 0 || i8 % 2 == i4 - 1) {
                    bArr[i8 + i] = (byte) iArr[i8 / i6];
                }
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.m2421a();
        }
    }
}
