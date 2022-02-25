package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

/* renamed from: z1.bpq */
/* loaded from: classes3.dex */
public final class SingleInternalHelper {
    private SingleInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleInternalHelper.java */
    /* renamed from: z1.bpq$a */
    /* loaded from: classes3.dex */
    public enum EnumC4686a implements Callable<NoSuchElementException> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public NoSuchElementException call() throws Exception {
            return new NoSuchElementException();
        }
    }

    /* renamed from: a */
    public static <T> Callable<NoSuchElementException> m9545a() {
        return EnumC4686a.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleInternalHelper.java */
    /* renamed from: z1.bpq$b */
    /* loaded from: classes3.dex */
    public enum EnumC4687b implements Function<SingleSource, Publisher> {
        INSTANCE;

        public Publisher apply(SingleSource ataVar) {
            return new SingleToFlowable(ataVar);
        }
    }

    /* renamed from: b */
    public static <T> Function<SingleSource<? extends T>, Publisher<? extends T>> m9543b() {
        return EnumC4687b.INSTANCE;
    }

    /* compiled from: SingleInternalHelper.java */
    /* renamed from: z1.bpq$d */
    /* loaded from: classes3.dex */
    static final class C4689d<T> implements Iterator<Flowable<T>> {

        /* renamed from: a */
        private final Iterator<? extends SingleSource<? extends T>> f19805a;

        C4689d(Iterator<? extends SingleSource<? extends T>> it) {
            this.f19805a = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f19805a.hasNext();
        }

        /* renamed from: a */
        public Flowable<T> next() {
            return new SingleToFlowable((SingleSource) this.f19805a.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: SingleInternalHelper.java */
    /* renamed from: z1.bpq$c */
    /* loaded from: classes3.dex */
    static final class C4688c<T> implements Iterable<Flowable<T>> {

        /* renamed from: a */
        private final Iterable<? extends SingleSource<? extends T>> f19804a;

        C4688c(Iterable<? extends SingleSource<? extends T>> iterable) {
            this.f19804a = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<Flowable<T>> iterator() {
            return new C4689d(this.f19804a.iterator());
        }
    }

    /* renamed from: a */
    public static <T> Iterable<? extends Flowable<T>> m9544a(Iterable<? extends SingleSource<? extends T>> iterable) {
        return new C4688c(iterable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleInternalHelper.java */
    /* renamed from: z1.bpq$e */
    /* loaded from: classes3.dex */
    public enum EnumC4690e implements Function<SingleSource, Observable> {
        INSTANCE;

        public Observable apply(SingleSource ataVar) {
            return new SingleToObservable(ataVar);
        }
    }

    /* renamed from: c */
    public static <T> Function<SingleSource<? extends T>, Observable<? extends T>> m9542c() {
        return EnumC4690e.INSTANCE;
    }
}
