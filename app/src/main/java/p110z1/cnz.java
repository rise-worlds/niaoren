package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0007H\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m8860e = {"Lkotlin/sequences/IndexingSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "Lkotlin/collections/IndexedValue;", "sequence", "(Lkotlin/sequences/Sequence;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cnz */
/* loaded from: classes3.dex */
public final class cnz<T> implements Sequence<IndexedValue<? extends T>> {

    /* renamed from: a */
    private final Sequence<T> f20918a;

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001J\t\u0010\f\u001a\u00020\rH\u0096\u0002J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, m8860e = {"kotlin/sequences/IndexingSequence$iterator$1", "", "Lkotlin/collections/IndexedValue;", "index", "", "getIndex", "()I", "setIndex", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "hasNext", "", "next", "kotlin-stdlib"})
    /* renamed from: z1.cnz$a */
    /* loaded from: classes3.dex */
    public static final class C5018a implements Iterator<IndexedValue<? extends T>>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20920b;

        /* renamed from: c */
        private int f20921c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5018a() {
            this.f20920b = cnz.this.f20918a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4508a() {
            return this.f20920b;
        }

        /* renamed from: a */
        public final void m4507a(int i) {
            this.f20921c = i;
        }

        /* renamed from: b */
        public final int m4506b() {
            return this.f20921c;
        }

        @NotNull
        /* renamed from: c */
        public IndexedValue<T> next() {
            int i = this.f20921c;
            this.f20921c = i + 1;
            if (i < 0) {
                bzk.m6800b();
            }
            return new IndexedValue<>(i, this.f20920b.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20920b.hasNext();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public cnz(@NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(cobVar, "sequence");
        this.f20918a = cobVar;
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<IndexedValue<T>> mo3707a() {
        return new C5018a();
    }
}
