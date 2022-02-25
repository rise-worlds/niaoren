package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azh */
/* loaded from: classes3.dex */
public final class FlowableCache<T> extends AbstractFlowableWithUpstream<T, T> implements FlowableSubscriber<T> {

    /* renamed from: f */
    static final C4005a[] f17921f = new C4005a[0];

    /* renamed from: g */
    static final C4005a[] f17922g = new C4005a[0];

    /* renamed from: d */
    final int f17924d;

    /* renamed from: h */
    volatile long f17926h;

    /* renamed from: i */
    final C4006b<T> f17927i;

    /* renamed from: j */
    C4006b<T> f17928j;

    /* renamed from: k */
    int f17929k;

    /* renamed from: l */
    Throwable f17930l;

    /* renamed from: m */
    volatile boolean f17931m;

    /* renamed from: c */
    final AtomicBoolean f17923c = new AtomicBoolean();

    /* renamed from: e */
    final AtomicReference<C4005a<T>[]> f17925e = new AtomicReference<>(f17921f);

    public FlowableCache(Flowable<T> arvVar, int i) {
        super(arvVar);
        this.f17924d = i;
        C4006b<T> bVar = new C4006b<>(i);
        this.f17927i = bVar;
        this.f17928j = bVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4005a<T> aVar = new C4005a<>(dbxVar, this);
        dbxVar.onSubscribe(aVar);
        m9800a((C4005a) aVar);
        if (this.f17923c.get() || !this.f17923c.compareAndSet(false, true)) {
            m9798c((C4005a) aVar);
        } else {
            this.f17812b.m11187a((FlowableSubscriber) this);
        }
    }

    /* renamed from: T */
    boolean m9803T() {
        return this.f17923c.get();
    }

    /* renamed from: U */
    boolean m9802U() {
        return this.f17925e.get().length != 0;
    }

    /* renamed from: V */
    long m9801V() {
        return this.f17926h;
    }

    /* renamed from: a */
    void m9800a(C4005a<T> aVar) {
        C4005a<T>[] aVarArr;
        C4005a<T>[] aVarArr2;
        do {
            aVarArr = this.f17925e.get();
            if (aVarArr != f17922g) {
                int length = aVarArr.length;
                aVarArr2 = new C4005a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } else {
                return;
            }
        } while (!this.f17925e.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: b */
    void m9799b(C4005a<T> aVar) {
        C4005a<T>[] aVarArr;
        C4005a<T>[] aVarArr2;
        do {
            aVarArr = this.f17925e.get();
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
                    if (length == 1) {
                        aVarArr2 = f17921f;
                    } else {
                        C4005a<T>[] aVarArr3 = new C4005a[length - 1];
                        System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                        System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                        aVarArr2 = aVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.f17925e.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: c */
    void m9798c(C4005a<T> aVar) {
        if (aVar.getAndIncrement() == 0) {
            long j = aVar.index;
            int i = aVar.offset;
            C4006b<T> bVar = aVar.node;
            AtomicLong atomicLong = aVar.requested;
            Subscriber<? super T> dbxVar = aVar.downstream;
            int i2 = this.f17924d;
            C4006b<T> bVar2 = bVar;
            int i3 = i;
            int i4 = 1;
            while (true) {
                boolean z = this.f17931m;
                int i5 = 0;
                boolean z2 = this.f17926h == j;
                if (!z || !z2) {
                    if (!z2) {
                        long j2 = atomicLong.get();
                        if (j2 == Long.MIN_VALUE) {
                            aVar.node = null;
                            return;
                        } else if (j2 != j) {
                            if (i3 == i2) {
                                bVar2 = bVar2.f17933b;
                            } else {
                                i5 = i3;
                            }
                            dbxVar.onNext((Object) bVar2.f17932a[i5]);
                            i3 = i5 + 1;
                            j++;
                        }
                    }
                    aVar.index = j;
                    aVar.offset = i3;
                    aVar.node = bVar2;
                    i4 = aVar.addAndGet(-i4);
                    if (i4 == 0) {
                        return;
                    }
                } else {
                    aVar.node = null;
                    Throwable th = this.f17930l;
                    if (th != null) {
                        dbxVar.onError(th);
                        return;
                    } else {
                        dbxVar.onComplete();
                        return;
                    }
                }
            }
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        dbyVar.request(cjm.f20759b);
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        int i = this.f17929k;
        if (i == this.f17924d) {
            C4006b<T> bVar = new C4006b<>(i);
            bVar.f17932a[0] = t;
            this.f17929k = 1;
            this.f17928j.f17933b = bVar;
            this.f17928j = bVar;
        } else {
            this.f17928j.f17932a[i] = t;
            this.f17929k = i + 1;
        }
        this.f17926h++;
        for (C4005a<T> aVar : this.f17925e.get()) {
            m9798c((C4005a) aVar);
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f17931m) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f17930l = th;
        this.f17931m = true;
        for (C4005a<T> aVar : this.f17925e.getAndSet(f17922g)) {
            m9798c((C4005a) aVar);
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        this.f17931m = true;
        for (C4005a<T> aVar : this.f17925e.getAndSet(f17922g)) {
            m9798c((C4005a) aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableCache.java */
    /* renamed from: z1.azh$a */
    /* loaded from: classes3.dex */
    public static final class C4005a<T> extends AtomicInteger implements dby {
        private static final long serialVersionUID = 6770240836423125754L;
        final Subscriber<? super T> downstream;
        long index;
        C4006b<T> node;
        int offset;
        final FlowableCache<T> parent;
        final AtomicLong requested = new AtomicLong();

        C4005a(Subscriber<? super T> dbxVar, FlowableCache<T> azhVar) {
            this.downstream = dbxVar;
            this.parent = azhVar;
            this.node = azhVar.f17927i;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9447b(this.requested, j);
                this.parent.m9798c((C4005a) this);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.requested.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.m9799b((C4005a) this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableCache.java */
    /* renamed from: z1.azh$b */
    /* loaded from: classes3.dex */
    public static final class C4006b<T> {

        /* renamed from: a */
        final T[] f17932a;

        /* renamed from: b */
        volatile C4006b<T> f17933b;

        C4006b(int i) {
            this.f17932a = (T[]) new Object[i];
        }
    }
}
