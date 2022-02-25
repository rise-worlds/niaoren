package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.azn */
/* loaded from: classes3.dex */
public final class FlowableConcatMapEager<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f17960c;

    /* renamed from: d */
    final int f17961d;

    /* renamed from: e */
    final int f17962e;

    /* renamed from: f */
    final ErrorMode f17963f;

    public FlowableConcatMapEager(Flowable<T> arvVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, int i2, ErrorMode bsuVar) {
        super(arvVar);
        this.f17960c = aunVar;
        this.f17961d = i;
        this.f17962e = i2;
        this.f17963f = bsuVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4020a(dbxVar, this.f17960c, this.f17961d, this.f17962e, this.f17963f));
    }

    /* compiled from: FlowableConcatMapEager.java */
    /* renamed from: z1.azn$a */
    /* loaded from: classes3.dex */
    static final class C4020a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, InnerQueuedSubscriberSupport<R>, dby {
        private static final long serialVersionUID = -4255299542215038287L;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final ErrorMode errorMode;
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;
        dby upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4020a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, int i2, ErrorMode bsuVar) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = bsuVar;
            this.subscribers = new SpscLinkedArrayQueue<>(Math.min(i2, i));
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                dbyVar.request(i == Integer.MAX_VALUE ? cjm.f20759b : i);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            try {
                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber<R> brpVar = new InnerQueuedSubscriber<>(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(brpVar);
                    dbwVar.subscribe(brpVar);
                    if (this.cancelled) {
                        brpVar.cancel();
                        drainAndCancel();
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                drainAndCancel();
            }
        }

        void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        void cancelAll() {
            InnerQueuedSubscriber<R> brpVar = this.current;
            this.current = null;
            if (brpVar != null) {
                brpVar.cancel();
            }
            while (true) {
                InnerQueuedSubscriber<R> poll = this.subscribers.poll();
                if (poll != null) {
                    poll.cancel();
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.InnerQueuedSubscriberSupport
        public void innerNext(InnerQueuedSubscriber<R> brpVar, R r) {
            if (brpVar.queue().offer(r)) {
                drain();
                return;
            }
            brpVar.cancel();
            innerError(brpVar, new MissingBackpressureException());
        }

        @Override // p110z1.InnerQueuedSubscriberSupport
        public void innerError(InnerQueuedSubscriber<R> brpVar, Throwable th) {
            if (this.errors.addThrowable(th)) {
                brpVar.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.InnerQueuedSubscriberSupport
        public void innerComplete(InnerQueuedSubscriber<R> brpVar) {
            brpVar.setDone();
            drain();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x00cd, code lost:
            r18 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00cf, code lost:
            if (r17 != 0) goto L_0x0110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00d3, code lost:
            if (r20.cancelled == false) goto L_0x00d9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00d5, code lost:
            cancelAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00d8, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00db, code lost:
            if (r3 != p110z1.ErrorMode.IMMEDIATE) goto L_0x00f9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00e5, code lost:
            if (r20.errors.get() == null) goto L_0x00f9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00e7, code lost:
            r20.current = null;
            r8.cancel();
            cancelAll();
            r2.onError(r20.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00f8, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00f9, code lost:
            r0 = r8.isDone();
            r12 = r12.isEmpty();
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0101, code lost:
            if (r0 == false) goto L_0x0110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0103, code lost:
            if (r12 == false) goto L_0x0110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0105, code lost:
            r20.current = null;
            r20.upstream.request(1);
            r0 = null;
            r18 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0110, code lost:
            r0 = r8;
         */
        @Override // p110z1.InnerQueuedSubscriberSupport
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain() {
            /*
                Method dump skipped, instructions count: 318
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableConcatMapEager.C4020a.drain():void");
        }
    }
}
