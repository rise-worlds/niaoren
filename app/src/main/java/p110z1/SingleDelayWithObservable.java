package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.boq */
/* loaded from: classes3.dex */
public final class SingleDelayWithObservable<T, U> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19718a;

    /* renamed from: b */
    final ObservableSource<U> f19719b;

    public SingleDelayWithObservable(SingleSource<T> ataVar, ObservableSource<U> asqVar) {
        this.f19718a = ataVar;
        this.f19719b = asqVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19719b.subscribe(new C4660a(asxVar, this.f19718a));
    }

    /* compiled from: SingleDelayWithObservable.java */
    /* renamed from: z1.boq$a */
    /* loaded from: classes3.dex */
    static final class C4660a<T, U> extends AtomicReference<Disposable> implements Observer<U>, Disposable {
        private static final long serialVersionUID = -8565274649390031272L;
        boolean done;
        final SingleObserver<? super T> downstream;
        final SingleSource<T> source;

        C4660a(SingleObserver<? super T> asxVar, SingleSource<T> ataVar) {
            this.downstream = asxVar;
            this.source = ataVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.set(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(U u) {
            get().dispose();
            onComplete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.source.mo10018a(new ResumeSingleObserver(this, this.downstream));
            }
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
