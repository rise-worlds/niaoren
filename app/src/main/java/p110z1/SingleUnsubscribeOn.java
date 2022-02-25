package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqf */
/* loaded from: classes3.dex */
public final class SingleUnsubscribeOn<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19838a;

    /* renamed from: b */
    final Scheduler f19839b;

    public SingleUnsubscribeOn(SingleSource<T> ataVar, Scheduler astVar) {
        this.f19838a = ataVar;
        this.f19839b = astVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19838a.mo10018a(new RunnableC4704a(asxVar, this.f19839b));
    }

    /* compiled from: SingleUnsubscribeOn.java */
    /* renamed from: z1.bqf$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4704a<T> extends AtomicReference<Disposable> implements Runnable, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 3256698449646456986L;
        final SingleObserver<? super T> downstream;

        /* renamed from: ds */
        Disposable f19840ds;
        final Scheduler scheduler;

        RunnableC4704a(SingleObserver<? super T> asxVar, Scheduler astVar) {
            this.downstream = asxVar;
            this.scheduler = astVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable andSet = getAndSet(DisposableHelper.DISPOSED);
            if (andSet != DisposableHelper.DISPOSED) {
                this.f19840ds = andSet;
                this.scheduler.mo9481a(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f19840ds.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
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
