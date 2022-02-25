package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Comparator;

/* compiled from: Comparisons.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u0004\u0012\u0002H\u00010\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u0003B\u001d\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003¢\u0006\u0002\u0010\u0005J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m8860e = {"Lkotlin/comparisons/ReversedComparator;", TessBaseAPI.f9204e, "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "(Ljava/util/Comparator;)V", "getComparator", "()Ljava/util/Comparator;", "compare", "", "a", "b", "(Ljava/lang/Object;Ljava/lang/Object;)I", "reversed", "kotlin-stdlib"})
/* renamed from: z1.cbx */
/* loaded from: classes3.dex */
final class cbx<T> implements Comparator<T> {
    @NotNull

    /* renamed from: a */
    private final Comparator<T> f20546a;

    public cbx(@NotNull Comparator<T> comparator) {
        cji.m5162f(comparator, "comparator");
        this.f20546a = comparator;
    }

    @NotNull
    /* renamed from: a */
    public final Comparator<T> m5699a() {
        return this.f20546a;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        return this.f20546a.compare(t2, t);
    }

    @Override // java.util.Comparator
    @NotNull
    public final Comparator<T> reversed() {
        return this.f20546a;
    }
}
