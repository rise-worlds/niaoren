package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.Comparable;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0016R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, m8860e = {"Lkotlin/ranges/ClosedRange;", TessBaseAPI.f9204e, "", "", "endInclusive", "getEndInclusive", "()Ljava/lang/Comparable;", "start", "getStart", "contains", "", SizeSelector.SIZE_KEY, "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"})
/* renamed from: z1.cma */
/* loaded from: classes3.dex */
public interface Range<T extends Comparable<? super T>> {
    /* renamed from: a */
    boolean mo4668a(@NotNull T t);

    /* renamed from: e */
    boolean mo4667e();

    @NotNull
    /* renamed from: g */
    T mo4665g();

    @NotNull
    /* renamed from: i */
    T mo4663i();

    /* compiled from: Range.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.cma$a */
    /* loaded from: classes3.dex */
    public static final class C4986a {
        /* renamed from: a */
        public static <T extends Comparable<? super T>> boolean m4834a(Range<T> cmaVar, @NotNull T t) {
            cji.m5162f(t, SizeSelector.SIZE_KEY);
            return t.compareTo(cmaVar.mo4665g()) >= 0 && t.compareTo(cmaVar.mo4663i()) <= 0;
        }

        /* renamed from: a */
        public static <T extends Comparable<? super T>> boolean m4835a(Range<T> cmaVar) {
            return cmaVar.mo4665g().compareTo(cmaVar.mo4663i()) > 0;
        }
    }
}
