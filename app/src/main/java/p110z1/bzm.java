package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Collections.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a@\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\b\u001a@\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\b\u001a\u001f\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a5\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007\u001a\u0015\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\u001c\u001a%\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u0001H\u0007¢\u0006\u0002\u0010 \u001a3\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020\u001e2\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00070\u0018\"\u0004\u0018\u0001H\u0007¢\u0006\u0002\u0010\u001c\u001a\u0015\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007¢\u0006\u0002\u0010\u001c\u001a%\u0010\"\u001a\u00020#2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0002¢\u0006\u0002\b&\u001a\b\u0010'\u001a\u00020#H\u0001\u001a\b\u0010(\u001a\u00020#H\u0001\u001a%\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018H\u0000¢\u0006\u0002\u0010*\u001aS\u0010+\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010\u001f\u001a\u0002H\u00072\u001a\u0010,\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00070-j\n\u0012\u0006\b\u0000\u0012\u0002H\u0007`.2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0002\u0010/\u001a>\u0010+\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u00062\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00060\u000e\u001aE\u0010+\u001a\u00020\u0006\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u000701*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\b2\b\u0010\u001f\u001a\u0004\u0018\u0001H\u00072\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0002\u00102\u001ad\u00103\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007\"\u000e\b\u0001\u00104*\b\u0012\u0004\u0012\u0002H401*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\u00105\u001a\u0004\u0018\u0001H42\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u00062\u0016\b\u0004\u00106\u001a\u0010\u0012\u0004\u0012\u0002H\u0007\u0012\u0006\u0012\u0004\u0018\u0001H40\u000eH\u0086\b¢\u0006\u0002\u00107\u001a,\u00108\u001a\u000209\"\t\b\u0000\u0010\u0007¢\u0006\u0002\b:*\b\u0012\u0004\u0012\u0002H\u00070\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a8\u0010;\u001a\u0002H<\"\u0010\b\u0000\u0010=*\u0006\u0012\u0002\b\u00030\u0002*\u0002H<\"\u0004\b\u0001\u0010<*\u0002H=2\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H<0?H\u0087\b¢\u0006\u0002\u0010@\u001a\u0019\u0010A\u001a\u000209\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a,\u0010B\u001a\u000209\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\u001e\u0010C\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\bH\u0000\u001a!\u0010D\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u001a!\u0010D\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\bH\u0087\b\"\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006E"}, m8860e = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "", TessBaseAPI.f9204e, "", "getLastIndex", "(Ljava/util/List;)I", "List", "size", "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "", "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "emptyList", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "listOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", "rangeCheck", "", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "throwCountOverflow", "throwIndexOverflow", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "binarySearch", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "binarySearchBy", "K", "key", "selector", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "containsAll", "", "Lkotlin/internal/OnlyInputTypes;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "optimizeReadOnlyList", "orEmpty", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzm */
/* loaded from: classes3.dex */
public class bzm extends CollectionsJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> Collection<T> m6801a(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$asCollection");
        return new Collections(tArr, false);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m6816a() {
        return bzx.INSTANCE;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> List<T> m6795b(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return tArr.length > 0 ? bzb.m8093d((Object[]) tArr) : bzk.m6816a();
    }

    @cey
    /* renamed from: d */
    private static final <T> List<T> m6790d() {
        return bzk.m6816a();
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final <T> List<T> m6787e() {
        return new ArrayList();
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final <T> ArrayList<T> m6785f() {
        return new ArrayList<>();
    }

    @NotNull
    /* renamed from: c */
    public static final <T> List<T> m6791c(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new Collections(tArr, true));
    }

    @NotNull
    /* renamed from: d */
    public static final <T> ArrayList<T> m6788d(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return tArr.length == 0 ? new ArrayList<>() : new ArrayList<>(new Collections(tArr, true));
    }

    @NotNull
    /* renamed from: b */
    public static final <T> List<T> m6798b(@dbs T t) {
        return t != null ? bzk.m6822a(t) : bzk.m6816a();
    }

    @NotNull
    /* renamed from: e */
    public static final <T> List<T> m6786e(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return bzb.m7067m(tArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final <T> List<T> m6814a(int i, chd<? super Integer, ? extends T> chdVar) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(chdVar.invoke(Integer.valueOf(i2)));
        }
        return arrayList;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final <T> List<T> m6799b(int i, chd<? super Integer, ? extends T> chdVar) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(chdVar.invoke(Integer.valueOf(i2)));
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: a */
    public static final cme m6813a(@NotNull Collection<?> collection) {
        cji.m5162f(collection, "$this$indices");
        return new cme(0, collection.size() - 1);
    }

    /* renamed from: a */
    public static final <T> int m6810a(@NotNull List<? extends T> list) {
        cji.m5162f(list, "$this$lastIndex");
        return list.size() - 1;
    }

    @cey
    /* renamed from: b */
    private static final <T> boolean m6797b(@NotNull Collection<? extends T> collection) {
        return !collection.isEmpty();
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: c */
    private static final <T> boolean m6793c(@dbs Collection<? extends T> collection) {
        return collection == null || collection.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: d */
    private static final <T> Collection<T> m6789d(@dbs Collection<? extends T> collection) {
        return collection != 0 ? collection : bzk.m6816a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: c */
    private static final <T> List<T> m6792c(@dbs List<? extends T> list) {
        return list != 0 ? list : bzk.m6816a();
    }

    /* JADX WARN: Incorrect types in method signature: <C::Ljava/util/Collection<*>;:TR;R:Ljava/lang/Object;>(TC;Lz1/chc<+TR;>;)TR; */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final Object m6811a(Collection collection, chc chcVar) {
        return collection.isEmpty() ? chcVar.invoke() : collection;
    }

    @cey
    /* renamed from: a */
    private static final <T> boolean m6812a(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        return collection.containsAll(collection2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: b */
    public static final <T> List<T> m6796b(@NotNull List<? extends T> list) {
        cji.m5162f(list, "$this$optimizeReadOnlyList");
        switch (list.size()) {
            case 0:
                return bzk.m6816a();
            case 1:
                return bzk.m6822a(list.get(0));
            default:
                return list;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ int m6806a(List list, Comparable comparable, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = list.size();
        }
        return bzk.m6807a(list, comparable, i, i2);
    }

    /* renamed from: a */
    public static final <T extends Comparable<? super T>> int m6807a(@NotNull List<? extends T> list, @dbs T t, int i, int i2) {
        cji.m5162f(list, "$this$binarySearch");
        m6815a(list.size(), i, i2);
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int a = cbr.m5757a((T) list.get(i4), t);
            if (a < 0) {
                i = i4 + 1;
            } else if (a <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: a */
    public static /* synthetic */ int m6802a(List list, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = list.size();
        }
        return bzk.m6803a(list, obj, comparator, i, i2);
    }

    /* renamed from: a */
    public static final <T> int m6803a(@NotNull List<? extends T> list, T t, @NotNull Comparator<? super T> comparator, int i, int i2) {
        cji.m5162f(list, "$this$binarySearch");
        cji.m5162f(comparator, "comparator");
        m6815a(list.size(), i, i2);
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int compare = comparator.compare((Object) list.get(i4), t);
            if (compare < 0) {
                i = i4 + 1;
            } else if (compare <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: a */
    public static /* synthetic */ int m6804a(List list, Comparable comparable, int i, int i2, chd chdVar, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = list.size();
        }
        cji.m5162f(list, "$this$binarySearchBy");
        cji.m5162f(chdVar, "selector");
        return bzk.m6809a(list, i, i2, new C4865a(chdVar, comparable));
    }

    /* compiled from: Collections.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "K", "", "it", "invoke", "(Ljava/lang/Object;)I"})
    /* renamed from: z1.bzm$a */
    /* loaded from: classes3.dex */
    public static final class C4865a extends Lambda implements chd<T, Integer> {
        final /* synthetic */ Comparable $key;
        final /* synthetic */ chd $selector;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C4865a(chd chdVar, Comparable comparable) {
            super(1);
            this.$selector = chdVar;
            this.$key = comparable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r2v3, types: [int, java.lang.Integer] */
        @Override // p110z1.chd
        public final Integer invoke(T t) {
            return cbr.m5757a((Comparable) this.$selector.invoke(t), this.$key);
        }
    }

    /* renamed from: a */
    public static final <T, K extends Comparable<? super K>> int m6805a(@NotNull List<? extends T> list, @dbs K k, int i, int i2, @NotNull chd<? super T, ? extends K> chdVar) {
        cji.m5162f(list, "$this$binarySearchBy");
        cji.m5162f(chdVar, "selector");
        return bzk.m6809a(list, i, i2, new C4865a(chdVar, k));
    }

    /* renamed from: a */
    public static /* synthetic */ int m6808a(List list, int i, int i2, chd chdVar, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = list.size();
        }
        return bzk.m6809a(list, i, i2, chdVar);
    }

    /* renamed from: a */
    public static final <T> int m6809a(@NotNull List<? extends T> list, int i, int i2, @NotNull chd<? super T, Integer> chdVar) {
        cji.m5162f(list, "$this$binarySearch");
        cji.m5162f(chdVar, "comparison");
        m6815a(list.size(), i, i2);
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int intValue = chdVar.invoke((Object) list.get(i4)).intValue();
            if (intValue < 0) {
                i = i4 + 1;
            } else if (intValue <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: a */
    private static final void m6815a(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex (" + i2 + ") is greater than toIndex (" + i3 + ").");
        } else if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i2 + ") is less than zero.");
        } else if (i3 > i) {
            throw new IndexOutOfBoundsException("toIndex (" + i3 + ") is greater than size (" + i + ").");
        }
    }

    @bwy(m8750a = "1.3")
    @bwt
    /* renamed from: b */
    public static final void m6800b() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    @bwy(m8750a = "1.3")
    @bwt
    /* renamed from: c */
    public static final void m6794c() {
        throw new ArithmeticException("Count overflow has happened.");
    }
}
