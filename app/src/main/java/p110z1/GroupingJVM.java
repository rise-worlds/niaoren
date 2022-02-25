package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import p110z1.Ref;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a0\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aW\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\b¨\u0006\r"}, m8860e = {"eachCount", "", "K", "", TessBaseAPI.f9204e, "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"}, m8859f = "kotlin/collections/GroupingKt", m8857h = 1)
/* renamed from: z1.cad */
/* loaded from: classes3.dex */
class GroupingJVM {
    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T, K> Map<K, Integer> m6508a(@NotNull Grouping<T, ? extends K> cabVar) {
        cji.m5162f(cabVar, "$this$eachCount");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> a = cabVar.mo3706a();
        while (a.hasNext()) {
            Object a2 = cabVar.mo3704a(a.next());
            Object obj = linkedHashMap.get(a2);
            if (obj == null && !linkedHashMap.containsKey(a2)) {
                obj = new Ref.C4970f();
            }
            Ref.C4970f fVar = (Ref.C4970f) obj;
            fVar.element++;
            linkedHashMap.put(a2, fVar);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (entry != null) {
                TypeIntrinsics.m5041w(entry).setValue(Integer.valueOf(((Ref.C4970f) entry.getValue()).element));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
        }
        return TypeIntrinsics.m5044t(linkedHashMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwt
    @cey
    /* renamed from: a */
    private static final <K, V, R> Map<K, R> m6509a(@NotNull Map<K, V> map, chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry != null) {
                TypeIntrinsics.m5041w(entry).setValue(chdVar.invoke(entry));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
        }
        if (map != null) {
            return TypeIntrinsics.m5044t(map);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
    }
}
