package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bed */
/* loaded from: classes3.dex */
public final class FlowableTimer extends Flowable<Long> {

    /* renamed from: b */
    final Scheduler f18422b;

    /* renamed from: c */
    final long f18423c;

    /* renamed from: d */
    final TimeUnit f18424d;

    public FlowableTimer(long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f18423c = j;
        this.f18424d = timeUnit;
        this.f18422b = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Long> dbxVar) {
        RunnableC4230a aVar = new RunnableC4230a(dbxVar);
        dbxVar.onSubscribe(aVar);
        aVar.setResource(this.f18422b.mo9480a(aVar, this.f18423c, this.f18424d));
    }

    /* compiled from: FlowableTimer.java */
    /* renamed from: z1.bed$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4230a extends AtomicReference<Disposable> implements Runnable, dby {
        private static final long serialVersionUID = -2809475196591179431L;
        final Subscriber<? super Long> downstream;
        volatile boolean requested;

        RunnableC4230a(Subscriber<? super Long> dbxVar) {
            this.downstream = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.requested = true;
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            DisposableHelper.dispose(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (this.requested) {
                this.downstream.onNext(0L);
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
                return;
            }
            lazySet(EmptyDisposable.INSTANCE);
            this.downstream.onError(new MissingBackpressureException("Can't deliver value due to lack of requests"));
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.trySet(this, atrVar);
        }
    }
}
