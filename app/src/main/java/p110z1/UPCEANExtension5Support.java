package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.lz */
/* loaded from: classes3.dex */
public final class UPCEANExtension5Support {

    /* renamed from: a */
    static final int[] f22387a = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* renamed from: b */
    final int[] f22388b = new int[4];

    /* renamed from: c */
    final StringBuilder f22389c = new StringBuilder();

    /* JADX WARN: Removed duplicated region for block: B:65:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0128  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.C5422ol m2078a(int r18, p110z1.BitArray r19, int[] r20) throws p110z1.NotFoundException {
        /*
            Method dump skipped, instructions count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.UPCEANExtension5Support.m2078a(int, z1.hu, int[]):z1.ol");
    }

    /* renamed from: a */
    private int m2075a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        String sb2;
        int[] iArr2 = this.f22388b;
        int i = 0;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = huVar.f21908b;
        int i3 = iArr[1];
        int i4 = 0;
        int i5 = 0;
        while (i4 < 5 && i3 < i2) {
            int a = UPCEANReader.m2064a(huVar, iArr2, i3, UPCEANReader.f22398f);
            sb.append((char) ((a % 10) + 48));
            int i6 = i3;
            for (int i7 : iArr2) {
                i6 += i7;
            }
            if (a >= 10) {
                i5 |= 1 << (4 - i4);
            }
            i3 = i4 != 4 ? huVar.m2538d(huVar.m2541c(i6)) : i6;
            i4++;
        }
        if (sb.length() == 5) {
            for (int i8 = 0; i8 < 10; i8++) {
                if (i5 == f22387a[i8]) {
                    int length = sb.toString().length();
                    for (int i9 = length - 2; i9 >= 0; i9 -= 2) {
                        i += sb2.charAt(i9) - '0';
                    }
                    int i10 = i * 3;
                    for (int i11 = length - 1; i11 >= 0; i11 -= 2) {
                        i10 += sb2.charAt(i11) - '0';
                    }
                    if ((i10 * 3) % 10 == i8) {
                        return i3;
                    }
                    throw NotFoundException.m1647a();
                }
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int m2077a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += charSequence.charAt(i2) - '0';
        }
        int i3 = i * 3;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            i3 += charSequence.charAt(i4) - '0';
        }
        return (i3 * 3) % 10;
    }

    /* renamed from: a */
    private static int m2079a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == f22387a[i2]) {
                return i2;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
        if (r6.equals("90000") != false) goto L_0x004a;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map<p110z1.ResultMetadataType, java.lang.Object> m2076a(java.lang.String r6) {
        /*
            int r0 = r6.length()
            r1 = 0
            r2 = 5
            if (r0 == r2) goto L_0x0009
            return r1
        L_0x0009:
            r0 = 0
            char r2 = r6.charAt(r0)
            r3 = 48
            r4 = 1
            if (r2 == r3) goto L_0x005b
            r3 = 53
            if (r2 == r3) goto L_0x0058
            r3 = 57
            if (r2 == r3) goto L_0x001e
            java.lang.String r0 = ""
            goto L_0x005d
        L_0x001e:
            r2 = -1
            int r3 = r6.hashCode()
            r5 = 54118329(0x339c7b9, float:5.4595884E-37)
            if (r3 == r5) goto L_0x0040
            switch(r3) {
                case 54395376: goto L_0x0036;
                case 54395377: goto L_0x002c;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x0049
        L_0x002c:
            java.lang.String r0 = "99991"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 1
            goto L_0x004a
        L_0x0036:
            java.lang.String r0 = "99990"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 2
            goto L_0x004a
        L_0x0040:
            java.lang.String r3 = "90000"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L_0x0049
            goto L_0x004a
        L_0x0049:
            r0 = -1
        L_0x004a:
            switch(r0) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0053;
                case 2: goto L_0x0050;
                default: goto L_0x004d;
            }
        L_0x004d:
            java.lang.String r0 = ""
            goto L_0x005d
        L_0x0050:
            java.lang.String r6 = "Used"
            goto L_0x0097
        L_0x0053:
            java.lang.String r6 = "0.00"
            goto L_0x0097
        L_0x0056:
            r6 = r1
            goto L_0x0097
        L_0x0058:
            java.lang.String r0 = "$"
            goto L_0x005d
        L_0x005b:
            java.lang.String r0 = "£"
        L_0x005d:
            java.lang.String r6 = r6.substring(r4)
            int r6 = java.lang.Integer.parseInt(r6)
            int r2 = r6 / 100
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r6 = r6 % 100
            r3 = 10
            if (r6 >= r3) goto L_0x007c
            java.lang.String r3 = "0"
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r6 = r3.concat(r6)
            goto L_0x0080
        L_0x007c:
            java.lang.String r6 = java.lang.String.valueOf(r6)
        L_0x0080:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r2)
            r0 = 46
            r3.append(r0)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
        L_0x0097:
            if (r6 != 0) goto L_0x009a
            return r1
        L_0x009a:
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<z1.om> r1 = p110z1.ResultMetadataType.class
            r0.<init>(r1)
            z1.om r1 = p110z1.ResultMetadataType.SUGGESTED_PRICE
            r0.put(r1, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.UPCEANExtension5Support.m2076a(java.lang.String):java.util.Map");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
        if (r5.equals("90000") != false) goto L_0x0041;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m2074b(java.lang.String r5) {
        /*
            r0 = 0
            char r1 = r5.charAt(r0)
            r2 = 48
            r3 = 1
            if (r1 == r2) goto L_0x0052
            r2 = 53
            if (r1 == r2) goto L_0x004f
            r2 = 57
            if (r1 == r2) goto L_0x0015
            java.lang.String r0 = ""
            goto L_0x0054
        L_0x0015:
            r1 = -1
            int r2 = r5.hashCode()
            r4 = 54118329(0x339c7b9, float:5.4595884E-37)
            if (r2 == r4) goto L_0x0037
            switch(r2) {
                case 54395376: goto L_0x002d;
                case 54395377: goto L_0x0023;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x0040
        L_0x0023:
            java.lang.String r0 = "99991"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x002d:
            java.lang.String r0 = "99990"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 2
            goto L_0x0041
        L_0x0037:
            java.lang.String r2 = "90000"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r0 = -1
        L_0x0041:
            switch(r0) {
                case 0: goto L_0x004d;
                case 1: goto L_0x004a;
                case 2: goto L_0x0047;
                default: goto L_0x0044;
            }
        L_0x0044:
            java.lang.String r0 = ""
            goto L_0x0054
        L_0x0047:
            java.lang.String r5 = "Used"
            return r5
        L_0x004a:
            java.lang.String r5 = "0.00"
            return r5
        L_0x004d:
            r5 = 0
            return r5
        L_0x004f:
            java.lang.String r0 = "$"
            goto L_0x0054
        L_0x0052:
            java.lang.String r0 = "£"
        L_0x0054:
            java.lang.String r5 = r5.substring(r3)
            int r5 = java.lang.Integer.parseInt(r5)
            int r1 = r5 / 100
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r5 = r5 % 100
            r2 = 10
            if (r5 >= r2) goto L_0x0073
            java.lang.String r2 = "0"
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0077
        L_0x0073:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L_0x0077:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r1)
            r0 = 46
            r2.append(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.UPCEANExtension5Support.m2074b(java.lang.String):java.lang.String");
    }
}
