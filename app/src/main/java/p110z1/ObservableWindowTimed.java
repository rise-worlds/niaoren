package p110z1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bnn */
/* loaded from: classes3.dex */
public final class ObservableWindowTimed<T> extends AbstractObservableWithUpstream<T, Observable<T>> {

    /* renamed from: b */
    final long f19511b;

    /* renamed from: c */
    final long f19512c;

    /* renamed from: d */
    final TimeUnit f19513d;

    /* renamed from: e */
    final Scheduler f19514e;

    /* renamed from: f */
    final long f19515f;

    /* renamed from: g */
    final int f19516g;

    /* renamed from: h */
    final boolean f19517h;

    public ObservableWindowTimed(ObservableSource<T> asqVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, long j3, int i, boolean z) {
        super(asqVar);
        this.f19511b = j;
        this.f19512c = j2;
        this.f19513d = timeUnit;
        this.f19514e = astVar;
        this.f19515f = j3;
        this.f19516g = i;
        this.f19517h = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Observable<T>> assVar) {
        SerializedObserver btyVar = new SerializedObserver(assVar);
        if (this.f19511b != this.f19512c) {
            this.f18807a.subscribe(new RunnableC4607c(btyVar, this.f19511b, this.f19512c, this.f19513d, this.f19514e.mo9034b(), this.f19516g));
        } else if (this.f19515f == cjm.f20759b) {
            this.f18807a.subscribe(new RunnableC4606b(btyVar, this.f19511b, this.f19513d, this.f19514e, this.f19516g));
        } else {
            this.f18807a.subscribe(new C4604a(btyVar, this.f19511b, this.f19513d, this.f19514e, this.f19516g, this.f19515f, this.f19517h));
        }
    }

    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: z1.bnn$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4606b<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Runnable, Observer<T>, Disposable {

        /* renamed from: R */
        static final Object f19533R = new Object();

        /* renamed from: K */
        final long f19534K;

        /* renamed from: L */
        final TimeUnit f19535L;

        /* renamed from: M */
        final Scheduler f19536M;

        /* renamed from: N */
        final int f19537N;

        /* renamed from: O */
        Disposable f19538O;

        /* renamed from: P */
        UnicastSubject<T> f19539P;

        /* renamed from: Q */
        final AtomicReference<Disposable> f19540Q = new AtomicReference<>();

        /* renamed from: S */
        volatile boolean f19541S;

        RunnableC4606b(Observer<? super Observable<T>> assVar, long j, TimeUnit timeUnit, Scheduler astVar, int i) {
            super(assVar, new MpscLinkedQueue());
            this.f19534K = j;
            this.f19535L = timeUnit;
            this.f19536M = astVar;
            this.f19537N = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19538O, atrVar)) {
                this.f19538O = atrVar;
                this.f19539P = UnicastSubject.m8933i(this.f19537N);
                Observer<? super V> assVar = this.f17632a;
                assVar.onSubscribe(this);
                assVar.onNext(this.f19539P);
                if (!this.f17634c) {
                    Scheduler astVar = this.f19536M;
                    long j = this.f19534K;
                    DisposableHelper.replace(this.f19540Q, astVar.mo9485a(this, j, j, this.f19535L));
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19541S) {
                if (m9841d()) {
                    this.f19539P.onNext(t);
                    if (mo9399a(-1) == 0) {
                        return;
                    }
                } else {
                    this.f17633b.offer(NotificationLite.next(t));
                    if (!mo9396c()) {
                        return;
                    }
                }
                m9561g();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f17636e = th;
            this.f17635d = true;
            if (mo9396c()) {
                m9561g();
            }
            m9562f();
            this.f17632a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f17635d = true;
            if (mo9396c()) {
                m9561g();
            }
            m9562f();
            this.f17632a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17634c = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        /* renamed from: f */
        void m9562f() {
            DisposableHelper.dispose(this.f19540Q);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f17634c) {
                this.f19541S = true;
                m9562f();
            }
            this.f17633b.offer(f19533R);
            if (mo9396c()) {
                m9561g();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
            r2.onError(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
            r7.f19539P = null;
            r0.clear();
            m9562f();
            r0 = r7.f17636e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0024, code lost:
            if (r0 == null) goto L_0x002a;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: g */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void m9561g() {
            /*
                r7 = this;
                z1.avv<U> r0 = r7.f17633b
                z1.bqj r0 = (p110z1.MpscLinkedQueue) r0
                z1.ass<? super V> r1 = r7.f17632a
                z1.bva<T> r2 = r7.f19539P
                r3 = 1
            L_0x0009:
                boolean r4 = r7.f19541S
                boolean r5 = r7.f17635d
                java.lang.Object r6 = r0.poll()
                if (r5 == 0) goto L_0x002e
                if (r6 == 0) goto L_0x0019
                java.lang.Object r5 = p110z1.ObservableWindowTimed.RunnableC4606b.f19533R
                if (r6 != r5) goto L_0x002e
            L_0x0019:
                r1 = 0
                r7.f19539P = r1
                r0.clear()
                r7.m9562f()
                java.lang.Throwable r0 = r7.f17636e
                if (r0 == 0) goto L_0x002a
                r2.onError(r0)
                goto L_0x002d
            L_0x002a:
                r2.onComplete()
            L_0x002d:
                return
            L_0x002e:
                if (r6 != 0) goto L_0x0038
                int r3 = -r3
                int r3 = r7.mo9399a(r3)
                if (r3 != 0) goto L_0x0009
                return
            L_0x0038:
                java.lang.Object r5 = p110z1.ObservableWindowTimed.RunnableC4606b.f19533R
                if (r6 != r5) goto L_0x0053
                r2.onComplete()
                if (r4 != 0) goto L_0x004d
                int r2 = r7.f19537N
                z1.bva r2 = p110z1.UnicastSubject.m8933i(r2)
                r7.f19539P = r2
                r1.onNext(r2)
                goto L_0x0009
            L_0x004d:
                z1.atr r4 = r7.f19538O
                r4.dispose()
                goto L_0x0009
            L_0x0053:
                java.lang.Object r4 = p110z1.NotificationLite.getValue(r6)
                r2.onNext(r4)
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableWindowTimed.RunnableC4606b.m9561g():void");
        }
    }

    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: z1.bnn$a */
    /* loaded from: classes3.dex */
    static final class C4604a<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {

        /* renamed from: K */
        final long f19518K;

        /* renamed from: L */
        final TimeUnit f19519L;

        /* renamed from: M */
        final Scheduler f19520M;

        /* renamed from: N */
        final int f19521N;

        /* renamed from: O */
        final boolean f19522O;

        /* renamed from: P */
        final long f19523P;

        /* renamed from: Q */
        final Scheduler.AbstractC3881c f19524Q;

        /* renamed from: R */
        long f19525R;

        /* renamed from: S */
        long f19526S;

        /* renamed from: T */
        Disposable f19527T;

        /* renamed from: U */
        UnicastSubject<T> f19528U;

        /* renamed from: V */
        volatile boolean f19529V;

        /* renamed from: W */
        final AtomicReference<Disposable> f19530W = new AtomicReference<>();

        C4604a(Observer<? super Observable<T>> assVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, long j2, boolean z) {
            super(assVar, new MpscLinkedQueue());
            this.f19518K = j;
            this.f19519L = timeUnit;
            this.f19520M = astVar;
            this.f19521N = i;
            this.f19523P = j2;
            this.f19522O = z;
            if (z) {
                this.f19524Q = astVar.mo9034b();
            } else {
                this.f19524Q = null;
            }
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            Disposable atrVar2;
            if (DisposableHelper.validate(this.f19527T, atrVar)) {
                this.f19527T = atrVar;
                Observer<? super V> assVar = this.f17632a;
                assVar.onSubscribe(this);
                if (!this.f17634c) {
                    UnicastSubject<T> i = UnicastSubject.m8933i(this.f19521N);
                    this.f19528U = i;
                    assVar.onNext(i);
                    RunnableC4605a aVar = new RunnableC4605a(this.f19526S, this);
                    if (this.f19522O) {
                        Scheduler.AbstractC3881c cVar = this.f19524Q;
                        long j = this.f19518K;
                        atrVar2 = cVar.mo9511a(aVar, j, j, this.f19519L);
                    } else {
                        Scheduler astVar = this.f19520M;
                        long j2 = this.f19518K;
                        atrVar2 = astVar.mo9485a(aVar, j2, j2, this.f19519L);
                    }
                    DisposableHelper.replace(this.f19530W, atrVar2);
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19529V) {
                if (m9841d()) {
                    UnicastSubject<T> bvaVar = this.f19528U;
                    bvaVar.onNext(t);
                    long j = this.f19525R + 1;
                    if (j >= this.f19523P) {
                        this.f19526S++;
                        this.f19525R = 0L;
                        bvaVar.onComplete();
                        UnicastSubject<T> i = UnicastSubject.m8933i(this.f19521N);
                        this.f19528U = i;
                        this.f17632a.onNext(i);
                        if (this.f19522O) {
                            this.f19530W.get().dispose();
                            Scheduler.AbstractC3881c cVar = this.f19524Q;
                            RunnableC4605a aVar = new RunnableC4605a(this.f19526S, this);
                            long j2 = this.f19518K;
                            DisposableHelper.replace(this.f19530W, cVar.mo9511a(aVar, j2, j2, this.f19519L));
                        }
                    } else {
                        this.f19525R = j;
                    }
                    if (mo9399a(-1) == 0) {
                        return;
                    }
                } else {
                    this.f17633b.offer(NotificationLite.next(t));
                    if (!mo9396c()) {
                        return;
                    }
                }
                m9563g();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f17636e = th;
            this.f17635d = true;
            if (mo9396c()) {
                m9563g();
            }
            this.f17632a.onError(th);
            m9564f();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f17635d = true;
            if (mo9396c()) {
                m9563g();
            }
            this.f17632a.onComplete();
            m9564f();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17634c = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        /* renamed from: f */
        void m9564f() {
            DisposableHelper.dispose(this.f19530W);
            Scheduler.AbstractC3881c cVar = this.f19524Q;
            if (cVar != null) {
                cVar.dispose();
            }
        }

        /* renamed from: g */
        void m9563g() {
            MpscLinkedQueue bqjVar = (MpscLinkedQueue) this.f17633b;
            Observer<? super V> assVar = this.f17632a;
            UnicastSubject<T> bvaVar = this.f19528U;
            int i = 1;
            while (!this.f19529V) {
                boolean z = this.f17635d;
                Object poll = bqjVar.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof RunnableC4605a;
                if (z && (z2 || z3)) {
                    this.f19528U = null;
                    bqjVar.clear();
                    m9564f();
                    Throwable th = this.f17636e;
                    if (th != null) {
                        bvaVar.onError(th);
                        return;
                    } else {
                        bvaVar.onComplete();
                        return;
                    }
                } else if (z2) {
                    i = mo9399a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    RunnableC4605a aVar = (RunnableC4605a) poll;
                    if (this.f19522O || this.f19526S == aVar.f19531a) {
                        bvaVar.onComplete();
                        this.f19525R = 0L;
                        bvaVar = UnicastSubject.m8933i(this.f19521N);
                        this.f19528U = bvaVar;
                        assVar.onNext(bvaVar);
                    }
                } else {
                    bvaVar.onNext((T) NotificationLite.getValue(poll));
                    long j = this.f19525R + 1;
                    if (j >= this.f19523P) {
                        this.f19526S++;
                        this.f19525R = 0L;
                        bvaVar.onComplete();
                        bvaVar = UnicastSubject.m8933i(this.f19521N);
                        this.f19528U = bvaVar;
                        this.f17632a.onNext(bvaVar);
                        if (this.f19522O) {
                            Disposable atrVar = this.f19530W.get();
                            atrVar.dispose();
                            Scheduler.AbstractC3881c cVar = this.f19524Q;
                            RunnableC4605a aVar2 = new RunnableC4605a(this.f19526S, this);
                            long j2 = this.f19518K;
                            Disposable a = cVar.mo9511a(aVar2, j2, j2, this.f19519L);
                            if (!this.f19530W.compareAndSet(atrVar, a)) {
                                a.dispose();
                            }
                        }
                    } else {
                        this.f19525R = j;
                    }
                }
            }
            this.f19527T.dispose();
            bqjVar.clear();
            m9564f();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: z1.bnn$a$a */
        /* loaded from: classes3.dex */
        public static final class RunnableC4605a implements Runnable {

            /* renamed from: a */
            final long f19531a;

            /* renamed from: b */
            final C4604a<?> f19532b;

            RunnableC4605a(long j, C4604a<?> aVar) {
                this.f19531a = j;
                this.f19532b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4604a<?> aVar = this.f19532b;
                if (!aVar.f17634c) {
                    aVar.f17633b.offer(this);
                } else {
                    aVar.f19529V = true;
                    aVar.m9564f();
                }
                if (aVar.mo9396c()) {
                    aVar.m9563g();
                }
            }
        }
    }

    /* compiled from: ObservableWindowTimed.java */
    /* renamed from: z1.bnn$c */
    /* loaded from: classes3.dex */
    static final class RunnableC4607c<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Runnable, Disposable {

        /* renamed from: K */
        final long f19542K;

        /* renamed from: L */
        final long f19543L;

        /* renamed from: M */
        final TimeUnit f19544M;

        /* renamed from: N */
        final Scheduler.AbstractC3881c f19545N;

        /* renamed from: O */
        final int f19546O;

        /* renamed from: P */
        final List<UnicastSubject<T>> f19547P = new LinkedList();

        /* renamed from: Q */
        Disposable f19548Q;

        /* renamed from: R */
        volatile boolean f19549R;

        RunnableC4607c(Observer<? super Observable<T>> assVar, long j, long j2, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, int i) {
            super(assVar, new MpscLinkedQueue());
            this.f19542K = j;
            this.f19543L = j2;
            this.f19544M = timeUnit;
            this.f19545N = cVar;
            this.f19546O = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19548Q, atrVar)) {
                this.f19548Q = atrVar;
                this.f17632a.onSubscribe(this);
                if (!this.f17634c) {
                    UnicastSubject<T> i = UnicastSubject.m8933i(this.f19546O);
                    this.f19547P.add(i);
                    this.f17632a.onNext(i);
                    this.f19545N.mo9030a(new RunnableC4608a(i), this.f19542K, this.f19544M);
                    Scheduler.AbstractC3881c cVar = this.f19545N;
                    long j = this.f19543L;
                    cVar.mo9511a(this, j, j, this.f19544M);
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (m9841d()) {
                for (UnicastSubject<T> bvaVar : this.f19547P) {
                    bvaVar.onNext(t);
                }
                if (mo9399a(-1) == 0) {
                    return;
                }
            } else {
                this.f17633b.offer(t);
                if (!mo9396c()) {
                    return;
                }
            }
            m9558g();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f17636e = th;
            this.f17635d = true;
            if (mo9396c()) {
                m9558g();
            }
            this.f17632a.onError(th);
            m9559f();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f17635d = true;
            if (mo9396c()) {
                m9558g();
            }
            this.f17632a.onComplete();
            m9559f();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17634c = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        /* renamed from: f */
        void m9559f() {
            this.f19545N.dispose();
        }

        /* renamed from: a */
        void m9560a(UnicastSubject<T> bvaVar) {
            this.f17633b.offer(new C4609b(bvaVar, false));
            if (mo9396c()) {
                m9558g();
            }
        }

        /* renamed from: g */
        void m9558g() {
            MpscLinkedQueue bqjVar = (MpscLinkedQueue) this.f17633b;
            Observer<? super V> assVar = this.f17632a;
            List<UnicastSubject<T>> list = this.f19547P;
            int i = 1;
            while (!this.f19549R) {
                boolean z = this.f17635d;
                T t = (T) bqjVar.poll();
                boolean z2 = t == null;
                boolean z3 = t instanceof C4609b;
                if (z && (z2 || z3)) {
                    bqjVar.clear();
                    Throwable th = this.f17636e;
                    if (th != null) {
                        for (UnicastSubject<T> bvaVar : list) {
                            bvaVar.onError(th);
                        }
                    } else {
                        for (UnicastSubject<T> bvaVar2 : list) {
                            bvaVar2.onComplete();
                        }
                    }
                    m9559f();
                    list.clear();
                    return;
                } else if (z2) {
                    i = mo9399a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    C4609b bVar = (C4609b) t;
                    if (!bVar.f19553b) {
                        list.remove(bVar.f19552a);
                        bVar.f19552a.onComplete();
                        if (list.isEmpty() && this.f17634c) {
                            this.f19549R = true;
                        }
                    } else if (!this.f17634c) {
                        UnicastSubject<T> i2 = UnicastSubject.m8933i(this.f19546O);
                        list.add(i2);
                        assVar.onNext(i2);
                        this.f19545N.mo9030a(new RunnableC4608a(i2), this.f19542K, this.f19544M);
                    }
                } else {
                    for (UnicastSubject<T> bvaVar3 : list) {
                        bvaVar3.onNext(t);
                    }
                }
            }
            this.f19548Q.dispose();
            m9559f();
            bqjVar.clear();
            list.clear();
        }

        @Override // java.lang.Runnable
        public void run() {
            C4609b bVar = new C4609b(UnicastSubject.m8933i(this.f19546O), true);
            if (!this.f17634c) {
                this.f17633b.offer(bVar);
            }
            if (mo9396c()) {
                m9558g();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: z1.bnn$c$b */
        /* loaded from: classes3.dex */
        public static final class C4609b<T> {

            /* renamed from: a */
            final UnicastSubject<T> f19552a;

            /* renamed from: b */
            final boolean f19553b;

            C4609b(UnicastSubject<T> bvaVar, boolean z) {
                this.f19552a = bvaVar;
                this.f19553b = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableWindowTimed.java */
        /* renamed from: z1.bnn$c$a */
        /* loaded from: classes3.dex */
        public final class RunnableC4608a implements Runnable {

            /* renamed from: b */
            private final UnicastSubject<T> f19551b;

            RunnableC4608a(UnicastSubject<T> bvaVar) {
                this.f19551b = bvaVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                RunnableC4607c.this.m9560a(this.f19551b);
            }
        }
    }
}
