package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.beg */
/* loaded from: classes3.dex */
public final class FlowableUnsubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Scheduler f18431c;

    public FlowableUnsubscribeOn(Flowable<T> arvVar, Scheduler astVar) {
        super(arvVar);
        this.f18431c = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4233a(dbxVar, this.f18431c));
    }

    /* compiled from: FlowableUnsubscribeOn.java */
    /* renamed from: z1.beg$a */
    /* loaded from: classes3.dex */
    static final class C4233a<T> extends AtomicBoolean implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 1015244841293359600L;
        final Subscriber<? super T> downstream;
        final Scheduler scheduler;
        dby upstream;

        C4233a(Subscriber<? super T> dbxVar, Scheduler astVar) {
            this.downstream = dbxVar;
            this.scheduler = astVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (get()) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (compareAndSet(false, true)) {
                this.scheduler.mo9481a(new RunnableC4234a());
            }
        }

        /* compiled from: FlowableUnsubscribeOn.java */
        /* renamed from: z1.beg$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4234a implements Runnable {
            RunnableC4234a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C4233a.this.upstream.cancel();
            }
        }
    }
}
