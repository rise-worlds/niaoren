package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhv */
/* loaded from: classes3.dex */
public final class MaybeFlatMapObservable<T, R> extends Observable<R> {

    /* renamed from: a */
    final MaybeSource<T> f18770a;

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends R>> f18771b;

    public MaybeFlatMapObservable(MaybeSource<T> asiVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        this.f18770a = asiVar;
        this.f18771b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        C4361a aVar = new C4361a(assVar, this.f18771b);
        assVar.onSubscribe(aVar);
        this.f18770a.mo10646a(aVar);
    }

    /* compiled from: MaybeFlatMapObservable.java */
    /* renamed from: z1.bhv$a */
    /* loaded from: classes3.dex */
    static final class C4361a<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Observer<R>, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

        C4361a(Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
            this.downstream = assVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Observer
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
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

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
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
