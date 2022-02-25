package p110z1;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ly */
/* loaded from: classes3.dex */
public final class UPCEANExtension2Support {

    /* renamed from: a */
    private final int[] f22385a = new int[4];

    /* renamed from: b */
    private final StringBuilder f22386b = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C5422ol m2082a(int i, BitArray huVar, int[] iArr) throws NotFoundException {
        EnumMap enumMap;
        StringBuilder sb = this.f22386b;
        sb.setLength(0);
        int[] iArr2 = this.f22385a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = huVar.f21908b;
        int i3 = iArr[1];
        int i4 = 0;
        int i5 = 0;
        while (i4 < 2 && i3 < i2) {
            int a = UPCEANReader.m2064a(huVar, iArr2, i3, UPCEANReader.f22398f);
            sb.append((char) ((a % 10) + 48));
            int i6 = i3;
            for (int i7 : iArr2) {
                i6 += i7;
            }
            if (a >= 10) {
                i5 = (1 << (1 - i4)) | i5;
            }
            i3 = i4 != 1 ? huVar.m2538d(huVar.m2541c(i6)) : i6;
            i4++;
        }
        if (sb.length() != 2) {
            throw NotFoundException.m1647a();
        } else if (Integer.parseInt(sb.toString()) % 4 == i5) {
            String sb2 = sb.toString();
            if (sb2.length() != 2) {
                enumMap = null;
            } else {
                enumMap = new EnumMap(ResultMetadataType.class);
                enumMap.put((EnumMap) ResultMetadataType.ISSUE_NUMBER, (ResultMetadataType) Integer.valueOf(sb2));
            }
            float f = i;
            C5422ol olVar = new C5422ol(sb2, null, new ResultPoint[]{new ResultPoint((iArr[0] + iArr[1]) / 2.0f, f), new ResultPoint(i3, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
            if (enumMap != null) {
                olVar.m1634a(enumMap);
            }
            return olVar;
        } else {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    private int m2080a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f22385a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = huVar.f21908b;
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 2 && i2 < i) {
            int a = UPCEANReader.m2064a(huVar, iArr2, i2, UPCEANReader.f22398f);
            sb.append((char) ((a % 10) + 48));
            int i5 = i2;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (a >= 10) {
                i4 = (1 << (1 - i3)) | i4;
            }
            i2 = i3 != 1 ? huVar.m2538d(huVar.m2541c(i5)) : i5;
            i3++;
        }
        if (sb.length() != 2) {
            throw NotFoundException.m1647a();
        } else if (Integer.parseInt(sb.toString()) % 4 == i4) {
            return i2;
        } else {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    private static Map<ResultMetadataType, Object> m2081a(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put((EnumMap) ResultMetadataType.ISSUE_NUMBER, (ResultMetadataType) Integer.valueOf(str));
        return enumMap;
    }
}
