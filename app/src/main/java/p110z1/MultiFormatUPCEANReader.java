package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: z1.lt */
/* loaded from: classes3.dex */
public final class MultiFormatUPCEANReader extends OneDReader {

    /* renamed from: a */
    private final UPCEANReader[] f22382a;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new EAN13Reader());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new UPCAReader());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new EAN8Reader());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new UPCEReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new EAN13Reader());
            arrayList.add(new EAN8Reader());
            arrayList.add(new UPCEReader());
        }
        this.f22382a = (UPCEANReader[]) arrayList.toArray(new UPCEANReader[arrayList.size()]);
    }

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z;
        int[] a = UPCEANReader.m2068a(huVar);
        for (UPCEANReader mbVar : this.f22382a) {
            try {
                C5422ol a2 = mbVar.mo2071a(i, huVar, a, map);
                boolean z2 = a2.f22711e == BarcodeFormat.EAN_13 && a2.f22707a.charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                if (collection != null && !collection.contains(BarcodeFormat.UPC_A)) {
                    z = false;
                    if (z2 || !z) {
                        return a2;
                    }
                    C5422ol olVar = new C5422ol(a2.f22707a.substring(1), a2.f22708b, a2.f22710d, BarcodeFormat.UPC_A);
                    olVar.m1634a(a2.f22712f);
                    return olVar;
                }
                z = true;
                if (z2) {
                }
                return a2;
            } catch (ReaderException unused) {
            }
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        for (UPCEANReader mbVar : this.f22382a) {
            mbVar.mo1638a();
        }
    }
}
