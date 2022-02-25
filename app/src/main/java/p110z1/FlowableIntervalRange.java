package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bbk */
/* loaded from: classes3.dex */
public final class FlowableIntervalRange extends Flowable<Long> {

    /* renamed from: b */
    final Scheduler f18168b;

    /* renamed from: c */
    final long f18169c;

    /* renamed from: d */
    final long f18170d;

    /* renamed from: e */
    final long f18171e;

    /* renamed from: f */
    final long f18172f;

    /* renamed from: g */
    final TimeUnit f18173g;

    public FlowableIntervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler astVar) {
        this.f18171e = j3;
        this.f18172f = j4;
        this.f18173g = timeUnit;
        this.f18168b = astVar;
        this.f18169c = j;
        this.f18170d = j2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Long> dbxVar) {
        RunnableC4112a aVar = new RunnableC4112a(dbxVar, this.f18169c, this.f18170d);
        dbxVar.onSubscribe(aVar);
        Scheduler astVar = this.f18168b;
        if (astVar instanceof TrampolineScheduler) {
            Scheduler.AbstractC3881c b = astVar.mo9034b();
            aVar.setResource(b);
            b.mo9511a(aVar, this.f18171e, this.f18172f, this.f18173g);
            return;
        }
        aVar.setResource(astVar.mo9485a(aVar, this.f18171e, this.f18172f, this.f18173g));
    }

    /* compiled from: FlowableIntervalRange.java */
    /* renamed from: z1.bbk$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4112a extends AtomicLong implements Runnable, dby {
        private static final long serialVersionUID = -2809475196591179431L;
        long count;
        final Subscriber<? super Long> downstream;
        final long end;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        RunnableC4112a(Subscriber<? super Long> dbxVar, long j, long j2) {
            this.downstream = dbxVar;
            this.count = j;
            this.end = j2;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            DisposableHelper.dispose(this.resource);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.resource.get() != DisposableHelper.DISPOSED) {
                long j = get();
                if (j != 0) {
                    long j2 = this.count;
                    this.downstream.onNext(Long.valueOf(j2));
                    if (j2 == this.end) {
                        if (this.resource.get() != DisposableHelper.DISPOSED) {
                            this.downstream.onComplete();
                        }
                        DisposableHelper.dispose(this.resource);
                        return;
                    }
                    this.count = j2 + 1;
                    if (j != cjm.f20759b) {
                        decrementAndGet();
                        return;
                    }
                    return;
                }
                Subscriber<? super Long> dbxVar = this.downstream;
                dbxVar.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
                DisposableHelper.dispose(this.resource);
            }
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.setOnce(this.resource, atrVar);
        }
    }
}
