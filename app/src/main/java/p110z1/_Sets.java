package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, m8860e = {"minus", "", TessBaseAPI.f9204e, "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, m8859f = "kotlin/collections/SetsKt", m8857h = 1)
/* renamed from: z1.cbb */
/* loaded from: classes3.dex */
class _Sets extends cba {
    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6343a(@NotNull Set<? extends T> set, T t) {
        cji.m5162f(set, "$this$minus");
        LinkedHashSet linkedHashSet = new LinkedHashSet(can.m6469a(set.size()));
        boolean z = false;
        for (T t2 : set) {
            boolean z2 = true;
            if (!z && cji.m5184a(t2, t)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(t2);
            }
        }
        return linkedHashSet;
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6341a(@NotNull Set<? extends T> set, @NotNull T[] tArr) {
        cji.m5162f(set, "$this$minus");
        cji.m5162f(tArr, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        bzk.m6745b((Collection) linkedHashSet, (Object[]) tArr);
        return linkedHashSet;
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6344a(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        cji.m5162f(set, "$this$minus");
        cji.m5162f(iterable, "elements");
        Set<? extends T> set2 = set;
        Collection<?> a = bzk.m6782a((Iterable) iterable, (Iterable) set2);
        if (a.isEmpty()) {
            return bzk.m6535u(set2);
        }
        if (a instanceof Set) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (T t : set2) {
                if (!a.contains(t)) {
                    linkedHashSet.add(t);
                }
            }
            return linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(set);
        linkedHashSet2.removeAll(a);
        return linkedHashSet2;
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6342a(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(set, "$this$minus");
        cji.m5162f(cobVar, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        bzk.m6746b((Collection) linkedHashSet, (Sequence) cobVar);
        return linkedHashSet;
    }

    @cey
    /* renamed from: c */
    private static final <T> Set<T> m6336c(@NotNull Set<? extends T> set, T t) {
        return cay.m6343a(set, t);
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Set<T> m6339b(@NotNull Set<? extends T> set, T t) {
        cji.m5162f(set, "$this$plus");
        LinkedHashSet linkedHashSet = new LinkedHashSet(can.m6469a(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(t);
        return linkedHashSet;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Set<T> m6337b(@NotNull Set<? extends T> set, @NotNull T[] tArr) {
        cji.m5162f(set, "$this$plus");
        cji.m5162f(tArr, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(can.m6469a(set.size() + tArr.length));
        linkedHashSet.addAll(set);
        bzk.m6756a((Collection) linkedHashSet, (Object[]) tArr);
        return linkedHashSet;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Set<T> m6340b(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        int i;
        cji.m5162f(set, "$this$plus");
        cji.m5162f(iterable, "elements");
        Integer a = bzk.m6784a((Iterable) iterable);
        if (a != null) {
            i = set.size() + a.intValue();
        } else {
            i = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(can.m6469a(i));
        linkedHashSet.addAll(set);
        bzk.m6760a((Collection) linkedHashSet, (Iterable) iterable);
        return linkedHashSet;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Set<T> m6338b(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(set, "$this$plus");
        cji.m5162f(cobVar, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(can.m6469a(set.size() * 2));
        linkedHashSet.addAll(set);
        bzk.m6757a((Collection) linkedHashSet, (Sequence) cobVar);
        return linkedHashSet;
    }

    @cey
    /* renamed from: d */
    private static final <T> Set<T> m6335d(@NotNull Set<? extends T> set, T t) {
        return cay.m6339b(set, t);
    }
}
