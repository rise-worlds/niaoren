package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bcy */
/* loaded from: classes3.dex */
public final class FlowableSampleTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18306c;

    /* renamed from: d */
    final TimeUnit f18307d;

    /* renamed from: e */
    final Scheduler f18308e;

    /* renamed from: f */
    final boolean f18309f;

    public FlowableSampleTimed(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(arvVar);
        this.f18306c = j;
        this.f18307d = timeUnit;
        this.f18308e = astVar;
        this.f18309f = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        if (this.f18309f) {
            this.f17812b.m11187a((FlowableSubscriber) new C4183a(bvfVar, this.f18306c, this.f18307d, this.f18308e));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4184b(bvfVar, this.f18306c, this.f18307d, this.f18308e));
        }
    }

    /* compiled from: FlowableSampleTimed.java */
    /* renamed from: z1.bcy$c */
    /* loaded from: classes3.dex */
    static abstract class AbstractRunnableC4185c<T> extends AtomicReference<T> implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -3517602651313910099L;
        final Subscriber<? super T> downstream;
        final long period;
        final Scheduler scheduler;
        final TimeUnit unit;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable timer = new SequentialDisposable();

        abstract void complete();

        AbstractRunnableC4185c(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.downstream = dbxVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = astVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                SequentialDisposable avfVar = this.timer;
                Scheduler astVar = this.scheduler;
                long j = this.period;
                avfVar.replace(astVar.mo9485a(this, j, j, this.unit));
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            cancelTimer();
            complete();
        }

        void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            cancelTimer();
            this.upstream.cancel();
        }

        void emit() {
            T andSet = getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (this.requested.get() != 0) {
                this.downstream.onNext(andSet);
                BackpressureHelper.m9446c(this.requested, 1L);
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
        }
    }

    /* compiled from: FlowableSampleTimed.java */
    /* renamed from: z1.bcy$b */
    /* loaded from: classes3.dex */
    static final class C4184b<T> extends AbstractRunnableC4185c<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        C4184b(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(dbxVar, j, timeUnit, astVar);
        }

        @Override // p110z1.FlowableSampleTimed.AbstractRunnableC4185c
        void complete() {
            this.downstream.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }
    }

    /* compiled from: FlowableSampleTimed.java */
    /* renamed from: z1.bcy$a */
    /* loaded from: classes3.dex */
    static final class C4183a<T> extends AbstractRunnableC4185c<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        C4183a(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(dbxVar, j, timeUnit, astVar);
        }

        @Override // p110z1.FlowableSampleTimed.AbstractRunnableC4185c
        void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
