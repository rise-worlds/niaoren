package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bfi */
/* loaded from: classes3.dex */
public final class MaybeDoFinally<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Action f18585b;

    public MaybeDoFinally(MaybeSource<T> asiVar, Action augVar) {
        super(asiVar);
        this.f18585b = augVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4281a(asfVar, this.f18585b));
    }

    /* compiled from: MaybeDoFinally.java */
    /* renamed from: z1.bfi$a */
    /* loaded from: classes3.dex */
    static final class C4281a<T> extends AtomicInteger implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final MaybeObserver<? super T> downstream;
        final Action onFinally;
        Disposable upstream;

        C4281a(MaybeObserver<? super T> asfVar, Action augVar) {
            this.downstream = asfVar;
            this.onFinally = augVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
            runFinally();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
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
