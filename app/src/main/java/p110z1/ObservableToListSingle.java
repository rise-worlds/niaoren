package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bng */
/* loaded from: classes3.dex */
public final class ObservableToListSingle<T, U extends Collection<? super T>> extends Single<U> implements FuseToObservable<U> {

    /* renamed from: a */
    final ObservableSource<T> f19471a;

    /* renamed from: b */
    final Callable<U> f19472b;

    public ObservableToListSingle(ObservableSource<T> asqVar, int i) {
        this.f19471a = asqVar;
        this.f19472b = Functions.m9934a(i);
    }

    public ObservableToListSingle(ObservableSource<T> asqVar, Callable<U> callable) {
        this.f19471a = asqVar;
        this.f19472b = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super U> asxVar) {
        try {
            this.f19471a.subscribe(new C4590a(asxVar, (Collection) ObjectHelper.m9873a(this.f19472b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<U> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableToList(this.f19471a, this.f19472b));
    }

    /* compiled from: ObservableToListSingle.java */
    /* renamed from: z1.bng$a */
    /* loaded from: classes3.dex */
    static final class C4590a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super U> f19473a;

        /* renamed from: b */
        U f19474b;

        /* renamed from: c */
        Disposable f19475c;

        C4590a(SingleObserver<? super U> asxVar, U u) {
            this.f19473a = asxVar;
            this.f19474b = u;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19475c, atrVar)) {
                this.f19475c = atrVar;
                this.f19473a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19475c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19475c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19474b.add(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19474b = null;
            this.f19473a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            U u = this.f19474b;
            this.f19474b = null;
            this.f19473a.onSuccess(u);
        }
    }
}
