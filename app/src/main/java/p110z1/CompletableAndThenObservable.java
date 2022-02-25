package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhm */
/* loaded from: classes3.dex */
public final class CompletableAndThenObservable<R> extends Observable<R> {

    /* renamed from: a */
    final CompletableSource f18735a;

    /* renamed from: b */
    final ObservableSource<? extends R> f18736b;

    public CompletableAndThenObservable(CompletableSource arsVar, ObservableSource<? extends R> asqVar) {
        this.f18735a = arsVar;
        this.f18736b = asqVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        C4347a aVar = new C4347a(assVar, this.f18736b);
        assVar.onSubscribe(aVar);
        this.f18735a.mo11309a(aVar);
    }

    /* compiled from: CompletableAndThenObservable.java */
    /* renamed from: z1.bhm$a */
    /* loaded from: classes3.dex */
    static final class C4347a<R> extends AtomicReference<Disposable> implements CompletableObserver, Observer<R>, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        ObservableSource<? extends R> other;

        C4347a(Observer<? super R> assVar, ObservableSource<? extends R> asqVar) {
            this.other = asqVar;
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            ObservableSource<? extends R> asqVar = this.other;
            if (asqVar == null) {
                this.downstream.onComplete();
                return;
            }
            this.other = null;
            asqVar.subscribe(this);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }
    }
}
