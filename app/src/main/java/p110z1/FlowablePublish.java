package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bch */
/* loaded from: classes3.dex */
public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T> {

    /* renamed from: b */
    static final long f18219b = Long.MIN_VALUE;

    /* renamed from: c */
    final Flowable<T> f18220c;

    /* renamed from: d */
    final AtomicReference<C4141c<T>> f18221d;

    /* renamed from: e */
    final int f18222e;

    /* renamed from: f */
    final Publisher<T> f18223f;

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9757a(Flowable<T> arvVar, int i) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.m9198a((ConnectableFlowable) new FlowablePublish(new C4139a(atomicReference, i), arvVar, atomicReference, i));
    }

    private FlowablePublish(Publisher<T> dbwVar, Flowable<T> arvVar, AtomicReference<C4141c<T>> atomicReference, int i) {
        this.f18223f = dbwVar;
        this.f18220c = arvVar;
        this.f18221d = atomicReference;
        this.f18222e = i;
    }

    @Override // p110z1.HasUpstreamPublisher
    /* renamed from: p_ */
    public Publisher<T> mo9741p_() {
        return this.f18220c;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18223f.subscribe(dbxVar);
    }

    @Override // p110z1.ConnectableFlowable
    /* renamed from: l */
    public void mo9740l(Consumer<? super Disposable> aumVar) {
        C4141c<T> cVar;
        while (true) {
            cVar = this.f18221d.get();
            if (cVar != null && !cVar.isDisposed()) {
                break;
            }
            C4141c<T> cVar2 = new C4141c<>(this.f18221d, this.f18222e);
            if (this.f18221d.compareAndSet(cVar, cVar2)) {
                cVar = cVar2;
                break;
            }
        }
        boolean z = true;
        if (cVar.shouldConnect.get() || !cVar.shouldConnect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            aumVar.accept(cVar);
            if (z) {
                this.f18220c.m11187a((FlowableSubscriber) cVar);
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* compiled from: FlowablePublish.java */
    /* renamed from: z1.bch$c */
    /* loaded from: classes3.dex */
    static final class C4141c<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final C4140b[] EMPTY = new C4140b[0];
        static final C4140b[] TERMINATED = new C4140b[0];
        private static final long serialVersionUID = -202316842419149694L;
        final int bufferSize;
        final AtomicReference<C4141c<T>> current;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        volatile Object terminalEvent;
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicReference<C4140b<T>[]> subscribers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        C4141c(AtomicReference<C4141c<T>> atomicReference, int i) {
            this.current = atomicReference;
            this.bufferSize = i;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            C4140b<T>[] bVarArr = this.subscribers.get();
            C4140b<T>[] bVarArr2 = TERMINATED;
            if (bVarArr != bVarArr2 && this.subscribers.getAndSet(bVarArr2) != TERMINATED) {
                this.current.compareAndSet(this, null);
                SubscriptionHelper.cancel(this.upstream);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        dbyVar.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                dbyVar.request(this.bufferSize);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                dispatch();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(th);
                dispatch();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        boolean add(C4140b<T> bVar) {
            C4140b<T>[] bVarArr;
            C4140b<T>[] bVarArr2;
            do {
                bVarArr = this.subscribers.get();
                if (bVarArr == TERMINATED) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new C4140b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!this.subscribers.compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        void remove(C4140b<T> bVar) {
            C4140b<T>[] bVarArr;
            C4140b<T>[] bVarArr2;
            do {
                bVarArr = this.subscribers.get();
                int length = bVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (bVarArr[i2].equals(bVar)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            bVarArr2 = EMPTY;
                        } else {
                            C4140b<T>[] bVarArr3 = new C4140b[length - 1];
                            System.arraycopy(bVarArr, 0, bVarArr3, 0, i);
                            System.arraycopy(bVarArr, i + 1, bVarArr3, i, (length - i) - 1);
                            bVarArr2 = bVarArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(bVarArr, bVarArr2));
        }

        boolean checkTerminated(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                if (!NotificationLite.isComplete(obj)) {
                    Throwable error = NotificationLite.getError(obj);
                    this.current.compareAndSet(this, null);
                    C4140b<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
                    if (andSet.length != 0) {
                        int length = andSet.length;
                        while (i < length) {
                            andSet[i].child.onError(error);
                            i++;
                        }
                    } else {
                        RxJavaPlugins.m9212a(error);
                    }
                    return true;
                } else if (z) {
                    this.current.compareAndSet(this, null);
                    C4140b<T>[] andSet2 = this.subscribers.getAndSet(TERMINATED);
                    int length2 = andSet2.length;
                    while (i < length2) {
                        andSet2[i].child.onComplete();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:75:0x0140, code lost:
            if (r8 == 0) goto L_0x0153;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0145, code lost:
            if (r25.sourceMode == 1) goto L_0x0154;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0147, code lost:
            r25.upstream.get().request(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0158, code lost:
            if (r14 == 0) goto L_0x0160;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x015a, code lost:
            if (r0 != false) goto L_0x0160;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void dispatch() {
            /*
                Method dump skipped, instructions count: 370
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowablePublish.C4141c.dispatch():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowablePublish.java */
    /* renamed from: z1.bch$b */
    /* loaded from: classes3.dex */
    public static final class C4140b<T> extends AtomicLong implements dby {
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        long emitted;
        volatile C4141c<T> parent;

        C4140b(Subscriber<? super T> dbxVar) {
            this.child = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9447b(this, j);
                C4141c<T> cVar = this.parent;
                if (cVar != null) {
                    cVar.dispatch();
                }
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            C4141c<T> cVar;
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE && (cVar = this.parent) != null) {
                cVar.remove(this);
                cVar.dispatch();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowablePublish.java */
    /* renamed from: z1.bch$a */
    /* loaded from: classes3.dex */
    public static final class C4139a<T> implements Publisher<T> {

        /* renamed from: a */
        private final AtomicReference<C4141c<T>> f18224a;

        /* renamed from: b */
        private final int f18225b;

        C4139a(AtomicReference<C4141c<T>> atomicReference, int i) {
            this.f18224a = atomicReference;
            this.f18225b = i;
        }

        @Override // p110z1.Publisher
        public void subscribe(Subscriber<? super T> dbxVar) {
            C4141c<T> cVar;
            C4140b<T> bVar = new C4140b<>(dbxVar);
            dbxVar.onSubscribe(bVar);
            while (true) {
                cVar = this.f18224a.get();
                if (cVar == null || cVar.isDisposed()) {
                    C4141c<T> cVar2 = new C4141c<>(this.f18224a, this.f18225b);
                    if (!this.f18224a.compareAndSet(cVar, cVar2)) {
                        continue;
                    } else {
                        cVar = cVar2;
                    }
                }
                if (cVar.add(bVar)) {
                    break;
                }
            }
            if (bVar.get() == Long.MIN_VALUE) {
                cVar.remove(bVar);
            } else {
                bVar.parent = cVar;
            }
            cVar.dispatch();
        }
    }
}
