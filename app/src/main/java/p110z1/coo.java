package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B'\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J3\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0003\"\u0004\b\u0002\u0010\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000b0\u0006H\u0000¢\u0006\u0002\b\fJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/sequences/TransformingSequence;", TessBaseAPI.f9204e, "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "flatten", "E", "iterator", "", "flatten$kotlin_stdlib", "kotlin-stdlib"})
/* renamed from: z1.coo */
/* loaded from: classes3.dex */
public final class coo<T, R> implements Sequence<R> {

    /* renamed from: a */
    private final Sequence<T> f20989a;

    /* renamed from: b */
    private final chd<T, R> f20990b;

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0005\u001a\u00020\u0006H\u0096\u0002J\u000e\u0010\u0007\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, m8860e = {"kotlin/sequences/TransformingSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.coo$a */
    /* loaded from: classes3.dex */
    public static final class C5062a implements Iterator<R>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20992b;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5062a() {
            this.f20992b = coo.this.f20989a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4260a() {
            return this.f20992b;
        }

        @Override // java.util.Iterator
        public R next() {
            return (R) coo.this.f20990b.invoke(this.f20992b.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20992b.hasNext();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public coo(@NotNull Sequence<? extends T> cobVar, @NotNull chd<? super T, ? extends R> chdVar) {
        cji.m5162f(cobVar, "sequence");
        cji.m5162f(chdVar, "transformer");
        this.f20989a = cobVar;
        this.f20990b = chdVar;
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<R> mo3707a() {
        return new C5062a();
    }

    @NotNull
    /* renamed from: a */
    public final <E> Sequence<E> m4263a(@NotNull chd<? super R, ? extends Iterator<? extends E>> chdVar) {
        cji.m5162f(chdVar, "iterator");
        return new cnx(this.f20989a, this.f20990b, chdVar);
    }
}
