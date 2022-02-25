package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: z1.oe */
/* loaded from: classes3.dex */
public final class MultiFormatReader implements Reader {

    /* renamed from: a */
    private Map<DecodeHintType, ?> f22690a;

    /* renamed from: b */
    private Reader[] f22691b;

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException {
        m1650a((Map<DecodeHintType, ?>) null);
        return m1648c(htVar);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        m1650a(map);
        return m1648c(htVar);
    }

    /* renamed from: b */
    private C5422ol m1649b(BinaryBitmap htVar) throws NotFoundException {
        if (this.f22691b == null) {
            m1650a((Map<DecodeHintType, ?>) null);
        }
        return m1648c(htVar);
    }

    /* renamed from: a */
    private void m1650a(Map<DecodeHintType, ?> map) {
        this.f22690a = map;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (!collection.contains(BarcodeFormat.UPC_A) && !collection.contains(BarcodeFormat.UPC_E) && !collection.contains(BarcodeFormat.EAN_13) && !collection.contains(BarcodeFormat.EAN_8) && !collection.contains(BarcodeFormat.CODABAR) && !collection.contains(BarcodeFormat.CODE_39) && !collection.contains(BarcodeFormat.CODE_93) && !collection.contains(BarcodeFormat.CODE_128) && !collection.contains(BarcodeFormat.ITF) && !collection.contains(BarcodeFormat.RSS_14) && !collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = false;
            }
            if (z && !z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new QRCodeReader());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new DataMatrixReader());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new AztecReader());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new PDF417Reader());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new MaxiCodeReader());
            }
            if (z && z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            arrayList.add(new QRCodeReader());
            arrayList.add(new DataMatrixReader());
            arrayList.add(new AztecReader());
            arrayList.add(new PDF417Reader());
            arrayList.add(new MaxiCodeReader());
            if (z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        this.f22691b = (Reader[]) arrayList.toArray(new Reader[arrayList.size()]);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        Reader[] ojVarArr = this.f22691b;
        if (ojVarArr != null) {
            for (Reader ojVar : ojVarArr) {
                ojVar.mo1638a();
            }
        }
    }

    /* renamed from: c */
    private C5422ol m1648c(BinaryBitmap htVar) throws NotFoundException {
        Reader[] ojVarArr = this.f22691b;
        if (ojVarArr != null) {
            for (Reader ojVar : ojVarArr) {
                try {
                    return ojVar.mo1636a(htVar, this.f22690a);
                } catch (ReaderException unused) {
                }
            }
        }
        throw NotFoundException.m1647a();
    }
}
