package p110z1;

import java.util.TreeMap;

/* renamed from: z1.e */
/* loaded from: classes3.dex */
public class UniqueKeyTreeMap<K, V> extends TreeMap<K, V> {
    private String tipText;

    public UniqueKeyTreeMap(String str) {
        this.tipText = str;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (!containsKey(k)) {
            return (V) super.put(k, v);
        }
        throw new RuntimeException(String.format(this.tipText, k));
    }
}
