package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.blf */
/* loaded from: classes3.dex */
public final class ObservableMergeWithMaybe<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final MaybeSource<? extends T> f19237b;

    public ObservableMergeWithMaybe(Observable<T> aslVar, MaybeSource<? extends T> asiVar) {
        super(aslVar);
        this.f19237b = asiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4496a aVar = new C4496a(assVar);
        assVar.onSubscribe(aVar);
        this.f18807a.subscribe(aVar);
        this.f19237b.mo10646a(aVar.otherObserver);
    }

    /* compiled from: ObservableMergeWithMaybe.java */
    /* renamed from: z1.blf$a */
    /* loaded from: classes3.dex */
    static final class C4496a<T> extends AtomicInteger implements Observer<T>, Disposable {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        volatile boolean mainDone;
        volatile int otherState;
        volatile SimplePlainQueue<T> queue;
        T singleItem;
        final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
        final C4497a<T> otherObserver = new C4497a<>(this);
        final AtomicThrowable error = new AtomicThrowable();

        C4496a(Observer<? super T> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.mainDisposable, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                getOrCreateQueue().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.mainDisposable);
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.mainDisposable.get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        void otherSuccess(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                this.otherState = 2;
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        void otherError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.mainDisposable);
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void otherComplete() {
            this.otherState = 2;
            drain();
        }

        SimplePlainQueue<T> getOrCreateQueue() {
            SimplePlainQueue<T> avvVar = this.queue;
            if (avvVar != null) {
                return avvVar;
            }
            SpscLinkedArrayQueue bqlVar = new SpscLinkedArrayQueue(Observable.m10338d());
            this.queue = bqlVar;
            return bqlVar;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            Observer<? super T> assVar = this.downstream;
            int i = 1;
            while (!this.disposed) {
                if (this.error.get() != null) {
                    this.singleItem = null;
                    this.queue = null;
                    assVar.onError(this.error.terminate());
                    return;
                }
                int i2 = this.otherState;
                if (i2 == 1) {
                    this.singleItem = null;
                    this.otherState = 2;
                    assVar.onNext((T) this.singleItem);
                    i2 = 2;
                }
                boolean z = this.mainDone;
                SimplePlainQueue<T> avvVar = this.queue;
                T poll = avvVar != null ? avvVar.poll() : (Object) null;
                boolean z2 = poll == null;
                if (z && z2 && i2 == 2) {
                    this.queue = null;
                    assVar.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    assVar.onNext(poll);
                }
            }
            this.singleItem = null;
            this.queue = null;
        }

        /* compiled from: ObservableMergeWithMaybe.java */
        /* renamed from: z1.blf$a$a */
        /* loaded from: classes3.dex */
        static final class C4497a<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final C4496a<T> parent;

            C4497a(C4496a<T> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
