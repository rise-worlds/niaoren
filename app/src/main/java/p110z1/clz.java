package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.Comparable;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Ranges.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bg\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016J\u001d\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\f¨\u0006\r"}, m8860e = {"Lkotlin/ranges/ClosedFloatingPointRange;", TessBaseAPI.f9204e, "", "Lkotlin/ranges/ClosedRange;", "contains", "", SizeSelector.SIZE_KEY, "(Ljava/lang/Comparable;)Z", "isEmpty", "lessThanOrEquals", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"})
/* renamed from: z1.clz */
/* loaded from: classes3.dex */
public interface clz<T extends Comparable<? super T>> extends Range<T> {
    @Override // p110z1.Range
    /* renamed from: a */
    boolean mo4668a(@NotNull T t);

    /* renamed from: a */
    boolean mo4842a(@NotNull T t, @NotNull T t2);

    @Override // p110z1.Range
    /* renamed from: e */
    boolean mo4667e();

    /* compiled from: Ranges.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.clz$a */
    /* loaded from: classes3.dex */
    public static final class C4984a {
        /* renamed from: a */
        public static <T extends Comparable<? super T>> boolean m4840a(clz<T> clzVar, @NotNull T t) {
            cji.m5162f(t, SizeSelector.SIZE_KEY);
            return clzVar.mo4842a(clzVar.mo4665g(), t) && clzVar.mo4842a(t, clzVar.mo4663i());
        }

        /* renamed from: a */
        public static <T extends Comparable<? super T>> boolean m4841a(clz<T> clzVar) {
            return !clzVar.mo4842a(clzVar.mo4665g(), clzVar.mo4663i());
        }
    }
}
