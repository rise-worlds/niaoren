package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001aA\u0010\u0006\u001a\u0002H\u0007\"\u0010\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\b\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\t\u001a\u0002H\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\n\u001a\u0016\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0011*\b\u0012\u0004\u0012\u0002H\r0\u0003\u001a8\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u001a\u0010\u0012\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\r0\u0013j\n\u0012\u0006\b\u0000\u0012\u0002H\r`\u0014¨\u0006\u0015"}, m8860e = {"filterIsInstance", "", "R", "", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Ljava/lang/Iterable;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "reverse", "", TessBaseAPI.f9204e, "", "toSortedSet", "Ljava/util/SortedSet;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzt */
/* loaded from: classes3.dex */
public class _CollectionsJvm extends ReversedViews {
    @NotNull
    /* renamed from: a */
    public static final <R> List<R> m6727a(@NotNull Iterable<?> iterable, @NotNull Class<R> cls) {
        cji.m5162f(iterable, "$this$filterIsInstance");
        cji.m5162f(cls, "klass");
        return (List) bzk.m6726a(iterable, new ArrayList(), cls);
    }

    @NotNull
    /* renamed from: a */
    public static final <C extends Collection<? super R>, R> C m6726a(@NotNull Iterable<?> iterable, @NotNull C c, @NotNull Class<R> cls) {
        cji.m5162f(iterable, "$this$filterIsInstanceTo");
        cji.m5162f(c, "destination");
        cji.m5162f(cls, "klass");
        for (Object obj : iterable) {
            if (cls.isInstance(obj)) {
                c.add(obj);
            }
        }
        return c;
    }

    /* renamed from: f */
    public static final <T> void m6723f(@NotNull List<T> list) {
        cji.m5162f(list, "$this$reverse");
        Collections.reverse(list);
    }

    @NotNull
    /* renamed from: f */
    public static final <T extends Comparable<? super T>> SortedSet<T> m6724f(@NotNull Iterable<? extends T> iterable) {
        cji.m5162f(iterable, "$this$toSortedSet");
        return (SortedSet) bzk.m6632c((Iterable) iterable, new TreeSet());
    }

    @NotNull
    /* renamed from: a */
    public static final <T> SortedSet<T> m6725a(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(iterable, "$this$toSortedSet");
        cji.m5162f(comparator, "comparator");
        return (SortedSet) bzk.m6632c((Iterable) iterable, new TreeSet(comparator));
    }
}
