package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bie */
/* loaded from: classes3.dex */
public final class SingleFlatMapObservable<T, R> extends Observable<R> {

    /* renamed from: a */
    final SingleSource<T> f18805a;

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends R>> f18806b;

    public SingleFlatMapObservable(SingleSource<T> ataVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        this.f18805a = ataVar;
        this.f18806b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        C4376a aVar = new C4376a(assVar, this.f18806b);
        assVar.onSubscribe(aVar);
        this.f18805a.mo10018a(aVar);
    }

    /* compiled from: SingleFlatMapObservable.java */
    /* renamed from: z1.bie$a */
    /* loaded from: classes3.dex */
    static final class C4376a<T, R> extends AtomicReference<Disposable> implements Observer<R>, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

        C4376a(Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
            this.downstream = assVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Observer
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                ((ObservableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }
    }
}
