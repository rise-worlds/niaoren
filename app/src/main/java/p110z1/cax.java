package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

/* compiled from: SlidingWindow.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0096\u0002J\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0006J\u0015\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0014¢\u0006\u0002\u0010 J'\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0001\u0010\u00012\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0014¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0082\bR\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m8860e = {"Lkotlin/collections/RingBuffer;", TessBaseAPI.f9204e, "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "getCapacity", "()I", "<set-?>", "size", "getSize", "startIndex", "add", "", "element", "(Ljava/lang/Object;)V", "get", "index", "(I)Ljava/lang/Object;", "isFull", "", "iterator", "", "removeFirst", "n", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "forward", "kotlin-stdlib"})
/* renamed from: z1.cax */
/* loaded from: classes3.dex */
final class cax<T> extends AbstractList<T> implements RandomAccess {

    /* renamed from: b */
    private final Object[] f20501b;

    /* renamed from: c */
    private int f20502c;

    /* renamed from: d */
    private int f20503d;

    /* renamed from: e */
    private final int f20504e;

    public cax(int i) {
        this.f20504e = i;
        if (this.f20504e >= 0) {
            this.f20501b = new Object[this.f20504e];
            return;
        }
        throw new IllegalArgumentException(("ring buffer capacity should not be negative but it is " + this.f20504e).toString());
    }

    /* renamed from: c */
    public final int m6360c() {
        return this.f20504e;
    }

    @Override // p110z1.AbstractList, p110z1.AbstractCollection
    /* renamed from: a */
    public int mo4172a() {
        return this.f20503d;
    }

    @Override // p110z1.AbstractList, java.util.List
    public T get(int i) {
        AbstractList.f20424a.m8322a(i, size());
        return (T) this.f20501b[(this.f20502c + i) % m6360c()];
    }

    /* renamed from: b */
    public final boolean m6363b() {
        return size() == this.f20504e;
    }

    /* compiled from: SlidingWindow.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m8860e = {"kotlin/collections/RingBuffer$iterator$1", "Lkotlin/collections/AbstractIterator;", "count", "", "index", "computeNext", "", "kotlin-stdlib"})
    /* renamed from: z1.cax$a */
    /* loaded from: classes3.dex */
    public static final class C4874a extends AbstractIterator<T> {

        /* renamed from: b */
        private int f20506b;

        /* renamed from: c */
        private int f20507c;

        C4874a() {
            this.f20506b = cax.this.size();
            this.f20507c = cax.this.f20502c;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.AbstractIterator
        /* renamed from: a */
        protected void mo4549a() {
            if (this.f20506b == 0) {
                m8324b();
                return;
            }
            m8325a(cax.this.f20501b[this.f20507c]);
            this.f20507c = (this.f20507c + 1) % cax.this.m6360c();
            this.f20506b--;
        }
    }

    @Override // p110z1.AbstractList, p110z1.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<T> iterator() {
        return new C4874a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.AbstractCollection, java.util.Collection
    @NotNull
    public <T> T[] toArray(@NotNull T[] tArr) {
        cji.m5162f(tArr, "array");
        if (tArr.length < size()) {
            tArr = (T[]) Arrays.copyOf(tArr, size());
            cji.m5175b(tArr, "java.util.Arrays.copyOf(this, newSize)");
        }
        int size = size();
        int i = 0;
        int i2 = 0;
        for (int i3 = this.f20502c; i2 < size && i3 < this.f20504e; i3++) {
            tArr[i2] = this.f20501b[i3];
            i2++;
        }
        while (i2 < size) {
            tArr[i2] = this.f20501b[i];
            i2++;
            i++;
        }
        if (tArr.length > size()) {
            tArr[size()] = null;
        }
        if (tArr != null) {
            return tArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.AbstractCollection, java.util.Collection
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    /* renamed from: a */
    public final void m6367a(T t) {
        if (!m6363b()) {
            this.f20501b[(this.f20502c + size()) % m6360c()] = t;
            this.f20503d = size() + 1;
            return;
        }
        throw new IllegalStateException("ring buffer is full");
    }

    /* renamed from: a */
    public final void m6369a(int i) {
        boolean z = true;
        if (i >= 0) {
            if (i > size()) {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = " + i + ", size = " + size()).toString());
            } else if (i > 0) {
                int i2 = this.f20502c;
                int c = (i2 + i) % m6360c();
                if (i2 > c) {
                    bzb.m8132b(this.f20501b, (Object) null, i2, this.f20504e);
                    bzb.m8132b(this.f20501b, (Object) null, 0, c);
                } else {
                    bzb.m8132b(this.f20501b, (Object) null, i2, c);
                }
                this.f20502c = c;
                this.f20503d = size() - i;
            }
        } else {
            throw new IllegalArgumentException(("n shouldn't be negative but it is " + i).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final int m6368a(int i, int i2) {
        return (i + i2) % m6360c();
    }
}
