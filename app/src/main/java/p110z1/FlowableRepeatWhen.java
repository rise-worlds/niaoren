package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bcs */
/* loaded from: classes3.dex */
public final class FlowableRepeatWhen<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super Flowable<Object>, ? extends Publisher<?>> f18277c;

    public FlowableRepeatWhen(Flowable<T> arvVar, Function<? super Flowable<Object>, ? extends Publisher<?>> aunVar) {
        super(arvVar);
        this.f18277c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        FlowableProcessor<T> ac = UnicastProcessor.m9051m(8).m9111ac();
        try {
            Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f18277c.apply(ac), "handler returned a null Publisher");
            C4159b bVar = new C4159b(this.f17812b);
            C4158a aVar = new C4158a(bvfVar, ac, bVar);
            bVar.subscriber = aVar;
            dbxVar.onSubscribe(aVar);
            dbwVar.subscribe(bVar);
            bVar.onNext(0);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableRepeatWhen.java */
    /* renamed from: z1.bcs$b */
    /* loaded from: classes3.dex */
    static final class C4159b<T, U> extends AtomicInteger implements FlowableSubscriber<Object>, dby {
        private static final long serialVersionUID = 2827772011130406689L;
        final Publisher<T> source;
        AbstractC4160c<T, U> subscriber;
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4159b(Publisher<T> dbwVar) {
            this.source = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            if (getAndIncrement() == 0) {
                while (this.upstream.get() != SubscriptionHelper.CANCELLED) {
                    this.source.subscribe(this.subscriber);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.subscriber.cancel();
            this.subscriber.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.subscriber.cancel();
            this.subscriber.downstream.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableRepeatWhen.java */
    /* renamed from: z1.bcs$c */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4160c<T, U> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -5604623027276966720L;
        protected final Subscriber<? super T> downstream;
        protected final FlowableProcessor<U> processor;
        private long produced;
        protected final dby receiver;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AbstractC4160c(Subscriber<? super T> dbxVar, FlowableProcessor<U> buhVar, dby dbyVar) {
            super(false);
            this.downstream = dbxVar;
            this.processor = buhVar;
            this.receiver = dbyVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public final void onSubscribe(dby dbyVar) {
            setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void again(U u) {
            setSubscription(EmptySubscription.INSTANCE);
            long j = this.produced;
            if (j != 0) {
                this.produced = 0L;
                produced(j);
            }
            this.receiver.request(1L);
            this.processor.onNext(u);
        }

        @Override // p110z1.SubscriptionArbiter, p110z1.dby
        public final void cancel() {
            super.cancel();
            this.receiver.cancel();
        }
    }

    /* compiled from: FlowableRepeatWhen.java */
    /* renamed from: z1.bcs$a */
    /* loaded from: classes3.dex */
    static final class C4158a<T> extends AbstractC4160c<T, Object> {
        private static final long serialVersionUID = -2680129890138081029L;

        C4158a(Subscriber<? super T> dbxVar, FlowableProcessor<Object> buhVar, dby dbyVar) {
            super(dbxVar, buhVar, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.receiver.cancel();
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            again(0);
        }
    }
}
