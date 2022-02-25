package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000h\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001aG\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001aG\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u001aY\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\u0086\b\u001ar\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\u0086\b¢\u0006\u0002\u0010\u0017\u001aG\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\u0087\b\u001aS\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0086\b\u001aY\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\u0086\b\u001ar\u0010\u001e\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\u0086\b¢\u0006\u0002\u0010\u0017\u001al\u0010\u001f\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0086\b¢\u0006\u0002\u0010\u0017\u001ae\u0010 \u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0087\b\u001ai\u0010#\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&H\u0087\b\u001ae\u0010'\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\u0086\b\u001af\u0010(\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&\u001a$\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u001aV\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010+*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002H+2\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\u0087\b¢\u0006\u0002\u0010,\u001a6\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030.0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004¨\u0006/"}, m8860e = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "flatMap", "", "R", "transform", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "forEach", "", "action", "map", "mapNotNull", "", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "minBy", "minWith", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, m8859f = "kotlin/collections/MapsKt", m8857h = 1)
/* renamed from: z1.car */
/* loaded from: classes3.dex */
class _Maps extends caq {
    @NotNull
    /* renamed from: f */
    public static final <K, V> List<Tuples<K, V>> m6395f(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$toList");
        if (map.size() == 0) {
            return bzk.m6816a();
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return bzk.m6816a();
        }
        Map.Entry<? extends K, ? extends V> next = it.next();
        if (!it.hasNext()) {
            return bzk.m6822a(new Tuples(next.getKey(), next.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new Tuples(next.getKey(), next.getValue()));
        do {
            Map.Entry<? extends K, ? extends V> next2 = it.next();
            arrayList.add(new Tuples(next2.getKey(), next2.getValue()));
        } while (it.hasNext());
        return arrayList;
    }

    @NotNull
    /* renamed from: i */
    public static final <K, V, R> List<R> m6391i(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> chdVar) {
        cji.m5162f(map, "$this$flatMap");
        cji.m5162f(chdVar, "transform");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            bzk.m6760a((Collection) arrayList, (Iterable) chdVar.invoke(entry));
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V, R, C extends Collection<? super R>> C m6400a(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> chdVar) {
        cji.m5162f(map, "$this$flatMapTo");
        cji.m5162f(c, "destination");
        cji.m5162f(chdVar, "transform");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            bzk.m6760a((Collection) c, (Iterable) chdVar.invoke(entry));
        }
        return c;
    }

    @NotNull
    /* renamed from: j */
    public static final <K, V, R> List<R> m6389j(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$map");
        cji.m5162f(chdVar, "transform");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            arrayList.add(chdVar.invoke(entry));
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: k */
    public static final <K, V, R> List<R> m6387k(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapNotNull");
        cji.m5162f(chdVar, "transform");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            Object invoke = chdVar.invoke(entry);
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: c */
    public static final <K, V, R, C extends Collection<? super R>> C m6397c(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapTo");
        cji.m5162f(c, "destination");
        cji.m5162f(chdVar, "transform");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            c.add(chdVar.invoke(entry));
        }
        return c;
    }

    /* renamed from: l */
    public static final <K, V> boolean m6386l(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$all");
        cji.m5162f(chdVar, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (!chdVar.invoke(entry).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: g */
    public static final <K, V> boolean m6394g(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$any");
        return !map.isEmpty();
    }

    /* renamed from: m */
    public static final <K, V> boolean m6385m(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$any");
        cji.m5162f(chdVar, "predicate");
        if (map.isEmpty()) {
            return false;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke(entry).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @cey
    /* renamed from: j */
    private static final <K, V> int m6390j(@NotNull Map<? extends K, ? extends V> map) {
        return map.size();
    }

    /* renamed from: n */
    public static final <K, V> int m6384n(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$count");
        cji.m5162f(chdVar, "predicate");
        int i = 0;
        if (map.isEmpty()) {
            return 0;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke(entry).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @cex
    /* renamed from: o */
    public static final <K, V> void m6383o(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Unit> chdVar) {
        cji.m5162f(map, "$this$forEach");
        cji.m5162f(chdVar, "action");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            chdVar.invoke(entry);
        }
    }

    @cey
    /* renamed from: s */
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> m6379s(@NotNull Map<? extends K, ? extends V> map, chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        Object obj;
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            obj = (Object) null;
        } else {
            obj = (Object) it.next();
            if (it.hasNext()) {
                Comparable comparable = (Comparable) chdVar.invoke(obj);
                do {
                    Object obj2 = (Object) it.next();
                    Comparable comparable2 = (Comparable) chdVar.invoke(obj2);
                    if (comparable.compareTo(comparable2) < 0) {
                        obj = (Object) obj2;
                        comparable = comparable2;
                    }
                } while (it.hasNext());
            }
        }
        return (Map.Entry) obj;
    }

    @cey
    /* renamed from: c */
    private static final <K, V> Map.Entry<K, V> m6396c(@NotNull Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) bzk.m6629c((Iterable<? extends Object>) map.entrySet(), (Comparator<? super Object>) comparator);
    }

    @dbs
    /* renamed from: p */
    public static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> m6382p(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        Object obj;
        cji.m5162f(map, "$this$minBy");
        cji.m5162f(chdVar, "selector");
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            obj = (Object) null;
        } else {
            obj = (Object) it.next();
            if (it.hasNext()) {
                Comparable comparable = (Comparable) chdVar.invoke(obj);
                do {
                    Object obj2 = (Object) it.next();
                    Comparable comparable2 = (Comparable) chdVar.invoke(obj2);
                    if (comparable.compareTo(comparable2) > 0) {
                        obj = (Object) obj2;
                        comparable = comparable2;
                    }
                } while (it.hasNext());
            }
        }
        return (Map.Entry) obj;
    }

    @dbs
    /* renamed from: b */
    public static final <K, V> Map.Entry<K, V> m6398b(@NotNull Map<? extends K, ? extends V> map, @NotNull Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        cji.m5162f(map, "$this$minWith");
        cji.m5162f(comparator, "comparator");
        return (Map.Entry) bzk.m6617d((Iterable<? extends Object>) map.entrySet(), (Comparator<? super Object>) comparator);
    }

    /* renamed from: h */
    public static final <K, V> boolean m6393h(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$none");
        return map.isEmpty();
    }

    /* renamed from: q */
    public static final <K, V> boolean m6381q(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$none");
        cji.m5162f(chdVar, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke(entry).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: r */
    public static final <K, V, M extends Map<? extends K, ? extends V>> M m6380r(@NotNull M m, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Unit> chdVar) {
        cji.m5162f(m, "$this$onEach");
        cji.m5162f(chdVar, "action");
        for (Map.Entry<K, V> entry : m.entrySet()) {
            chdVar.invoke(entry);
        }
        return m;
    }

    @cey
    /* renamed from: k */
    private static final <K, V> Iterable<Map.Entry<K, V>> m6388k(@NotNull Map<? extends K, ? extends V> map) {
        return map.entrySet();
    }

    @NotNull
    /* renamed from: i */
    public static final <K, V> Sequence<Map.Entry<K, V>> m6392i(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$asSequence");
        return bzk.m6704J(map.entrySet());
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V, R, C extends Collection<? super R>> C m6399b(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapNotNullTo");
        cji.m5162f(c, "destination");
        cji.m5162f(chdVar, "transform");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            Object invoke = chdVar.invoke(entry);
            if (invoke != null) {
                c.add(invoke);
            }
        }
        return c;
    }
}
