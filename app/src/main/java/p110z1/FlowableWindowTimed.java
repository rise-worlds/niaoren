package p110z1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.bem */
/* loaded from: classes3.dex */
public final class FlowableWindowTimed<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {

    /* renamed from: c */
    final long f18466c;

    /* renamed from: d */
    final long f18467d;

    /* renamed from: e */
    final TimeUnit f18468e;

    /* renamed from: f */
    final Scheduler f18469f;

    /* renamed from: g */
    final long f18470g;

    /* renamed from: h */
    final int f18471h;

    /* renamed from: i */
    final boolean f18472i;

    public FlowableWindowTimed(Flowable<T> arvVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, long j3, int i, boolean z) {
        super(arvVar);
        this.f18466c = j;
        this.f18467d = j2;
        this.f18468e = timeUnit;
        this.f18469f = astVar;
        this.f18470g = j3;
        this.f18471h = i;
        this.f18472i = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Flowable<T>> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        if (this.f18466c != this.f18467d) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4250c(bvfVar, this.f18466c, this.f18467d, this.f18468e, this.f18469f.mo9034b(), this.f18471h));
        } else if (this.f18470g == cjm.f20759b) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4249b(bvfVar, this.f18466c, this.f18468e, this.f18469f, this.f18471h));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4247a(bvfVar, this.f18466c, this.f18468e, this.f18469f, this.f18471h, this.f18470g, this.f18472i));
        }
    }

    /* compiled from: FlowableWindowTimed.java */
    /* renamed from: z1.bem$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4249b<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Runnable, FlowableSubscriber<T>, dby {

        /* renamed from: h */
        static final Object f18488h = new Object();

        /* renamed from: a */
        final long f18489a;

        /* renamed from: b */
        final TimeUnit f18490b;

        /* renamed from: c */
        final Scheduler f18491c;

        /* renamed from: d */
        final int f18492d;

        /* renamed from: e */
        dby f18493e;

        /* renamed from: f */
        UnicastProcessor<T> f18494f;

        /* renamed from: g */
        final SequentialDisposable f18495g = new SequentialDisposable();

        /* renamed from: i */
        volatile boolean f18496i;

        RunnableC4249b(Subscriber<? super Flowable<T>> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar, int i) {
            super(dbxVar, new MpscLinkedQueue());
            this.f18489a = j;
            this.f18490b = timeUnit;
            this.f18491c = astVar;
            this.f18492d = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18493e, dbyVar)) {
                this.f18493e = dbyVar;
                this.f18494f = UnicastProcessor.m9051m(this.f18492d);
                Subscriber<? super V> dbxVar = this.f20003n;
                dbxVar.onSubscribe(this);
                long h = mo9378h();
                if (h != 0) {
                    dbxVar.onNext(this.f18494f);
                    if (h != cjm.f20759b) {
                        mo9384a(1L);
                    }
                    if (!this.f20005p) {
                        SequentialDisposable avfVar = this.f18495g;
                        Scheduler astVar = this.f18491c;
                        long j = this.f18489a;
                        if (avfVar.replace(astVar.mo9485a(this, j, j, this.f18490b))) {
                            dbyVar.request(cjm.f20759b);
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.f20005p = true;
                dbyVar.cancel();
                dbxVar.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18496i) {
                if (m9464f()) {
                    this.f18494f.onNext(t);
                    if (mo9385a(-1) == 0) {
                        return;
                    }
                } else {
                    this.f20004o.offer(NotificationLite.next(t));
                    if (!mo9380e()) {
                        return;
                    }
                }
                m9716b();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f20007r = th;
            this.f20006q = true;
            if (mo9380e()) {
                m9716b();
            }
            this.f20003n.onError(th);
            m9717a();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f20006q = true;
            if (mo9380e()) {
                m9716b();
            }
            this.f20003n.onComplete();
            m9717a();
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f20005p = true;
        }

        /* renamed from: a */
        public void m9717a() {
            DisposableHelper.dispose(this.f18495g);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f20005p) {
                this.f18496i = true;
                m9717a();
            }
            this.f20004o.offer(f18488h);
            if (mo9380e()) {
                m9716b();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
            r2.onError(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
            r10.f18494f = null;
            r0.clear();
            m9717a();
            r0 = r10.f20007r;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
            if (r0 == null) goto L_0x0028;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void m9716b() {
            /*
                r10 = this;
                z1.avv<U> r0 = r10.f20004o
                z1.dbx<? super V> r1 = r10.f20003n
                z1.bum<T> r2 = r10.f18494f
                r3 = 1
            L_0x0007:
                boolean r4 = r10.f18496i
                boolean r5 = r10.f20006q
                java.lang.Object r6 = r0.poll()
                r7 = 0
                if (r5 == 0) goto L_0x002c
                if (r6 == 0) goto L_0x0018
                java.lang.Object r5 = p110z1.FlowableWindowTimed.RunnableC4249b.f18488h
                if (r6 != r5) goto L_0x002c
            L_0x0018:
                r10.f18494f = r7
                r0.clear()
                r10.m9717a()
                java.lang.Throwable r0 = r10.f20007r
                if (r0 == 0) goto L_0x0028
                r2.onError(r0)
                goto L_0x002b
            L_0x0028:
                r2.onComplete()
            L_0x002b:
                return
            L_0x002c:
                if (r6 != 0) goto L_0x0036
                int r3 = -r3
                int r3 = r10.mo9385a(r3)
                if (r3 != 0) goto L_0x0007
                return
            L_0x0036:
                java.lang.Object r5 = p110z1.FlowableWindowTimed.RunnableC4249b.f18488h
                if (r6 != r5) goto L_0x0083
                r2.onComplete()
                if (r4 != 0) goto L_0x007d
                int r2 = r10.f18492d
                z1.bum r2 = p110z1.UnicastProcessor.m9051m(r2)
                r10.f18494f = r2
                long r4 = r10.mo9378h()
                r8 = 0
                int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r6 == 0) goto L_0x0063
                r1.onNext(r2)
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 == 0) goto L_0x0007
                r4 = 1
                r10.mo9384a(r4)
                goto L_0x0007
            L_0x0063:
                r10.f18494f = r7
                z1.avv<U> r0 = r10.f20004o
                r0.clear()
                z1.dby r0 = r10.f18493e
                r0.cancel()
                r10.m9717a()
                z1.aua r0 = new z1.aua
                java.lang.String r2 = "Could not deliver first window due to lack of requests."
                r0.<init>(r2)
                r1.onError(r0)
                return
            L_0x007d:
                z1.dby r4 = r10.f18493e
                r4.cancel()
                goto L_0x0007
            L_0x0083:
                java.lang.Object r4 = p110z1.NotificationLite.getValue(r6)
                r2.onNext(r4)
                goto L_0x0007
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableWindowTimed.RunnableC4249b.m9716b():void");
        }
    }

    /* compiled from: FlowableWindowTimed.java */
    /* renamed from: z1.bem$a */
    /* loaded from: classes3.dex */
    static final class C4247a<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements dby {

        /* renamed from: a */
        final long f18473a;

        /* renamed from: b */
        final TimeUnit f18474b;

        /* renamed from: c */
        final Scheduler f18475c;

        /* renamed from: d */
        final int f18476d;

        /* renamed from: e */
        final boolean f18477e;

        /* renamed from: f */
        final long f18478f;

        /* renamed from: g */
        final Scheduler.AbstractC3881c f18479g;

        /* renamed from: h */
        long f18480h;

        /* renamed from: i */
        long f18481i;

        /* renamed from: j */
        dby f18482j;

        /* renamed from: k */
        UnicastProcessor<T> f18483k;

        /* renamed from: l */
        volatile boolean f18484l;

        /* renamed from: m */
        final SequentialDisposable f18485m = new SequentialDisposable();

        C4247a(Subscriber<? super Flowable<T>> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, long j2, boolean z) {
            super(dbxVar, new MpscLinkedQueue());
            this.f18473a = j;
            this.f18474b = timeUnit;
            this.f18475c = astVar;
            this.f18476d = i;
            this.f18478f = j2;
            this.f18477e = z;
            if (z) {
                this.f18479g = astVar.mo9034b();
            } else {
                this.f18479g = null;
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            Disposable atrVar;
            if (SubscriptionHelper.validate(this.f18482j, dbyVar)) {
                this.f18482j = dbyVar;
                Subscriber<? super V> dbxVar = this.f20003n;
                dbxVar.onSubscribe(this);
                if (!this.f20005p) {
                    UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18476d);
                    this.f18483k = m;
                    long h = mo9378h();
                    if (h != 0) {
                        dbxVar.onNext(m);
                        if (h != cjm.f20759b) {
                            mo9384a(1L);
                        }
                        RunnableC4248a aVar = new RunnableC4248a(this.f18481i, this);
                        if (this.f18477e) {
                            Scheduler.AbstractC3881c cVar = this.f18479g;
                            long j = this.f18473a;
                            atrVar = cVar.mo9511a(aVar, j, j, this.f18474b);
                        } else {
                            Scheduler astVar = this.f18475c;
                            long j2 = this.f18473a;
                            atrVar = astVar.mo9485a(aVar, j2, j2, this.f18474b);
                        }
                        if (this.f18485m.replace(atrVar)) {
                            dbyVar.request(cjm.f20759b);
                            return;
                        }
                        return;
                    }
                    this.f20005p = true;
                    dbyVar.cancel();
                    dbxVar.onError(new MissingBackpressureException("Could not deliver initial window due to lack of requests."));
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18484l) {
                if (m9464f()) {
                    UnicastProcessor<T> bumVar = this.f18483k;
                    bumVar.onNext(t);
                    long j = this.f18480h + 1;
                    if (j >= this.f18478f) {
                        this.f18481i++;
                        this.f18480h = 0L;
                        bumVar.onComplete();
                        long h = mo9378h();
                        if (h != 0) {
                            UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18476d);
                            this.f18483k = m;
                            this.f20003n.onNext(m);
                            if (h != cjm.f20759b) {
                                mo9384a(1L);
                            }
                            if (this.f18477e) {
                                this.f18485m.get().dispose();
                                Scheduler.AbstractC3881c cVar = this.f18479g;
                                RunnableC4248a aVar = new RunnableC4248a(this.f18481i, this);
                                long j2 = this.f18473a;
                                this.f18485m.replace(cVar.mo9511a(aVar, j2, j2, this.f18474b));
                            }
                        } else {
                            this.f18483k = null;
                            this.f18482j.cancel();
                            this.f20003n.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            m9721a();
                            return;
                        }
                    } else {
                        this.f18480h = j;
                    }
                    if (mo9385a(-1) == 0) {
                        return;
                    }
                } else {
                    this.f20004o.offer(NotificationLite.next(t));
                    if (!mo9380e()) {
                        return;
                    }
                }
                m9719b();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f20007r = th;
            this.f20006q = true;
            if (mo9380e()) {
                m9719b();
            }
            this.f20003n.onError(th);
            m9721a();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f20006q = true;
            if (mo9380e()) {
                m9719b();
            }
            this.f20003n.onComplete();
            m9721a();
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f20005p = true;
        }

        /* renamed from: a */
        public void m9721a() {
            DisposableHelper.dispose(this.f18485m);
            Scheduler.AbstractC3881c cVar = this.f18479g;
            if (cVar != null) {
                cVar.dispose();
            }
        }

        /* renamed from: b */
        void m9719b() {
            SimpleQueue avwVar = this.f20004o;
            Subscriber<? super V> dbxVar = this.f20003n;
            UnicastProcessor<T> bumVar = this.f18483k;
            int i = 1;
            while (!this.f18484l) {
                boolean z = this.f20006q;
                Object poll = avwVar.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof RunnableC4248a;
                if (z && (z2 || z3)) {
                    this.f18483k = null;
                    avwVar.clear();
                    Throwable th = this.f20007r;
                    if (th != null) {
                        bumVar.onError(th);
                    } else {
                        bumVar.onComplete();
                    }
                    m9721a();
                    return;
                } else if (z2) {
                    i = mo9385a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    RunnableC4248a aVar = (RunnableC4248a) poll;
                    if (this.f18477e || this.f18481i == aVar.f18486a) {
                        bumVar.onComplete();
                        this.f18480h = 0L;
                        bumVar = UnicastProcessor.m9051m(this.f18476d);
                        this.f18483k = bumVar;
                        long h = mo9378h();
                        if (h != 0) {
                            dbxVar.onNext(bumVar);
                            if (h != cjm.f20759b) {
                                mo9384a(1L);
                            }
                            i = i;
                        } else {
                            this.f18483k = null;
                            this.f20004o.clear();
                            this.f18482j.cancel();
                            dbxVar.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                            m9721a();
                            return;
                        }
                    } else {
                        i = i;
                    }
                } else {
                    bumVar.onNext(NotificationLite.getValue(poll));
                    long j = this.f18480h + 1;
                    if (j >= this.f18478f) {
                        this.f18481i++;
                        this.f18480h = 0L;
                        bumVar.onComplete();
                        long h2 = mo9378h();
                        if (h2 != 0) {
                            UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18476d);
                            this.f18483k = m;
                            this.f20003n.onNext(m);
                            if (h2 != cjm.f20759b) {
                                mo9384a(1L);
                            }
                            if (this.f18477e) {
                                this.f18485m.get().dispose();
                                Scheduler.AbstractC3881c cVar = this.f18479g;
                                RunnableC4248a aVar2 = new RunnableC4248a(this.f18481i, this);
                                long j2 = this.f18473a;
                                this.f18485m.replace(cVar.mo9511a(aVar2, j2, j2, this.f18474b));
                            }
                            bumVar = m;
                        } else {
                            this.f18483k = null;
                            this.f18482j.cancel();
                            this.f20003n.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            m9721a();
                            return;
                        }
                    } else {
                        this.f18480h = j;
                    }
                    i = i;
                }
            }
            this.f18482j.cancel();
            avwVar.clear();
            m9721a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableWindowTimed.java */
        /* renamed from: z1.bem$a$a */
        /* loaded from: classes3.dex */
        public static final class RunnableC4248a implements Runnable {

            /* renamed from: a */
            final long f18486a;

            /* renamed from: b */
            final C4247a<?> f18487b;

            RunnableC4248a(long j, C4247a<?> aVar) {
                this.f18486a = j;
                this.f18487b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4247a<?> aVar = this.f18487b;
                if (!aVar.f20005p) {
                    aVar.f20004o.offer(this);
                } else {
                    aVar.f18484l = true;
                    aVar.m9721a();
                }
                if (aVar.mo9380e()) {
                    aVar.m9719b();
                }
            }
        }
    }

    /* compiled from: FlowableWindowTimed.java */
    /* renamed from: z1.bem$c */
    /* loaded from: classes3.dex */
    static final class RunnableC4250c<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Runnable, dby {

        /* renamed from: a */
        final long f18497a;

        /* renamed from: b */
        final long f18498b;

        /* renamed from: c */
        final TimeUnit f18499c;

        /* renamed from: d */
        final Scheduler.AbstractC3881c f18500d;

        /* renamed from: e */
        final int f18501e;

        /* renamed from: f */
        final List<UnicastProcessor<T>> f18502f = new LinkedList();

        /* renamed from: g */
        dby f18503g;

        /* renamed from: h */
        volatile boolean f18504h;

        RunnableC4250c(Subscriber<? super Flowable<T>> dbxVar, long j, long j2, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, int i) {
            super(dbxVar, new MpscLinkedQueue());
            this.f18497a = j;
            this.f18498b = j2;
            this.f18499c = timeUnit;
            this.f18500d = cVar;
            this.f18501e = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18503g, dbyVar)) {
                this.f18503g = dbyVar;
                this.f20003n.onSubscribe(this);
                if (!this.f20005p) {
                    long h = mo9378h();
                    if (h != 0) {
                        UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18501e);
                        this.f18502f.add(m);
                        this.f20003n.onNext(m);
                        if (h != cjm.f20759b) {
                            mo9384a(1L);
                        }
                        this.f18500d.mo9030a(new RunnableC4251a(m), this.f18497a, this.f18499c);
                        Scheduler.AbstractC3881c cVar = this.f18500d;
                        long j = this.f18498b;
                        cVar.mo9511a(this, j, j, this.f18499c);
                        dbyVar.request(cjm.f20759b);
                        return;
                    }
                    dbyVar.cancel();
                    this.f20003n.onError(new MissingBackpressureException("Could not emit the first window due to lack of requests"));
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (m9464f()) {
                for (UnicastProcessor<T> bumVar : this.f18502f) {
                    bumVar.onNext(t);
                }
                if (mo9385a(-1) == 0) {
                    return;
                }
            } else {
                this.f20004o.offer(t);
                if (!mo9380e()) {
                    return;
                }
            }
            m9713b();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f20007r = th;
            this.f20006q = true;
            if (mo9380e()) {
                m9713b();
            }
            this.f20003n.onError(th);
            m9715a();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f20006q = true;
            if (mo9380e()) {
                m9713b();
            }
            this.f20003n.onComplete();
            m9715a();
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f20005p = true;
        }

        /* renamed from: a */
        public void m9715a() {
            this.f18500d.dispose();
        }

        /* renamed from: a */
        void m9714a(UnicastProcessor<T> bumVar) {
            this.f20004o.offer(new C4252b(bumVar, false));
            if (mo9380e()) {
                m9713b();
            }
        }

        /* renamed from: b */
        void m9713b() {
            SimpleQueue avwVar = this.f20004o;
            Subscriber<? super V> dbxVar = this.f20003n;
            List<UnicastProcessor<T>> list = this.f18502f;
            int i = 1;
            while (!this.f18504h) {
                boolean z = this.f20006q;
                T t = (T) avwVar.poll();
                boolean z2 = t == null;
                boolean z3 = t instanceof C4252b;
                if (z && (z2 || z3)) {
                    avwVar.clear();
                    Throwable th = this.f20007r;
                    if (th != null) {
                        for (UnicastProcessor<T> bumVar : list) {
                            bumVar.onError(th);
                        }
                    } else {
                        for (UnicastProcessor<T> bumVar2 : list) {
                            bumVar2.onComplete();
                        }
                    }
                    list.clear();
                    m9715a();
                    return;
                } else if (z2) {
                    i = mo9385a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    C4252b bVar = (C4252b) t;
                    if (!bVar.f18508b) {
                        list.remove(bVar.f18507a);
                        bVar.f18507a.onComplete();
                        if (list.isEmpty() && this.f20005p) {
                            this.f18504h = true;
                        }
                    } else if (!this.f20005p) {
                        long h = mo9378h();
                        if (h != 0) {
                            UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18501e);
                            list.add(m);
                            dbxVar.onNext(m);
                            if (h != cjm.f20759b) {
                                mo9384a(1L);
                            }
                            this.f18500d.mo9030a(new RunnableC4251a(m), this.f18497a, this.f18499c);
                        } else {
                            dbxVar.onError(new MissingBackpressureException("Can't emit window due to lack of requests"));
                        }
                    }
                } else {
                    for (UnicastProcessor<T> bumVar3 : list) {
                        bumVar3.onNext(t);
                    }
                }
            }
            this.f18503g.cancel();
            m9715a();
            avwVar.clear();
            list.clear();
        }

        @Override // java.lang.Runnable
        public void run() {
            C4252b bVar = new C4252b(UnicastProcessor.m9051m(this.f18501e), true);
            if (!this.f20005p) {
                this.f20004o.offer(bVar);
            }
            if (mo9380e()) {
                m9713b();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableWindowTimed.java */
        /* renamed from: z1.bem$c$b */
        /* loaded from: classes3.dex */
        public static final class C4252b<T> {

            /* renamed from: a */
            final UnicastProcessor<T> f18507a;

            /* renamed from: b */
            final boolean f18508b;

            C4252b(UnicastProcessor<T> bumVar, boolean z) {
                this.f18507a = bumVar;
                this.f18508b = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableWindowTimed.java */
        /* renamed from: z1.bem$c$a */
        /* loaded from: classes3.dex */
        public final class RunnableC4251a implements Runnable {

            /* renamed from: b */
            private final UnicastProcessor<T> f18506b;

            RunnableC4251a(UnicastProcessor<T> bumVar) {
                this.f18506b = bumVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                RunnableC4250c.this.m9714a(this.f18506b);
            }
        }
    }
}
