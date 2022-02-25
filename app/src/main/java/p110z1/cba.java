package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Sets.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001a\u001f\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0004j\b\u0012\u0004\u0012\u0002H\u0002`\u0005\"\u0004\b\u0000\u0010\u0002H\u0087\b\u001a5\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0004j\b\u0012\u0004\u0012\u0002H\u0002`\u0005\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0007\"\u0002H\u0002¢\u0006\u0002\u0010\b\u001a\u001f\u0010\t\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000b\"\u0004\b\u0000\u0010\u0002H\u0087\b\u001a5\u0010\t\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000b\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0007\"\u0002H\u0002¢\u0006\u0002\u0010\f\u001a\u0015\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000e\"\u0004\b\u0000\u0010\u0002H\u0087\b\u001a+\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0007\"\u0002H\u0002¢\u0006\u0002\u0010\u000f\u001a\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0087\b\u001a+\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0007\"\u0002H\u0002¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a!\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\b¨\u0006\u0013"}, m8860e = {"emptySet", "", TessBaseAPI.f9204e, "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "optimizeReadOnlySet", "orEmpty", "kotlin-stdlib"}, m8859f = "kotlin/collections/SetsKt", m8857h = 1)
/* renamed from: z1.cba */
/* loaded from: classes3.dex */
public class cba extends SetsJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6355a() {
        return Sets.INSTANCE;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Set<T> m6351b(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return tArr.length > 0 ? bzb.m6905v(tArr) : cay.m6355a();
    }

    @cey
    /* renamed from: b */
    private static final <T> Set<T> m6353b() {
        return cay.m6355a();
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: c */
    private static final <T> Set<T> m6350c() {
        return new LinkedHashSet();
    }

    @NotNull
    /* renamed from: c */
    public static final <T> Set<T> m6349c(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return (Set) bzb.m7239e((Object[]) tArr, new LinkedHashSet(can.m6469a(tArr.length)));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: d */
    private static final <T> HashSet<T> m6348d() {
        return new HashSet<>();
    }

    @NotNull
    /* renamed from: d */
    public static final <T> HashSet<T> m6347d(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return (HashSet) bzb.m7239e((Object[]) tArr, new HashSet(can.m6469a(tArr.length)));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final <T> LinkedHashSet<T> m6346e() {
        return new LinkedHashSet<>();
    }

    @NotNull
    /* renamed from: e */
    public static final <T> LinkedHashSet<T> m6345e(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return (LinkedHashSet) bzb.m7239e((Object[]) tArr, new LinkedHashSet(can.m6469a(tArr.length)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: b */
    private static final <T> Set<T> m6352b(@dbs Set<? extends T> set) {
        return set != 0 ? set : cay.m6355a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6354a(@NotNull Set<? extends T> set) {
        cji.m5162f(set, "$this$optimizeReadOnlySet");
        switch (set.size()) {
            case 0:
                return cay.m6355a();
            case 1:
                return cay.m6358a(set.iterator().next());
            default:
                return set;
        }
    }
}
