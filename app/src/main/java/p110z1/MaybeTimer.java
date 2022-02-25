package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhc */
/* loaded from: classes3.dex */
public final class MaybeTimer extends Maybe<Long> {

    /* renamed from: a */
    final long f18713a;

    /* renamed from: b */
    final TimeUnit f18714b;

    /* renamed from: c */
    final Scheduler f18715c;

    public MaybeTimer(long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f18713a = j;
        this.f18714b = timeUnit;
        this.f18715c = astVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super Long> asfVar) {
        RunnableC4337a aVar = new RunnableC4337a(asfVar);
        asfVar.onSubscribe(aVar);
        aVar.setFuture(this.f18715c.mo9480a(aVar, this.f18713a, this.f18714b));
    }

    /* compiled from: MaybeTimer.java */
    /* renamed from: z1.bhc$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4337a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 2875964065294031672L;
        final MaybeObserver<? super Long> downstream;

        RunnableC4337a(MaybeObserver<? super Long> asfVar) {
            this.downstream = asfVar;
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
