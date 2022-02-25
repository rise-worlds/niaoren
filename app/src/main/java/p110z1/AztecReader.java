package p110z1;

/* renamed from: z1.fx */
/* loaded from: classes3.dex */
public final class AztecReader implements Reader {
    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, FormatException {
        return mo1636a(htVar, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    @Override // p110z1.Reader
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.C5422ol mo1636a(p110z1.BinaryBitmap r12, java.util.Map<p110z1.DecodeHintType, ?> r13) throws p110z1.NotFoundException, p110z1.FormatException {
        /*
            r11 = this;
            z1.fy r0 = new z1.fy
            z1.hy r12 = r12.m2557c()
            r0.<init>(r12)
            r12 = 0
            r1 = 0
            z1.fv r12 = r0.m2786a(r12)     // Catch: md -> 0x0023, og -> 0x0029
            z1.on[] r2 = r12.f22000e     // Catch: md -> 0x0023, og -> 0x0029
            z1.fw r3 = new z1.fw     // Catch: md -> 0x001f, og -> 0x0021
            r3.<init>()     // Catch: md -> 0x001f, og -> 0x0021
            z1.ig r12 = r3.m2806a(r12)     // Catch: md -> 0x001f, og -> 0x0021
            r3 = r2
            r2 = r1
            r1 = r12
            r12 = r2
            goto L_0x002d
        L_0x001f:
            r12 = move-exception
            goto L_0x0025
        L_0x0021:
            r12 = move-exception
            goto L_0x002b
        L_0x0023:
            r12 = move-exception
            r2 = r1
        L_0x0025:
            r3 = r2
            r2 = r12
            r12 = r1
            goto L_0x002d
        L_0x0029:
            r12 = move-exception
            r2 = r1
        L_0x002b:
            r3 = r2
            r2 = r1
        L_0x002d:
            if (r1 != 0) goto L_0x0049
            r1 = 1
            z1.fv r0 = r0.m2786a(r1)     // Catch: og | md -> 0x0041
            z1.on[] r3 = r0.f22000e     // Catch: og | md -> 0x0041
            z1.fw r1 = new z1.fw     // Catch: og | md -> 0x0041
            r1.<init>()     // Catch: og | md -> 0x0041
            z1.ig r1 = r1.m2806a(r0)     // Catch: og | md -> 0x0041
            r7 = r3
            goto L_0x004a
        L_0x0041:
            r13 = move-exception
            if (r12 != 0) goto L_0x0048
            if (r2 == 0) goto L_0x0047
            throw r2
        L_0x0047:
            throw r13
        L_0x0048:
            throw r12
        L_0x0049:
            r7 = r3
        L_0x004a:
            if (r13 == 0) goto L_0x0051
            z1.jl r12 = p110z1.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r13.get(r12)
        L_0x0051:
            z1.ol r12 = new z1.ol
            java.lang.String r4 = r1.f21991c
            byte[] r5 = r1.f21989a
            int r6 = r1.f21990b
            z1.fu r8 = p110z1.BarcodeFormat.AZTEC
            long r9 = java.lang.System.currentTimeMillis()
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9)
            java.util.List<byte[]> r13 = r1.f21992d
            if (r13 == 0) goto L_0x006c
            z1.om r0 = p110z1.ResultMetadataType.BYTE_SEGMENTS
            r12.m1633a(r0, r13)
        L_0x006c:
            java.lang.String r13 = r1.f21993e
            if (r13 == 0) goto L_0x0075
            z1.om r0 = p110z1.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r12.m1633a(r0, r13)
        L_0x0075:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AztecReader.mo1636a(z1.ht, java.util.Map):z1.ol");
    }
}
