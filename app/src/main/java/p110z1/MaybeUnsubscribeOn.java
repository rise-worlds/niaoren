package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhi */
/* loaded from: classes3.dex */
public final class MaybeUnsubscribeOn<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f18723b;

    public MaybeUnsubscribeOn(MaybeSource<T> asiVar, Scheduler astVar) {
        super(asiVar);
        this.f18723b = astVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new RunnableC4341a(asfVar, this.f18723b));
    }

    /* compiled from: MaybeUnsubscribeOn.java */
    /* renamed from: z1.bhi$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4341a<T> extends AtomicReference<Disposable> implements Runnable, MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 3256698449646456986L;
        final MaybeObserver<? super T> downstream;

        /* renamed from: ds */
        Disposable f18724ds;
        final Scheduler scheduler;

        RunnableC4341a(MaybeObserver<? super T> asfVar, Scheduler astVar) {
            this.downstream = asfVar;
            this.scheduler = astVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable andSet = getAndSet(DisposableHelper.DISPOSED);
            if (andSet != DisposableHelper.DISPOSED) {
                this.f18724ds = andSet;
                this.scheduler.mo9481a(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18724ds.dispose();
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
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
