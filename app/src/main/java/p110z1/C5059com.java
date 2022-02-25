package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/sequences/TakeWhileSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.com */
/* loaded from: classes3.dex */
public final class C5059com<T> implements Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20978a;

    /* renamed from: b */
    private final chd<T, Boolean> f20979b;

    /* JADX WARN: Multi-variable type inference failed */
    public C5059com(@NotNull Sequence<? extends T> cobVar, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(cobVar, "sequence");
        cji.m5162f(chdVar, "predicate");
        this.f20978a = cobVar;
        this.f20979b = chdVar;
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\t\u0010\u0013\u001a\u00020\u0014H\u0096\u0002J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, m8860e = {"kotlin/sequences/TakeWhileSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "nextState", "", "getNextState", "()I", "setNextState", "(I)V", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"})
    /* renamed from: z1.com$a */
    /* loaded from: classes3.dex */
    public static final class C5060a implements Iterator<T>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20981b;

        /* renamed from: c */
        private int f20982c = -1;
        @dbs

        /* renamed from: d */
        private T f20983d;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5060a() {
            this.f20981b = C5059com.this.f20978a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4274a() {
            return this.f20981b;
        }

        /* renamed from: a */
        public final void m4273a(int i) {
            this.f20982c = i;
        }

        /* renamed from: b */
        public final int m4271b() {
            return this.f20982c;
        }

        /* renamed from: a */
        public final void m4272a(@dbs T t) {
            this.f20983d = t;
        }

        @dbs
        /* renamed from: c */
        public final T m4270c() {
            return this.f20983d;
        }

        /* renamed from: d */
        private final void m4269d() {
            if (this.f20981b.hasNext()) {
                T next = this.f20981b.next();
                if (((Boolean) C5059com.this.f20979b.invoke(next)).booleanValue()) {
                    this.f20982c = 1;
                    this.f20983d = next;
                    return;
                }
            }
            this.f20982c = 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.f20982c == -1) {
                m4269d();
            }
            if (this.f20982c != 0) {
                T t = this.f20983d;
                this.f20983d = null;
                this.f20982c = -1;
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f20982c == -1) {
                m4269d();
            }
            return this.f20982c == 1;
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5060a();
    }
}
