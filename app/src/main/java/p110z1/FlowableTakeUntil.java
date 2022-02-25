package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bdv */
/* loaded from: classes3.dex */
public final class FlowableTakeUntil<T, U> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Publisher<? extends U> f18385c;

    public FlowableTakeUntil(Flowable<T> arvVar, Publisher<? extends U> dbwVar) {
        super(arvVar);
        this.f18385c = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4212a aVar = new C4212a(dbxVar);
        dbxVar.onSubscribe(aVar);
        this.f18385c.subscribe(aVar.other);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableTakeUntil.java */
    /* renamed from: z1.bdv$a */
    /* loaded from: classes3.dex */
    public static final class C4212a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -4945480365982832967L;
        final Subscriber<? super T> downstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final C4212a<T>.C4213a other = new C4213a();
        final AtomicThrowable error = new AtomicThrowable();

        C4212a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            HalfSerializer.m9424a(this.downstream, t, this, this.error);
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

        /* compiled from: FlowableTakeUntil.java */
        /* renamed from: z1.bdv$a$a */
        /* loaded from: classes3.dex */
        final class C4213a extends AtomicReference<dby> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            C4213a() {
            }

            @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
            }

            @Override // p110z1.Subscriber
            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                SubscriptionHelper.cancel(C4212a.this.upstream);
                Subscriber<? super T> dbxVar = C4212a.this.downstream;
                C4212a aVar = C4212a.this;
                HalfSerializer.m9423a((Subscriber<?>) dbxVar, th, (AtomicInteger) aVar, aVar.error);
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                SubscriptionHelper.cancel(C4212a.this.upstream);
                Subscriber<? super T> dbxVar = C4212a.this.downstream;
                C4212a aVar = C4212a.this;
                HalfSerializer.m9422a(dbxVar, aVar, aVar.error);
            }
        }
    }
}
