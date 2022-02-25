package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Comparator;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001a?\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001a?\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\u000b"}, m8860e = {"maxOf", TessBaseAPI.f9204e, "a", "b", "c", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "kotlin-stdlib"}, m8859f = "kotlin/comparisons/ComparisonsKt", m8857h = 1)
/* renamed from: z1.cbu */
/* loaded from: classes3.dex */
class _Comparisons extends _ComparisonsJvm {
    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static final <T> T m5705a(T t, T t2, T t3, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return (T) cbr.m5704a(t, cbr.m5704a(t2, t3, comparator), comparator);
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static final <T> T m5704a(T t, T t2, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return comparator.compare(t, t2) >= 0 ? t : t2;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: b */
    public static final <T> T m5703b(T t, T t2, T t3, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return (T) cbr.m5702b(t, cbr.m5702b(t2, t3, comparator), comparator);
    }

    @bwy(m8750a = "1.1")
    /* renamed from: b */
    public static final <T> T m5702b(T t, T t2, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(comparator, "comparator");
        return comparator.compare(t, t2) <= 0 ? t : t2;
    }
}
