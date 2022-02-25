package p110z1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buk */
/* loaded from: classes3.dex */
public final class ReplayProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: b */
    final AbstractC4765b<T> f20207b;

    /* renamed from: c */
    boolean f20208c;

    /* renamed from: d */
    final AtomicReference<C4766c<T>[]> f20209d = new AtomicReference<>(f20204e);

    /* renamed from: g */
    private static final Object[] f20206g = new Object[0];

    /* renamed from: e */
    static final C4766c[] f20204e = new C4766c[0];

    /* renamed from: f */
    static final C4766c[] f20205f = new C4766c[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4765b<T> {
        /* renamed from: a */
        void mo9076a();

        /* renamed from: a */
        void mo9075a(T t);

        /* renamed from: a */
        void mo9074a(Throwable th);

        /* renamed from: a */
        void mo9073a(C4766c<T> cVar);

        /* renamed from: a */
        T[] mo9072a(T[] tArr);

        /* renamed from: b */
        int mo9071b();

        @atn
        /* renamed from: c */
        T mo9070c();

        /* renamed from: d */
        boolean mo9069d();

        /* renamed from: e */
        Throwable mo9068e();

        /* renamed from: f */
        void mo9067f();
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> ReplayProcessor<T> m9096T() {
        return new ReplayProcessor<>(new C4770g(16));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public static <T> ReplayProcessor<T> m9084m(int i) {
        return new ReplayProcessor<>(new C4770g(i));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: n */
    public static <T> ReplayProcessor<T> m9083n(int i) {
        return new ReplayProcessor<>(new C4768e(i));
    }

    /* renamed from: Y */
    static <T> ReplayProcessor<T> m9095Y() {
        return new ReplayProcessor<>(new C4768e(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: s */
    public static <T> ReplayProcessor<T> m9082s(long j, TimeUnit timeUnit, Scheduler astVar) {
        return new ReplayProcessor<>(new C4767d(Integer.MAX_VALUE, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> ReplayProcessor<T> m9087b(long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return new ReplayProcessor<>(new C4767d(i, j, timeUnit, astVar));
    }

    ReplayProcessor(AbstractC4765b<T> bVar) {
        this.f20207b = bVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4766c<T> cVar = new C4766c<>(dbxVar, this);
        dbxVar.onSubscribe(cVar);
        if (!m9093a((C4766c) cVar) || !cVar.cancelled) {
            this.f20207b.mo9073a((C4766c) cVar);
        } else {
            m9086b((C4766c) cVar);
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.f20208c) {
            dbyVar.cancel();
        } else {
            dbyVar.request(cjm.f20759b);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20208c) {
            AbstractC4765b<T> bVar = this.f20207b;
            bVar.mo9075a((AbstractC4765b<T>) t);
            for (C4766c<T> cVar : this.f20209d.get()) {
                bVar.mo9073a((C4766c) cVar);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20208c) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20208c = true;
        AbstractC4765b<T> bVar = this.f20207b;
        bVar.mo9074a(th);
        for (C4766c<T> cVar : this.f20209d.getAndSet(f20205f)) {
            bVar.mo9073a((C4766c) cVar);
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20208c) {
            this.f20208c = true;
            AbstractC4765b<T> bVar = this.f20207b;
            bVar.mo9076a();
            for (C4766c<T> cVar : this.f20209d.getAndSet(f20205f)) {
                bVar.mo9073a((C4766c) cVar);
            }
        }
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20209d.get().length != 0;
    }

    /* renamed from: Z */
    int m9094Z() {
        return this.f20209d.get().length;
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        AbstractC4765b<T> bVar = this.f20207b;
        if (bVar.mo9069d()) {
            return bVar.mo9068e();
        }
        return null;
    }

    /* renamed from: aa */
    public void m9092aa() {
        this.f20207b.mo9067f();
    }

    /* renamed from: ab */
    public T m9091ab() {
        return this.f20207b.mo9070c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: ad */
    public Object[] m9090ad() {
        Object[] c = m9085c(f20206g);
        return c == f20206g ? new Object[0] : c;
    }

    /* renamed from: c */
    public T[] m9085c(T[] tArr) {
        return this.f20207b.mo9072a((Object[]) tArr);
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        AbstractC4765b<T> bVar = this.f20207b;
        return bVar.mo9069d() && bVar.mo9068e() == null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        AbstractC4765b<T> bVar = this.f20207b;
        return bVar.mo9069d() && bVar.mo9068e() != null;
    }

    /* renamed from: ae */
    public boolean m9089ae() {
        return this.f20207b.mo9071b() != 0;
    }

    /* renamed from: af */
    int m9088af() {
        return this.f20207b.mo9071b();
    }

    /* renamed from: a */
    boolean m9093a(C4766c<T> cVar) {
        C4766c<T>[] cVarArr;
        C4766c<T>[] cVarArr2;
        do {
            cVarArr = this.f20209d.get();
            if (cVarArr == f20205f) {
                return false;
            }
            int length = cVarArr.length;
            cVarArr2 = new C4766c[length + 1];
            System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
            cVarArr2[length] = cVar;
        } while (!this.f20209d.compareAndSet(cVarArr, cVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9086b(C4766c<T> cVar) {
        C4766c<T>[] cVarArr;
        C4766c<T>[] cVarArr2;
        do {
            cVarArr = this.f20209d.get();
            if (cVarArr != f20205f && cVarArr != f20204e) {
                int length = cVarArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cVarArr[i2] == cVar) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        cVarArr2 = f20204e;
                    } else {
                        C4766c<T>[] cVarArr3 = new C4766c[length - 1];
                        System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                        System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                        cVarArr2 = cVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.f20209d.compareAndSet(cVarArr, cVarArr2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$c */
    /* loaded from: classes3.dex */
    public static final class C4766c<T> extends AtomicInteger implements dby {
        private static final long serialVersionUID = 466549804534799122L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        long emitted;
        Object index;
        final AtomicLong requested = new AtomicLong();
        final ReplayProcessor<T> state;

        C4766c(Subscriber<? super T> dbxVar, ReplayProcessor<T> bukVar) {
            this.downstream = dbxVar;
            this.state = bukVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                this.state.f20207b.mo9073a((C4766c) this);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.m9086b((C4766c) this);
            }
        }
    }

    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$g */
    /* loaded from: classes3.dex */
    static final class C4770g<T> implements AbstractC4765b<T> {

        /* renamed from: a */
        final List<T> f20225a;

        /* renamed from: b */
        Throwable f20226b;

        /* renamed from: c */
        volatile boolean f20227c;

        /* renamed from: d */
        volatile int f20228d;

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: f */
        public void mo9067f() {
        }

        C4770g(int i) {
            this.f20225a = new ArrayList(ObjectHelper.m9878a(i, "capacityHint"));
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9075a(T t) {
            this.f20225a.add(t);
            this.f20228d++;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9074a(Throwable th) {
            this.f20226b = th;
            this.f20227c = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9076a() {
            this.f20227c = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        @atn
        /* renamed from: c */
        public T mo9070c() {
            int i = this.f20228d;
            if (i == 0) {
                return null;
            }
            return this.f20225a.get(i - 1);
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public T[] mo9072a(T[] tArr) {
            int i = this.f20228d;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            List<T> list = this.f20225a;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
            if (r10 != 0) goto L_0x007c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
            if (r14.cancelled == false) goto L_0x0064;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
            r14.index = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0063, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
            r7 = r13.f20227c;
            r8 = r13.f20228d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r7 == false) goto L_0x007c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006a, code lost:
            if (r3 != r8) goto L_0x007c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006c, code lost:
            r14.index = null;
            r14.cancelled = true;
            r14 = r13.f20226b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0072, code lost:
            if (r14 != null) goto L_0x0078;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0074, code lost:
            r1.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
            r1.onError(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x007b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
            r14.index = java.lang.Integer.valueOf(r3);
            r14.emitted = r4;
            r6 = r14.addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
            return;
         */
        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo9073a(p110z1.ReplayProcessor.C4766c<T> r14) {
            /*
                r13 = this;
                int r0 = r14.getAndIncrement()
                if (r0 == 0) goto L_0x0007
                return
            L_0x0007:
                java.util.List<T> r0 = r13.f20225a
                z1.dbx<? super T> r1 = r14.downstream
                java.lang.Object r2 = r14.index
                java.lang.Integer r2 = (java.lang.Integer) r2
                r3 = 0
                if (r2 == 0) goto L_0x0017
                int r3 = r2.intValue()
                goto L_0x001d
            L_0x0017:
                java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
                r14.index = r2
            L_0x001d:
                long r4 = r14.emitted
                r2 = 1
                r6 = 1
            L_0x0021:
                java.util.concurrent.atomic.AtomicLong r7 = r14.requested
                long r7 = r7.get()
            L_0x0027:
                r9 = 0
                int r10 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r10 == 0) goto L_0x005b
                boolean r11 = r14.cancelled
                if (r11 == 0) goto L_0x0033
                r14.index = r9
                return
            L_0x0033:
                boolean r11 = r13.f20227c
                int r12 = r13.f20228d
                if (r11 == 0) goto L_0x004b
                if (r3 != r12) goto L_0x004b
                r14.index = r9
                r14.cancelled = r2
                java.lang.Throwable r14 = r13.f20226b
                if (r14 != 0) goto L_0x0047
                r1.onComplete()
                goto L_0x004a
            L_0x0047:
                r1.onError(r14)
            L_0x004a:
                return
            L_0x004b:
                if (r3 != r12) goto L_0x004e
                goto L_0x005b
            L_0x004e:
                java.lang.Object r9 = r0.get(r3)
                r1.onNext(r9)
                int r3 = r3 + 1
                r9 = 1
                long r4 = r4 + r9
                goto L_0x0027
            L_0x005b:
                if (r10 != 0) goto L_0x007c
                boolean r7 = r14.cancelled
                if (r7 == 0) goto L_0x0064
                r14.index = r9
                return
            L_0x0064:
                boolean r7 = r13.f20227c
                int r8 = r13.f20228d
                if (r7 == 0) goto L_0x007c
                if (r3 != r8) goto L_0x007c
                r14.index = r9
                r14.cancelled = r2
                java.lang.Throwable r14 = r13.f20226b
                if (r14 != 0) goto L_0x0078
                r1.onComplete()
                goto L_0x007b
            L_0x0078:
                r1.onError(r14)
            L_0x007b:
                return
            L_0x007c:
                java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
                r14.index = r7
                r14.emitted = r4
                int r6 = -r6
                int r6 = r14.addAndGet(r6)
                if (r6 != 0) goto L_0x0021
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ReplayProcessor.C4770g.mo9073a(z1.buk$c):void");
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: b */
        public int mo9071b() {
            return this.f20228d;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: d */
        public boolean mo9069d() {
            return this.f20227c;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: e */
        public Throwable mo9068e() {
            return this.f20226b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$a */
    /* loaded from: classes3.dex */
    public static final class C4764a<T> extends AtomicReference<C4764a<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        C4764a(T t) {
            this.value = t;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$f */
    /* loaded from: classes3.dex */
    public static final class C4769f<T> extends AtomicReference<C4769f<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        C4769f(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$e */
    /* loaded from: classes3.dex */
    static final class C4768e<T> implements AbstractC4765b<T> {

        /* renamed from: a */
        final int f20219a;

        /* renamed from: b */
        int f20220b;

        /* renamed from: c */
        volatile C4764a<T> f20221c;

        /* renamed from: d */
        C4764a<T> f20222d;

        /* renamed from: e */
        Throwable f20223e;

        /* renamed from: f */
        volatile boolean f20224f;

        C4768e(int i) {
            this.f20219a = ObjectHelper.m9878a(i, "maxSize");
            C4764a<T> aVar = new C4764a<>(null);
            this.f20222d = aVar;
            this.f20221c = aVar;
        }

        /* renamed from: g */
        void m9077g() {
            int i = this.f20220b;
            if (i > this.f20219a) {
                this.f20220b = i - 1;
                this.f20221c = this.f20221c.get();
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9075a(T t) {
            C4764a<T> aVar = new C4764a<>(t);
            C4764a<T> aVar2 = this.f20222d;
            this.f20222d = aVar;
            this.f20220b++;
            aVar2.set(aVar);
            m9077g();
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9074a(Throwable th) {
            this.f20223e = th;
            mo9067f();
            this.f20224f = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9076a() {
            mo9067f();
            this.f20224f = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: f */
        public void mo9067f() {
            if (this.f20221c.value != null) {
                C4764a<T> aVar = new C4764a<>(null);
                aVar.lazySet(this.f20221c.get());
                this.f20221c = aVar;
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: d */
        public boolean mo9069d() {
            return this.f20224f;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: e */
        public Throwable mo9068e() {
            return this.f20223e;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: c */
        public T mo9070c() {
            C4764a<T> aVar = this.f20221c;
            while (true) {
                C4764a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    return aVar.value;
                }
                aVar = aVar2;
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public T[] mo9072a(T[] tArr) {
            C4764a<T> aVar = this.f20221c;
            C4764a<T> aVar2 = aVar;
            int i = 0;
            while (true) {
                aVar2 = aVar2.get();
                if (aVar2 == null) {
                    break;
                }
                i++;
            }
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                aVar = aVar.get();
                tArr[i2] = aVar.value;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9073a(C4766c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = cVar.downstream;
                C4764a<T> aVar = (C4764a) cVar.index;
                if (aVar == null) {
                    aVar = this.f20221c;
                }
                long j = cVar.emitted;
                int i2 = 1;
                do {
                    long j2 = cVar.requested.get();
                    while (true) {
                        i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (cVar.cancelled) {
                            cVar.index = null;
                            return;
                        } else {
                            boolean z = this.f20224f;
                            C4764a<T> aVar2 = aVar.get();
                            boolean z2 = aVar2 == null;
                            if (z && z2) {
                                cVar.index = null;
                                cVar.cancelled = true;
                                Throwable th = this.f20223e;
                                if (th == null) {
                                    dbxVar.onComplete();
                                    return;
                                } else {
                                    dbxVar.onError(th);
                                    return;
                                }
                            } else if (z2) {
                                break;
                            } else {
                                dbxVar.onNext((T) aVar2.value);
                                j++;
                                aVar = aVar2;
                            }
                        }
                    }
                    if (i == 0) {
                        if (cVar.cancelled) {
                            cVar.index = null;
                            return;
                        } else if (this.f20224f && aVar.get() == null) {
                            cVar.index = null;
                            cVar.cancelled = true;
                            Throwable th2 = this.f20223e;
                            if (th2 == null) {
                                dbxVar.onComplete();
                                return;
                            } else {
                                dbxVar.onError(th2);
                                return;
                            }
                        }
                    }
                    cVar.index = aVar;
                    cVar.emitted = j;
                    i2 = cVar.addAndGet(-i2);
                } while (i2 != 0);
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: b */
        public int mo9071b() {
            C4764a<T> aVar = this.f20221c;
            int i = 0;
            while (i != Integer.MAX_VALUE && (aVar = aVar.get()) != null) {
                i++;
            }
            return i;
        }
    }

    /* compiled from: ReplayProcessor.java */
    /* renamed from: z1.buk$d */
    /* loaded from: classes3.dex */
    static final class C4767d<T> implements AbstractC4765b<T> {

        /* renamed from: a */
        final int f20210a;

        /* renamed from: b */
        final long f20211b;

        /* renamed from: c */
        final TimeUnit f20212c;

        /* renamed from: d */
        final Scheduler f20213d;

        /* renamed from: e */
        int f20214e;

        /* renamed from: f */
        volatile C4769f<T> f20215f;

        /* renamed from: g */
        C4769f<T> f20216g;

        /* renamed from: h */
        Throwable f20217h;

        /* renamed from: i */
        volatile boolean f20218i;

        C4767d(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f20210a = ObjectHelper.m9878a(i, "maxSize");
            this.f20211b = ObjectHelper.m9876a(j, "maxAge");
            this.f20212c = (TimeUnit) ObjectHelper.m9873a(timeUnit, "unit is null");
            this.f20213d = (Scheduler) ObjectHelper.m9873a(astVar, "scheduler is null");
            C4769f<T> fVar = new C4769f<>(null, 0L);
            this.f20216g = fVar;
            this.f20215f = fVar;
        }

        /* renamed from: g */
        void m9080g() {
            int i = this.f20214e;
            if (i > this.f20210a) {
                this.f20214e = i - 1;
                this.f20215f = this.f20215f.get();
            }
            long a = this.f20213d.mo9035a(this.f20212c) - this.f20211b;
            C4769f<T> fVar = this.f20215f;
            while (true) {
                C4769f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    this.f20215f = fVar;
                    return;
                } else if (fVar2.time > a) {
                    this.f20215f = fVar;
                    return;
                } else {
                    fVar = fVar2;
                }
            }
        }

        /* renamed from: h */
        void m9079h() {
            long a = this.f20213d.mo9035a(this.f20212c) - this.f20211b;
            C4769f<T> fVar = this.f20215f;
            while (true) {
                C4769f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    if (fVar.value != null) {
                        this.f20215f = new C4769f<>(null, 0L);
                        return;
                    } else {
                        this.f20215f = fVar;
                        return;
                    }
                } else if (fVar2.time <= a) {
                    fVar = fVar2;
                } else if (fVar.value != null) {
                    C4769f<T> fVar3 = new C4769f<>(null, 0L);
                    fVar3.lazySet(fVar.get());
                    this.f20215f = fVar3;
                    return;
                } else {
                    this.f20215f = fVar;
                    return;
                }
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: f */
        public void mo9067f() {
            if (this.f20215f.value != null) {
                C4769f<T> fVar = new C4769f<>(null, 0L);
                fVar.lazySet(this.f20215f.get());
                this.f20215f = fVar;
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9075a(T t) {
            C4769f<T> fVar = new C4769f<>(t, this.f20213d.mo9035a(this.f20212c));
            C4769f<T> fVar2 = this.f20216g;
            this.f20216g = fVar;
            this.f20214e++;
            fVar2.set(fVar);
            m9080g();
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9074a(Throwable th) {
            m9079h();
            this.f20217h = th;
            this.f20218i = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9076a() {
            m9079h();
            this.f20218i = true;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        @atn
        /* renamed from: c */
        public T mo9070c() {
            C4769f<T> fVar = this.f20215f;
            while (true) {
                C4769f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    break;
                }
                fVar = fVar2;
            }
            if (fVar.time < this.f20213d.mo9035a(this.f20212c) - this.f20211b) {
                return null;
            }
            return fVar.value;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public T[] mo9072a(T[] tArr) {
            C4769f<T> i = m9078i();
            int a = m9081a((C4769f) i);
            if (a != 0) {
                if (tArr.length < a) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), a));
                }
                for (int i2 = 0; i2 != a; i2++) {
                    i = i.get();
                    tArr[i2] = i.value;
                }
                if (tArr.length > a) {
                    tArr[a] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        /* renamed from: i */
        C4769f<T> m9078i() {
            C4769f<T> fVar = this.f20215f;
            long a = this.f20213d.mo9035a(this.f20212c) - this.f20211b;
            C4769f<T> fVar2 = fVar.get();
            C4769f<T> fVar3 = fVar;
            while (fVar2 != null && fVar2.time <= a) {
                fVar2 = fVar2.get();
                fVar3 = fVar2;
            }
            return fVar3;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: a */
        public void mo9073a(C4766c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = cVar.downstream;
                C4769f<T> fVar = (C4769f) cVar.index;
                if (fVar == null) {
                    fVar = m9078i();
                }
                long j = cVar.emitted;
                int i2 = 1;
                do {
                    long j2 = cVar.requested.get();
                    while (true) {
                        i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (cVar.cancelled) {
                            cVar.index = null;
                            return;
                        } else {
                            boolean z = this.f20218i;
                            C4769f<T> fVar2 = fVar.get();
                            boolean z2 = fVar2 == null;
                            if (z && z2) {
                                cVar.index = null;
                                cVar.cancelled = true;
                                Throwable th = this.f20217h;
                                if (th == null) {
                                    dbxVar.onComplete();
                                    return;
                                } else {
                                    dbxVar.onError(th);
                                    return;
                                }
                            } else if (z2) {
                                break;
                            } else {
                                dbxVar.onNext((T) fVar2.value);
                                j++;
                                fVar = fVar2;
                            }
                        }
                    }
                    if (i == 0) {
                        if (cVar.cancelled) {
                            cVar.index = null;
                            return;
                        } else if (this.f20218i && fVar.get() == null) {
                            cVar.index = null;
                            cVar.cancelled = true;
                            Throwable th2 = this.f20217h;
                            if (th2 == null) {
                                dbxVar.onComplete();
                                return;
                            } else {
                                dbxVar.onError(th2);
                                return;
                            }
                        }
                    }
                    cVar.index = fVar;
                    cVar.emitted = j;
                    i2 = cVar.addAndGet(-i2);
                } while (i2 != 0);
            }
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: b */
        public int mo9071b() {
            return m9081a((C4769f) m9078i());
        }

        /* renamed from: a */
        int m9081a(C4769f<T> fVar) {
            int i = 0;
            while (i != Integer.MAX_VALUE && (fVar = fVar.get()) != null) {
                i++;
            }
            return i;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: e */
        public Throwable mo9068e() {
            return this.f20217h;
        }

        @Override // p110z1.ReplayProcessor.AbstractC4765b
        /* renamed from: d */
        public boolean mo9069d() {
            return this.f20218i;
        }
    }
}
