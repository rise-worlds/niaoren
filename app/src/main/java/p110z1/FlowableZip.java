package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bep */
/* loaded from: classes3.dex */
public final class FlowableZip<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Publisher<? extends T>[] f18517b;

    /* renamed from: c */
    final Iterable<? extends Publisher<? extends T>> f18518c;

    /* renamed from: d */
    final Function<? super Object[], ? extends R> f18519d;

    /* renamed from: e */
    final int f18520e;

    /* renamed from: f */
    final boolean f18521f;

    public FlowableZip(Publisher<? extends T>[] dbwVarArr, Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
        this.f18517b = dbwVarArr;
        this.f18518c = iterable;
        this.f18519d = aunVar;
        this.f18520e = i;
        this.f18521f = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super R> dbxVar) {
        int i;
        Publisher<? extends T>[] dbwVarArr = this.f18517b;
        if (dbwVarArr == null) {
            dbwVarArr = new Publisher[8];
            i = 0;
            for (Publisher<? extends T> dbwVar : this.f18518c) {
                if (i == dbwVarArr.length) {
                    Publisher<? extends T>[] dbwVarArr2 = new Publisher[(i >> 2) + i];
                    System.arraycopy(dbwVarArr, 0, dbwVarArr2, 0, i);
                    dbwVarArr = dbwVarArr2;
                }
                i++;
                dbwVarArr[i] = dbwVar;
            }
        } else {
            i = dbwVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(dbxVar);
            return;
        }
        C4258a aVar = new C4258a(dbxVar, this.f18519d, i, this.f18520e, this.f18521f);
        dbxVar.onSubscribe(aVar);
        aVar.subscribe(dbwVarArr, i);
    }

    /* compiled from: FlowableZip.java */
    /* renamed from: z1.bep$a */
    /* loaded from: classes3.dex */
    static final class C4258a<T, R> extends AtomicInteger implements dby {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final C4259b<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        C4258a(Subscriber<? super R> dbxVar, Function<? super Object[], ? extends R> aunVar, int i, int i2, boolean z) {
            this.downstream = dbxVar;
            this.zipper = aunVar;
            this.delayErrors = z;
            C4259b<T, R>[] bVarArr = new C4259b[i];
            for (int i3 = 0; i3 < i; i3++) {
                bVarArr[i3] = new C4259b<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = bVarArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        void subscribe(Publisher<? extends T>[] dbwVarArr, int i) {
            C4259b<T, R>[] bVarArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled; i2++) {
                if (this.delayErrors || this.errors.get() == null) {
                    dbwVarArr[i2].subscribe(bVarArr[i2]);
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

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
            }
        }

        void error(C4259b<T, R> bVar, Throwable th) {
            if (this.errors.addThrowable(th)) {
                bVar.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void cancelAll() {
            for (C4259b<T, R> bVar : this.subscribers) {
                bVar.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:128:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00cf, code lost:
            if (r16 != 0) goto L_0x014d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00d3, code lost:
            if (r18.cancelled == false) goto L_0x00d6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00d5, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00d8, code lost:
            if (r18.delayErrors != false) goto L_0x00ef;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00e0, code lost:
            if (r18.errors.get() == null) goto L_0x00ef;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00e2, code lost:
            cancelAll();
            r2.onError(r18.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00ee, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00ef, code lost:
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00f0, code lost:
            if (r6 >= r4) goto L_0x014a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00f2, code lost:
            r0 = r3[r6];
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00f6, code lost:
            if (r5[r6] != null) goto L_0x0147;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00f8, code lost:
            r10 = r0.done;
            r0 = r0.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00fc, code lost:
            if (r0 == null) goto L_0x0103;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00fe, code lost:
            r0 = r0.poll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0103, code lost:
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0104, code lost:
            if (r0 != null) goto L_0x0108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0106, code lost:
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0108, code lost:
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0109, code lost:
            if (r10 == false) goto L_0x0128;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x010b, code lost:
            if (r11 == false) goto L_0x0128;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x010d, code lost:
            cancelAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0118, code lost:
            if (r18.errors.get() == null) goto L_0x0124;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x011a, code lost:
            r2.onError(r18.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0124, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0127, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0128, code lost:
            if (r11 != false) goto L_0x0147;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x012a, code lost:
            r5[r6] = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x012d, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x012e, code lost:
            p110z1.Exceptions.m9983b(r0);
            r18.errors.addThrowable(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0138, code lost:
            if (r18.delayErrors == false) goto L_0x013a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x013a, code lost:
            cancelAll();
            r2.onError(r18.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0146, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0147, code lost:
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x014a, code lost:
            r10 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x014d, code lost:
            r10 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0151, code lost:
            if (r12 == r10) goto L_0x016e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0153, code lost:
            r0 = r3.length;
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0155, code lost:
            if (r6 >= r0) goto L_0x015f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0157, code lost:
            r3[r6].request(r12);
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0166, code lost:
            if (r8 == p110z1.cjm.f20759b) goto L_0x016e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0168, code lost:
            r18.requested.addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x016e, code lost:
            r7 = addAndGet(-r7);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 374
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableZip.C4258a.drain():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableZip.java */
    /* renamed from: z1.bep$b */
    /* loaded from: classes3.dex */
    public static final class C4259b<T, R> extends AtomicReference<dby> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final C4258a<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        C4259b(C4258a<T, R> aVar, int i) {
            this.parent = aVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this, dbyVar)) {
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
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
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= this.limit) {
                    this.produced = 0L;
                    get().request(j2);
                    return;
                }
                this.produced = j2;
            }
        }
    }
}
