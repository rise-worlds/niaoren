package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/sequences/GeneratorSequence;", TessBaseAPI.f9204e, "", "Lkotlin/sequences/Sequence;", "getInitialValue", "Lkotlin/Function0;", "getNextValue", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cny */
/* loaded from: classes3.dex */
public final class cny<T> implements Sequence<T> {

    /* renamed from: a */
    private final chc<T> f20913a;

    /* renamed from: b */
    private final chd<T, T> f20914b;

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\t\u0010\u0010\u001a\u00020\u0011H\u0096\u0002J\u000e\u0010\u0012\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0002\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0007\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, m8860e = {"kotlin/sequences/GeneratorSequence$iterator$1", "", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "nextState", "", "getNextState", "()I", "setNextState", "(I)V", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"})
    /* renamed from: z1.cny$a */
    /* loaded from: classes3.dex */
    public static final class C5017a implements Iterator<T>, KMarkers {
        @dbs

        /* renamed from: b */
        private T f20916b;

        /* renamed from: c */
        private int f20917c = -2;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5017a() {
        }

        @dbs
        /* renamed from: a */
        public final T m4514a() {
            return this.f20916b;
        }

        /* renamed from: a */
        public final void m4512a(@dbs T t) {
            this.f20916b = t;
        }

        /* renamed from: a */
        public final void m4513a(int i) {
            this.f20917c = i;
        }

        /* renamed from: b */
        public final int m4511b() {
            return this.f20917c;
        }

        /* renamed from: c */
        private final void m4510c() {
            T t;
            if (this.f20917c == -2) {
                t = (T) cny.this.f20913a.invoke();
            } else {
                chd chdVar = cny.this.f20914b;
                T t2 = this.f20916b;
                if (t2 == null) {
                    cji.m5196a();
                }
                t = (T) chdVar.invoke(t2);
            }
            this.f20916b = t;
            this.f20917c = this.f20916b == null ? 0 : 1;
        }

        @Override // java.util.Iterator
        @NotNull
        public T next() {
            if (this.f20917c < 0) {
                m4510c();
            }
            if (this.f20917c != 0) {
                T t = this.f20916b;
                if (t != null) {
                    this.f20917c = -1;
                    return t;
                }
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f20917c < 0) {
                m4510c();
            }
            return this.f20917c == 1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public cny(@NotNull chc<? extends T> chcVar, @NotNull chd<? super T, ? extends T> chdVar) {
        cji.m5162f(chcVar, "getInitialValue");
        cji.m5162f(chdVar, "getNextValue");
        this.f20913a = chcVar;
        this.f20914b = chdVar;
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new C5017a();
    }
}
