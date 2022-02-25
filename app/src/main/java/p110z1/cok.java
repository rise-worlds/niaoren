package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m8860e = {"Lkotlin/sequences/SubSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"})
/* renamed from: z1.cok */
/* loaded from: classes3.dex */
public final class cok<T> implements cnt<T>, Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20967a;

    /* renamed from: b */
    private final int f20968b;

    /* renamed from: c */
    private final int f20969c;

    /* JADX WARN: Multi-variable type inference failed */
    public cok(@NotNull Sequence<? extends T> cobVar, int i, int i2) {
        cji.m5162f(cobVar, "sequence");
        this.f20967a = cobVar;
        this.f20968b = i;
        this.f20969c = i2;
        boolean z = true;
        if (this.f20968b >= 0) {
            if (this.f20969c >= 0) {
                if (!(this.f20969c < this.f20968b ? false : z)) {
                    throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + this.f20969c + " < " + this.f20968b).toString());
                }
                return;
            }
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + this.f20969c).toString());
        }
        throw new IllegalArgumentException(("startIndex should be non-negative, but is " + this.f20968b).toString());
    }

    /* renamed from: b */
    private final int m4290b() {
        return this.f20969c - this.f20968b;
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: a */
    public Sequence<T> mo4283a(int i) {
        return i >= m4290b() ? coe.m4440b() : new cok(this.f20967a, this.f20968b + i, this.f20969c);
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: b */
    public Sequence<T> mo4281b(int i) {
        if (i >= m4290b()) {
            return this;
        }
        Sequence<T> cobVar = this.f20967a;
        int i2 = this.f20968b;
        return new cok(cobVar, i2, i + i2);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, m8860e = {"kotlin/sequences/SubSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "position", "", "getPosition", "()I", "setPosition", "(I)V", "drop", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.cok$a */
    /* loaded from: classes3.dex */
    public static final class C5057a implements Iterator<T>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20971b;

        /* renamed from: c */
        private int f20972c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5057a() {
            this.f20971b = cok.this.f20967a.mo3707a();
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4287a() {
            return this.f20971b;
        }

        /* renamed from: a */
        public final void m4286a(int i) {
            this.f20972c = i;
        }

        /* renamed from: b */
        public final int m4285b() {
            return this.f20972c;
        }

        /* renamed from: c */
        private final void m4284c() {
            while (this.f20972c < cok.this.f20968b && this.f20971b.hasNext()) {
                this.f20971b.next();
                this.f20972c++;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            m4284c();
            return this.f20972c < cok.this.f20969c && this.f20971b.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            m4284c();
            if (this.f20972c < cok.this.f20969c) {
                this.f20972c++;
                return this.f20971b.next();
            }
            throw new NoSuchElementException();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5057a();
    }
}
