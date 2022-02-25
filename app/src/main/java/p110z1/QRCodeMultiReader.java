package p110z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: z1.js */
/* loaded from: classes3.dex */
public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {

    /* renamed from: b */
    private static final C5422ol[] f22169b = new C5422ol[0];

    /* renamed from: c */
    private static final ResultPoint[] f22170c = new ResultPoint[0];

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: b */
    public final C5422ol[] mo1931b(BinaryBitmap htVar) throws NotFoundException {
        return mo1928c(htVar);
    }

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: c */
    public final C5422ol[] mo1928c(BinaryBitmap htVar) throws NotFoundException {
        DetectorResult[] a;
        ArrayList arrayList = new ArrayList();
        for (DetectorResult iiVar : new MultiDetector(htVar.m2557c()).m2251a()) {
            try {
                DecoderResult a2 = this.f22575a.m1828a(iiVar.f21999d, (Map<DecodeHintType, ?>) null);
                ResultPoint[] onVarArr = iiVar.f22000e;
                if (a2.f21996h instanceof QRCodeDecoderMetaData) {
                    ((QRCodeDecoderMetaData) a2.f21996h).m1812a(onVarArr);
                }
                C5422ol olVar = new C5422ol(a2.f21991c, a2.f21989a, onVarArr, BarcodeFormat.QR_CODE);
                List<byte[]> list = a2.f21992d;
                if (list != null) {
                    olVar.m1633a(ResultMetadataType.BYTE_SEGMENTS, list);
                }
                String str = a2.f21993e;
                if (str != null) {
                    olVar.m1633a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                }
                if (a2.m2460a()) {
                    olVar.m1633a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a2.f21998j));
                    olVar.m1633a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a2.f21997i));
                }
                arrayList.add(olVar);
            } catch (ReaderException unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return f22169b;
        }
        List<C5422ol> a3 = m2253a(arrayList);
        return (C5422ol[]) a3.toArray(new C5422ol[a3.size()]);
    }

    /* renamed from: a */
    private static List<C5422ol> m2253a(List<C5422ol> list) {
        boolean z;
        Iterator<C5422ol> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().f22712f.containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<C5422ol> arrayList2 = new ArrayList();
        for (C5422ol olVar : list) {
            arrayList.add(olVar);
            if (olVar.f22712f.containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(olVar);
            }
        }
        Collections.sort(arrayList2, new C5386a((byte) 0));
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (C5422ol olVar2 : arrayList2) {
            sb.append(olVar2.f22707a);
            i += olVar2.f22708b.length;
            if (olVar2.f22712f.containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] bArr : (Iterable) olVar2.f22712f.get(ResultMetadataType.BYTE_SEGMENTS)) {
                    i2 += bArr.length;
                }
            }
        }
        byte[] bArr2 = new byte[i];
        byte[] bArr3 = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        for (C5422ol olVar3 : arrayList2) {
            System.arraycopy(olVar3.f22708b, 0, bArr2, i3, olVar3.f22708b.length);
            i3 += olVar3.f22708b.length;
            if (olVar3.f22712f.containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] bArr4 : (Iterable) olVar3.f22712f.get(ResultMetadataType.BYTE_SEGMENTS)) {
                    System.arraycopy(bArr4, 0, bArr3, i4, bArr4.length);
                    i4 += bArr4.length;
                }
            }
        }
        C5422ol olVar4 = new C5422ol(sb.toString(), bArr2, f22170c, BarcodeFormat.QR_CODE);
        if (i2 > 0) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(bArr3);
            olVar4.m1633a(ResultMetadataType.BYTE_SEGMENTS, arrayList3);
        }
        arrayList.add(olVar4);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: QRCodeMultiReader.java */
    /* renamed from: z1.js$a */
    /* loaded from: classes3.dex */
    public static final class C5386a implements Serializable, Comparator<C5422ol> {
        private C5386a() {
        }

        /* synthetic */ C5386a(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C5422ol olVar, C5422ol olVar2) {
            return Integer.compare(((Integer) olVar.f22712f.get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue(), ((Integer) olVar2.f22712f.get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue());
        }

        /* renamed from: a */
        private static int m2252a(C5422ol olVar, C5422ol olVar2) {
            return Integer.compare(((Integer) olVar.f22712f.get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue(), ((Integer) olVar2.f22712f.get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue());
        }
    }
}
