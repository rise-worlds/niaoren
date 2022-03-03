package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/sequences/TransformingIndexedSequence;", TessBaseAPI.f9204e, "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.con */
/* loaded from: classes3.dex */
public final class con<T, R> implements Sequence<R> {

    /* renamed from: a */
    private final Sequence<T> f20984a;

    /* renamed from: b */
    private final cho<Integer, T, R> f20985b;

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001b\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m8860e = {"kotlin/sequences/TransformingIndexedSequence$iterator$1", "", "index", "", "getIndex", "()I", "setIndex", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.con$a */
    /* loaded from: classes3.dex */
    public static final class C5061a implements Iterator<R>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20987b;

        /* renamed from: c */
        private int f20988c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5061a() {
            this.f20987b = con.this.f20984a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4266a() {
            return this.f20987b;
        }

        /* renamed from: a */
        public final void m4265a(int i) {
            this.f20988c = i;
        }

        /* renamed from: b */
        public final int m4264b() {
            return this.f20988c;
        }

        @Override // java.util.Iterator
        public R next() {
            cho choVar = con.this.f20985b;
            int i = this.f20988c;
            this.f20988c = i + 1;
            if (i < 0) {
                bzk.m6800b();
            }
            return (R) choVar.invoke(Integer.valueOf(i), this.f20987b.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20987b.hasNext();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public con(@NotNull Sequence<? extends T> cobVar, @NotNull cho<? super Integer, ? super T, ? extends R> choVar) {
        cji.m5162f(cobVar, "sequence");
        cji.m5162f(choVar, "transformer");
        this.f20984a = cobVar;
        this.f20985b = choVar;
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<R> mo3707a() {
        return new C5061a();
    }
}
