package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.box */
/* loaded from: classes3.dex */
public final class SingleDoFinally<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19742a;

    /* renamed from: b */
    final Action f19743b;

    public SingleDoFinally(SingleSource<T> ataVar, Action augVar) {
        this.f19742a = ataVar;
        this.f19743b = augVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19742a.mo10018a(new C4667a(asxVar, this.f19743b));
    }

    /* compiled from: SingleDoFinally.java */
    /* renamed from: z1.box$a */
    /* loaded from: classes3.dex */
    static final class C4667a<T> extends AtomicInteger implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final SingleObserver<? super T> downstream;
        final Action onFinally;
        Disposable upstream;

        C4667a(SingleObserver<? super T> asxVar, Action augVar) {
            this.downstream = asxVar;
            this.onFinally = augVar;
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
            runFinally();
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
