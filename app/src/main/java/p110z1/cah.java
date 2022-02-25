package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Iterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\tH\u0086\u0002J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0086\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/collections/IndexingIterator;", TessBaseAPI.f9204e, "", "Lkotlin/collections/IndexedValue;", "iterator", "(Ljava/util/Iterator;)V", "index", "", "hasNext", "", "next", "kotlin-stdlib"})
/* renamed from: z1.cah */
/* loaded from: classes3.dex */
public final class cah<T> implements Iterator<IndexedValue<? extends T>>, KMarkers {

    /* renamed from: a */
    private int f20489a;

    /* renamed from: b */
    private final Iterator<T> f20490b;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public cah(@NotNull Iterator<? extends T> it) {
        cji.m5162f(it, "iterator");
        this.f20490b = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f20490b.hasNext();
    }

    @NotNull
    /* renamed from: a */
    public final IndexedValue<T> next() {
        int i = this.f20489a;
        this.f20489a = i + 1;
        if (i < 0) {
            bzk.m6800b();
        }
        return new IndexedValue<>(i, this.f20490b.next());
    }
}
