package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eR\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m8860e = {"Lkotlin/jvm/internal/ArrayIterator;", TessBaseAPI.f9204e, "", "array", "", "([Ljava/lang/Object;)V", "getArray", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "index", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
/* renamed from: z1.cig */
/* loaded from: classes3.dex */
final class ArrayIterator<T> implements Iterator<T>, KMarkers {

    /* renamed from: a */
    private int f20701a;
    @NotNull

    /* renamed from: b */
    private final T[] f20702b;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public ArrayIterator(@NotNull T[] tArr) {
        cji.m5162f(tArr, "array");
        this.f20702b = tArr;
    }

    @NotNull
    /* renamed from: a */
    public final T[] m5259a() {
        return this.f20702b;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20701a < this.f20702b.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f20702b;
            int i = this.f20701a;
            this.f20701a = i + 1;
            return tArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20701a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
