package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004BA\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\tH\u0096\u0002R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/sequences/FlatteningSequence;", TessBaseAPI.f9204e, "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "iterator", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"})
/* renamed from: z1.cnx */
/* loaded from: classes3.dex */
public final class cnx<T, R, E> implements Sequence<E> {

    /* renamed from: a */
    private final Sequence<T> f20907a;

    /* renamed from: b */
    private final chd<T, R> f20908b;

    /* renamed from: c */
    private final chd<R, Iterator<E>> f20909c;

    /* JADX WARN: Multi-variable type inference failed */
    public cnx(@NotNull Sequence<? extends T> cobVar, @NotNull chd<? super T, ? extends R> chdVar, @NotNull chd<? super R, ? extends Iterator<? extends E>> chdVar2) {
        cji.m5162f(cobVar, "sequence");
        cji.m5162f(chdVar, "transformer");
        cji.m5162f(chdVar2, "iterator");
        this.f20907a = cobVar;
        this.f20908b = chdVar;
        this.f20909c = chdVar2;
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\t\u001a\u00020\nH\u0002J\t\u0010\u000b\u001a\u00020\nH\u0096\u0002J\u000e\u0010\f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0004¨\u0006\u000e"}, m8860e = {"kotlin/sequences/FlatteningSequence$iterator$1", "", "itemIterator", "getItemIterator", "()Ljava/util/Iterator;", "setItemIterator", "(Ljava/util/Iterator;)V", "iterator", "getIterator", "ensureItemIterator", "", "hasNext", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.cnx$a */
    /* loaded from: classes3.dex */
    public static final class C5016a implements Iterator<E>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20911b;
        @dbs

        /* renamed from: c */
        private Iterator<? extends E> f20912c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5016a() {
            this.f20911b = cnx.this.f20907a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4520a() {
            return this.f20911b;
        }

        /* renamed from: a */
        public final void m4519a(@dbs Iterator<? extends E> it) {
            this.f20912c = it;
        }

        @dbs
        /* renamed from: b */
        public final Iterator<E> m4518b() {
            return (Iterator<? extends E>) this.f20912c;
        }

        @Override // java.util.Iterator
        public E next() {
            if (m4517c()) {
                Iterator<? extends E> it = this.f20912c;
                if (it == null) {
                    cji.m5196a();
                }
                return (E) it.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return m4517c();
        }

        /* renamed from: c */
        private final boolean m4517c() {
            Iterator<? extends E> it = this.f20912c;
            if (it != null && !it.hasNext()) {
                this.f20912c = null;
            }
            while (this.f20912c == null) {
                if (!this.f20911b.hasNext()) {
                    return false;
                }
                Iterator<? extends E> it2 = (Iterator) cnx.this.f20909c.invoke(cnx.this.f20908b.invoke(this.f20911b.next()));
                if (it2.hasNext()) {
                    this.f20912c = it2;
                    return true;
                }
            }
            return true;
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<E> mo3707a() {
        return new C5016a();
    }
}
