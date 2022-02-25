package p110z1;

import java.util.Arrays;
import java.util.Map;

/* renamed from: z1.lu */
/* loaded from: classes3.dex */
public abstract class OneDReader implements Reader {
    /* renamed from: a */
    public abstract C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // p110z1.Reader
    /* renamed from: a */
    public void mo1638a() {
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, FormatException {
        return mo1636a(htVar, null);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return m2088b(htVar, map);
        } catch (NotFoundException e) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !htVar.f21905a.f21780a.mo1656c()) {
                throw e;
            }
            BinaryBitmap htVar2 = new BinaryBitmap(htVar.f21905a.mo2433a(htVar.f21905a.f21780a.mo1654e()));
            C5422ol b = m2088b(htVar2, map);
            Map<ResultMetadataType, Object> map2 = b.f22712f;
            int i = 270;
            if (map2 != null && map2.containsKey(ResultMetadataType.ORIENTATION)) {
                i = (((Integer) map2.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            b.m1633a(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
            ResultPoint[] onVarArr = b.f22710d;
            if (onVarArr != null) {
                int b2 = htVar2.m2558b();
                for (int i2 = 0; i2 < onVarArr.length; i2++) {
                    onVarArr[i2] = new ResultPoint((b2 - onVarArr[i2].f22727d) - 1.0f, onVarArr[i2].f22726c);
                }
            }
            return b;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0083 A[Catch: ok -> 0x00c0, TryCatch #1 {ok -> 0x00c0, blocks: (B:38:0x007d, B:40:0x0083, B:42:0x0092), top: B:66:0x007d }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00bf A[SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.C5422ol m2088b(p110z1.BinaryBitmap r20, java.util.Map<p110z1.DecodeHintType, ?> r21) throws p110z1.NotFoundException {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.OneDReader.m2088b(z1.ht, java.util.Map):z1.ol");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m2090a(BitArray huVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int i2 = huVar.f21908b;
        if (i < i2) {
            boolean z = !huVar.m2551a(i);
            int i3 = 0;
            while (i < i2) {
                if (huVar.m2551a(i) == z) {
                    i3++;
                    if (i3 == length) {
                        break;
                    }
                    iArr[i3] = 1;
                    z = !z;
                } else {
                    iArr[i3] = iArr[i3] + 1;
                }
                i++;
            }
            if (i3 == length) {
                return;
            }
            if (i3 != length - 1 || i != i2) {
                throw NotFoundException.m1647a();
            }
            return;
        }
        throw NotFoundException.m1647a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static void m2087b(BitArray huVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean a = huVar.m2551a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (huVar.m2551a(i) != a) {
                length--;
                a = !a;
            }
        }
        if (length < 0) {
            m2090a(huVar, i + 1, iArr);
            return;
        }
        throw NotFoundException.m1647a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static float m2089a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f6 = iArr2[i4] * f3;
            float f7 = iArr[i4];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
