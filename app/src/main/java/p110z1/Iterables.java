package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.RequestOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a\u001d\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0002¢\u0006\u0002\b\u0013\u001a@\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00100\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00160\u00150\u0001¨\u0006\u0017"}, m8860e = {"Iterable", "", TessBaseAPI.f9204e, "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "convertToSetForSetOperation", "", "convertToSetForSetOperationWith", RequestOptions.f12301a, "flatten", "", "safeToConvertToSet", "", "safeToConvertToSet$CollectionsKt__IterablesKt", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzn */
/* loaded from: classes3.dex */
public class Iterables extends bzm {

    /* compiled from: Iterables.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004"}, m8860e = {"kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", "", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.bzn$a */
    /* loaded from: classes3.dex */
    public static final class C4866a implements Iterable<T>, KMarkers {

        /* renamed from: a */
        final /* synthetic */ chc f20471a;

        public C4866a(chc chcVar) {
            this.f20471a = chcVar;
        }

        @Override // java.lang.Iterable
        @NotNull
        public Iterator<T> iterator() {
            return (Iterator) this.f20471a.invoke();
        }
    }

    @cey
    /* renamed from: a */
    private static final <T> Iterable<T> m6781a(chc<? extends Iterator<? extends T>> chcVar) {
        return new C4866a(chcVar);
    }

    @dbs
    @bwt
    /* renamed from: a */
    public static final <T> Integer m6784a(@NotNull Iterable<? extends T> iterable) {
        cji.m5162f(iterable, "$this$collectionSizeOrNull");
        if (iterable instanceof Collection) {
            return Integer.valueOf(((Collection) iterable).size());
        }
        return null;
    }

    @bwt
    /* renamed from: a */
    public static final <T> int m6783a(@NotNull Iterable<? extends T> iterable, int i) {
        cji.m5162f(iterable, "$this$collectionSizeOrDefault");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    /* renamed from: b */
    private static final <T> boolean m6779b(@NotNull Collection<? extends T> collection) {
        return collection.size() > 2 && (collection instanceof ArrayList);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Collection<T> m6782a(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> iterable2) {
        cji.m5162f(iterable, "$this$convertToSetForSetOperationWith");
        cji.m5162f(iterable2, RequestOptions.f12301a);
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return bzk.m6543r(iterable);
        }
        if ((iterable2 instanceof Collection) && ((Collection) iterable2).size() < 2) {
            return (Collection) iterable;
        }
        Collection<T> collection = (Collection) iterable;
        return m6779b((Collection) collection) ? bzk.m6543r(iterable) : collection;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Collection<T> m6780b(@NotNull Iterable<? extends T> iterable) {
        cji.m5162f(iterable, "$this$convertToSetForSetOperation");
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return bzk.m6543r(iterable);
        }
        Collection<T> collection = (Collection) iterable;
        return m6779b((Collection) collection) ? bzk.m6543r(iterable) : collection;
    }

    @NotNull
    /* renamed from: c */
    public static final <T> List<T> m6778c(@NotNull Iterable<? extends Iterable<? extends T>> iterable) {
        cji.m5162f(iterable, "$this$flatten");
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Iterable<? extends T>> it = iterable.iterator();
        while (it.hasNext()) {
            bzk.m6760a((Collection) arrayList, (Iterable) it.next());
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: d */
    public static final <T, R> Tuples<List<T>, List<R>> m6777d(@NotNull Iterable<? extends Tuples<? extends T, ? extends R>> iterable) {
        cji.m5162f(iterable, "$this$unzip");
        int a = bzk.m6783a(iterable, 10);
        ArrayList arrayList = new ArrayList(a);
        ArrayList arrayList2 = new ArrayList(a);
        Iterator<? extends Tuples<? extends T, ? extends R>> it = iterable.iterator();
        while (it.hasNext()) {
            Tuples bwoVar = (Tuples) it.next();
            arrayList.add(bwoVar.getFirst());
            arrayList2.add(bwoVar.getSecond());
        }
        return bxh.m8730a(arrayList, arrayList2);
    }
}
