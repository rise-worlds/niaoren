package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpw */
/* loaded from: classes3.dex */
public final class SingleObserveOn<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19815a;

    /* renamed from: b */
    final Scheduler f19816b;

    public SingleObserveOn(SingleSource<T> ataVar, Scheduler astVar) {
        this.f19815a = ataVar;
        this.f19816b = astVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19815a.mo10018a(new RunnableC4692a(asxVar, this.f19816b));
    }

    /* compiled from: SingleObserveOn.java */
    /* renamed from: z1.bpw$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4692a<T> extends AtomicReference<Disposable> implements Runnable, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 3528003840217436037L;
        final SingleObserver<? super T> downstream;
        Throwable error;
        final Scheduler scheduler;
        T value;

        RunnableC4692a(SingleObserver<? super T> asxVar, Scheduler astVar) {
            this.downstream = asxVar;
            this.scheduler = astVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.value = t;
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onSuccess((T) this.value);
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
