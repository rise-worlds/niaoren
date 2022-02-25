package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.commons.p105io.FilenameUtils;

/* compiled from: Ranges.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a@\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0018\b\u0001\u0010\t*\b\u0012\u0004\u0012\u0002H\u00070\n*\b\u0012\u0004\u0012\u0002H\u00070\u000b*\u0002H\t2\b\u0010\f\u001a\u0004\u0018\u0001H\u0007H\u0087\n¢\u0006\u0002\u0010\r\u001a0\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000f*\u0002H\u00072\u0006\u0010\u0010\u001a\u0002H\u0007H\u0086\u0002¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0013H\u0087\u0002¨\u0006\u0014"}, m8860e = {"checkStepIsPositive", "", "isPositive", "", "step", "", "contains", TessBaseAPI.f9204e, "", "R", "", "Lkotlin/ranges/ClosedRange;", "element", "(Ljava/lang/Iterable;Ljava/lang/Object;)Z", "rangeTo", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/ClosedRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "kotlin-stdlib"}, m8859f = "kotlin/ranges/RangesKt", m8857h = 1)
/* renamed from: z1.cmk */
/* loaded from: classes3.dex */
class cmk extends cmj {
    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> Range<T> m4807a(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "$this$rangeTo");
        cji.m5162f(t2, "that");
        return new cmb(t, t2);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final clz<Double> m4808a(double d, double d2) {
        return new clx(d, d2);
    }

    /* JADX WARN: Incorrect types in method signature: <T:Ljava/lang/Object;R::Ljava/lang/Iterable<+TT;>;:Lz1/cma<TT;>;>(TR;TT;)Z */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final boolean m4806a(@NotNull Iterable iterable, Object obj) {
        cji.m5162f(iterable, "$this$contains");
        return obj != null && ((Range) iterable).mo4668a((Comparable) obj);
    }

    /* renamed from: a */
    public static final void m4805a(boolean z, @NotNull Number number) {
        cji.m5162f(number, "step");
        if (!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + number + FilenameUtils.EXTENSION_SEPARATOR);
        }
    }
}
