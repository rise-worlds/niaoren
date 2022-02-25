package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a+\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\b\"\u0002H\u0002¢\u0006\u0002\u0010\t\u001aG\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u00022\u001a\u0010\n\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000bj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\f2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\b\"\u0002H\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, m8860e = {"setOf", "", TessBaseAPI.f9204e, "element", "(Ljava/lang/Object;)Ljava/util/Set;", "sortedSetOf", "Ljava/util/TreeSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/TreeSet;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Comparator;[Ljava/lang/Object;)Ljava/util/TreeSet;", "kotlin-stdlib"}, m8859f = "kotlin/collections/SetsKt", m8857h = 1)
/* renamed from: z1.caz */
/* loaded from: classes3.dex */
public class SetsJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> Set<T> m6358a(T t) {
        Set<T> singleton = Collections.singleton(t);
        cji.m5175b(singleton, "java.util.Collections.singleton(element)");
        return singleton;
    }

    @NotNull
    /* renamed from: a */
    public static final <T> TreeSet<T> m6356a(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return (TreeSet) bzb.m7239e((Object[]) tArr, new TreeSet());
    }

    @NotNull
    /* renamed from: a */
    public static final <T> TreeSet<T> m6357a(@NotNull Comparator<? super T> comparator, @NotNull T... tArr) {
        cji.m5162f(comparator, "comparator");
        cji.m5162f(tArr, "elements");
        return (TreeSet) bzb.m7239e((Object[]) tArr, new TreeSet(comparator));
    }
}
