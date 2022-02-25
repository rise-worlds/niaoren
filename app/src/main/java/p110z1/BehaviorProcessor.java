package p110z1;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import p110z1.AppendOnlyLinkedArrayList;

/* renamed from: z1.bug */
/* loaded from: classes3.dex */
public final class BehaviorProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: c */
    static final Object[] f20176c = new Object[0];

    /* renamed from: d */
    static final C4761a[] f20177d = new C4761a[0];

    /* renamed from: e */
    static final C4761a[] f20178e = new C4761a[0];

    /* renamed from: b */
    final AtomicReference<C4761a<T>[]> f20179b;

    /* renamed from: f */
    final ReadWriteLock f20180f;

    /* renamed from: g */
    final Lock f20181g;

    /* renamed from: h */
    final Lock f20182h;

    /* renamed from: i */
    final AtomicReference<Object> f20183i;

    /* renamed from: j */
    final AtomicReference<Throwable> f20184j;

    /* renamed from: k */
    long f20185k;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> BehaviorProcessor<T> m9123T() {
        return new BehaviorProcessor<>();
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public static <T> BehaviorProcessor<T> m9115m(T t) {
        ObjectHelper.m9873a((Object) t, "defaultValue is null");
        return new BehaviorProcessor<>(t);
    }

    BehaviorProcessor() {
        this.f20183i = new AtomicReference<>();
        this.f20180f = new ReentrantReadWriteLock();
        this.f20181g = this.f20180f.readLock();
        this.f20182h = this.f20180f.writeLock();
        this.f20179b = new AtomicReference<>(f20177d);
        this.f20184j = new AtomicReference<>();
    }

    BehaviorProcessor(T t) {
        this();
        this.f20183i.lazySet(ObjectHelper.m9873a((Object) t, "defaultValue is null"));
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4761a<T> aVar = new C4761a<>(dbxVar, this);
        dbxVar.onSubscribe(aVar);
        if (!m9120a((C4761a) aVar)) {
            Throwable th = this.f20184j.get();
            if (th == ExceptionHelper.f20064a) {
                dbxVar.onComplete();
            } else {
                dbxVar.onError(th);
            }
        } else if (aVar.cancelled) {
            m9117b((C4761a) aVar);
        } else {
            aVar.emitFirst();
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.f20184j.get() != null) {
            dbyVar.cancel();
        } else {
            dbyVar.request(cjm.f20759b);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20184j.get() == null) {
            Object next = NotificationLite.next(t);
            m9112p(next);
            for (C4761a<T> aVar : this.f20179b.get()) {
                aVar.emitNext(next, this.f20185k);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20184j.compareAndSet(null, th)) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (C4761a<T> aVar : m9113o(error)) {
            aVar.emitNext(error, this.f20185k);
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (this.f20184j.compareAndSet(null, ExceptionHelper.f20064a)) {
            Object complete = NotificationLite.complete();
            for (C4761a<T> aVar : m9113o(complete)) {
                aVar.emitNext(complete, this.f20185k);
            }
        }
    }

    /* renamed from: n */
    public boolean m9114n(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return true;
        }
        C4761a<T>[] aVarArr = this.f20179b.get();
        for (C4761a<T> aVar : aVarArr) {
            if (aVar.isFull()) {
                return false;
            }
        }
        Object next = NotificationLite.next(t);
        m9112p(next);
        for (C4761a<T> aVar2 : aVarArr) {
            aVar2.emitNext(next, this.f20185k);
        }
        return true;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20179b.get().length != 0;
    }

    /* renamed from: Y */
    int m9122Y() {
        return this.f20179b.get().length;
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        Object obj = this.f20183i.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @atn
    /* renamed from: Z */
    public T m9121Z() {
        Object obj = this.f20183i.get();
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) NotificationLite.getValue(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    /* renamed from: aa */
    public Object[] m9119aa() {
        Object[] c = m9116c(f20176c);
        return c == f20176c ? new Object[0] : c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    /* renamed from: c */
    public T[] m9116c(T[] tArr) {
        Object obj = this.f20183i.get();
        if (obj == null || NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            if (tArr.length != 0) {
                tArr[0] = 0;
            }
            return tArr;
        }
        Object value = NotificationLite.getValue(obj);
        if (tArr.length != 0) {
            tArr[0] = value;
            if (tArr.length == 1) {
                return tArr;
            }
            tArr[1] = 0;
            return tArr;
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
        tArr2[0] = value;
        return tArr2;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return NotificationLite.isComplete(this.f20183i.get());
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return NotificationLite.isError(this.f20183i.get());
    }

    /* renamed from: ab */
    public boolean m9118ab() {
        Object obj = this.f20183i.get();
        return obj != null && !NotificationLite.isComplete(obj) && !NotificationLite.isError(obj);
    }

    /* renamed from: a */
    boolean m9120a(C4761a<T> aVar) {
        C4761a<T>[] aVarArr;
        C4761a<T>[] aVarArr2;
        do {
            aVarArr = this.f20179b.get();
            if (aVarArr == f20178e) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4761a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20179b.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9117b(C4761a<T> aVar) {
        C4761a<T>[] aVarArr;
        C4761a<T>[] aVarArr2;
        do {
            aVarArr = this.f20179b.get();
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
                        aVarArr2 = f20177d;
                    } else {
                        C4761a<T>[] aVarArr3 = new C4761a[length - 1];
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
        } while (!this.f20179b.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: o */
    C4761a<T>[] m9113o(Object obj) {
        C4761a<T>[] aVarArr = this.f20179b.get();
        C4761a<T>[] aVarArr2 = f20178e;
        if (!(aVarArr == aVarArr2 || (aVarArr = this.f20179b.getAndSet(aVarArr2)) == f20178e)) {
            m9112p(obj);
        }
        return aVarArr;
    }

    /* renamed from: p */
    void m9112p(Object obj) {
        Lock lock = this.f20182h;
        lock.lock();
        this.f20185k++;
        this.f20183i.lazySet(obj);
        lock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BehaviorProcessor.java */
    /* renamed from: z1.bug$a */
    /* loaded from: classes3.dex */
    public static final class C4761a<T> extends AtomicLong implements AppendOnlyLinkedArrayList.AbstractC4743a<Object>, dby {
        private static final long serialVersionUID = 3293175281126227086L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        AppendOnlyLinkedArrayList<Object> queue;
        final BehaviorProcessor<T> state;

        C4761a(Subscriber<? super T> dbxVar, BehaviorProcessor<T> bugVar) {
            this.downstream = dbxVar;
            this.state = bugVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.m9117b((C4761a) this);
            }
        }

        void emitFirst() {
            if (!this.cancelled) {
                synchronized (this) {
                    if (!this.cancelled) {
                        if (!this.next) {
                            BehaviorProcessor<T> bugVar = this.state;
                            Lock lock = bugVar.f20181g;
                            lock.lock();
                            this.index = bugVar.f20185k;
                            Object obj = bugVar.f20183i.get();
                            lock.unlock();
                            this.emitting = obj != null;
                            this.next = true;
                            if (obj != null && !test(obj)) {
                                emitLoop();
                            }
                        }
                    }
                }
            }
        }

        void emitNext(Object obj, long j) {
            if (!this.cancelled) {
                if (!this.fastPath) {
                    synchronized (this) {
                        if (!this.cancelled) {
                            if (this.index != j) {
                                if (this.emitting) {
                                    AppendOnlyLinkedArrayList<Object> bslVar = this.queue;
                                    if (bslVar == null) {
                                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                                        this.queue = bslVar;
                                    }
                                    bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) obj);
                                    return;
                                }
                                this.next = true;
                                this.fastPath = true;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                test(obj);
            }
        }

        @Override // p110z1.AppendOnlyLinkedArrayList.AbstractC4743a, p110z1.Predicate
        public boolean test(Object obj) {
            if (this.cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(obj)) {
                this.downstream.onComplete();
                return true;
            } else if (NotificationLite.isError(obj)) {
                this.downstream.onError(NotificationLite.getError(obj));
                return true;
            } else {
                long j = get();
                if (j != 0) {
                    this.downstream.onNext((Object) NotificationLite.getValue(obj));
                    if (j == cjm.f20759b) {
                        return false;
                    }
                    decrementAndGet();
                    return false;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                return true;
            }
        }

        void emitLoop() {
            AppendOnlyLinkedArrayList<Object> bslVar;
            while (!this.cancelled) {
                synchronized (this) {
                    bslVar = this.queue;
                    if (bslVar == null) {
                        this.emitting = false;
                        return;
                    }
                    this.queue = null;
                }
                bslVar.m9453a((AppendOnlyLinkedArrayList.AbstractC4743a<? super Object>) this);
            }
        }

        public boolean isFull() {
            return get() == 0;
        }
    }
}
