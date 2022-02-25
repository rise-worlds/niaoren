package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ben */
/* loaded from: classes3.dex */
public final class FlowableWithLatestFrom<T, U, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final BiFunction<? super T, ? super U, ? extends R> f18509c;

    /* renamed from: d */
    final Publisher<? extends U> f18510d;

    public FlowableWithLatestFrom(Flowable<T> arvVar, BiFunction<? super T, ? super U, ? extends R> auiVar, Publisher<? extends U> dbwVar) {
        super(arvVar);
        this.f18509c = auiVar;
        this.f18510d = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        C4254b bVar = new C4254b(bvfVar, this.f18509c);
        bvfVar.onSubscribe(bVar);
        this.f18510d.subscribe(new C4253a(bVar));
        this.f17812b.m11187a((FlowableSubscriber) bVar);
    }

    /* compiled from: FlowableWithLatestFrom.java */
    /* renamed from: z1.ben$b */
    /* loaded from: classes3.dex */
    static final class C4254b<T, U, R> extends AtomicReference<U> implements ConditionalSubscriber<T>, dby {
        private static final long serialVersionUID = -312246233408980075L;
        final BiFunction<? super T, ? super U, ? extends R> combiner;
        final Subscriber<? super R> downstream;
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<dby> other = new AtomicReference<>();

        C4254b(Subscriber<? super R> dbxVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
            this.downstream = dbxVar;
            this.combiner = auiVar;
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
            U u = get();
            if (u == null) {
                return false;
            }
            try {
                this.downstream.onNext(ObjectHelper.m9873a(this.combiner.apply(t, u), "The combiner returned a null value"));
                return true;
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                this.downstream.onError(th);
                return false;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onComplete();
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

        public boolean setOther(dby dbyVar) {
            return SubscriptionHelper.setOnce(this.other, dbyVar);
        }

        public void otherError(Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(th);
        }
    }

    /* compiled from: FlowableWithLatestFrom.java */
    /* renamed from: z1.ben$a */
    /* loaded from: classes3.dex */
    final class C4253a implements FlowableSubscriber<U> {

        /* renamed from: b */
        private final C4254b<T, U, R> f18512b;

        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        C4253a(C4254b<T, U, R> bVar) {
            this.f18512b = bVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (this.f18512b.setOther(dbyVar)) {
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(U u) {
            this.f18512b.lazySet(u);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18512b.otherError(th);
        }
    }
}
