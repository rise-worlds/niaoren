package p110z1;

import java.util.Map;
import java.util.NoSuchElementException;

/* compiled from: MapWithDefault.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001aQ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001aX\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007¢\u0006\u0002\b\r¨\u0006\u000e"}, m8860e = {"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, m8859f = "kotlin/collections/MapsKt", m8857h = 1)
/* renamed from: z1.cao */
/* loaded from: classes3.dex */
class cao {
    @bwt
    @cgo(m5270a = "getOrImplicitDefaultNullable")
    /* renamed from: a */
    public static final <K, V> V m6481a(@NotNull Map<K, ? extends V> map, K k) {
        cji.m5162f(map, "$this$getOrImplicitDefault");
        if (map instanceof MapWithDefault) {
            return (V) ((MapWithDefault) map).mo6376a(k);
        }
        V v = (V) map.get(k);
        if (v != null || map.containsKey(k)) {
            return v;
        }
        throw new NoSuchElementException("Key " + k + " is missing in the map.");
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6480a(@NotNull Map<K, ? extends V> map, @NotNull chd<? super K, ? extends V> chdVar) {
        cji.m5162f(map, "$this$withDefault");
        cji.m5162f(chdVar, "defaultValue");
        return map instanceof MapWithDefault ? can.m6480a((Map) ((MapWithDefault) map).mo6377a(), (chd) chdVar) : new cam(map, chdVar);
    }

    @cgo(m5270a = "withDefaultMutable")
    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6479b(@NotNull Map<K, V> map, @NotNull chd<? super K, ? extends V> chdVar) {
        cji.m5162f(map, "$this$withDefault");
        cji.m5162f(chdVar, "defaultValue");
        return map instanceof cat ? can.m6479b((Map) ((cat) map).mo6377a(), (chd) chdVar) : new cau(map, chdVar);
    }
}
