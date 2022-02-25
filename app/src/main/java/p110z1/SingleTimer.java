package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqc */
/* loaded from: classes3.dex */
public final class SingleTimer extends Single<Long> {

    /* renamed from: a */
    final long f19833a;

    /* renamed from: b */
    final TimeUnit f19834b;

    /* renamed from: c */
    final Scheduler f19835c;

    public SingleTimer(long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f19833a = j;
        this.f19834b = timeUnit;
        this.f19835c = astVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Long> asxVar) {
        RunnableC4701a aVar = new RunnableC4701a(asxVar);
        asxVar.onSubscribe(aVar);
        aVar.setFuture(this.f19835c.mo9480a(aVar, this.f19833a, this.f19834b));
    }

    /* compiled from: SingleTimer.java */
    /* renamed from: z1.bqc$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4701a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 8465401857522493082L;
        final SingleObserver<? super Long> downstream;

        RunnableC4701a(SingleObserver<? super Long> asxVar) {
            this.downstream = asxVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.downstream.onSuccess(0L);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        void setFuture(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }
    }
}
