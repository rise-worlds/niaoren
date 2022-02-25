package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bjc */
/* loaded from: classes3.dex */
public final class ObservableConcatWithCompletable<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final CompletableSource f18967b;

    public ObservableConcatWithCompletable(Observable<T> aslVar, CompletableSource arsVar) {
        super(aslVar);
        this.f18967b = arsVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4415a(assVar, this.f18967b));
    }

    /* compiled from: ObservableConcatWithCompletable.java */
    /* renamed from: z1.bjc$a */
    /* loaded from: classes3.dex */
    static final class C4415a<T> extends AtomicReference<Disposable> implements CompletableObserver, Observer<T>, Disposable {
        private static final long serialVersionUID = -1953724749712440952L;
        final Observer<? super T> downstream;
        boolean inCompletable;
        CompletableSource other;

        C4415a(Observer<? super T> assVar, CompletableSource arsVar) {
            this.downstream = assVar;
            this.other = arsVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar) && !this.inCompletable) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.inCompletable) {
                this.downstream.onComplete();
                return;
            }
            this.inCompletable = true;
            DisposableHelper.replace(this, null);
            CompletableSource arsVar = this.other;
            this.other = null;
            arsVar.mo11309a(this);
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
