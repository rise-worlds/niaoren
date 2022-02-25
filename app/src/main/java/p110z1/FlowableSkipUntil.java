package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bdl */
/* loaded from: classes3.dex */
public final class FlowableSkipUntil<T, U> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Publisher<U> f18357c;

    public FlowableSkipUntil(Flowable<T> arvVar, Publisher<U> dbwVar) {
        super(arvVar);
        this.f18357c = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4200a aVar = new C4200a(dbxVar);
        dbxVar.onSubscribe(aVar);
        this.f18357c.subscribe(aVar.other);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
    }

    /* compiled from: FlowableSkipUntil.java */
    /* renamed from: z1.bdl$a */
    /* loaded from: classes3.dex */
    static final class C4200a<T> extends AtomicInteger implements ConditionalSubscriber<T>, dby {
        private static final long serialVersionUID = -6270983465606289181L;
        final Subscriber<? super T> downstream;
        volatile boolean gate;
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final C4200a<T>.C4201a other = new C4201a();
        final AtomicThrowable error = new AtomicThrowable();

        C4200a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.get().request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (!this.gate) {
                return false;
            }
            HalfSerializer.m9424a(this.downstream, t, this, this.error);
            return true;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.m9422a(this.downstream, this, this.error);
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            SubscriptionHelper.cancel(this.other);
        }

        /* compiled from: FlowableSkipUntil.java */
        /* renamed from: z1.bdl$a$a */
        /* loaded from: classes3.dex */
        final class C4201a extends AtomicReference<dby> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -5592042965931999169L;

            C4201a() {
            }

            @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
            }

            @Override // p110z1.Subscriber
            public void onNext(Object obj) {
                C4200a.this.gate = true;
                get().cancel();
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                SubscriptionHelper.cancel(C4200a.this.upstream);
                Subscriber<? super T> dbxVar = C4200a.this.downstream;
                C4200a aVar = C4200a.this;
                HalfSerializer.m9423a((Subscriber<?>) dbxVar, th, (AtomicInteger) aVar, aVar.error);
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                C4200a.this.gate = true;
            }
        }
    }
}
