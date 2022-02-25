package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bec */
/* loaded from: classes3.dex */
public final class FlowableTimeoutTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18414c;

    /* renamed from: d */
    final TimeUnit f18415d;

    /* renamed from: e */
    final Scheduler f18416e;

    /* renamed from: f */
    final Publisher<? extends T> f18417f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableTimeoutTimed.java */
    /* renamed from: z1.bec$d */
    /* loaded from: classes3.dex */
    public interface AbstractC4228d {
        void onTimeout(long j);
    }

    public FlowableTimeoutTimed(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, Publisher<? extends T> dbwVar) {
        super(arvVar);
        this.f18414c = j;
        this.f18415d = timeUnit;
        this.f18416e = astVar;
        this.f18417f = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (this.f18417f == null) {
            C4227c cVar = new C4227c(dbxVar, this.f18414c, this.f18415d, this.f18416e.mo9034b());
            dbxVar.onSubscribe(cVar);
            cVar.startTimeout(0L);
            this.f17812b.m11187a((FlowableSubscriber) cVar);
            return;
        }
        C4226b bVar = new C4226b(dbxVar, this.f18414c, this.f18415d, this.f18416e.mo9034b(), this.f18417f);
        dbxVar.onSubscribe(bVar);
        bVar.startTimeout(0L);
        this.f17812b.m11187a((FlowableSubscriber) bVar);
    }

    /* compiled from: FlowableTimeoutTimed.java */
    /* renamed from: z1.bec$c */
    /* loaded from: classes3.dex */
    static final class C4227c<T> extends AtomicLong implements FlowableSubscriber<T>, AbstractC4228d, dby {
        private static final long serialVersionUID = 3764492702657003550L;
        final Subscriber<? super T> downstream;
        final long timeout;
        final TimeUnit unit;
        final Scheduler.AbstractC3881c worker;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        C4227c(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.downstream = dbxVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        void startTimeout(long j) {
            this.task.replace(this.worker.mo9030a(new RunnableC4229e(j, this), this.timeout, this.unit));
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // p110z1.FlowableTimeoutTimed.AbstractC4228d
        public void onTimeout(long j) {
            if (compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(new TimeoutException(ExceptionHelper.m9433a(this.timeout, this.unit)));
                this.worker.dispose();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.worker.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableTimeoutTimed.java */
    /* renamed from: z1.bec$e */
    /* loaded from: classes3.dex */
    public static final class RunnableC4229e implements Runnable {

        /* renamed from: a */
        final AbstractC4228d f18420a;

        /* renamed from: b */
        final long f18421b;

        RunnableC4229e(long j, AbstractC4228d dVar) {
            this.f18421b = j;
            this.f18420a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18420a.onTimeout(this.f18421b);
        }
    }

    /* compiled from: FlowableTimeoutTimed.java */
    /* renamed from: z1.bec$b */
    /* loaded from: classes3.dex */
    static final class C4226b<T> extends SubscriptionArbiter implements FlowableSubscriber<T>, AbstractC4228d {
        private static final long serialVersionUID = 3764492702657003550L;
        long consumed;
        final Subscriber<? super T> downstream;
        Publisher<? extends T> fallback;
        final long timeout;
        final TimeUnit unit;
        final Scheduler.AbstractC3881c worker;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong index = new AtomicLong();

        C4226b(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, Publisher<? extends T> dbwVar) {
            super(true);
            this.downstream = dbxVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.fallback = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
                setSubscription(dbyVar);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.index.get();
            if (j != cjm.f20759b) {
                long j2 = j + 1;
                if (this.index.compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.consumed++;
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        void startTimeout(long j) {
            this.task.replace(this.worker.mo9030a(new RunnableC4229e(j, this), this.timeout, this.unit));
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // p110z1.FlowableTimeoutTimed.AbstractC4228d
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                Publisher<? extends T> dbwVar = this.fallback;
                this.fallback = null;
                dbwVar.subscribe(new C4225a(this.downstream, this));
                this.worker.dispose();
            }
        }

        @Override // p110z1.SubscriptionArbiter, p110z1.dby
        public void cancel() {
            super.cancel();
            this.worker.dispose();
        }
    }

    /* compiled from: FlowableTimeoutTimed.java */
    /* renamed from: z1.bec$a */
    /* loaded from: classes3.dex */
    static final class C4225a<T> implements FlowableSubscriber<T> {

        /* renamed from: a */
        final Subscriber<? super T> f18418a;

        /* renamed from: b */
        final SubscriptionArbiter f18419b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4225a(Subscriber<? super T> dbxVar, SubscriptionArbiter bsjVar) {
            this.f18418a = dbxVar;
            this.f18419b = bsjVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18419b.setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18418a.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18418a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18418a.onComplete();
        }
    }
}
