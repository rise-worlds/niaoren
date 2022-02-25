package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bdc */
/* loaded from: classes3.dex */
public final class FlowableSequenceEqual<T> extends Flowable<Boolean> {

    /* renamed from: b */
    final Publisher<? extends T> f18321b;

    /* renamed from: c */
    final Publisher<? extends T> f18322c;

    /* renamed from: d */
    final BiPredicate<? super T, ? super T> f18323d;

    /* renamed from: e */
    final int f18324e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableSequenceEqual.java */
    /* renamed from: z1.bdc$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4191b {
        void drain();

        void innerError(Throwable th);
    }

    public FlowableSequenceEqual(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        this.f18321b = dbwVar;
        this.f18322c = dbwVar2;
        this.f18323d = aujVar;
        this.f18324e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Boolean> dbxVar) {
        C4190a aVar = new C4190a(dbxVar, this.f18324e, this.f18323d);
        dbxVar.onSubscribe(aVar);
        aVar.subscribe(this.f18321b, this.f18322c);
    }

    /* compiled from: FlowableSequenceEqual.java */
    /* renamed from: z1.bdc$a */
    /* loaded from: classes3.dex */
    static final class C4190a<T> extends DeferredScalarSubscription<Boolean> implements AbstractC4191b {
        private static final long serialVersionUID = -6178010334400373240L;
        final BiPredicate<? super T, ? super T> comparer;
        final C4192c<T> first;
        final C4192c<T> second;

        /* renamed from: v1 */
        T f18325v1;

        /* renamed from: v2 */
        T f18326v2;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicThrowable error = new AtomicThrowable();

        C4190a(Subscriber<? super Boolean> dbxVar, int i, BiPredicate<? super T, ? super T> aujVar) {
            super(dbxVar);
            this.comparer = aujVar;
            this.first = new C4192c<>(this, i);
            this.second = new C4192c<>(this, i);
        }

        void subscribe(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
            dbwVar.subscribe(this.first);
            dbwVar2.subscribe(this.second);
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.first.cancel();
            this.second.cancel();
            if (this.wip.getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        @Override // p110z1.FlowableSequenceEqual.AbstractC4191b
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int i = 1;
                do {
                    SimpleQueue<T> avwVar = this.first.queue;
                    SimpleQueue<T> avwVar2 = this.second.queue;
                    if (avwVar != null && avwVar2 != null) {
                        while (!isCancelled()) {
                            if (this.error.get() != null) {
                                cancelAndClear();
                                this.downstream.onError(this.error.terminate());
                                return;
                            }
                            boolean z = this.first.done;
                            T t = this.f18325v1;
                            if (t == null) {
                                try {
                                    t = avwVar.poll();
                                    this.f18325v1 = t;
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z2 = t == null;
                            boolean z3 = this.second.done;
                            T t2 = this.f18326v2;
                            if (t2 == null) {
                                try {
                                    t2 = avwVar2.poll();
                                    this.f18326v2 = t2;
                                } catch (Throwable th2) {
                                    Exceptions.m9983b(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z4 = t2 == null;
                            if (z && z3 && z2 && z4) {
                                complete(true);
                                return;
                            } else if (z && z3 && z2 != z4) {
                                cancelAndClear();
                                complete(false);
                                return;
                            } else if (!z2 && !z4) {
                                try {
                                    if (!this.comparer.mo9871a(t, t2)) {
                                        cancelAndClear();
                                        complete(false);
                                        return;
                                    }
                                    this.f18325v1 = null;
                                    this.f18326v2 = null;
                                    this.first.request();
                                    this.second.request();
                                } catch (Throwable th3) {
                                    Exceptions.m9983b(th3);
                                    cancelAndClear();
                                    this.error.addThrowable(th3);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isCancelled()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (this.error.get() != null) {
                        cancelAndClear();
                        this.downstream.onError(this.error.terminate());
                        return;
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }

        @Override // p110z1.FlowableSequenceEqual.AbstractC4191b
        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableSequenceEqual.java */
    /* renamed from: z1.bdc$c */
    /* loaded from: classes3.dex */
    public static final class C4192c<T> extends AtomicReference<dby> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 4804128302091633067L;
        volatile boolean done;
        final int limit;
        final AbstractC4191b parent;
        final int prefetch;
        long produced;
        volatile SimpleQueue<T> queue;
        int sourceMode;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4192c(AbstractC4191b bVar, int i) {
            this.parent = bVar;
            this.limit = i - (i >> 2);
            this.prefetch = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this, dbyVar)) {
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        dbyVar.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                dbyVar.request(this.prefetch);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                this.parent.drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void request() {
            if (this.sourceMode != 1) {
                long j = this.produced + 1;
                if (j >= this.limit) {
                    this.produced = 0L;
                    get().request(j);
                    return;
                }
                this.produced = j;
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void clear() {
            SimpleQueue<T> avwVar = this.queue;
            if (avwVar != null) {
                avwVar.clear();
            }
        }
    }
}
