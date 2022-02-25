package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bcx */
/* loaded from: classes3.dex */
public final class FlowableSamplePublisher<T> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<T> f18302b;

    /* renamed from: c */
    final Publisher<?> f18303c;

    /* renamed from: d */
    final boolean f18304d;

    public FlowableSamplePublisher(Publisher<T> dbwVar, Publisher<?> dbwVar2, boolean z) {
        this.f18302b = dbwVar;
        this.f18303c = dbwVar2;
        this.f18304d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        if (this.f18304d) {
            this.f18302b.subscribe(new C4179a(bvfVar, this.f18303c));
        } else {
            this.f18302b.subscribe(new C4180b(bvfVar, this.f18303c));
        }
    }

    /* compiled from: FlowableSamplePublisher.java */
    /* renamed from: z1.bcx$c */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4181c<T> extends AtomicReference<T> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -3517602651313910099L;
        final Subscriber<? super T> downstream;
        final Publisher<?> sampler;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<dby> other = new AtomicReference<>();

        abstract void completion();

        abstract void run();

        AbstractC4181c(Subscriber<? super T> dbxVar, Publisher<?> dbwVar) {
            this.downstream = dbxVar;
            this.sampler = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new C4182d(this));
                    dbyVar.request(cjm.f20759b);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            completion();
        }

        void setOther(dby dbyVar) {
            SubscriptionHelper.setOnce(this.other, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            this.upstream.cancel();
        }

        public void error(Throwable th) {
            this.upstream.cancel();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.cancel();
            completion();
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

    /* compiled from: FlowableSamplePublisher.java */
    /* renamed from: z1.bcx$d */
    /* loaded from: classes3.dex */
    static final class C4182d<T> implements FlowableSubscriber<Object> {

        /* renamed from: a */
        final AbstractC4181c<T> f18305a;

        C4182d(AbstractC4181c<T> cVar) {
            this.f18305a = cVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18305a.setOther(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            this.f18305a.run();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18305a.error(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18305a.complete();
        }
    }

    /* compiled from: FlowableSamplePublisher.java */
    /* renamed from: z1.bcx$b */
    /* loaded from: classes3.dex */
    static final class C4180b<T> extends AbstractC4181c<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        C4180b(Subscriber<? super T> dbxVar, Publisher<?> dbwVar) {
            super(dbxVar, dbwVar);
        }

        @Override // p110z1.FlowableSamplePublisher.AbstractC4181c
        void completion() {
            this.downstream.onComplete();
        }

        @Override // p110z1.FlowableSamplePublisher.AbstractC4181c
        void run() {
            emit();
        }
    }

    /* compiled from: FlowableSamplePublisher.java */
    /* renamed from: z1.bcx$a */
    /* loaded from: classes3.dex */
    static final class C4179a<T> extends AbstractC4181c<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        C4179a(Subscriber<? super T> dbxVar, Publisher<?> dbwVar) {
            super(dbxVar, dbwVar);
        }

        @Override // p110z1.FlowableSamplePublisher.AbstractC4181c
        void completion() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.FlowableSamplePublisher.AbstractC4181c
        void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
