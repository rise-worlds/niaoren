package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: z1.ls */
/* loaded from: classes3.dex */
public final class MultiFormatOneDReader extends OneDReader {

    /* renamed from: a */
    private final OneDReader[] f22381a;

    public MultiFormatOneDReader(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        boolean z = (map == null || map.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new MultiFormatUPCEANReader(map));
            }
            if (collection.contains(BarcodeFormat.CODE_39)) {
                arrayList.add(new Code39Reader(z));
            }
            if (collection.contains(BarcodeFormat.CODE_93)) {
                arrayList.add(new Code93Reader());
            }
            if (collection.contains(BarcodeFormat.CODE_128)) {
                arrayList.add(new Code128Reader());
            }
            if (collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new ITFReader());
            }
            if (collection.contains(BarcodeFormat.CODABAR)) {
                arrayList.add(new CodaBarReader());
            }
            if (collection.contains(BarcodeFormat.RSS_14)) {
                arrayList.add(new RSS14Reader());
            }
            if (collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                arrayList.add(new RSSExpandedReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new MultiFormatUPCEANReader(map));
            arrayList.add(new Code39Reader());
            arrayList.add(new CodaBarReader());
            arrayList.add(new Code93Reader());
            arrayList.add(new Code128Reader());
            arrayList.add(new ITFReader());
            arrayList.add(new RSS14Reader());
            arrayList.add(new RSSExpandedReader());
        }
        this.f22381a = (OneDReader[]) arrayList.toArray(new OneDReader[arrayList.size()]);
    }

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        for (OneDReader luVar : this.f22381a) {
            try {
                return luVar.mo2072a(i, huVar, map);
            } catch (ReaderException unused) {
            }
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        for (OneDReader luVar : this.f22381a) {
            luVar.mo1638a();
        }
    }
}
