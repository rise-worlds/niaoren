package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import org.apache.commons.p105io.FilenameUtils;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/sequences/DropSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"})
/* renamed from: z1.cns */
/* loaded from: classes3.dex */
public final class cns<T> implements cnt<T>, Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20888a;

    /* renamed from: b */
    private final int f20889b;

    /* JADX WARN: Multi-variable type inference failed */
    public cns(@NotNull Sequence<? extends T> cobVar, int i) {
        cji.m5162f(cobVar, "sequence");
        this.f20888a = cobVar;
        this.f20889b = i;
        if (!(this.f20889b >= 0)) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + this.f20889b + FilenameUtils.EXTENSION_SEPARATOR).toString());
        }
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: a */
    public Sequence<T> mo4283a(int i) {
        int i2 = this.f20889b + i;
        return i2 < 0 ? new cns(this, i) : new cns(this.f20888a, i2);
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: b */
    public Sequence<T> mo4281b(int i) {
        int i2 = this.f20889b;
        int i3 = i2 + i;
        return i3 < 0 ? new col(this, i) : new cok(this.f20888a, i2, i3);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, m8860e = {"kotlin/sequences/DropSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "left", "", "getLeft", "()I", "setLeft", "(I)V", "drop", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.cns$a */
    /* loaded from: classes3.dex */
    public static final class C5013a implements Iterator<T>, KMarkers {
        @NotNull

        /* renamed from: b */
        private final Iterator<T> f20891b;

        /* renamed from: c */
        private int f20892c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5013a() {
            this.f20891b = cns.this.f20888a.mo3707a();
            this.f20892c = cns.this.f20889b;
        }

        @NotNull
        /* renamed from: a */
        public final Iterator<T> m4546a() {
            return this.f20891b;
        }

        /* renamed from: a */
        public final void m4545a(int i) {
            this.f20892c = i;
        }

        /* renamed from: b */
        public final int m4544b() {
            return this.f20892c;
        }

        /* renamed from: c */
        private final void m4543c() {
            while (this.f20892c > 0 && this.f20891b.hasNext()) {
                this.f20891b.next();
                this.f20892c--;
            }
        }

        @Override // java.util.Iterator
        public T next() {
            m4543c();
            return this.f20891b.next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            m4543c();
            return this.f20891b.hasNext();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5013a();
    }
}
