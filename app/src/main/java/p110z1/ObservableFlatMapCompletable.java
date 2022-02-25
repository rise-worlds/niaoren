package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bkc */
/* loaded from: classes3.dex */
public final class ObservableFlatMapCompletable<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f19112b;

    /* renamed from: c */
    final boolean f19113c;

    public ObservableFlatMapCompletable(ObservableSource<T> asqVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        super(asqVar);
        this.f19112b = aunVar;
        this.f19113c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4446a(assVar, this.f19112b, this.f19113c));
    }

    /* compiled from: ObservableFlatMapCompletable.java */
    /* renamed from: z1.bkc$a */
    /* loaded from: classes3.dex */
    static final class C4446a<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        final boolean delayErrors;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        final Function<? super T, ? extends CompletableSource> mapper;
        Disposable upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final CompositeDisposable set = new CompositeDisposable();

        @Override // p110z1.SimpleQueue
        public void clear() {
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return true;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            return null;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return i & 2;
        }

        C4446a(Observer<? super T> assVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.delayErrors = z;
            lazySet(1);
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
                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                getAndIncrement();
                C4447a aVar = new C4447a();
                if (!this.disposed && this.set.mo9939a(aVar)) {
                    arsVar.mo11309a(aVar);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (!this.delayErrors) {
                dispose();
                if (getAndSet(0) > 0) {
                    this.downstream.onError(this.errors.terminate());
                }
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.errors.terminate());
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable terminate = this.errors.terminate();
                if (terminate != null) {
                    this.downstream.onError(terminate);
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        void innerComplete(C4446a<T>.C4447a aVar) {
            this.set.mo9936c(aVar);
            onComplete();
        }

        void innerError(C4446a<T>.C4447a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            onError(th);
        }

        /* compiled from: ObservableFlatMapCompletable.java */
        /* renamed from: z1.bkc$a$a */
        /* loaded from: classes3.dex */
        final class C4447a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            C4447a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                C4446a.this.innerComplete(this);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4446a.this.innerError(this, th);
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }
        }
    }
}
