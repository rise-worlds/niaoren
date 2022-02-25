package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.biw */
/* loaded from: classes3.dex */
public final class ObservableCache<T> extends AbstractObservableWithUpstream<T, T> implements Observer<T> {

    /* renamed from: e */
    static final C4403a[] f18925e = new C4403a[0];

    /* renamed from: f */
    static final C4403a[] f18926f = new C4403a[0];

    /* renamed from: c */
    final int f18928c;

    /* renamed from: g */
    volatile long f18930g;

    /* renamed from: h */
    final C4404b<T> f18931h;

    /* renamed from: i */
    C4404b<T> f18932i;

    /* renamed from: j */
    int f18933j;

    /* renamed from: k */
    Throwable f18934k;

    /* renamed from: l */
    volatile boolean f18935l;

    /* renamed from: b */
    final AtomicBoolean f18927b = new AtomicBoolean();

    /* renamed from: d */
    final AtomicReference<C4403a<T>[]> f18929d = new AtomicReference<>(f18925e);

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
    }

    public ObservableCache(Observable<T> aslVar, int i) {
        super(aslVar);
        this.f18928c = i;
        C4404b<T> bVar = new C4404b<>(i);
        this.f18931h = bVar;
        this.f18932i = bVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4403a<T> aVar = new C4403a<>(assVar, this);
        assVar.onSubscribe(aVar);
        m9645a((C4403a) aVar);
        if (this.f18927b.get() || !this.f18927b.compareAndSet(false, true)) {
            m9641c((C4403a) aVar);
        } else {
            this.f18807a.subscribe(this);
        }
    }

    /* renamed from: b */
    boolean m9644b() {
        return this.f18927b.get();
    }

    /* renamed from: c */
    boolean m9642c() {
        return this.f18929d.get().length != 0;
    }

    /* renamed from: R */
    long m9646R() {
        return this.f18930g;
    }

    /* renamed from: a */
    void m9645a(C4403a<T> aVar) {
        C4403a<T>[] aVarArr;
        C4403a<T>[] aVarArr2;
        do {
            aVarArr = this.f18929d.get();
            if (aVarArr != f18926f) {
                int length = aVarArr.length;
                aVarArr2 = new C4403a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } else {
                return;
            }
        } while (!this.f18929d.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: b */
    void m9643b(C4403a<T> aVar) {
        C4403a<T>[] aVarArr;
        C4403a<T>[] aVarArr2;
        do {
            aVarArr = this.f18929d.get();
            int length = aVarArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        aVarArr2 = f18925e;
                    } else {
                        C4403a<T>[] aVarArr3 = new C4403a[length - 1];
                        System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                        System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                        aVarArr2 = aVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.f18929d.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: c */
    void m9641c(C4403a<T> aVar) {
        if (aVar.getAndIncrement() == 0) {
            long j = aVar.index;
            int i = aVar.offset;
            C4404b<T> bVar = aVar.node;
            Observer<? super T> assVar = aVar.downstream;
            int i2 = this.f18928c;
            int i3 = 1;
            while (!aVar.disposed) {
                boolean z = this.f18935l;
                boolean z2 = this.f18930g == j;
                if (z && z2) {
                    aVar.node = null;
                    Throwable th = this.f18934k;
                    if (th != null) {
                        assVar.onError(th);
                        return;
                    } else {
                        assVar.onComplete();
                        return;
                    }
                } else if (!z2) {
                    if (i == i2) {
                        bVar = bVar.f18937b;
                        i = 0;
                    }
                    assVar.onNext((Object) bVar.f18936a[i]);
                    i++;
                    j++;
                } else {
                    aVar.index = j;
                    aVar.offset = i;
                    aVar.node = bVar;
                    i3 = aVar.addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                }
            }
            aVar.node = null;
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        int i = this.f18933j;
        if (i == this.f18928c) {
            C4404b<T> bVar = new C4404b<>(i);
            bVar.f18936a[0] = t;
            this.f18933j = 1;
            this.f18932i.f18937b = bVar;
            this.f18932i = bVar;
        } else {
            this.f18932i.f18936a[i] = t;
            this.f18933j = i + 1;
        }
        this.f18930g++;
        for (C4403a<T> aVar : this.f18929d.get()) {
            m9641c((C4403a) aVar);
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        this.f18934k = th;
        this.f18935l = true;
        for (C4403a<T> aVar : this.f18929d.getAndSet(f18926f)) {
            m9641c((C4403a) aVar);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        this.f18935l = true;
        for (C4403a<T> aVar : this.f18929d.getAndSet(f18926f)) {
            m9641c((C4403a) aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableCache.java */
    /* renamed from: z1.biw$a */
    /* loaded from: classes3.dex */
    public static final class C4403a<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 6770240836423125754L;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        long index;
        C4404b<T> node;
        int offset;
        final ObservableCache<T> parent;

        C4403a(Observer<? super T> assVar, ObservableCache<T> biwVar) {
            this.downstream = assVar;
            this.parent = biwVar;
            this.node = biwVar.f18931h;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.parent.m9643b((C4403a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableCache.java */
    /* renamed from: z1.biw$b */
    /* loaded from: classes3.dex */
    public static final class C4404b<T> {

        /* renamed from: a */
        final T[] f18936a;

        /* renamed from: b */
        volatile C4404b<T> f18937b;

        C4404b(int i) {
            this.f18936a = (T[]) new Object[i];
        }
    }
}
