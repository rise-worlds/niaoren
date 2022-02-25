package p110z1;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import p110z1.AppendOnlyLinkedArrayList;

/* renamed from: z1.bus */
/* loaded from: classes3.dex */
public final class BehaviorSubject<T> extends Subject<T> {

    /* renamed from: a */
    final AtomicReference<Object> f20275a;

    /* renamed from: b */
    final AtomicReference<C4784a<T>[]> f20276b;

    /* renamed from: e */
    final ReadWriteLock f20277e;

    /* renamed from: f */
    final Lock f20278f;

    /* renamed from: g */
    final Lock f20279g;

    /* renamed from: h */
    final AtomicReference<Throwable> f20280h;

    /* renamed from: i */
    long f20281i;

    /* renamed from: j */
    private static final Object[] f20274j = new Object[0];

    /* renamed from: c */
    static final C4784a[] f20272c = new C4784a[0];

    /* renamed from: d */
    static final C4784a[] f20273d = new C4784a[0];

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> BehaviorSubject<T> m9012a() {
        return new BehaviorSubject<>();
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public static <T> BehaviorSubject<T> m9008m(T t) {
        return new BehaviorSubject<>(t);
    }

    BehaviorSubject() {
        this.f20277e = new ReentrantReadWriteLock();
        this.f20278f = this.f20277e.readLock();
        this.f20279g = this.f20277e.writeLock();
        this.f20276b = new AtomicReference<>(f20272c);
        this.f20275a = new AtomicReference<>();
        this.f20280h = new AtomicReference<>();
    }

    BehaviorSubject(T t) {
        this();
        this.f20275a.lazySet(ObjectHelper.m9873a((Object) t, "defaultValue is null"));
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4784a<T> aVar = new C4784a<>(assVar, this);
        assVar.onSubscribe(aVar);
        if (!m9011a((C4784a) aVar)) {
            Throwable th = this.f20280h.get();
            if (th == ExceptionHelper.f20064a) {
                assVar.onComplete();
            } else {
                assVar.onError(th);
            }
        } else if (aVar.f20288g) {
            m9010b((C4784a) aVar);
        } else {
            aVar.m9005a();
        }
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (this.f20280h.get() != null) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20280h.get() == null) {
            Object next = NotificationLite.next(t);
            m9006o(next);
            for (C4784a<T> aVar : this.f20276b.get()) {
                aVar.m9004a(next, this.f20281i);
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20280h.compareAndSet(null, th)) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (C4784a<T> aVar : m9007n(error)) {
            aVar.m9004a(error, this.f20281i);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (this.f20280h.compareAndSet(null, ExceptionHelper.f20064a)) {
            Object complete = NotificationLite.complete();
            for (C4784a<T> aVar : m9007n(complete)) {
                aVar.m9004a(complete, this.f20281i);
            }
        }
    }

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20276b.get().length != 0;
    }

    /* renamed from: T */
    int m9016T() {
        return this.f20276b.get().length;
    }

    @Override // p110z1.Subject
    @atn
    /* renamed from: S */
    public Throwable mo8946S() {
        Object obj = this.f20275a.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @atn
    /* renamed from: U */
    public T m9015U() {
        Object obj = this.f20275a.get();
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) NotificationLite.getValue(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    /* renamed from: V */
    public Object[] m9014V() {
        Object[] c = m9009c(f20274j);
        return c == f20274j ? new Object[0] : c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    /* renamed from: c */
    public T[] m9009c(T[] tArr) {
        Object obj = this.f20275a.get();
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

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return NotificationLite.isComplete(this.f20275a.get());
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return NotificationLite.isError(this.f20275a.get());
    }

    /* renamed from: W */
    public boolean m9013W() {
        Object obj = this.f20275a.get();
        return obj != null && !NotificationLite.isComplete(obj) && !NotificationLite.isError(obj);
    }

    /* renamed from: a */
    boolean m9011a(C4784a<T> aVar) {
        C4784a<T>[] aVarArr;
        C4784a<T>[] aVarArr2;
        do {
            aVarArr = this.f20276b.get();
            if (aVarArr == f20273d) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4784a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20276b.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9010b(C4784a<T> aVar) {
        C4784a<T>[] aVarArr;
        C4784a<T>[] aVarArr2;
        do {
            aVarArr = this.f20276b.get();
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
                        aVarArr2 = f20272c;
                    } else {
                        C4784a<T>[] aVarArr3 = new C4784a[length - 1];
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
        } while (!this.f20276b.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: n */
    C4784a<T>[] m9007n(Object obj) {
        C4784a<T>[] andSet = this.f20276b.getAndSet(f20273d);
        if (andSet != f20273d) {
            m9006o(obj);
        }
        return andSet;
    }

    /* renamed from: o */
    void m9006o(Object obj) {
        this.f20279g.lock();
        this.f20281i++;
        this.f20275a.lazySet(obj);
        this.f20279g.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BehaviorSubject.java */
    /* renamed from: z1.bus$a */
    /* loaded from: classes3.dex */
    public static final class C4784a<T> implements Disposable, AppendOnlyLinkedArrayList.AbstractC4743a<Object> {

        /* renamed from: a */
        final Observer<? super T> f20282a;

        /* renamed from: b */
        final BehaviorSubject<T> f20283b;

        /* renamed from: c */
        boolean f20284c;

        /* renamed from: d */
        boolean f20285d;

        /* renamed from: e */
        AppendOnlyLinkedArrayList<Object> f20286e;

        /* renamed from: f */
        boolean f20287f;

        /* renamed from: g */
        volatile boolean f20288g;

        /* renamed from: h */
        long f20289h;

        C4784a(Observer<? super T> assVar, BehaviorSubject<T> busVar) {
            this.f20282a = assVar;
            this.f20283b = busVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f20288g) {
                this.f20288g = true;
                this.f20283b.m9010b((C4784a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f20288g;
        }

        /* renamed from: a */
        void m9005a() {
            if (!this.f20288g) {
                synchronized (this) {
                    if (!this.f20288g) {
                        if (!this.f20284c) {
                            BehaviorSubject<T> busVar = this.f20283b;
                            Lock lock = busVar.f20278f;
                            lock.lock();
                            this.f20289h = busVar.f20281i;
                            Object obj = busVar.f20275a.get();
                            lock.unlock();
                            this.f20285d = obj != null;
                            this.f20284c = true;
                            if (obj != null && !test(obj)) {
                                m9003b();
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        void m9004a(Object obj, long j) {
            if (!this.f20288g) {
                if (!this.f20287f) {
                    synchronized (this) {
                        if (!this.f20288g) {
                            if (this.f20289h != j) {
                                if (this.f20285d) {
                                    AppendOnlyLinkedArrayList<Object> bslVar = this.f20286e;
                                    if (bslVar == null) {
                                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                                        this.f20286e = bslVar;
                                    }
                                    bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) obj);
                                    return;
                                }
                                this.f20284c = true;
                                this.f20287f = true;
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
            return this.f20288g || NotificationLite.accept(obj, this.f20282a);
        }

        /* renamed from: b */
        void m9003b() {
            AppendOnlyLinkedArrayList<Object> bslVar;
            while (!this.f20288g) {
                synchronized (this) {
                    bslVar = this.f20286e;
                    if (bslVar == null) {
                        this.f20285d = false;
                        return;
                    }
                    this.f20286e = null;
                }
                bslVar.m9453a((AppendOnlyLinkedArrayList.AbstractC4743a<? super Object>) this);
            }
        }
    }
}
