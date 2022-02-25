package p110z1;

import java.util.Iterator;

/* renamed from: z1.bkk */
/* loaded from: classes3.dex */
public final class ObservableFromIterable<T> extends Observable<T> {

    /* renamed from: a */
    final Iterable<? extends T> f19135a;

    public ObservableFromIterable(Iterable<? extends T> iterable) {
        this.f19135a = iterable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        try {
            Iterator<? extends T> it = this.f19135a.iterator();
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete(assVar);
                    return;
                }
                C4456a aVar = new C4456a(assVar, it);
                assVar.onSubscribe(aVar);
                if (!aVar.f19139i) {
                    aVar.m9626a();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, assVar);
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            EmptyDisposable.error(th2, assVar);
        }
    }

    /* compiled from: ObservableFromIterable.java */
    /* renamed from: z1.bkk$a */
    /* loaded from: classes3.dex */
    static final class C4456a<T> extends BasicQueueDisposable<T> {

        /* renamed from: f */
        final Observer<? super T> f19136f;

        /* renamed from: g */
        final Iterator<? extends T> f19137g;

        /* renamed from: h */
        volatile boolean f19138h;

        /* renamed from: i */
        boolean f19139i;

        /* renamed from: j */
        boolean f19140j;

        /* renamed from: k */
        boolean f19141k;

        C4456a(Observer<? super T> assVar, Iterator<? extends T> it) {
            this.f19136f = assVar;
            this.f19137g = it;
        }

        /* renamed from: a */
        void m9626a() {
            while (!isDisposed()) {
                try {
                    this.f19136f.onNext(ObjectHelper.m9873a(this.f19137g.next(), "The iterator returned a null value"));
                    if (!isDisposed()) {
                        try {
                            if (!this.f19137g.hasNext()) {
                                if (!isDisposed()) {
                                    this.f19136f.onComplete();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            this.f19136f.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.f19136f.onError(th2);
                    return;
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f19139i = true;
            return 1;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() {
            if (this.f19140j) {
                return null;
            }
            if (!this.f19141k) {
                this.f19141k = true;
            } else if (!this.f19137g.hasNext()) {
                this.f19140j = true;
                return null;
            }
            return (T) ObjectHelper.m9873a(this.f19137g.next(), "The iterator returned a null value");
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f19140j;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f19140j = true;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19138h = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19138h;
        }
    }
}
