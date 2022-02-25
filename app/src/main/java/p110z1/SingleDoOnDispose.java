package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.boy */
/* loaded from: classes3.dex */
public final class SingleDoOnDispose<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19744a;

    /* renamed from: b */
    final Action f19745b;

    public SingleDoOnDispose(SingleSource<T> ataVar, Action augVar) {
        this.f19744a = ataVar;
        this.f19745b = augVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19744a.mo10018a(new C4668a(asxVar, this.f19745b));
    }

    /* compiled from: SingleDoOnDispose.java */
    /* renamed from: z1.boy$a */
    /* loaded from: classes3.dex */
    static final class C4668a<T> extends AtomicReference<Action> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -8583764624474935784L;
        final SingleObserver<? super T> downstream;
        Disposable upstream;

        C4668a(SingleObserver<? super T> asxVar, Action augVar) {
            this.downstream = asxVar;
            lazySet(augVar);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Action andSet = getAndSet(null);
            if (andSet != null) {
                try {
                    andSet.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
                this.upstream.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
