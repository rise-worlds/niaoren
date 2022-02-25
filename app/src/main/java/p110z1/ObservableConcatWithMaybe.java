package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bjd */
/* loaded from: classes3.dex */
public final class ObservableConcatWithMaybe<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final MaybeSource<? extends T> f18968b;

    public ObservableConcatWithMaybe(Observable<T> aslVar, MaybeSource<? extends T> asiVar) {
        super(aslVar);
        this.f18968b = asiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4416a(assVar, this.f18968b));
    }

    /* compiled from: ObservableConcatWithMaybe.java */
    /* renamed from: z1.bjd$a */
    /* loaded from: classes3.dex */
    static final class C4416a<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Observer<T>, Disposable {
        private static final long serialVersionUID = -1953724749712440952L;
        final Observer<? super T> downstream;
        boolean inMaybe;
        MaybeSource<? extends T> other;

        C4416a(Observer<? super T> assVar, MaybeSource<? extends T> asiVar) {
            this.downstream = assVar;
            this.other = asiVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar) && !this.inMaybe) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onNext(t);
            this.downstream.onComplete();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            if (this.inMaybe) {
                this.downstream.onComplete();
                return;
            }
            this.inMaybe = true;
            DisposableHelper.replace(this, null);
            MaybeSource<? extends T> asiVar = this.other;
            this.other = null;
            asiVar.mo10646a(this);
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
