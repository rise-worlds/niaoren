package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0087\b\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0007\u001a&\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a \u0010\f\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a3\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00100\u000fH\u0087\b\u001a5\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0011\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0012j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0013H\u0087\b\u001a2\u0010\u0014\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0011\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0012j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0013¨\u0006\u0015"}, m8860e = {"fill", "", TessBaseAPI.f9204e, "", SizeSelector.SIZE_KEY, "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "random", "Ljava/util/Random;", "shuffled", "", "", "sort", "", "comparison", "Lkotlin/Function2;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzq */
/* loaded from: classes3.dex */
public class MutableCollectionsJVM extends bzp {
    @Annotations(m8902a = "Use sortWith(comparator) instead.", m8901b = @bwu(m8768a = "this.sortWith(comparator)", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: b */
    private static final <T> void m6767b(@NotNull List<T> list, Comparator<? super T> comparator) {
        throw new Standard(null, 1, null);
    }

    @Annotations(m8902a = "Use sortWith(Comparator(comparison)) instead.", m8901b = @bwu(m8768a = "this.sortWith(Comparator(comparison))", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: a */
    private static final <T> void m6768a(@NotNull List<T> list, cho<? super T, ? super T, Integer> choVar) {
        throw new Standard(null, 1, null);
    }

    /* renamed from: c */
    public static final <T extends Comparable<? super T>> void m6766c(@NotNull List<T> list) {
        cji.m5162f(list, "$this$sort");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }

    /* renamed from: a */
    public static final <T> void m6770a(@NotNull List<T> list, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(list, "$this$sortWith");
        cji.m5162f(comparator, "comparator");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final <T> void m6771a(@NotNull List<T> list, T t) {
        Collections.fill(list, t);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final <T> void m6765d(@NotNull List<T> list) {
        Collections.shuffle(list);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final <T> void m6769a(@NotNull List<T> list, Random random) {
        Collections.shuffle(list, random);
    }

    @bwy(m8750a = "1.2")
    @NotNull
    /* renamed from: e */
    public static final <T> List<T> m6764e(@NotNull Iterable<? extends T> iterable) {
        cji.m5162f(iterable, "$this$shuffled");
        List<T> t = bzk.m6537t(iterable);
        Collections.shuffle(t);
        return t;
    }

    @bwy(m8750a = "1.2")
    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m6772a(@NotNull Iterable<? extends T> iterable, @NotNull Random random) {
        cji.m5162f(iterable, "$this$shuffled");
        cji.m5162f(random, "random");
        List<T> t = bzk.m6537t(iterable);
        Collections.shuffle(t, random);
        return t;
    }
}
