package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000^\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a(\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a(\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a-\u0010\u0016\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0018\u001a&\u0010\u0016\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0087\b¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001c\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a-\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001e\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u0015\u0010\u001f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002¢\u0006\u0002\b \u001a \u0010!\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010\"\u001a\u00020#H\u0007\u001a&\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020%\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0007¨\u0006&"}, m8860e = {"addAll", "", TessBaseAPI.f9204e, "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "", "minusAssign", "", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "shuffle", "random", "Lkotlin/random/Random;", "shuffled", "", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzr */
/* loaded from: classes3.dex */
public class MutableCollections extends MutableCollectionsJVM {
    @cey
    /* renamed from: a */
    private static final <T> boolean m6759a(@NotNull Collection<? extends T> collection, T t) {
        if (collection != null) {
            return TypeIntrinsics.m5053k(collection).remove(t);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @cey
    /* renamed from: a */
    private static final <T> boolean m6758a(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.m5053k(collection).removeAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @cey
    /* renamed from: b */
    private static final <T> boolean m6747b(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.m5053k(collection).retainAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @Annotations(m8902a = "Use removeAt(index) instead.", m8901b = @bwu(m8768a = "removeAt(index)", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: a */
    private static final <T> T m6755a(@NotNull List<T> list, int i) {
        return list.remove(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: b */
    private static final <T> void m6748b(@NotNull Collection<? super T> collection, T t) {
        cji.m5162f(collection, "$this$plusAssign");
        collection.add(t);
    }

    @cey
    /* renamed from: d */
    private static final <T> void m6739d(@NotNull Collection<? super T> collection, Iterable<? extends T> iterable) {
        cji.m5162f(collection, "$this$plusAssign");
        bzk.m6760a((Collection) collection, (Iterable) iterable);
    }

    @cey
    /* renamed from: d */
    private static final <T> void m6737d(@NotNull Collection<? super T> collection, T[] tArr) {
        cji.m5162f(collection, "$this$plusAssign");
        bzk.m6756a((Collection) collection, (Object[]) tArr);
    }

    @cey
    /* renamed from: d */
    private static final <T> void m6738d(@NotNull Collection<? super T> collection, Sequence<? extends T> cobVar) {
        cji.m5162f(collection, "$this$plusAssign");
        bzk.m6757a((Collection) collection, (Sequence) cobVar);
    }

    @cey
    /* renamed from: c */
    private static final <T> void m6742c(@NotNull Collection<? super T> collection, T t) {
        cji.m5162f(collection, "$this$minusAssign");
        collection.remove(t);
    }

    @cey
    /* renamed from: e */
    private static final <T> void m6736e(@NotNull Collection<? super T> collection, Iterable<? extends T> iterable) {
        cji.m5162f(collection, "$this$minusAssign");
        bzk.m6749b((Collection) collection, (Iterable) iterable);
    }

    @cey
    /* renamed from: e */
    private static final <T> void m6734e(@NotNull Collection<? super T> collection, T[] tArr) {
        cji.m5162f(collection, "$this$minusAssign");
        bzk.m6745b((Collection) collection, (Object[]) tArr);
    }

    @cey
    /* renamed from: e */
    private static final <T> void m6735e(@NotNull Collection<? super T> collection, Sequence<? extends T> cobVar) {
        cji.m5162f(collection, "$this$minusAssign");
        bzk.m6746b((Collection) collection, (Sequence) cobVar);
    }

    /* renamed from: a */
    public static final <T> boolean m6760a(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        cji.m5162f(collection, "$this$addAll");
        cji.m5162f(iterable, "elements");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (collection.add((Object) it.next())) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static final <T> boolean m6757a(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(collection, "$this$addAll");
        cji.m5162f(cobVar, "elements");
        Iterator<? extends T> a = cobVar.mo3707a();
        boolean z = false;
        while (a.hasNext()) {
            if (collection.add((Object) a.next())) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static final <T> boolean m6756a(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        cji.m5162f(collection, "$this$addAll");
        cji.m5162f(tArr, "elements");
        return collection.addAll(bzb.m8093d((Object[]) tArr));
    }

    /* renamed from: a */
    public static final <T> boolean m6763a(@NotNull Iterable<? extends T> iterable, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(iterable, "$this$removeAll");
        cji.m5162f(chdVar, "predicate");
        return m6762a((Iterable) iterable, (chd) chdVar, true);
    }

    /* renamed from: b */
    public static final <T> boolean m6751b(@NotNull Iterable<? extends T> iterable, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(iterable, "$this$retainAll");
        cji.m5162f(chdVar, "predicate");
        return m6762a((Iterable) iterable, (chd) chdVar, false);
    }

    /* renamed from: a */
    private static final <T> boolean m6762a(@NotNull Iterable<? extends T> iterable, chd<? super T, Boolean> chdVar, boolean z) {
        Iterator<? extends T> it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (chdVar.invoke((Object) it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    /* renamed from: a */
    public static final <T> boolean m6754a(@NotNull List<T> list, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(list, "$this$removeAll");
        cji.m5162f(chdVar, "predicate");
        return m6753a((List) list, (chd) chdVar, true);
    }

    /* renamed from: b */
    public static final <T> boolean m6744b(@NotNull List<T> list, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(list, "$this$retainAll");
        cji.m5162f(chdVar, "predicate");
        return m6753a((List) list, (chd) chdVar, false);
    }

    /* renamed from: a */
    private static final <T> boolean m6753a(@NotNull List<T> list, chd<? super T, Boolean> chdVar, boolean z) {
        int i;
        if (list instanceof RandomAccess) {
            int a = bzk.m6810a((List) list);
            if (a >= 0) {
                int i2 = 0;
                i = 0;
                while (true) {
                    T t = list.get(i2);
                    if (chdVar.invoke(t).booleanValue() != z) {
                        if (i != i2) {
                            list.set(i, t);
                        }
                        i++;
                    }
                    if (i2 == a) {
                        break;
                    }
                    i2++;
                }
            } else {
                i = 0;
            }
            if (i >= list.size()) {
                return false;
            }
            int a2 = bzk.m6810a((List) list);
            if (a2 < i) {
                return true;
            }
            while (true) {
                list.remove(a2);
                if (a2 == i) {
                    return true;
                }
                a2--;
            }
        } else if (list != null) {
            return m6762a(TypeIntrinsics.m5058h(list), chdVar, z);
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
        }
    }

    /* renamed from: b */
    public static final <T> boolean m6749b(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        cji.m5162f(collection, "$this$removeAll");
        cji.m5162f(iterable, "elements");
        return TypeIntrinsics.m5053k(collection).removeAll(bzk.m6782a((Iterable) iterable, (Iterable) collection));
    }

    /* renamed from: b */
    public static final <T> boolean m6746b(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(collection, "$this$removeAll");
        cji.m5162f(cobVar, "elements");
        HashSet p = coe.m4313p(cobVar);
        return (p.isEmpty() ^ true) && collection.removeAll(p);
    }

    /* renamed from: b */
    public static final <T> boolean m6745b(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        cji.m5162f(collection, "$this$removeAll");
        cji.m5162f(tArr, "elements");
        return ((tArr.length == 0) ^ true) && collection.removeAll(bzb.m6959s(tArr));
    }

    /* renamed from: c */
    public static final <T> boolean m6743c(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        cji.m5162f(collection, "$this$retainAll");
        cji.m5162f(iterable, "elements");
        return TypeIntrinsics.m5053k(collection).retainAll(bzk.m6782a((Iterable) iterable, (Iterable) collection));
    }

    /* renamed from: c */
    public static final <T> boolean m6740c(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        cji.m5162f(collection, "$this$retainAll");
        cji.m5162f(tArr, "elements");
        if (!(tArr.length == 0)) {
            return collection.retainAll(bzb.m6959s(tArr));
        }
        return m6750b((Collection<?>) collection);
    }

    /* renamed from: c */
    public static final <T> boolean m6741c(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(collection, "$this$retainAll");
        cji.m5162f(cobVar, "elements");
        HashSet p = coe.m4313p(cobVar);
        if (!p.isEmpty()) {
            return collection.retainAll(p);
        }
        return m6750b((Collection<?>) collection);
    }

    /* renamed from: b */
    private static final boolean m6750b(@NotNull Collection<?> collection) {
        boolean z = !collection.isEmpty();
        collection.clear();
        return z;
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final <T> void m6752a(@NotNull List<T> list, @NotNull Random clqVar) {
        cji.m5162f(list, "$this$shuffle");
        cji.m5162f(clqVar, "random");
        for (int a = bzk.m6810a((List) list); a >= 1; a--) {
            int b = clqVar.mo4893b(a + 1);
            T t = list.get(a);
            list.set(a, list.get(b));
            list.set(b, t);
        }
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m6761a(@NotNull Iterable<? extends T> iterable, @NotNull Random clqVar) {
        cji.m5162f(iterable, "$this$shuffled");
        cji.m5162f(clqVar, "random");
        List<T> t = bzk.m6537t(iterable);
        bzk.m6752a((List) t, clqVar);
        return t;
    }
}
