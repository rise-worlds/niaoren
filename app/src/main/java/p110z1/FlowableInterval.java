package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bbj */
/* loaded from: classes3.dex */
public final class FlowableInterval extends Flowable<Long> {

    /* renamed from: b */
    final Scheduler f18164b;

    /* renamed from: c */
    final long f18165c;

    /* renamed from: d */
    final long f18166d;

    /* renamed from: e */
    final TimeUnit f18167e;

    public FlowableInterval(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        this.f18165c = j;
        this.f18166d = j2;
        this.f18167e = timeUnit;
        this.f18164b = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Long> dbxVar) {
        RunnableC4111a aVar = new RunnableC4111a(dbxVar);
        dbxVar.onSubscribe(aVar);
        Scheduler astVar = this.f18164b;
        if (astVar instanceof TrampolineScheduler) {
            Scheduler.AbstractC3881c b = astVar.mo9034b();
            aVar.setResource(b);
            b.mo9511a(aVar, this.f18165c, this.f18166d, this.f18167e);
            return;
        }
        aVar.setResource(astVar.mo9485a(aVar, this.f18165c, this.f18166d, this.f18167e));
    }

    /* compiled from: FlowableInterval.java */
    /* renamed from: z1.bbj$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4111a extends AtomicLong implements Runnable, dby {
        private static final long serialVersionUID = -2809475196591179431L;
        long count;
        final Subscriber<? super Long> downstream;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        RunnableC4111a(Subscriber<? super Long> dbxVar) {
            this.downstream = dbxVar;
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
            if (this.resource.get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (get() != 0) {
                Subscriber<? super Long> dbxVar = this.downstream;
                long j = this.count;
                this.count = j + 1;
                dbxVar.onNext(Long.valueOf(j));
                BackpressureHelper.m9446c(this, 1L);
                return;
            }
            Subscriber<? super Long> dbxVar2 = this.downstream;
            dbxVar2.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
            DisposableHelper.dispose(this.resource);
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.setOnce(this.resource, atrVar);
        }
    }
}
