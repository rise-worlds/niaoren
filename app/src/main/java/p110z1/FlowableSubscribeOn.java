package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bdn */
/* loaded from: classes3.dex */
public final class FlowableSubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Scheduler f18363c;

    /* renamed from: d */
    final boolean f18364d;

    public FlowableSubscribeOn(Flowable<T> arvVar, Scheduler astVar, boolean z) {
        super(arvVar);
        this.f18363c = astVar;
        this.f18364d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        Scheduler.AbstractC3881c b = this.f18363c.mo9034b();
        RunnableC4203a aVar = new RunnableC4203a(dbxVar, b, this.f17812b, this.f18364d);
        dbxVar.onSubscribe(aVar);
        b.mo9031a(aVar);
    }

    /* compiled from: FlowableSubscribeOn.java */
    /* renamed from: z1.bdn$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4203a<T> extends AtomicReference<Thread> implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 8094547886072529208L;
        final Subscriber<? super T> downstream;
        final boolean nonScheduledRequests;
        Publisher<T> source;
        final Scheduler.AbstractC3881c worker;
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        RunnableC4203a(Subscriber<? super T> dbxVar, Scheduler.AbstractC3881c cVar, Publisher<T> dbwVar, boolean z) {
            this.downstream = dbxVar;
            this.worker = cVar;
            this.source = dbwVar;
            this.nonScheduledRequests = !z;
        }

        @Override // java.lang.Runnable
        public void run() {
            lazySet(Thread.currentThread());
            Publisher<T> dbwVar = this.source;
            this.source = null;
            dbwVar.subscribe(this);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
                long andSet = this.requested.getAndSet(0L);
                if (andSet != 0) {
                    requestUpstream(andSet, dbyVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
            this.worker.dispose();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                dby dbyVar = this.upstream.get();
                if (dbyVar != null) {
                    requestUpstream(j, dbyVar);
                    return;
                }
                BackpressureHelper.m9449a(this.requested, j);
                dby dbyVar2 = this.upstream.get();
                if (dbyVar2 != null) {
                    long andSet = this.requested.getAndSet(0L);
                    if (andSet != 0) {
                        requestUpstream(andSet, dbyVar2);
                    }
                }
            }
        }

        void requestUpstream(long j, dby dbyVar) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                dbyVar.request(j);
            } else {
                this.worker.mo9031a(new RunnableC4204a(dbyVar, j));
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.worker.dispose();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableSubscribeOn.java */
        /* renamed from: z1.bdn$a$a */
        /* loaded from: classes3.dex */
        public static final class RunnableC4204a implements Runnable {

            /* renamed from: a */
            final dby f18365a;

            /* renamed from: b */
            final long f18366b;

            RunnableC4204a(dby dbyVar, long j) {
                this.f18365a = dbyVar;
                this.f18366b = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f18365a.request(this.f18366b);
            }
        }
    }
}
