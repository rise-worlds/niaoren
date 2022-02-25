package p110z1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.mj */
/* loaded from: classes3.dex */
final class BarcodeValue {

    /* renamed from: a */
    final Map<Integer, Integer> f22428a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2023a(int i) {
        Integer num = this.f22428a.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.f22428a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int[] m2024a() {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (Map.Entry<Integer, Integer> entry : this.f22428a.entrySet()) {
            if (entry.getValue().intValue() > i) {
                i = entry.getValue().intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (entry.getValue().intValue() == i) {
                arrayList.add(entry.getKey());
            }
        }
        return PDF417Common.m2056a(arrayList);
    }

    /* renamed from: b */
    private Integer m2022b(int i) {
        return this.f22428a.get(Integer.valueOf(i));
    }
}
