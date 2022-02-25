package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.biy */
/* loaded from: classes3.dex */
public final class ObservableCollectSingle<T, U> extends Single<U> implements FuseToObservable<U> {

    /* renamed from: a */
    final ObservableSource<T> f18945a;

    /* renamed from: b */
    final Callable<? extends U> f18946b;

    /* renamed from: c */
    final BiConsumer<? super U, ? super T> f18947c;

    public ObservableCollectSingle(ObservableSource<T> asqVar, Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        this.f18945a = asqVar;
        this.f18946b = callable;
        this.f18947c = auhVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super U> asxVar) {
        try {
            this.f18945a.subscribe(new C4406a(asxVar, ObjectHelper.m9873a(this.f18946b.call(), "The initialSupplier returned a null value"), this.f18947c));
        } catch (Throwable th) {
            EmptyDisposable.error(th, asxVar);
        }
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<U> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableCollect(this.f18945a, this.f18946b, this.f18947c));
    }

    /* compiled from: ObservableCollectSingle.java */
    /* renamed from: z1.biy$a */
    /* loaded from: classes3.dex */
    static final class C4406a<T, U> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super U> f18948a;

        /* renamed from: b */
        final BiConsumer<? super U, ? super T> f18949b;

        /* renamed from: c */
        final U f18950c;

        /* renamed from: d */
        Disposable f18951d;

        /* renamed from: e */
        boolean f18952e;

        C4406a(SingleObserver<? super U> asxVar, U u, BiConsumer<? super U, ? super T> auhVar) {
            this.f18948a = asxVar;
            this.f18949b = auhVar;
            this.f18950c = u;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18951d, atrVar)) {
                this.f18951d = atrVar;
                this.f18948a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18951d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18951d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18952e) {
                try {
                    this.f18949b.mo9895a((U) this.f18950c, t);
                } catch (Throwable th) {
                    this.f18951d.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18952e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18952e = true;
            this.f18948a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18952e) {
                this.f18952e = true;
                this.f18948a.onSuccess((U) this.f18950c);
            }
        }
    }
}
