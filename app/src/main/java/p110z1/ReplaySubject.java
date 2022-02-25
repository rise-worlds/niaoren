package p110z1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buw */
/* loaded from: classes3.dex */
public final class ReplaySubject<T> extends Subject<T> {

    /* renamed from: c */
    static final C4790c[] f20305c = new C4790c[0];

    /* renamed from: d */
    static final C4790c[] f20306d = new C4790c[0];

    /* renamed from: f */
    private static final Object[] f20307f = new Object[0];

    /* renamed from: a */
    final AbstractC4789b<T> f20308a;

    /* renamed from: b */
    final AtomicReference<C4790c<T>[]> f20309b = new AtomicReference<>(f20305c);

    /* renamed from: e */
    boolean f20310e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4789b<T> {
        void add(T t);

        void addFinal(Object obj);

        boolean compareAndSet(Object obj, Object obj2);

        Object get();

        @atn
        T getValue();

        T[] getValues(T[] tArr);

        void replay(C4790c<T> cVar);

        int size();

        void trimHead();
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ReplaySubject<T> m8972a() {
        return new ReplaySubject<>(new C4794g(16));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public static <T> ReplaySubject<T> m8967i(int i) {
        return new ReplaySubject<>(new C4794g(i));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: j */
    public static <T> ReplaySubject<T> m8966j(int i) {
        return new ReplaySubject<>(new C4792e(i));
    }

    /* renamed from: T */
    static <T> ReplaySubject<T> m8979T() {
        return new ReplaySubject<>(new C4792e(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: s */
    public static <T> ReplaySubject<T> m8964s(long j, TimeUnit timeUnit, Scheduler astVar) {
        return new ReplaySubject<>(new C4791d(Integer.MAX_VALUE, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> ReplaySubject<T> m8970b(long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return new ReplaySubject<>(new C4791d(i, j, timeUnit, astVar));
    }

    ReplaySubject(AbstractC4789b<T> bVar) {
        this.f20308a = bVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4790c<T> cVar = new C4790c<>(assVar, this);
        assVar.onSubscribe(cVar);
        if (cVar.cancelled) {
            return;
        }
        if (!m8971a((C4790c) cVar) || !cVar.cancelled) {
            this.f20308a.replay(cVar);
        } else {
            m8969b((C4790c) cVar);
        }
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (this.f20310e) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f20310e) {
            AbstractC4789b<T> bVar = this.f20308a;
            bVar.add(t);
            for (C4790c<T> cVar : this.f20309b.get()) {
                bVar.replay(cVar);
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20310e) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20310e = true;
        Object error = NotificationLite.error(th);
        AbstractC4789b<T> bVar = this.f20308a;
        bVar.addFinal(error);
        for (C4790c<T> cVar : m8965m(error)) {
            bVar.replay(cVar);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f20310e) {
            this.f20310e = true;
            Object complete = NotificationLite.complete();
            AbstractC4789b<T> bVar = this.f20308a;
            bVar.addFinal(complete);
            for (C4790c<T> cVar : m8965m(complete)) {
                bVar.replay(cVar);
            }
        }
    }

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20309b.get().length != 0;
    }

    /* renamed from: U */
    int m8978U() {
        return this.f20309b.get().length;
    }

    @Override // p110z1.Subject
    @atn
    /* renamed from: S */
    public Throwable mo8946S() {
        Object obj = this.f20308a.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @atn
    /* renamed from: V */
    public T m8977V() {
        return this.f20308a.getValue();
    }

    /* renamed from: W */
    public void m8976W() {
        this.f20308a.trimHead();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: X */
    public Object[] m8975X() {
        Object[] c = m8968c(f20307f);
        return c == f20307f ? new Object[0] : c;
    }

    /* renamed from: c */
    public T[] m8968c(T[] tArr) {
        return this.f20308a.getValues(tArr);
    }

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return NotificationLite.isComplete(this.f20308a.get());
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return NotificationLite.isError(this.f20308a.get());
    }

    /* renamed from: Y */
    public boolean m8974Y() {
        return this.f20308a.size() != 0;
    }

    /* renamed from: Z */
    int m8973Z() {
        return this.f20308a.size();
    }

    /* renamed from: a */
    boolean m8971a(C4790c<T> cVar) {
        C4790c<T>[] cVarArr;
        C4790c<T>[] cVarArr2;
        do {
            cVarArr = this.f20309b.get();
            if (cVarArr == f20306d) {
                return false;
            }
            int length = cVarArr.length;
            cVarArr2 = new C4790c[length + 1];
            System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
            cVarArr2[length] = cVar;
        } while (!this.f20309b.compareAndSet(cVarArr, cVarArr2));
        return true;
    }

    /* renamed from: b */
    void m8969b(C4790c<T> cVar) {
        C4790c<T>[] cVarArr;
        C4790c<T>[] cVarArr2;
        do {
            cVarArr = this.f20309b.get();
            if (cVarArr != f20306d && cVarArr != f20305c) {
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
                        cVarArr2 = f20305c;
                    } else {
                        C4790c<T>[] cVarArr3 = new C4790c[length - 1];
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
        } while (!this.f20309b.compareAndSet(cVarArr, cVarArr2));
    }

    /* renamed from: m */
    C4790c<T>[] m8965m(Object obj) {
        if (this.f20308a.compareAndSet(null, obj)) {
            return this.f20309b.getAndSet(f20306d);
        }
        return f20306d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$c */
    /* loaded from: classes3.dex */
    public static final class C4790c<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 466549804534799122L;
        volatile boolean cancelled;
        final Observer<? super T> downstream;
        Object index;
        final ReplaySubject<T> state;

        C4790c(Observer<? super T> assVar, ReplaySubject<T> buwVar) {
            this.downstream = assVar;
            this.state = buwVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.m8969b((C4790c) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$g */
    /* loaded from: classes3.dex */
    static final class C4794g<T> extends AtomicReference<Object> implements AbstractC4789b<T> {
        private static final long serialVersionUID = -733876083048047795L;
        final List<Object> buffer;
        volatile boolean done;
        volatile int size;

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void trimHead() {
        }

        C4794g(int i) {
            this.buffer = new ArrayList(ObjectHelper.m9878a(i, "capacityHint"));
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void add(T t) {
            this.buffer.add(t);
            this.size++;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void addFinal(Object obj) {
            this.buffer.add(obj);
            trimHead();
            this.size++;
            this.done = true;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        @atn
        public T getValue() {
            int i = this.size;
            if (i == 0) {
                return null;
            }
            List<Object> list = this.buffer;
            T t = (T) list.get(i - 1);
            if (!NotificationLite.isComplete(t) && !NotificationLite.isError(t)) {
                return t;
            }
            if (i == 1) {
                return null;
            }
            return (T) list.get(i - 2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.ReplaySubject.AbstractC4789b
        public T[] getValues(T[] tArr) {
            int i = this.size;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            List<Object> list = this.buffer;
            Object obj = list.get(i - 1);
            if ((NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) && i - 1 == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
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

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void replay(C4790c<T> cVar) {
            int i;
            int i2;
            if (cVar.getAndIncrement() == 0) {
                List<Object> list = this.buffer;
                Observer<? super T> assVar = cVar.downstream;
                Integer num = (Integer) cVar.index;
                int i3 = 0;
                if (num != null) {
                    i3 = num.intValue();
                    i = 1;
                } else {
                    cVar.index = 0;
                    i = 1;
                }
                while (!cVar.cancelled) {
                    int i4 = this.size;
                    while (i4 != i3) {
                        if (cVar.cancelled) {
                            cVar.index = null;
                            return;
                        }
                        Object obj = list.get(i3);
                        if (this.done && (i2 = i3 + 1) == i4 && i2 == (i4 = this.size)) {
                            if (NotificationLite.isComplete(obj)) {
                                assVar.onComplete();
                            } else {
                                assVar.onError(NotificationLite.getError(obj));
                            }
                            cVar.index = null;
                            cVar.cancelled = true;
                            return;
                        }
                        assVar.onNext(obj);
                        i3++;
                    }
                    if (i3 == this.size) {
                        cVar.index = Integer.valueOf(i3);
                        i = cVar.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
                cVar.index = null;
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public int size() {
            int i = this.size;
            if (i == 0) {
                return 0;
            }
            int i2 = i - 1;
            Object obj = this.buffer.get(i2);
            return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i2 : i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$a */
    /* loaded from: classes3.dex */
    public static final class C4788a<T> extends AtomicReference<C4788a<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        C4788a(T t) {
            this.value = t;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$f */
    /* loaded from: classes3.dex */
    public static final class C4793f<T> extends AtomicReference<C4793f<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        C4793f(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$e */
    /* loaded from: classes3.dex */
    static final class C4792e<T> extends AtomicReference<Object> implements AbstractC4789b<T> {
        private static final long serialVersionUID = 1107649250281456395L;
        volatile boolean done;
        volatile C4788a<Object> head;
        final int maxSize;
        int size;
        C4788a<Object> tail;

        C4792e(int i) {
            this.maxSize = ObjectHelper.m9878a(i, "maxSize");
            C4788a<Object> aVar = new C4788a<>(null);
            this.tail = aVar;
            this.head = aVar;
        }

        void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void add(T t) {
            C4788a<Object> aVar = new C4788a<>(t);
            C4788a<Object> aVar2 = this.tail;
            this.tail = aVar;
            this.size++;
            aVar2.set(aVar);
            trim();
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void addFinal(Object obj) {
            C4788a<Object> aVar = new C4788a<>(obj);
            C4788a<Object> aVar2 = this.tail;
            this.tail = aVar;
            this.size++;
            aVar2.lazySet(aVar);
            trimHead();
            this.done = true;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void trimHead() {
            C4788a<Object> aVar = this.head;
            if (aVar.value != null) {
                C4788a<Object> aVar2 = new C4788a<>(null);
                aVar2.lazySet(aVar.get());
                this.head = aVar2;
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        @atn
        public T getValue() {
            C4788a<Object> aVar = this.head;
            C4788a<Object> aVar2 = null;
            while (true) {
                C4788a<T> aVar3 = aVar.get();
                if (aVar3 == null) {
                    break;
                }
                aVar2 = aVar;
                aVar = aVar3;
            }
            T t = (T) aVar.value;
            if (t == null) {
                return null;
            }
            return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? (T) aVar2.value : t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.ReplaySubject.AbstractC4789b
        public T[] getValues(T[] tArr) {
            C4788a<T> aVar = this.head;
            int size = size();
            if (size != 0) {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    aVar = aVar.get();
                    tArr[i] = aVar.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void replay(C4790c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() == 0) {
                Observer<? super T> assVar = cVar.downstream;
                C4788a<T> aVar = (C4788a) cVar.index;
                if (aVar == null) {
                    aVar = this.head;
                    i = 1;
                } else {
                    i = 1;
                }
                while (!cVar.cancelled) {
                    C4788a<T> aVar2 = aVar.get();
                    if (aVar2 != null) {
                        Object obj = (T) aVar2.value;
                        if (!this.done || aVar2.get() != null) {
                            assVar.onNext(obj);
                            aVar = aVar2;
                        } else {
                            if (NotificationLite.isComplete(obj)) {
                                assVar.onComplete();
                            } else {
                                assVar.onError(NotificationLite.getError(obj));
                            }
                            cVar.index = null;
                            cVar.cancelled = true;
                            return;
                        }
                    } else if (aVar.get() != null) {
                        continue;
                    } else {
                        cVar.index = aVar;
                        i = cVar.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
                cVar.index = null;
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public int size() {
            C4788a<Object> aVar = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                C4788a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    Object obj = aVar.value;
                    return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i - 1 : i;
                }
                i++;
                aVar = aVar2;
            }
            return i;
        }
    }

    /* compiled from: ReplaySubject.java */
    /* renamed from: z1.buw$d */
    /* loaded from: classes3.dex */
    static final class C4791d<T> extends AtomicReference<Object> implements AbstractC4789b<T> {
        private static final long serialVersionUID = -8056260896137901749L;
        volatile boolean done;
        volatile C4793f<Object> head;
        final long maxAge;
        final int maxSize;
        final Scheduler scheduler;
        int size;
        C4793f<Object> tail;
        final TimeUnit unit;

        C4791d(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.maxSize = ObjectHelper.m9878a(i, "maxSize");
            this.maxAge = ObjectHelper.m9876a(j, "maxAge");
            this.unit = (TimeUnit) ObjectHelper.m9873a(timeUnit, "unit is null");
            this.scheduler = (Scheduler) ObjectHelper.m9873a(astVar, "scheduler is null");
            C4793f<Object> fVar = new C4793f<>(null, 0L);
            this.tail = fVar;
            this.head = fVar;
        }

        void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4793f<Object> fVar = this.head;
            while (true) {
                C4793f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    this.head = fVar;
                    return;
                } else if (fVar2.time > a) {
                    this.head = fVar;
                    return;
                } else {
                    fVar = fVar2;
                }
            }
        }

        void trimFinal() {
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4793f<Object> fVar = this.head;
            while (true) {
                C4793f<T> fVar2 = fVar.get();
                if (fVar2.get() == null) {
                    if (fVar.value != null) {
                        C4793f<Object> fVar3 = new C4793f<>(null, 0L);
                        fVar3.lazySet(fVar.get());
                        this.head = fVar3;
                        return;
                    }
                    this.head = fVar;
                    return;
                } else if (fVar2.time <= a) {
                    fVar = fVar2;
                } else if (fVar.value != null) {
                    C4793f<Object> fVar4 = new C4793f<>(null, 0L);
                    fVar4.lazySet(fVar.get());
                    this.head = fVar4;
                    return;
                } else {
                    this.head = fVar;
                    return;
                }
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void add(T t) {
            C4793f<Object> fVar = new C4793f<>(t, this.scheduler.mo9035a(this.unit));
            C4793f<Object> fVar2 = this.tail;
            this.tail = fVar;
            this.size++;
            fVar2.set(fVar);
            trim();
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void addFinal(Object obj) {
            C4793f<Object> fVar = new C4793f<>(obj, cjm.f20759b);
            C4793f<Object> fVar2 = this.tail;
            this.tail = fVar;
            this.size++;
            fVar2.lazySet(fVar);
            trimFinal();
            this.done = true;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void trimHead() {
            C4793f<Object> fVar = this.head;
            if (fVar.value != null) {
                C4793f<Object> fVar2 = new C4793f<>(null, 0L);
                fVar2.lazySet(fVar.get());
                this.head = fVar2;
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        @atn
        public T getValue() {
            T t;
            C4793f<Object> fVar = this.head;
            C4793f<Object> fVar2 = null;
            while (true) {
                C4793f<T> fVar3 = fVar.get();
                if (fVar3 == null) {
                    break;
                }
                fVar2 = fVar;
                fVar = fVar3;
            }
            if (fVar.time >= this.scheduler.mo9035a(this.unit) - this.maxAge && (t = (T) fVar.value) != null) {
                return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? (T) fVar2.value : t;
            }
            return null;
        }

        C4793f<Object> getHead() {
            C4793f<Object> fVar = this.head;
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4793f<Object> fVar2 = fVar.get();
            C4793f<Object> fVar3 = fVar;
            while (fVar2 != null && fVar2.time <= a) {
                fVar2 = fVar2.get();
                fVar3 = fVar2;
            }
            return fVar3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.ReplaySubject.AbstractC4789b
        public T[] getValues(T[] tArr) {
            C4793f<T> head = getHead();
            int size = size(head);
            if (size != 0) {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    head = head.get();
                    tArr[i] = head.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public void replay(C4790c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() == 0) {
                Observer<? super T> assVar = cVar.downstream;
                C4793f<T> fVar = (C4793f) cVar.index;
                if (fVar == null) {
                    fVar = getHead();
                    i = 1;
                } else {
                    i = 1;
                }
                while (true) {
                    fVar = fVar;
                    if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    }
                    while (!cVar.cancelled) {
                        C4793f<T> fVar2 = fVar.get();
                        if (fVar2 != null) {
                            Object obj = (T) fVar2.value;
                            if (!this.done || fVar2.get() != null) {
                                assVar.onNext(obj);
                                fVar = fVar2;
                            } else {
                                if (NotificationLite.isComplete(obj)) {
                                    assVar.onComplete();
                                } else {
                                    assVar.onError(NotificationLite.getError(obj));
                                }
                                cVar.index = null;
                                cVar.cancelled = true;
                                return;
                            }
                        } else if (fVar.get() == null) {
                            cVar.index = fVar;
                            i = cVar.addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    }
                    cVar.index = null;
                    return;
                }
            }
        }

        @Override // p110z1.ReplaySubject.AbstractC4789b
        public int size() {
            return size(getHead());
        }

        int size(C4793f<Object> fVar) {
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                C4793f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    Object obj = fVar.value;
                    return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i - 1 : i;
                }
                i++;
                fVar = fVar2;
            }
            return i;
        }
    }
}
