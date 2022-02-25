package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bva */
/* loaded from: classes3.dex */
public final class UnicastSubject<T> extends Subject<T> {

    /* renamed from: a */
    final SpscLinkedArrayQueue<T> f20324a;

    /* renamed from: b */
    final AtomicReference<Observer<? super T>> f20325b;

    /* renamed from: c */
    final AtomicReference<Runnable> f20326c;

    /* renamed from: d */
    final boolean f20327d;

    /* renamed from: e */
    volatile boolean f20328e;

    /* renamed from: f */
    volatile boolean f20329f;

    /* renamed from: g */
    Throwable f20330g;

    /* renamed from: h */
    final AtomicBoolean f20331h;

    /* renamed from: i */
    final BasicIntQueueDisposable<T> f20332i;

    /* renamed from: j */
    boolean f20333j;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> UnicastSubject<T> m8943a() {
        return new UnicastSubject<>(m10338d(), true);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public static <T> UnicastSubject<T> m8933i(int i) {
        return new UnicastSubject<>(i, true);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> UnicastSubject<T> m8942a(int i, Runnable runnable) {
        return new UnicastSubject<>(i, runnable, true);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> UnicastSubject<T> m8941a(int i, Runnable runnable, boolean z) {
        return new UnicastSubject<>(i, runnable, z);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> UnicastSubject<T> m8937b(boolean z) {
        return new UnicastSubject<>(m10338d(), z);
    }

    UnicastSubject(int i, boolean z) {
        this.f20324a = new SpscLinkedArrayQueue<>(ObjectHelper.m9878a(i, "capacityHint"));
        this.f20326c = new AtomicReference<>();
        this.f20327d = z;
        this.f20325b = new AtomicReference<>();
        this.f20331h = new AtomicBoolean();
        this.f20332i = new C4797a();
    }

    UnicastSubject(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    UnicastSubject(int i, Runnable runnable, boolean z) {
        this.f20324a = new SpscLinkedArrayQueue<>(ObjectHelper.m9878a(i, "capacityHint"));
        this.f20326c = new AtomicReference<>(ObjectHelper.m9873a(runnable, "onTerminate"));
        this.f20327d = z;
        this.f20325b = new AtomicReference<>();
        this.f20331h = new AtomicBoolean();
        this.f20332i = new C4797a();
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        if (this.f20331h.get() || !this.f20331h.compareAndSet(false, true)) {
            EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), assVar);
            return;
        }
        assVar.onSubscribe(this.f20332i);
        this.f20325b.lazySet(assVar);
        if (this.f20328e) {
            this.f20325b.lazySet(null);
        } else {
            m8944U();
        }
    }

    /* renamed from: T */
    void m8945T() {
        Runnable runnable = this.f20326c.get();
        if (runnable != null && this.f20326c.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (this.f20329f || this.f20328e) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20329f && !this.f20328e) {
            this.f20324a.offer(t);
            m8944U();
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20329f || this.f20328e) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20330g = th;
        this.f20329f = true;
        m8945T();
        m8944U();
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f20329f && !this.f20328e) {
            this.f20329f = true;
            m8945T();
            m8944U();
        }
    }

    /* renamed from: b */
    void m8938b(Observer<? super T> assVar) {
        SpscLinkedArrayQueue<T> bqlVar = this.f20324a;
        boolean z = !this.f20327d;
        boolean z2 = true;
        int i = 1;
        while (!this.f20328e) {
            boolean z3 = this.f20329f;
            Object obj = (T) this.f20324a.poll();
            boolean z4 = obj == null;
            if (z3) {
                if (z && z2) {
                    if (!m8940a((SimpleQueue) bqlVar, (Observer) assVar)) {
                        z2 = false;
                    } else {
                        return;
                    }
                }
                if (z4) {
                    m8934h((Observer) assVar);
                    return;
                }
            }
            if (z4) {
                i = this.f20332i.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                assVar.onNext(obj);
            }
        }
        this.f20325b.lazySet(null);
        bqlVar.clear();
    }

    /* renamed from: g */
    void m8935g(Observer<? super T> assVar) {
        SpscLinkedArrayQueue<T> bqlVar = this.f20324a;
        int i = 1;
        boolean z = !this.f20327d;
        while (!this.f20328e) {
            boolean z2 = this.f20329f;
            if (!z || !z2 || !m8940a((SimpleQueue) bqlVar, (Observer) assVar)) {
                assVar.onNext(null);
                if (z2) {
                    m8934h((Observer) assVar);
                    return;
                }
                i = this.f20332i.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                return;
            }
        }
        this.f20325b.lazySet(null);
        bqlVar.clear();
    }

    /* renamed from: h */
    void m8934h(Observer<? super T> assVar) {
        this.f20325b.lazySet(null);
        Throwable th = this.f20330g;
        if (th != null) {
            assVar.onError(th);
        } else {
            assVar.onComplete();
        }
    }

    /* renamed from: a */
    boolean m8940a(SimpleQueue<T> avwVar, Observer<? super T> assVar) {
        Throwable th = this.f20330g;
        if (th == null) {
            return false;
        }
        this.f20325b.lazySet(null);
        avwVar.clear();
        assVar.onError(th);
        return true;
    }

    /* renamed from: U */
    void m8944U() {
        if (this.f20332i.getAndIncrement() == 0) {
            Observer<? super T> assVar = this.f20325b.get();
            int i = 1;
            while (assVar == null) {
                i = this.f20332i.addAndGet(-i);
                if (i != 0) {
                    assVar = this.f20325b.get();
                } else {
                    return;
                }
            }
            if (this.f20333j) {
                m8935g((Observer) assVar);
            } else {
                m8938b((Observer) assVar);
            }
        }
    }

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20325b.get() != null;
    }

    @Override // p110z1.Subject
    @atn
    /* renamed from: S */
    public Throwable mo8946S() {
        if (this.f20329f) {
            return this.f20330g;
        }
        return null;
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return this.f20329f && this.f20330g != null;
    }

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return this.f20329f && this.f20330g == null;
    }

    /* compiled from: UnicastSubject.java */
    /* renamed from: z1.bva$a */
    /* loaded from: classes3.dex */
    final class C4797a extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        C4797a() {
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastSubject.this.f20333j = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            return UnicastSubject.this.f20324a.poll();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.f20324a.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            UnicastSubject.this.f20324a.clear();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!UnicastSubject.this.f20328e) {
                UnicastSubject bvaVar = UnicastSubject.this;
                bvaVar.f20328e = true;
                bvaVar.m8945T();
                UnicastSubject.this.f20325b.lazySet(null);
                if (UnicastSubject.this.f20332i.getAndIncrement() == 0) {
                    UnicastSubject.this.f20325b.lazySet(null);
                    UnicastSubject.this.f20324a.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.f20328e;
        }
    }
}
