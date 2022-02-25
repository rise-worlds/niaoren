package p110z1;

import java.util.ArrayList;
import java.util.Map;

/* renamed from: z1.ms */
/* loaded from: classes3.dex */
public final class PDF417Reader implements MultipleBarcodeReader, Reader {
    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, FormatException, ChecksumException {
        return mo1636a(htVar, (Map<DecodeHintType, ?>) null);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        C5422ol[] a = m1934a(htVar, false);
        if (a != null && a.length != 0 && a[0] != null) {
            return a[0];
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: b */
    public final C5422ol[] mo1931b(BinaryBitmap htVar) throws NotFoundException {
        return mo1928c(htVar);
    }

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: c */
    public final C5422ol[] mo1928c(BinaryBitmap htVar) throws NotFoundException {
        try {
            return m1934a(htVar, true);
        } catch (ChecksumException | FormatException unused) {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    private static C5422ol[] m1934a(BinaryBitmap htVar, boolean z) throws NotFoundException, FormatException, ChecksumException {
        ArrayList arrayList = new ArrayList();
        PDF417DetectorResult a = C5396mt.m1927a(htVar, z);
        for (ResultPoint[] onVarArr : a.f22508b) {
            DecoderResult a2 = PDF417ScanningDecoder.m1952a(a.f22507a, onVarArr[4], onVarArr[5], onVarArr[6], onVarArr[7], Math.min(Math.min(m1930b(onVarArr[0], onVarArr[4]), (m1930b(onVarArr[6], onVarArr[2]) * 17) / 18), Math.min(m1930b(onVarArr[1], onVarArr[5]), (m1930b(onVarArr[7], onVarArr[3]) * 17) / 18)), Math.max(Math.max(m1933a(onVarArr[0], onVarArr[4]), (m1933a(onVarArr[6], onVarArr[2]) * 17) / 18), Math.max(m1933a(onVarArr[1], onVarArr[5]), (m1933a(onVarArr[7], onVarArr[3]) * 17) / 18)));
            C5422ol olVar = new C5422ol(a2.f21991c, a2.f21989a, onVarArr, BarcodeFormat.PDF_417);
            olVar.m1633a(ResultMetadataType.ERROR_CORRECTION_LEVEL, a2.f21993e);
            PDF417ResultMetadata mvVar = (PDF417ResultMetadata) a2.f21996h;
            if (mvVar != null) {
                olVar.m1633a(ResultMetadataType.PDF417_EXTRA_METADATA, mvVar);
            }
            arrayList.add(olVar);
        }
        return (C5422ol[]) arrayList.toArray(new C5422ol[arrayList.size()]);
    }

    /* renamed from: a */
    private static int m1932a(ResultPoint[] onVarArr) {
        return Math.max(Math.max(m1933a(onVarArr[0], onVarArr[4]), (m1933a(onVarArr[6], onVarArr[2]) * 17) / 18), Math.max(m1933a(onVarArr[1], onVarArr[5]), (m1933a(onVarArr[7], onVarArr[3]) * 17) / 18));
    }

    /* renamed from: b */
    private static int m1929b(ResultPoint[] onVarArr) {
        return Math.min(Math.min(m1930b(onVarArr[0], onVarArr[4]), (m1930b(onVarArr[6], onVarArr[2]) * 17) / 18), Math.min(m1930b(onVarArr[1], onVarArr[5]), (m1930b(onVarArr[7], onVarArr[3]) * 17) / 18));
    }

    /* renamed from: a */
    private static int m1933a(ResultPoint onVar, ResultPoint onVar2) {
        if (onVar == null || onVar2 == null) {
            return 0;
        }
        return (int) Math.abs(onVar.f22726c - onVar2.f22726c);
    }

    /* renamed from: b */
    private static int m1930b(ResultPoint onVar, ResultPoint onVar2) {
        if (onVar == null || onVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(onVar.f22726c - onVar2.f22726c);
    }
}
