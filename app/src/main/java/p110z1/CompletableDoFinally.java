package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.axj */
/* loaded from: classes3.dex */
public final class CompletableDoFinally extends Completable {

    /* renamed from: a */
    final CompletableSource f17713a;

    /* renamed from: b */
    final Action f17714b;

    public CompletableDoFinally(CompletableSource arsVar, Action augVar) {
        this.f17713a = arsVar;
        this.f17714b = augVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17713a.mo11309a(new C3952a(arpVar, this.f17714b));
    }

    /* compiled from: CompletableDoFinally.java */
    /* renamed from: z1.axj$a */
    /* loaded from: classes3.dex */
    static final class C3952a extends AtomicInteger implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final CompletableObserver downstream;
        final Action onFinally;
        Disposable upstream;

        C3952a(CompletableObserver arpVar, Action augVar) {
            this.downstream = arpVar;
            this.onFinally = augVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
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
