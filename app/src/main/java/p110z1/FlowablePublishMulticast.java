package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bci */
/* loaded from: classes3.dex */
public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super Flowable<T>, ? extends Publisher<? extends R>> f18226c;

    /* renamed from: d */
    final int f18227d;

    /* renamed from: e */
    final boolean f18228e;

    public FlowablePublishMulticast(Flowable<T> arvVar, Function<? super Flowable<T>, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
        super(arvVar);
        this.f18226c = aunVar;
        this.f18227d = i;
        this.f18228e = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        C4142a aVar = new C4142a(this.f18227d, this.f18228e);
        try {
            ((Publisher) ObjectHelper.m9873a(this.f18226c.apply(aVar), "selector returned a null Publisher")).subscribe(new C4144c(dbxVar, aVar));
            this.f17812b.m11187a((FlowableSubscriber) aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowablePublishMulticast.java */
    /* renamed from: z1.bci$c */
    /* loaded from: classes3.dex */
    static final class C4144c<R> implements FlowableSubscriber<R>, dby {

        /* renamed from: a */
        final Subscriber<? super R> f18242a;

        /* renamed from: b */
        final C4142a<?> f18243b;

        /* renamed from: c */
        dby f18244c;

        C4144c(Subscriber<? super R> dbxVar, C4142a<?> aVar) {
            this.f18242a = dbxVar;
            this.f18243b = aVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18244c, dbyVar)) {
                this.f18244c = dbyVar;
                this.f18242a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(R r) {
            this.f18242a.onNext(r);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18242a.onError(th);
            this.f18243b.dispose();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18242a.onComplete();
            this.f18243b.dispose();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18244c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18244c.cancel();
            this.f18243b.dispose();
        }
    }

    /* compiled from: FlowablePublishMulticast.java */
    /* renamed from: z1.bci$a */
    /* loaded from: classes3.dex */
    static final class C4142a<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: b */
        static final C4143b[] f18229b = new C4143b[0];

        /* renamed from: c */
        static final C4143b[] f18230c = new C4143b[0];

        /* renamed from: f */
        final int f18233f;

        /* renamed from: g */
        final int f18234g;

        /* renamed from: h */
        final boolean f18235h;

        /* renamed from: j */
        volatile SimpleQueue<T> f18237j;

        /* renamed from: k */
        int f18238k;

        /* renamed from: l */
        volatile boolean f18239l;

        /* renamed from: m */
        Throwable f18240m;

        /* renamed from: n */
        int f18241n;

        /* renamed from: d */
        final AtomicInteger f18231d = new AtomicInteger();

        /* renamed from: i */
        final AtomicReference<dby> f18236i = new AtomicReference<>();

        /* renamed from: e */
        final AtomicReference<C4143b<T>[]> f18232e = new AtomicReference<>(f18229b);

        C4142a(int i, boolean z) {
            this.f18233f = i;
            this.f18234g = i - (i >> 2);
            this.f18235h = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.f18236i, dbyVar)) {
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.f18238k = requestFusion;
                        this.f18237j = avtVar;
                        this.f18239l = true;
                        m9756T();
                        return;
                    } else if (requestFusion == 2) {
                        this.f18238k = requestFusion;
                        this.f18237j = avtVar;
                        QueueDrainHelper.m9371a(dbyVar, this.f18233f);
                        return;
                    }
                }
                this.f18237j = QueueDrainHelper.m9377a(this.f18233f);
                QueueDrainHelper.m9371a(dbyVar, this.f18233f);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SimpleQueue<T> avwVar;
            SubscriptionHelper.cancel(this.f18236i);
            if (this.f18231d.getAndIncrement() == 0 && (avwVar = this.f18237j) != null) {
                avwVar.clear();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18236i.get() == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18239l) {
                if (this.f18238k != 0 || this.f18237j.offer(t)) {
                    m9756T();
                    return;
                }
                this.f18236i.get().cancel();
                onError(new MissingBackpressureException());
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18239l) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18240m = th;
            this.f18239l = true;
            m9756T();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18239l) {
                this.f18239l = true;
                m9756T();
            }
        }

        /* renamed from: a */
        boolean m9754a(C4143b<T> bVar) {
            C4143b<T>[] bVarArr;
            C4143b<T>[] bVarArr2;
            do {
                bVarArr = this.f18232e.get();
                if (bVarArr == f18230c) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new C4143b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!this.f18232e.compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        /* renamed from: b */
        void m9752b(C4143b<T> bVar) {
            C4143b<T>[] bVarArr;
            C4143b<T>[] bVarArr2;
            do {
                bVarArr = this.f18232e.get();
                int length = bVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (bVarArr[i2] == bVar) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            bVarArr2 = f18229b;
                        } else {
                            C4143b<T>[] bVarArr3 = new C4143b[length - 1];
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
            } while (!this.f18232e.compareAndSet(bVarArr, bVarArr2));
        }

        @Override // p110z1.Flowable
        /* renamed from: d */
        protected void mo9054d(Subscriber<? super T> dbxVar) {
            C4143b<T> bVar = new C4143b<>(dbxVar, this);
            dbxVar.onSubscribe(bVar);
            if (!m9754a((C4143b) bVar)) {
                Throwable th = this.f18240m;
                if (th != null) {
                    dbxVar.onError(th);
                } else {
                    dbxVar.onComplete();
                }
            } else if (bVar.isCancelled()) {
                m9752b((C4143b) bVar);
            } else {
                m9756T();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:131:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00eb, code lost:
            r8 = r5;
            r7 = r21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x00ff, code lost:
            if (r7 != 0) goto L_0x0131;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0105, code lost:
            if (isDisposed() == false) goto L_0x010b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0107, code lost:
            r0.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x010a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x010b, code lost:
            r5 = r24.f18239l;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x010d, code lost:
            if (r5 == false) goto L_0x011b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0111, code lost:
            if (r24.f18235h != false) goto L_0x011b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0113, code lost:
            r6 = r24.f18240m;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0115, code lost:
            if (r6 == null) goto L_0x011b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0117, code lost:
            m9753b(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x011a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x011b, code lost:
            if (r5 == false) goto L_0x0131;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0121, code lost:
            if (r0.isEmpty() == false) goto L_0x0131;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0123, code lost:
            r0 = r24.f18240m;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0125, code lost:
            if (r0 == null) goto L_0x012b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0127, code lost:
            m9753b(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x012b, code lost:
            m9755U();
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x012e, code lost:
            return;
         */
        /* renamed from: T */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void m9756T() {
            /*
                Method dump skipped, instructions count: 333
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowablePublishMulticast.C4142a.m9756T():void");
        }

        /* renamed from: b */
        void m9753b(Throwable th) {
            C4143b<T>[] andSet;
            for (C4143b<T> bVar : this.f18232e.getAndSet(f18230c)) {
                if (bVar.get() != Long.MIN_VALUE) {
                    bVar.downstream.onError(th);
                }
            }
        }

        /* renamed from: U */
        void m9755U() {
            C4143b<T>[] andSet;
            for (C4143b<T> bVar : this.f18232e.getAndSet(f18230c)) {
                if (bVar.get() != Long.MIN_VALUE) {
                    bVar.downstream.onComplete();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowablePublishMulticast.java */
    /* renamed from: z1.bci$b */
    /* loaded from: classes3.dex */
    public static final class C4143b<T> extends AtomicLong implements dby {
        private static final long serialVersionUID = 8664815189257569791L;
        final Subscriber<? super T> downstream;
        long emitted;
        final C4142a<T> parent;

        C4143b(Subscriber<? super T> dbxVar, C4142a<T> aVar) {
            this.downstream = dbxVar;
            this.parent = aVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9447b(this, j);
                this.parent.m9756T();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.m9752b((C4143b) this);
                this.parent.m9756T();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }
}
