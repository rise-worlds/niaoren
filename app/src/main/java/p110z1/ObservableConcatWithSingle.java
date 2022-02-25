package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bje */
/* loaded from: classes3.dex */
public final class ObservableConcatWithSingle<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final SingleSource<? extends T> f18969b;

    public ObservableConcatWithSingle(Observable<T> aslVar, SingleSource<? extends T> ataVar) {
        super(aslVar);
        this.f18969b = ataVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4417a(assVar, this.f18969b));
    }

    /* compiled from: ObservableConcatWithSingle.java */
    /* renamed from: z1.bje$a */
    /* loaded from: classes3.dex */
    static final class C4417a<T> extends AtomicReference<Disposable> implements Observer<T>, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -1953724749712440952L;
        final Observer<? super T> downstream;
        boolean inSingle;
        SingleSource<? extends T> other;

        C4417a(Observer<? super T> assVar, SingleSource<? extends T> ataVar) {
            this.downstream = assVar;
            this.other = ataVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar) && !this.inSingle) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onNext(t);
            this.downstream.onComplete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.inSingle = true;
            DisposableHelper.replace(this, null);
            SingleSource<? extends T> ataVar = this.other;
            this.other = null;
            ataVar.mo10018a(this);
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
