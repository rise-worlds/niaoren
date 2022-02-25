package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgq */
/* loaded from: classes3.dex */
public final class MaybeObserveOn<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f18673b;

    public MaybeObserveOn(MaybeSource<T> asiVar, Scheduler astVar) {
        super(asiVar);
        this.f18673b = astVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new RunnableC4314a(asfVar, this.f18673b));
    }

    /* compiled from: MaybeObserveOn.java */
    /* renamed from: z1.bgq$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4314a<T> extends AtomicReference<Disposable> implements Runnable, MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 8571289934935992137L;
        final MaybeObserver<? super T> downstream;
        Throwable error;
        final Scheduler scheduler;
        T value;

        RunnableC4314a(MaybeObserver<? super T> asfVar, Scheduler astVar) {
            this.downstream = asfVar;
            this.scheduler = astVar;
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
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.value = t;
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.error = null;
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.value = null;
                this.downstream.onSuccess(t);
                return;
            }
            this.downstream.onComplete();
        }
    }
}
