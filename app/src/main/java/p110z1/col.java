package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.p105io.FilenameUtils;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/sequences/TakeSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"})
/* renamed from: z1.col */
/* loaded from: classes3.dex */
public final class col<T> implements cnt<T>, Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20973a;

    /* renamed from: b */
    private final int f20974b;

    /* JADX WARN: Multi-variable type inference failed */
    public col(@NotNull Sequence<? extends T> cobVar, int i) {
        cji.m5162f(cobVar, "sequence");
        this.f20973a = cobVar;
        this.f20974b = i;
        if (!(this.f20974b >= 0)) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + this.f20974b + FilenameUtils.EXTENSION_SEPARATOR).toString());
        }
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: a */
    public Sequence<T> mo4283a(int i) {
        int i2 = this.f20974b;
        return i >= i2 ? coe.m4440b() : new cok(this.f20973a, i, i2);
    }

    @Override // p110z1.cnt
    @NotNull
    /* renamed from: b */
    public Sequence<T> mo4281b(int i) {
        return i >= this.f20974b ? this : new col(this.f20973a, i);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, m8860e = {"kotlin/sequences/TakeSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "left", "", "getLeft", "()I", "setLeft", "(I)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.col$a */
    /* loaded from: classes3.dex */
    public static final class C5058a implements Iterator<T>, KMarkers {

        /* renamed from: b */
        private int f20976b;
        @NotNull

        /* renamed from: c */
        private final Iterator<T> f20977c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5058a() {
            this.f20976b = col.this.f20974b;
            this.f20977c = col.this.f20973a.mo3707a();
        }

        /* renamed from: a */
        public final int m4279a() {
            return this.f20976b;
        }

        /* renamed from: a */
        public final void m4278a(int i) {
            this.f20976b = i;
        }

        @NotNull
        /* renamed from: b */
        public final Iterator<T> m4277b() {
            return this.f20977c;
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this.f20976b;
            if (i != 0) {
                this.f20976b = i - 1;
                return this.f20977c.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20976b > 0 && this.f20977c.hasNext();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5058a();
    }
}
