package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a;\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u001aY\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\t\u001aW\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u001a;\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u001aW\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u001a-\u0010\r\u001a\u00020\u000e\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u000f\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0010\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u0011\u001a>\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b¢\u0006\u0002\u0010\u0013\u001aY\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\u0014\u001aZ\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b¢\u0006\u0002\u0010\u0015\u001aG\u0010\u0016\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022 \u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0014\u001a&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a-\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a-\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a&\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a0\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\u001aO\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u001aO\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u001ak\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u001aO\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u001ak\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u001am\u0010!\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u000328\b\u0004\u0010\"\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e0#H\u0087\b\u001aO\u0010&\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004¨\u0006'"}, m8860e = {"compareBy", "Ljava/util/Comparator;", TessBaseAPI.f9204e, "Lkotlin/Comparator;", "selector", "Lkotlin/Function1;", "", "selectors", "", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "K", "comparator", "compareByDescending", "compareValues", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareValuesBy", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", "naturalOrder", "nullsFirst", "", "nullsLast", "reverseOrder", "reversed", "then", "thenBy", "thenByDescending", "thenComparator", "comparison", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "thenDescending", "kotlin-stdlib"}, m8859f = "kotlin/comparisons/ComparisonsKt", m8857h = 1)
/* renamed from: z1.cbs */
/* loaded from: classes3.dex */
public class Comparisons {
    /* renamed from: a */
    public static final <T> int m5754a(T t, T t2, @NotNull chd<? super T, ? extends Comparable<?>>... chdVarArr) {
        cji.m5162f(chdVarArr, "selectors");
        if (chdVarArr.length > 0) {
            return m5738c(t, t2, chdVarArr);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static final <T> int m5738c(T t, T t2, chd<? super T, ? extends Comparable<?>>[] chdVarArr) {
        for (chd<? super T, ? extends Comparable<?>> chdVar : chdVarArr) {
            int a = cbr.m5757a((Comparable) chdVar.invoke(t), (Comparable) chdVar.invoke(t2));
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    @cey
    /* renamed from: a */
    private static final <T> int m5755a(T t, T t2, chd<? super T, ? extends Comparable<?>> chdVar) {
        return cbr.m5757a((Comparable) chdVar.invoke(t), (Comparable) chdVar.invoke(t2));
    }

    @cey
    /* renamed from: a */
    private static final <T, K> int m5756a(T t, T t2, Comparator<? super K> comparator, chd<? super T, ? extends K> chdVar) {
        return comparator.compare((Object) chdVar.invoke(t), (Object) chdVar.invoke(t2));
    }

    /* renamed from: a */
    public static final <T extends Comparable<?>> int m5757a(@dbs T t, @dbs T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        return t.compareTo(t2);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$a */
    /* loaded from: classes3.dex */
    static final class C4886a<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ chd[] f20519a;

        C4886a(chd[] chdVarArr) {
            this.f20519a = chdVarArr;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return Comparisons.m5738c(t, t2, this.f20519a);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Comparator<T> m5747a(@NotNull chd<? super T, ? extends Comparable<?>>... chdVarArr) {
        cji.m5162f(chdVarArr, "selectors");
        if (chdVarArr.length > 0) {
            return new C4886a(chdVarArr);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$b */
    /* loaded from: classes3.dex */
    public static final class C4887b<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ chd f20520a;

        public C4887b(chd chdVar) {
            this.f20520a = chdVar;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return cbr.m5757a((Comparable) this.f20520a.invoke(t), (Comparable) this.f20520a.invoke(t2));
        }
    }

    @cey
    /* renamed from: a */
    private static final <T> Comparator<T> m5748a(chd<? super T, ? extends Comparable<?>> chdVar) {
        return new C4887b(chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$c */
    /* loaded from: classes3.dex */
    public static final class C4888c<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20521a;

        /* renamed from: b */
        final /* synthetic */ chd f20522b;

        public C4888c(Comparator comparator, chd chdVar) {
            this.f20521a = comparator;
            this.f20522b = chdVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return this.f20521a.compare(this.f20522b.invoke(t), this.f20522b.invoke(t2));
        }
    }

    @cey
    /* renamed from: a */
    private static final <T, K> Comparator<T> m5750a(Comparator<? super K> comparator, chd<? super T, ? extends K> chdVar) {
        return new C4888c(comparator, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$d */
    /* loaded from: classes3.dex */
    public static final class C4889d<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ chd f20523a;

        public C4889d(chd chdVar) {
            this.f20523a = chdVar;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return cbr.m5757a((Comparable) this.f20523a.invoke(t2), (Comparable) this.f20523a.invoke(t));
        }
    }

    @cey
    /* renamed from: b */
    private static final <T> Comparator<T> m5740b(chd<? super T, ? extends Comparable<?>> chdVar) {
        return new C4889d(chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$e */
    /* loaded from: classes3.dex */
    public static final class C4890e<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20524a;

        /* renamed from: b */
        final /* synthetic */ chd f20525b;

        public C4890e(Comparator comparator, chd chdVar) {
            this.f20524a = comparator;
            this.f20525b = chdVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return this.f20524a.compare(this.f20525b.invoke(t2), this.f20525b.invoke(t));
        }
    }

    @cey
    /* renamed from: b */
    private static final <T, K> Comparator<T> m5741b(Comparator<? super K> comparator, chd<? super T, ? extends K> chdVar) {
        return new C4890e(comparator, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$i */
    /* loaded from: classes3.dex */
    public static final class C4894i<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20530a;

        /* renamed from: b */
        final /* synthetic */ chd f20531b;

        public C4894i(Comparator comparator, chd chdVar) {
            this.f20530a = comparator;
            this.f20531b = chdVar;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20530a.compare(t, t2);
            return compare != 0 ? compare : cbr.m5757a((Comparable) this.f20531b.invoke(t), (Comparable) this.f20531b.invoke(t2));
        }
    }

    @cey
    /* renamed from: c */
    private static final <T> Comparator<T> m5736c(@NotNull Comparator<T> comparator, chd<? super T, ? extends Comparable<?>> chdVar) {
        return new C4894i(comparator, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$j */
    /* loaded from: classes3.dex */
    public static final class C4895j<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20532a;

        /* renamed from: b */
        final /* synthetic */ Comparator f20533b;

        /* renamed from: c */
        final /* synthetic */ chd f20534c;

        public C4895j(Comparator comparator, Comparator comparator2, chd chdVar) {
            this.f20532a = comparator;
            this.f20533b = comparator2;
            this.f20534c = chdVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20532a.compare(t, t2);
            return compare != 0 ? compare : this.f20533b.compare(this.f20534c.invoke(t), this.f20534c.invoke(t2));
        }
    }

    @cey
    /* renamed from: a */
    private static final <T, K> Comparator<T> m5751a(@NotNull Comparator<T> comparator, Comparator<? super K> comparator2, chd<? super T, ? extends K> chdVar) {
        return new C4895j(comparator, comparator2, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$k */
    /* loaded from: classes3.dex */
    public static final class C4896k<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20535a;

        /* renamed from: b */
        final /* synthetic */ chd f20536b;

        public C4896k(Comparator comparator, chd chdVar) {
            this.f20535a = comparator;
            this.f20536b = chdVar;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20535a.compare(t, t2);
            return compare != 0 ? compare : cbr.m5757a((Comparable) this.f20536b.invoke(t2), (Comparable) this.f20536b.invoke(t));
        }
    }

    @cey
    /* renamed from: d */
    private static final <T> Comparator<T> m5734d(@NotNull Comparator<T> comparator, chd<? super T, ? extends Comparable<?>> chdVar) {
        return new C4896k(comparator, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$l */
    /* loaded from: classes3.dex */
    public static final class C4897l<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20537a;

        /* renamed from: b */
        final /* synthetic */ Comparator f20538b;

        /* renamed from: c */
        final /* synthetic */ chd f20539c;

        public C4897l(Comparator comparator, Comparator comparator2, chd chdVar) {
            this.f20537a = comparator;
            this.f20538b = comparator2;
            this.f20539c = chdVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20537a.compare(t, t2);
            return compare != 0 ? compare : this.f20538b.compare(this.f20539c.invoke(t2), this.f20539c.invoke(t));
        }
    }

    @cey
    /* renamed from: b */
    private static final <T, K> Comparator<T> m5742b(@NotNull Comparator<T> comparator, Comparator<? super K> comparator2, chd<? super T, ? extends K> chdVar) {
        return new C4897l(comparator, comparator2, chdVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$m */
    /* loaded from: classes3.dex */
    public static final class C4898m<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20540a;

        /* renamed from: b */
        final /* synthetic */ cho f20541b;

        public C4898m(Comparator comparator, cho choVar) {
            this.f20540a = comparator;
            this.f20541b = choVar;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20540a.compare(t, t2);
            return compare != 0 ? compare : ((Number) this.f20541b.invoke(t, t2)).intValue();
        }
    }

    @cey
    /* renamed from: a */
    private static final <T> Comparator<T> m5749a(@NotNull Comparator<T> comparator, cho<? super T, ? super T, Integer> choVar) {
        return new C4898m(comparator, choVar);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$h */
    /* loaded from: classes3.dex */
    static final class C4893h<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20528a;

        /* renamed from: b */
        final /* synthetic */ Comparator f20529b;

        C4893h(Comparator comparator, Comparator comparator2) {
            this.f20528a = comparator;
            this.f20529b = comparator2;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20528a.compare(t, t2);
            return compare != 0 ? compare : this.f20529b.compare(t, t2);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Comparator<T> m5752a(@NotNull Comparator<T> comparator, @NotNull Comparator<? super T> comparator2) {
        cji.m5162f(comparator, "$this$then");
        cji.m5162f(comparator2, "comparator");
        return new C4893h(comparator, comparator2);
    }

    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$n */
    /* loaded from: classes3.dex */
    static final class C4899n<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20542a;

        /* renamed from: b */
        final /* synthetic */ Comparator f20543b;

        C4899n(Comparator comparator, Comparator comparator2) {
            this.f20542a = comparator;
            this.f20543b = comparator2;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            int compare = this.f20542a.compare(t, t2);
            return compare != 0 ? compare : this.f20543b.compare(t2, t);
        }
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Comparator<T> m5743b(@NotNull Comparator<T> comparator, @NotNull Comparator<? super T> comparator2) {
        cji.m5162f(comparator, "$this$thenDescending");
        cji.m5162f(comparator2, "comparator");
        return new C4899n(comparator, comparator2);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Comparator<T> m5753a(@NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return new C4891f(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "", "a", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$f */
    /* loaded from: classes3.dex */
    public static final class C4891f<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20526a;

        C4891f(Comparator comparator) {
            this.f20526a = comparator;
        }

        @Override // java.util.Comparator
        public final int compare(@dbs T t, @dbs T t2) {
            if (t == t2) {
                return 0;
            }
            if (t == null) {
                return -1;
            }
            if (t2 == null) {
                return 1;
            }
            return this.f20526a.compare(t, t2);
        }
    }

    @cey
    /* renamed from: c */
    private static final <T extends Comparable<? super T>> Comparator<T> m5739c() {
        return cbr.m5753a(cbr.m5758a());
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Comparator<T> m5744b(@NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return new C4892g(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Comparisons.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "", "a", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"})
    /* renamed from: z1.cbs$g */
    /* loaded from: classes3.dex */
    public static final class C4892g<T> implements Comparator<T> {

        /* renamed from: a */
        final /* synthetic */ Comparator f20527a;

        C4892g(Comparator comparator) {
            this.f20527a = comparator;
        }

        @Override // java.util.Comparator
        public final int compare(@dbs T t, @dbs T t2) {
            if (t == t2) {
                return 0;
            }
            if (t == null) {
                return 1;
            }
            if (t2 == null) {
                return -1;
            }
            return this.f20527a.compare(t, t2);
        }
    }

    @cey
    /* renamed from: d */
    private static final <T extends Comparable<? super T>> Comparator<T> m5735d() {
        return cbr.m5744b(cbr.m5758a());
    }

    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> Comparator<T> m5758a() {
        cbv cbvVar = cbv.f20544a;
        if (cbvVar != null) {
            return cbvVar;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
    }

    @NotNull
    /* renamed from: b */
    public static final <T extends Comparable<? super T>> Comparator<T> m5746b() {
        cbw cbwVar = cbw.f20545a;
        if (cbwVar != null) {
            return cbwVar;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
    }

    @NotNull
    /* renamed from: c */
    public static final <T> Comparator<T> m5737c(@NotNull Comparator<T> comparator) {
        cji.m5162f(comparator, "$this$reversed");
        if (comparator instanceof cbx) {
            return ((cbx) comparator).m5699a();
        }
        if (cji.m5184a(comparator, cbv.f20544a)) {
            cbw cbwVar = cbw.f20545a;
            if (cbwVar != null) {
                return cbwVar;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
        } else if (!cji.m5184a(comparator, cbw.f20545a)) {
            return new cbx(comparator);
        } else {
            cbv cbvVar = cbv.f20544a;
            if (cbvVar != null) {
                return cbvVar;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
        }
    }
}
