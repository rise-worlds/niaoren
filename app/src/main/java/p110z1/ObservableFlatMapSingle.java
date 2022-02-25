package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bkf */
/* loaded from: classes3.dex */
public final class ObservableFlatMapSingle<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends SingleSource<? extends R>> f19119b;

    /* renamed from: c */
    final boolean f19120c;

    public ObservableFlatMapSingle(ObservableSource<T> asqVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        super(asqVar);
        this.f19119b = aunVar;
        this.f19120c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        this.f18807a.subscribe(new C4452a(assVar, this.f19119b, this.f19120c));
    }

    /* compiled from: ObservableFlatMapSingle.java */
    /* renamed from: z1.bkf$a */
    /* loaded from: classes3.dex */
    static final class C4452a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8600231336733376951L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        Disposable upstream;
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        C4452a(Observer<? super R> assVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.delayErrors = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            try {
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                this.active.getAndIncrement();
                C4453a aVar = new C4453a();
                if (!this.cancelled && this.set.mo9939a(aVar)) {
                    ataVar.mo10018a(aVar);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerSuccess(C4452a<T, R>.C4453a aVar, R r) {
            this.set.mo9936c(aVar);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    this.downstream.onNext(r);
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    SpscLinkedArrayQueue<R> bqlVar = this.queue.get();
                    if (!z || (bqlVar != null && !bqlVar.isEmpty())) {
                        if (decrementAndGet() == 0) {
                            return;
                        }
                        drainLoop();
                    }
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null) {
                        this.downstream.onError(terminate);
                        return;
                    } else {
                        this.downstream.onComplete();
                        return;
                    }
                }
            }
            SpscLinkedArrayQueue<R> orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> bqlVar;
            do {
                SpscLinkedArrayQueue<R> bqlVar2 = this.queue.get();
                if (bqlVar2 != null) {
                    return bqlVar2;
                }
                bqlVar = new SpscLinkedArrayQueue<>(Observable.m10338d());
            } while (!this.queue.compareAndSet(null, bqlVar));
            return bqlVar;
        }

        void innerError(C4452a<T, R>.C4453a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> bqlVar = this.queue.get();
            if (bqlVar != null) {
                bqlVar.clear();
            }
        }

        void drainLoop() {
            Observer<? super R> assVar = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
            int i = 1;
            while (!this.cancelled) {
                if (this.delayErrors || this.errors.get() == null) {
                    boolean z = false;
                    boolean z2 = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> bqlVar = atomicReference.get();
                    R poll = bqlVar != null ? bqlVar.poll() : (Object) null;
                    if (poll == null) {
                        z = true;
                    }
                    if (z2 && z) {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            assVar.onError(terminate);
                            return;
                        } else {
                            assVar.onComplete();
                            return;
                        }
                    } else if (z) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        assVar.onNext(poll);
                    }
                } else {
                    Throwable terminate2 = this.errors.terminate();
                    clear();
                    assVar.onError(terminate2);
                    return;
                }
            }
            clear();
        }

        /* compiled from: ObservableFlatMapSingle.java */
        /* renamed from: z1.bkf$a$a */
        /* loaded from: classes3.dex */
        final class C4453a extends AtomicReference<Disposable> implements SingleObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            C4453a() {
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                C4452a.this.innerSuccess(this, r);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                C4452a.this.innerError(this, th);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
