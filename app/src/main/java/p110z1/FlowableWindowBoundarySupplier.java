package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bel */
/* loaded from: classes3.dex */
public final class FlowableWindowBoundarySupplier<T, B> extends AbstractFlowableWithUpstream<T, Flowable<T>> {

    /* renamed from: c */
    final Callable<? extends Publisher<B>> f18462c;

    /* renamed from: d */
    final int f18463d;

    public FlowableWindowBoundarySupplier(Flowable<T> arvVar, Callable<? extends Publisher<B>> callable, int i) {
        super(arvVar);
        this.f18462c = callable;
        this.f18463d = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Flowable<T>> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new RunnableC4246b(dbxVar, this.f18463d, this.f18462c));
    }

    /* compiled from: FlowableWindowBoundarySupplier.java */
    /* renamed from: z1.bel$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4246b<T, B> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        static final C4245a<Object, Object> BOUNDARY_DISPOSED = new C4245a<>(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final int capacityHint;
        volatile boolean done;
        final Subscriber<? super Flowable<T>> downstream;
        long emitted;
        final Callable<? extends Publisher<B>> other;
        dby upstream;
        UnicastProcessor<T> window;
        final AtomicReference<C4245a<T, B>> boundarySubscriber = new AtomicReference<>();
        final AtomicInteger windows = new AtomicInteger(1);
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();

        RunnableC4246b(Subscriber<? super Flowable<T>> dbxVar, int i, Callable<? extends Publisher<B>> callable) {
            this.downstream = dbxVar;
            this.capacityHint = i;
            this.other = callable;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.cancel();
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            BackpressureHelper.m9449a(this.requested, j);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void disposeBoundary() {
            Disposable atrVar = (Disposable) this.boundarySubscriber.getAndSet(BOUNDARY_DISPOSED);
            if (atrVar != null && atrVar != BOUNDARY_DISPOSED) {
                atrVar.dispose();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }

        void innerNext(C4245a<T, B> aVar) {
            this.boundarySubscriber.compareAndSet(aVar, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        void innerError(Throwable th) {
            this.upstream.cancel();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            this.upstream.cancel();
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
                                if (j != this.requested.get()) {
                                    UnicastProcessor<T> a = UnicastProcessor.m9058a(this.capacityHint, (Runnable) this);
                                    this.window = a;
                                    this.windows.getAndIncrement();
                                    try {
                                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.other.call(), "The other Callable returned a null Publisher");
                                        C4245a<T, B> aVar = new C4245a<>(this);
                                        if (this.boundarySubscriber.compareAndSet(null, aVar)) {
                                            dbwVar.subscribe(aVar);
                                            j++;
                                            dbxVar.onNext(a);
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.m9983b(th);
                                        bsnVar.addThrowable(th);
                                        this.done = true;
                                    }
                                } else {
                                    this.upstream.cancel();
                                    disposeBoundary();
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
    /* compiled from: FlowableWindowBoundarySupplier.java */
    /* renamed from: z1.bel$a */
    /* loaded from: classes3.dex */
    public static final class C4245a<T, B> extends DisposableSubscriber<B> {

        /* renamed from: a */
        final RunnableC4246b<T, B> f18464a;

        /* renamed from: b */
        boolean f18465b;

        C4245a(RunnableC4246b<T, B> bVar) {
            this.f18464a = bVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(B b) {
            if (!this.f18465b) {
                this.f18465b = true;
                dispose();
                this.f18464a.innerNext(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18465b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18465b = true;
            this.f18464a.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18465b) {
                this.f18465b = true;
                this.f18464a.innerComplete();
            }
        }
    }
}
