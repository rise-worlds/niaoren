package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bej */
/* loaded from: classes3.dex */
public final class FlowableWindowBoundary<T, B> extends AbstractFlowableWithUpstream<T, Flowable<T>> {

    /* renamed from: c */
    final Publisher<B> f18440c;

    /* renamed from: d */
    final int f18441d;

    public FlowableWindowBoundary(Flowable<T> arvVar, Publisher<B> dbwVar, int i) {
        super(arvVar);
        this.f18440c = dbwVar;
        this.f18441d = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Flowable<T>> dbxVar) {
        RunnableC4240b bVar = new RunnableC4240b(dbxVar, this.f18441d);
        dbxVar.onSubscribe(bVar);
        bVar.innerNext();
        this.f18440c.subscribe(bVar.boundarySubscriber);
        this.f17812b.m11187a((FlowableSubscriber) bVar);
    }

    /* compiled from: FlowableWindowBoundary.java */
    /* renamed from: z1.bej$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4240b<T, B> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final int capacityHint;
        volatile boolean done;
        final Subscriber<? super Flowable<T>> downstream;
        long emitted;
        UnicastProcessor<T> window;
        final C4239a<T, B> boundarySubscriber = new C4239a<>(this);
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicInteger windows = new AtomicInteger(1);
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();

        RunnableC4240b(Subscriber<? super Flowable<T>> dbxVar, int i) {
            this.downstream = dbxVar;
            this.capacityHint = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this.upstream, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.boundarySubscriber.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.boundarySubscriber.dispose();
            this.done = true;
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.stopWindows.compareAndSet(false, true)) {
                this.boundarySubscriber.dispose();
                if (this.windows.decrementAndGet() == 0) {
                    SubscriptionHelper.cancel(this.upstream);
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            BackpressureHelper.m9449a(this.requested, j);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                SubscriptionHelper.cancel(this.upstream);
            }
        }

        void innerNext() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        void innerError(Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            SubscriptionHelper.cancel(this.upstream);
            this.done = true;
            drain();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super Flowable<T>> dbxVar = this.downstream;
                MpscLinkedQueue<Object> bqjVar = this.queue;
                AtomicThrowable bsnVar = this.errors;
                long j = this.emitted;
                int i = 1;
                while (this.windows.get() != 0) {
                    UnicastProcessor<T> bumVar = this.window;
                    boolean z = this.done;
                    if (!z || bsnVar.get() == null) {
                        Object poll = bqjVar.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable terminate = bsnVar.terminate();
                            if (terminate == null) {
                                if (bumVar != 0) {
                                    this.window = null;
                                    bumVar.onComplete();
                                }
                                dbxVar.onComplete();
                                return;
                            }
                            if (bumVar != 0) {
                                this.window = null;
                                bumVar.onError(terminate);
                            }
                            dbxVar.onError(terminate);
                            return;
                        } else if (z2) {
                            this.emitted = j;
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else if (poll != NEXT_WINDOW) {
                            bumVar.onNext(poll);
                        } else {
                            if (bumVar != 0) {
                                this.window = null;
                                bumVar.onComplete();
                            }
                            if (!this.stopWindows.get()) {
                                UnicastProcessor<T> a = UnicastProcessor.m9058a(this.capacityHint, (Runnable) this);
                                this.window = a;
                                this.windows.getAndIncrement();
                                if (j != this.requested.get()) {
                                    j++;
                                    dbxVar.onNext(a);
                                } else {
                                    SubscriptionHelper.cancel(this.upstream);
                                    this.boundarySubscriber.dispose();
                                    bsnVar.addThrowable(new MissingBackpressureException("Could not deliver a window due to lack of requests"));
                                    this.done = true;
                                }
                            }
                        }
                    } else {
                        bqjVar.clear();
                        Throwable terminate2 = bsnVar.terminate();
                        if (bumVar != 0) {
                            this.window = null;
                            bumVar.onError(terminate2);
                        }
                        dbxVar.onError(terminate2);
                        return;
                    }
                }
                bqjVar.clear();
                this.window = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableWindowBoundary.java */
    /* renamed from: z1.bej$a */
    /* loaded from: classes3.dex */
    public static final class C4239a<T, B> extends DisposableSubscriber<B> {

        /* renamed from: a */
        final RunnableC4240b<T, B> f18442a;

        /* renamed from: b */
        boolean f18443b;

        C4239a(RunnableC4240b<T, B> bVar) {
            this.f18442a = bVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(B b) {
            if (!this.f18443b) {
                this.f18442a.innerNext();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18443b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18443b = true;
            this.f18442a.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18443b) {
                this.f18443b = true;
                this.f18442a.innerComplete();
            }
        }
    }
}
