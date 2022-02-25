package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bkd */
/* loaded from: classes3.dex */
public final class ObservableFlatMapCompletableCompletable<T> extends Completable implements FuseToObservable<T> {

    /* renamed from: a */
    final ObservableSource<T> f19114a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f19115b;

    /* renamed from: c */
    final boolean f19116c;

    public ObservableFlatMapCompletableCompletable(ObservableSource<T> asqVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        this.f19114a = asqVar;
        this.f19115b = aunVar;
        this.f19116c = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f19114a.subscribe(new C4448a(arpVar, this.f19115b, this.f19116c));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<T> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableFlatMapCompletable(this.f19114a, this.f19115b, this.f19116c));
    }

    /* compiled from: ObservableFlatMapCompletableCompletable.java */
    /* renamed from: z1.bkd$a */
    /* loaded from: classes3.dex */
    static final class C4448a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8443155186132538303L;
        final boolean delayErrors;
        volatile boolean disposed;
        final CompletableObserver downstream;
        final Function<? super T, ? extends CompletableSource> mapper;
        Disposable upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final CompositeDisposable set = new CompositeDisposable();

        C4448a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
            this.downstream = arpVar;
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
                C4449a aVar = new C4449a();
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

        void innerComplete(C4448a<T>.C4449a aVar) {
            this.set.mo9936c(aVar);
            onComplete();
        }

        void innerError(C4448a<T>.C4449a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            onError(th);
        }

        /* compiled from: ObservableFlatMapCompletableCompletable.java */
        /* renamed from: z1.bkd$a$a */
        /* loaded from: classes3.dex */
        final class C4449a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            C4449a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                C4448a.this.innerComplete(this);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4448a.this.innerError(this, th);
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
