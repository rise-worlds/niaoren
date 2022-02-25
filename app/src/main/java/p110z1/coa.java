package p110z1;

import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004B;\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000bH\u0096\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m8860e = {"Lkotlin/sequences/MergingSequence;", "T1", "T2", "V", "Lkotlin/sequences/Sequence;", "sequence1", "sequence2", "transform", "Lkotlin/Function2;", "(Lkotlin/sequences/Sequence;Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.coa */
/* loaded from: classes3.dex */
public final class coa<T1, T2, V> implements Sequence<V> {

    /* renamed from: a */
    private final Sequence<T1> f20931a;

    /* renamed from: b */
    private final Sequence<T2> f20932b;

    /* renamed from: c */
    private final cho<T1, T2, V> f20933c;

    /* JADX WARN: Multi-variable type inference failed */
    public coa(@NotNull Sequence<? extends T1> cobVar, @NotNull Sequence<? extends T2> cobVar2, @NotNull cho<? super T1, ? super T2, ? extends V> choVar) {
        cji.m5162f(cobVar, "sequence1");
        cji.m5162f(cobVar2, "sequence2");
        cji.m5162f(choVar, "transform");
        this.f20931a = cobVar;
        this.f20932b = cobVar2;
        this.f20933c = choVar;
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u000e\u0010\t\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u000b"}, m8860e = {"kotlin/sequences/MergingSequence$iterator$1", "", "iterator1", "getIterator1", "()Ljava/util/Iterator;", "iterator2", "getIterator2", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.coa$a */
    /* loaded from: classes3.dex */
    public static final class C5021a implements Iterator<V>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T1> f20935b;
        @NotNull

        /* renamed from: c */
        private final Iterator<T2> f20936c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5021a() {
            this.f20935b = coa.this.f20931a.mo3707a();
            this.f20936c = coa.this.f20932b.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T1> m4464a() {
            return this.f20935b;
        }

        @NotNull
        /* renamed from: b */
        public final Iterator<T2> m4463b() {
            return this.f20936c;
        }

        @Override // java.util.Iterator
        public V next() {
            return (V) coa.this.f20933c.invoke(this.f20935b.next(), this.f20936c.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20935b.hasNext() && this.f20936c.hasNext();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<V> mo3707a() {
        return new C5021a();
    }
}
