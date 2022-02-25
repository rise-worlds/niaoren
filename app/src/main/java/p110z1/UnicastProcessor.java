package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bum */
/* loaded from: classes3.dex */
public final class UnicastProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: b */
    final SpscLinkedArrayQueue<T> f20233b;

    /* renamed from: c */
    final AtomicReference<Runnable> f20234c;

    /* renamed from: d */
    final boolean f20235d;

    /* renamed from: e */
    volatile boolean f20236e;

    /* renamed from: f */
    Throwable f20237f;

    /* renamed from: g */
    final AtomicReference<Subscriber<? super T>> f20238g;

    /* renamed from: h */
    volatile boolean f20239h;

    /* renamed from: i */
    final AtomicBoolean f20240i;

    /* renamed from: j */
    final BasicIntQueueSubscription<T> f20241j;

    /* renamed from: k */
    final AtomicLong f20242k;

    /* renamed from: l */
    boolean f20243l;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> UnicastProcessor<T> m9065T() {
        return new UnicastProcessor<>(m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public static <T> UnicastProcessor<T> m9051m(int i) {
        return new UnicastProcessor<>(i);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> UnicastProcessor<T> m9055b(boolean z) {
        return new UnicastProcessor<>(m11274a(), null, z);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> UnicastProcessor<T> m9058a(int i, Runnable runnable) {
        ObjectHelper.m9873a(runnable, "onTerminate");
        return new UnicastProcessor<>(i, runnable);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> UnicastProcessor<T> m9057a(int i, Runnable runnable, boolean z) {
        ObjectHelper.m9873a(runnable, "onTerminate");
        return new UnicastProcessor<>(i, runnable, z);
    }

    UnicastProcessor(int i) {
        this(i, null, true);
    }

    UnicastProcessor(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    UnicastProcessor(int i, Runnable runnable, boolean z) {
        this.f20233b = new SpscLinkedArrayQueue<>(ObjectHelper.m9878a(i, "capacityHint"));
        this.f20234c = new AtomicReference<>(runnable);
        this.f20235d = z;
        this.f20238g = new AtomicReference<>();
        this.f20240i = new AtomicBoolean();
        this.f20241j = new C4771a();
        this.f20242k = new AtomicLong();
    }

    /* renamed from: Y */
    void m9060Y() {
        Runnable andSet = this.f20234c.getAndSet(null);
        if (andSet != null) {
            andSet.run();
        }
    }

    /* renamed from: f */
    void m9053f(Subscriber<? super T> dbxVar) {
        int i;
        long j;
        SpscLinkedArrayQueue<T> bqlVar = this.f20233b;
        boolean z = !this.f20235d;
        int i2 = 1;
        while (true) {
            long j2 = this.f20242k.get();
            long j3 = 0;
            while (true) {
                i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
                if (i == 0) {
                    j = j3;
                    break;
                }
                boolean z2 = this.f20236e;
                T poll = bqlVar.poll();
                boolean z3 = poll == null;
                j = j3;
                if (!m9056a(z, z2, z3, dbxVar, bqlVar)) {
                    if (z3) {
                        break;
                    }
                    dbxVar.onNext(poll);
                    j3 = 1 + j;
                } else {
                    return;
                }
            }
            if (i != 0 || !m9056a(z, this.f20236e, bqlVar.isEmpty(), dbxVar, bqlVar)) {
                if (!(j == 0 || j2 == cjm.f20759b)) {
                    this.f20242k.addAndGet(-j);
                }
                i2 = this.f20241j.addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: g */
    void m9052g(Subscriber<? super T> dbxVar) {
        SpscLinkedArrayQueue<T> bqlVar = this.f20233b;
        int i = 1;
        boolean z = !this.f20235d;
        while (!this.f20239h) {
            boolean z2 = this.f20236e;
            if (!z || !z2 || this.f20237f == null) {
                dbxVar.onNext(null);
                if (z2) {
                    this.f20238g.lazySet(null);
                    Throwable th = this.f20237f;
                    if (th != null) {
                        dbxVar.onError(th);
                        return;
                    } else {
                        dbxVar.onComplete();
                        return;
                    }
                } else {
                    i = this.f20241j.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            } else {
                bqlVar.clear();
                this.f20238g.lazySet(null);
                dbxVar.onError(this.f20237f);
                return;
            }
        }
        bqlVar.clear();
        this.f20238g.lazySet(null);
    }

    /* renamed from: Z */
    void m9059Z() {
        if (this.f20241j.getAndIncrement() == 0) {
            int i = 1;
            Subscriber<? super T> dbxVar = this.f20238g.get();
            while (dbxVar == null) {
                i = this.f20241j.addAndGet(-i);
                if (i != 0) {
                    dbxVar = this.f20238g.get();
                } else {
                    return;
                }
            }
            if (this.f20243l) {
                m9052g((Subscriber) dbxVar);
            } else {
                m9053f((Subscriber) dbxVar);
            }
        }
    }

    /* renamed from: a */
    boolean m9056a(boolean z, boolean z2, boolean z3, Subscriber<? super T> dbxVar, SpscLinkedArrayQueue<T> bqlVar) {
        if (this.f20239h) {
            bqlVar.clear();
            this.f20238g.lazySet(null);
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (z && this.f20237f != null) {
                bqlVar.clear();
                this.f20238g.lazySet(null);
                dbxVar.onError(this.f20237f);
                return true;
            } else if (!z3) {
                return false;
            } else {
                Throwable th = this.f20237f;
                this.f20238g.lazySet(null);
                if (th != null) {
                    dbxVar.onError(th);
                } else {
                    dbxVar.onComplete();
                }
                return true;
            }
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.f20236e || this.f20239h) {
            dbyVar.cancel();
        } else {
            dbyVar.request(cjm.f20759b);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20236e && !this.f20239h) {
            this.f20233b.offer(t);
            m9059Z();
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20236e || this.f20239h) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20237f = th;
        this.f20236e = true;
        m9060Y();
        m9059Z();
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20236e && !this.f20239h) {
            this.f20236e = true;
            m9060Y();
            m9059Z();
        }
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (this.f20240i.get() || !this.f20240i.compareAndSet(false, true)) {
            EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), dbxVar);
            return;
        }
        dbxVar.onSubscribe(this.f20241j);
        this.f20238g.set(dbxVar);
        if (this.f20239h) {
            this.f20238g.lazySet(null);
        } else {
            m9059Z();
        }
    }

    /* compiled from: UnicastProcessor.java */
    /* renamed from: z1.bum$a */
    /* loaded from: classes3.dex */
    final class C4771a extends BasicIntQueueSubscription<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        C4771a() {
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() {
            return UnicastProcessor.this.f20233b.poll();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return UnicastProcessor.this.f20233b.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            UnicastProcessor.this.f20233b.clear();
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastProcessor.this.f20243l = true;
            return 2;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(UnicastProcessor.this.f20242k, j);
                UnicastProcessor.this.m9059Z();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!UnicastProcessor.this.f20239h) {
                UnicastProcessor bumVar = UnicastProcessor.this;
                bumVar.f20239h = true;
                bumVar.m9060Y();
                if (!UnicastProcessor.this.f20243l && UnicastProcessor.this.f20241j.getAndIncrement() == 0) {
                    UnicastProcessor.this.f20233b.clear();
                    UnicastProcessor.this.f20238g.lazySet(null);
                }
            }
        }
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20238g.get() != null;
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        if (this.f20236e) {
            return this.f20237f;
        }
        return null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return this.f20236e && this.f20237f == null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return this.f20236e && this.f20237f != null;
    }
}
