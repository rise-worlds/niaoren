package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/sequences/DropWhileSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cnu */
/* loaded from: classes3.dex */
public final class cnu<T> implements Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20893a;

    /* renamed from: b */
    private final chd<T, Boolean> f20894b;

    /* JADX WARN: Multi-variable type inference failed */
    public cnu(@NotNull Sequence<? extends T> cobVar, @NotNull chd<? super T, Boolean> chdVar) {
        cji.m5162f(cobVar, "sequence");
        cji.m5162f(chdVar, "predicate");
        this.f20893a = cobVar;
        this.f20894b = chdVar;
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\t\u0010\u0013\u001a\u00020\u0014H\u0096\u0002J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, m8860e = {"kotlin/sequences/DropWhileSequence$iterator$1", "", "dropState", "", "getDropState", "()I", "setDropState", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "drop", "", "hasNext", "", "next", "kotlin-stdlib"})
    /* renamed from: z1.cnu$a */
    /* loaded from: classes3.dex */
    public static final class C5014a implements Iterator<T>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20896b;

        /* renamed from: c */
        private int f20897c = -1;
        @dbs

        /* renamed from: d */
        private T f20898d;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5014a() {
            this.f20896b = cnu.this.f20893a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4540a() {
            return this.f20896b;
        }

        /* renamed from: a */
        public final void m4539a(int i) {
            this.f20897c = i;
        }

        /* renamed from: b */
        public final int m4537b() {
            return this.f20897c;
        }

        /* renamed from: a */
        public final void m4538a(@dbs T t) {
            this.f20898d = t;
        }

        @dbs
        /* renamed from: c */
        public final T m4536c() {
            return this.f20898d;
        }

        /* renamed from: d */
        private final void m4535d() {
            while (this.f20896b.hasNext()) {
                T next = this.f20896b.next();
                if (!((Boolean) cnu.this.f20894b.invoke(next)).booleanValue()) {
                    this.f20898d = next;
                    this.f20897c = 1;
                    return;
                }
            }
            this.f20897c = 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.f20897c == -1) {
                m4535d();
            }
            if (this.f20897c != 1) {
                return this.f20896b.next();
            }
            T t = this.f20898d;
            this.f20898d = null;
            this.f20897c = 0;
            return t;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f20897c == -1) {
                m4535d();
            }
            return this.f20897c == 1 || this.f20896b.hasNext();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5014a();
    }
}
