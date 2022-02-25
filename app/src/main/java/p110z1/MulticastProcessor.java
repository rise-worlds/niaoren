package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@SchedulerSupport(m10000a = "none")
@BackpressureSupport(m10001a = BackpressureKind.FULL)
/* renamed from: z1.bui */
/* loaded from: classes3.dex */
public final class MulticastProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: n */
    static final C4762a[] f20186n = new C4762a[0];

    /* renamed from: o */
    static final C4762a[] f20187o = new C4762a[0];

    /* renamed from: f */
    final int f20192f;

    /* renamed from: g */
    final int f20193g;

    /* renamed from: h */
    final boolean f20194h;

    /* renamed from: i */
    volatile SimpleQueue<T> f20195i;

    /* renamed from: j */
    volatile boolean f20196j;

    /* renamed from: k */
    volatile Throwable f20197k;

    /* renamed from: l */
    int f20198l;

    /* renamed from: m */
    int f20199m;

    /* renamed from: b */
    final AtomicInteger f20188b = new AtomicInteger();

    /* renamed from: d */
    final AtomicReference<C4762a<T>[]> f20190d = new AtomicReference<>(f20186n);

    /* renamed from: c */
    final AtomicReference<dby> f20189c = new AtomicReference<>();

    /* renamed from: e */
    final AtomicBoolean f20191e = new AtomicBoolean();

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> MulticastProcessor<T> m9110T() {
        return new MulticastProcessor<>(m11274a(), false);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> MulticastProcessor<T> m9103b(boolean z) {
        return new MulticastProcessor<>(m11274a(), z);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public static <T> MulticastProcessor<T> m9102m(int i) {
        return new MulticastProcessor<>(i, false);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> MulticastProcessor<T> m9105b(int i, boolean z) {
        return new MulticastProcessor<>(i, z);
    }

    MulticastProcessor(int i, boolean z) {
        ObjectHelper.m9878a(i, "bufferSize");
        this.f20192f = i;
        this.f20193g = i - (i >> 2);
        this.f20194h = z;
    }

    /* renamed from: Y */
    public void m9109Y() {
        if (SubscriptionHelper.setOnce(this.f20189c, EmptySubscription.INSTANCE)) {
            this.f20195i = new SpscArrayQueue(this.f20192f);
        }
    }

    /* renamed from: Z */
    public void m9108Z() {
        if (SubscriptionHelper.setOnce(this.f20189c, EmptySubscription.INSTANCE)) {
            this.f20195i = new SpscLinkedArrayQueue(this.f20192f);
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.setOnce(this.f20189c, dbyVar)) {
            if (dbyVar instanceof QueueSubscription) {
                QueueSubscription avtVar = (QueueSubscription) dbyVar;
                int requestFusion = avtVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.f20199m = requestFusion;
                    this.f20195i = avtVar;
                    this.f20196j = true;
                    m9106aa();
                    return;
                } else if (requestFusion == 2) {
                    this.f20199m = requestFusion;
                    this.f20195i = avtVar;
                    dbyVar.request(this.f20192f);
                    return;
                }
            }
            this.f20195i = new SpscArrayQueue(this.f20192f);
            dbyVar.request(this.f20192f);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!this.f20191e.get()) {
            if (this.f20199m == 0) {
                ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                if (!this.f20195i.offer(t)) {
                    SubscriptionHelper.cancel(this.f20189c);
                    onError(new MissingBackpressureException());
                    return;
                }
            }
            m9106aa();
        }
    }

    /* renamed from: m */
    public boolean m9101m(T t) {
        if (this.f20191e.get()) {
            return false;
        }
        ObjectHelper.m9873a((Object) t, "offer called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20199m != 0 || !this.f20195i.offer(t)) {
            return false;
        }
        m9106aa();
        return true;
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20191e.compareAndSet(false, true)) {
            this.f20197k = th;
            this.f20196j = true;
            m9106aa();
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (this.f20191e.compareAndSet(false, true)) {
            this.f20196j = true;
            m9106aa();
        }
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20190d.get().length != 0;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return this.f20191e.get() && this.f20197k != null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return this.f20191e.get() && this.f20197k == null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: X */
    public Throwable mo9061X() {
        if (this.f20191e.get()) {
            return this.f20197k;
        }
        return null;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        Throwable th;
        C4762a<T> aVar = new C4762a<>(dbxVar, this);
        dbxVar.onSubscribe(aVar);
        if (m9107a((C4762a) aVar)) {
            if (aVar.get() == Long.MIN_VALUE) {
                m9104b((C4762a) aVar);
            } else {
                m9106aa();
            }
        } else if ((this.f20191e.get() || !this.f20194h) && (th = this.f20197k) != null) {
            dbxVar.onError(th);
        } else {
            dbxVar.onComplete();
        }
    }

    /* renamed from: a */
    boolean m9107a(C4762a<T> aVar) {
        C4762a<T>[] aVarArr;
        C4762a<T>[] aVarArr2;
        do {
            aVarArr = this.f20190d.get();
            if (aVarArr == f20187o) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4762a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20190d.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9104b(C4762a<T> aVar) {
        while (true) {
            C4762a<T>[] aVarArr = this.f20190d.get();
            int length = aVarArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length != 1) {
                        C4762a<T>[] aVarArr2 = new C4762a[length - 1];
                        System.arraycopy(aVarArr, 0, aVarArr2, 0, i);
                        System.arraycopy(aVarArr, i + 1, aVarArr2, i, (length - i) - 1);
                        if (this.f20190d.compareAndSet(aVarArr, aVarArr2)) {
                            return;
                        }
                    } else if (this.f20194h) {
                        if (this.f20190d.compareAndSet(aVarArr, f20187o)) {
                            SubscriptionHelper.cancel(this.f20189c);
                            this.f20191e.set(true);
                            return;
                        }
                    } else if (this.f20190d.compareAndSet(aVarArr, f20186n)) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: aa */
    void m9106aa() {
        int i;
        boolean z;
        T t;
        if (this.f20188b.getAndIncrement() == 0) {
            AtomicReference<C4762a<T>[]> atomicReference = this.f20190d;
            int i2 = this.f20198l;
            int i3 = this.f20193g;
            int i4 = this.f20199m;
            int i5 = 1;
            while (true) {
                SimpleQueue<T> avwVar = this.f20195i;
                if (avwVar != null) {
                    C4762a<T>[] aVarArr = atomicReference.get();
                    if (aVarArr.length != 0) {
                        int length = aVarArr.length;
                        long j = -1;
                        long j2 = -1;
                        int i6 = 0;
                        while (i6 < length) {
                            C4762a<T> aVar = aVarArr[i6];
                            long j3 = aVar.get();
                            if (j3 >= 0) {
                                if (j2 == j) {
                                    j2 = j3 - aVar.emitted;
                                } else {
                                    j2 = Math.min(j2, j3 - aVar.emitted);
                                }
                            }
                            i6++;
                            j = -1;
                        }
                        int i7 = i2;
                        while (true) {
                            i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
                            if (i <= 0) {
                                break;
                            }
                            C4762a<T>[] aVarArr2 = atomicReference.get();
                            if (aVarArr2 == f20187o) {
                                avwVar.clear();
                                return;
                            } else if (aVarArr != aVarArr2) {
                                break;
                            } else {
                                try {
                                    z = this.f20196j;
                                    t = avwVar.poll();
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    SubscriptionHelper.cancel(this.f20189c);
                                    t = null;
                                    this.f20197k = th;
                                    this.f20196j = true;
                                    z = true;
                                }
                                boolean z2 = t == null;
                                if (z && z2) {
                                    Throwable th2 = this.f20197k;
                                    if (th2 != null) {
                                        for (C4762a<T> aVar2 : atomicReference.getAndSet(f20187o)) {
                                            aVar2.onError(th2);
                                        }
                                        return;
                                    }
                                    for (C4762a<T> aVar3 : atomicReference.getAndSet(f20187o)) {
                                        aVar3.onComplete();
                                    }
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    for (C4762a<T> aVar4 : aVarArr) {
                                        aVar4.onNext(t);
                                    }
                                    j2--;
                                    if (i4 != 1) {
                                        int i8 = i7 + 1;
                                        if (i8 == i3) {
                                            this.f20189c.get().request(i3);
                                            i7 = 0;
                                        } else {
                                            i7 = i8;
                                        }
                                    }
                                }
                            }
                        }
                        if (i == 0) {
                            C4762a<T>[] aVarArr3 = atomicReference.get();
                            if (aVarArr3 == f20187o) {
                                avwVar.clear();
                                return;
                            } else if (aVarArr != aVarArr3) {
                                i2 = i7;
                            } else if (this.f20196j && avwVar.isEmpty()) {
                                Throwable th3 = this.f20197k;
                                if (th3 != null) {
                                    for (C4762a<T> aVar5 : atomicReference.getAndSet(f20187o)) {
                                        aVar5.onError(th3);
                                    }
                                    return;
                                }
                                for (C4762a<T> aVar6 : atomicReference.getAndSet(f20187o)) {
                                    aVar6.onComplete();
                                }
                                return;
                            }
                        }
                        i2 = i7;
                    }
                }
                i5 = this.f20188b.addAndGet(-i5);
                if (i5 == 0) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MulticastProcessor.java */
    /* renamed from: z1.bui$a */
    /* loaded from: classes3.dex */
    public static final class C4762a<T> extends AtomicLong implements dby {
        private static final long serialVersionUID = -363282618957264509L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        C4762a(Subscriber<? super T> dbxVar, MulticastProcessor<T> buiVar) {
            this.downstream = dbxVar;
            this.parent = buiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != Long.MIN_VALUE) {
                        j3 = cjm.f20759b;
                        if (j2 != cjm.f20759b) {
                            long j4 = j2 + j;
                            if (j4 >= 0) {
                                j3 = j4;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                this.parent.m9106aa();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.m9104b((C4762a) this);
            }
        }

        void onNext(T t) {
            if (get() != Long.MIN_VALUE) {
                this.emitted++;
                this.downstream.onNext(t);
            }
        }

        void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            }
        }

        void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }
    }
}
