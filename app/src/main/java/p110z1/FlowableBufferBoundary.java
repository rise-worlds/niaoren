package p110z1;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azd */
/* loaded from: classes3.dex */
public final class FlowableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Callable<U> f17867c;

    /* renamed from: d */
    final Publisher<? extends Open> f17868d;

    /* renamed from: e */
    final Function<? super Open, ? extends Publisher<? extends Close>> f17869e;

    public FlowableBufferBoundary(Flowable<T> arvVar, Publisher<? extends Open> dbwVar, Function<? super Open, ? extends Publisher<? extends Close>> aunVar, Callable<U> callable) {
        super(arvVar);
        this.f17868d = dbwVar;
        this.f17869e = aunVar;
        this.f17867c = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        C3994a aVar = new C3994a(dbxVar, this.f17868d, this.f17869e, this.f17867c);
        dbxVar.onSubscribe(aVar);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
    }

    /* compiled from: FlowableBufferBoundary.java */
    /* renamed from: z1.azd$a */
    /* loaded from: classes3.dex */
    static final class C3994a<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -8466418554264089604L;
        final Function<? super Open, ? extends Publisher<? extends Close>> bufferClose;
        final Publisher<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super C> downstream;
        long emitted;
        long index;
        final SpscLinkedArrayQueue<C> queue = new SpscLinkedArrayQueue<>(Flowable.m11274a());
        final CompositeDisposable subscribers = new CompositeDisposable();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        Map<Long, C> buffers = new LinkedHashMap();
        final AtomicThrowable errors = new AtomicThrowable();

        C3994a(Subscriber<? super C> dbxVar, Publisher<? extends Open> dbwVar, Function<? super Open, ? extends Publisher<? extends Close>> aunVar, Callable<C> callable) {
            this.downstream = dbxVar;
            this.bufferSupplier = callable;
            this.bufferOpen = dbwVar;
            this.bufferClose = aunVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
                C3995a aVar = new C3995a(this);
                this.subscribers.mo9939a(aVar);
                this.bufferOpen.subscribe(aVar);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        c.add(t);
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.subscribers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.subscribers.dispose();
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        this.queue.offer(c);
                    }
                    this.buffers = null;
                    this.done = true;
                    drain();
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            BackpressureHelper.m9449a(this.requested, j);
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            if (SubscriptionHelper.cancel(this.upstream)) {
                this.cancelled = true;
                this.subscribers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void open(Open open) {
            try {
                Collection collection = (Collection) ObjectHelper.m9873a(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.bufferClose.apply(open), "The bufferClose returned a null Publisher");
                long j = this.index;
                this.index = 1 + j;
                synchronized (this) {
                    Map<Long, C> map = this.buffers;
                    if (map != 0) {
                        map.put(Long.valueOf(j), collection);
                        C3996b bVar = new C3996b(this, j);
                        this.subscribers.mo9939a(bVar);
                        dbwVar.subscribe(bVar);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                SubscriptionHelper.cancel(this.upstream);
                onError(th);
            }
        }

        void openComplete(C3995a<Open> aVar) {
            this.subscribers.mo9936c(aVar);
            if (this.subscribers.m9996b() == 0) {
                SubscriptionHelper.cancel(this.upstream);
                this.done = true;
                drain();
            }
        }

        void close(C3996b<T, C> bVar, long j) {
            boolean z;
            this.subscribers.mo9936c(bVar);
            if (this.subscribers.m9996b() == 0) {
                SubscriptionHelper.cancel(this.upstream);
                z = true;
            } else {
                z = false;
            }
            synchronized (this) {
                if (this.buffers != null) {
                    this.queue.offer(this.buffers.remove(Long.valueOf(j)));
                    if (z) {
                        this.done = true;
                    }
                    drain();
                }
            }
        }

        void boundaryError(Disposable atrVar, Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            this.subscribers.mo9936c(atrVar);
            onError(th);
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
            if (r8 != 0) goto L_0x0084;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x005b, code lost:
            if (r12.cancelled == false) goto L_0x0061;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x005d, code lost:
            r3.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
            if (r12.done == false) goto L_0x0084;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
            if (r12.errors.get() == null) goto L_0x007a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x006d, code lost:
            r3.clear();
            r2.onError(r12.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007e, code lost:
            if (r3.isEmpty() == false) goto L_0x0084;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0083, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0084, code lost:
            r12.emitted = r0;
            r5 = addAndGet(-r5);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r12 = this;
                int r0 = r12.getAndIncrement()
                if (r0 == 0) goto L_0x0007
                return
            L_0x0007:
                long r0 = r12.emitted
                z1.dbx<? super C extends java.util.Collection<? super T>> r2 = r12.downstream
                z1.bql<C extends java.util.Collection<? super T>> r3 = r12.queue
                r4 = 1
                r5 = 1
            L_0x000f:
                java.util.concurrent.atomic.AtomicLong r6 = r12.requested
                long r6 = r6.get()
            L_0x0015:
                int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                if (r8 == 0) goto L_0x0057
                boolean r9 = r12.cancelled
                if (r9 == 0) goto L_0x0021
                r3.clear()
                return
            L_0x0021:
                boolean r9 = r12.done
                if (r9 == 0) goto L_0x003a
                z1.bsn r10 = r12.errors
                java.lang.Object r10 = r10.get()
                if (r10 == 0) goto L_0x003a
                r3.clear()
                z1.bsn r0 = r12.errors
                java.lang.Throwable r0 = r0.terminate()
                r2.onError(r0)
                return
            L_0x003a:
                java.lang.Object r10 = r3.poll()
                java.util.Collection r10 = (java.util.Collection) r10
                if (r10 != 0) goto L_0x0044
                r11 = 1
                goto L_0x0045
            L_0x0044:
                r11 = 0
            L_0x0045:
                if (r9 == 0) goto L_0x004d
                if (r11 == 0) goto L_0x004d
                r2.onComplete()
                return
            L_0x004d:
                if (r11 == 0) goto L_0x0050
                goto L_0x0057
            L_0x0050:
                r2.onNext(r10)
                r8 = 1
                long r0 = r0 + r8
                goto L_0x0015
            L_0x0057:
                if (r8 != 0) goto L_0x0084
                boolean r6 = r12.cancelled
                if (r6 == 0) goto L_0x0061
                r3.clear()
                return
            L_0x0061:
                boolean r6 = r12.done
                if (r6 == 0) goto L_0x0084
                z1.bsn r6 = r12.errors
                java.lang.Object r6 = r6.get()
                if (r6 == 0) goto L_0x007a
                r3.clear()
                z1.bsn r0 = r12.errors
                java.lang.Throwable r0 = r0.terminate()
                r2.onError(r0)
                return
            L_0x007a:
                boolean r6 = r3.isEmpty()
                if (r6 == 0) goto L_0x0084
                r2.onComplete()
                return
            L_0x0084:
                r12.emitted = r0
                int r5 = -r5
                int r5 = r12.addAndGet(r5)
                if (r5 != 0) goto L_0x000f
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableBufferBoundary.C3994a.drain():void");
        }

        /* compiled from: FlowableBufferBoundary.java */
        /* renamed from: z1.azd$a$a */
        /* loaded from: classes3.dex */
        static final class C3995a<Open> extends AtomicReference<dby> implements FlowableSubscriber<Open>, Disposable {
            private static final long serialVersionUID = -8498650778633225126L;
            final C3994a<?, ?, Open, ?> parent;

            C3995a(C3994a<?, ?, Open, ?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
            }

            @Override // p110z1.Subscriber
            public void onNext(Open open) {
                this.parent.open(open);
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.boundaryError(this, th);
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.openComplete(this);
            }

            @Override // p110z1.Disposable
            public void dispose() {
                SubscriptionHelper.cancel(this);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return get() == SubscriptionHelper.CANCELLED;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableBufferBoundary.java */
    /* renamed from: z1.azd$b */
    /* loaded from: classes3.dex */
    public static final class C3996b<T, C extends Collection<? super T>> extends AtomicReference<dby> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final C3994a<T, C, ?, ?> parent;

        C3996b(C3994a<T, C, ?, ?> aVar, long j) {
            this.parent = aVar;
            this.index = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            dby dbyVar = get();
            if (dbyVar != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                dbyVar.cancel();
                this.parent.close(this, this.index);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.boundaryError(this, th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.close(this, this.index);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
