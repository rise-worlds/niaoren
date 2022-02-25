package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.baa */
/* loaded from: classes3.dex */
public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<? extends T> f18008b;

    /* renamed from: c */
    final Publisher<U> f18009c;

    public FlowableDelaySubscriptionOther(Publisher<? extends T> dbwVar, Publisher<U> dbwVar2) {
        this.f18008b = dbwVar;
        this.f18009c = dbwVar2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        C4045a aVar = new C4045a(dbxVar, this.f18008b);
        dbxVar.onSubscribe(aVar);
        this.f18009c.subscribe(aVar.other);
    }

    /* compiled from: FlowableDelaySubscriptionOther.java */
    /* renamed from: z1.baa$a */
    /* loaded from: classes3.dex */
    static final class C4045a<T> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 2259811067697317255L;
        final Subscriber<? super T> downstream;
        final Publisher<? extends T> main;
        final C4045a<T>.C4046a other = new C4046a();
        final AtomicReference<dby> upstream = new AtomicReference<>();

        C4045a(Subscriber<? super T> dbxVar, Publisher<? extends T> dbwVar) {
            this.downstream = dbxVar;
            this.main = dbwVar;
        }

        void next() {
            this.main.subscribe(this);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                SubscriptionHelper.deferredRequest(this.upstream, this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            SubscriptionHelper.cancel(this.upstream);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this, dbyVar);
        }

        /* compiled from: FlowableDelaySubscriptionOther.java */
        /* renamed from: z1.baa$a$a */
        /* loaded from: classes3.dex */
        final class C4046a extends AtomicReference<dby> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -3892798459447644106L;

            C4046a() {
            }

            @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                if (SubscriptionHelper.setOnce(this, dbyVar)) {
                    dbyVar.request(cjm.f20759b);
                }
            }

            @Override // p110z1.Subscriber
            public void onNext(Object obj) {
                dby dbyVar = get();
                if (dbyVar != SubscriptionHelper.CANCELLED) {
                    lazySet(SubscriptionHelper.CANCELLED);
                    dbyVar.cancel();
                    C4045a.this.next();
                }
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                if (get() != SubscriptionHelper.CANCELLED) {
                    C4045a.this.downstream.onError(th);
                } else {
                    RxJavaPlugins.m9212a(th);
                }
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                if (get() != SubscriptionHelper.CANCELLED) {
                    C4045a.this.next();
                }
            }
        }
    }
}
