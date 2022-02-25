package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bct */
/* loaded from: classes3.dex */
public final class FlowableReplay<T> extends ConnectableFlowable<T> implements ResettableConnectable, HasUpstreamPublisher<T> {

    /* renamed from: f */
    static final Callable f18278f = new CallableC4163c();

    /* renamed from: b */
    final Flowable<T> f18279b;

    /* renamed from: c */
    final AtomicReference<C4171j<T>> f18280c;

    /* renamed from: d */
    final Callable<? extends AbstractC4168g<T>> f18281d;

    /* renamed from: e */
    final Publisher<T> f18282e;

    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$g */
    /* loaded from: classes3.dex */
    interface AbstractC4168g<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(C4164d<T> dVar);
    }

    /* renamed from: a */
    public static <U, R> Flowable<R> m9748a(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends Publisher<R>> aunVar) {
        return new C4165e(callable, aunVar);
    }

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9742a(ConnectableFlowable<T> aueVar, Scheduler astVar) {
        return RxJavaPlugins.m9198a((ConnectableFlowable) new C4162b(aueVar, aueVar.m11184a(astVar)));
    }

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9747a(Flowable<? extends T> arvVar) {
        return m9743a((Flowable) arvVar, f18278f);
    }

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9746a(Flowable<T> arvVar, int i) {
        if (i == Integer.MAX_VALUE) {
            return m9747a((Flowable) arvVar);
        }
        return m9743a((Flowable) arvVar, (Callable) new CallableC4169h(i));
    }

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9745a(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        return m9744a(arvVar, j, timeUnit, astVar, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9744a(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return m9743a((Flowable) arvVar, (Callable) new CallableC4172k(i, j, timeUnit, astVar));
    }

    /* renamed from: a */
    static <T> ConnectableFlowable<T> m9743a(Flowable<T> arvVar, Callable<? extends AbstractC4168g<T>> callable) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.m9198a((ConnectableFlowable) new FlowableReplay(new C4170i(atomicReference, callable), arvVar, atomicReference, callable));
    }

    private FlowableReplay(Publisher<T> dbwVar, Flowable<T> arvVar, AtomicReference<C4171j<T>> atomicReference, Callable<? extends AbstractC4168g<T>> callable) {
        this.f18282e = dbwVar;
        this.f18279b = arvVar;
        this.f18280c = atomicReference;
        this.f18281d = callable;
    }

    @Override // p110z1.HasUpstreamPublisher
    /* renamed from: p_ */
    public Publisher<T> mo9741p_() {
        return this.f18279b;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18282e.subscribe(dbxVar);
    }

    @Override // p110z1.ResettableConnectable
    /* renamed from: a */
    public void mo9582a(Disposable atrVar) {
        this.f18280c.compareAndSet((C4171j) atrVar, null);
    }

    @Override // p110z1.ConnectableFlowable
    /* renamed from: l */
    public void mo9740l(Consumer<? super Disposable> aumVar) {
        C4171j<T> jVar;
        while (true) {
            jVar = this.f18280c.get();
            if (jVar != null && !jVar.isDisposed()) {
                break;
            }
            try {
                C4171j<T> jVar2 = new C4171j<>((AbstractC4168g) this.f18281d.call());
                if (this.f18280c.compareAndSet(jVar, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            } finally {
                Exceptions.m9983b(th);
                RuntimeException a = ExceptionHelper.m9432a(th);
            }
        }
        boolean z = !jVar.shouldConnect.get() && jVar.shouldConnect.compareAndSet(false, true);
        try {
            aumVar.accept(jVar);
            if (z) {
                this.f18279b.m11187a((FlowableSubscriber) jVar);
            }
        } catch (Throwable th) {
            if (z) {
                jVar.shouldConnect.compareAndSet(true, false);
            }
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$j */
    /* loaded from: classes3.dex */
    public static final class C4171j<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, Disposable {
        static final C4164d[] EMPTY = new C4164d[0];
        static final C4164d[] TERMINATED = new C4164d[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final AbstractC4168g<T> buffer;
        boolean done;
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicInteger management = new AtomicInteger();
        final AtomicReference<C4164d<T>[]> subscribers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        C4171j(AbstractC4168g<T> gVar) {
            this.buffer = gVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        boolean add(C4164d<T> dVar) {
            C4164d<T>[] dVarArr;
            C4164d<T>[] dVarArr2;
            if (dVar != null) {
                do {
                    dVarArr = this.subscribers.get();
                    if (dVarArr == TERMINATED) {
                        return false;
                    }
                    int length = dVarArr.length;
                    dVarArr2 = new C4164d[length + 1];
                    System.arraycopy(dVarArr, 0, dVarArr2, 0, length);
                    dVarArr2[length] = dVar;
                } while (!this.subscribers.compareAndSet(dVarArr, dVarArr2));
                return true;
            }
            throw new NullPointerException();
        }

        void remove(C4164d<T> dVar) {
            C4164d<T>[] dVarArr;
            C4164d<T>[] dVarArr2;
            do {
                dVarArr = this.subscribers.get();
                int length = dVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (dVarArr[i2].equals(dVar)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            dVarArr2 = EMPTY;
                        } else {
                            C4164d<T>[] dVarArr3 = new C4164d[length - 1];
                            System.arraycopy(dVarArr, 0, dVarArr3, 0, i);
                            System.arraycopy(dVarArr, i + 1, dVarArr3, i, (length - i) - 1);
                            dVarArr2 = dVarArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(dVarArr, dVarArr2));
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this, dbyVar)) {
                manageRequests();
                for (C4164d<T> dVar : this.subscribers.get()) {
                    this.buffer.replay(dVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (C4164d<T> dVar : this.subscribers.get()) {
                    this.buffer.replay(dVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (C4164d<T> dVar : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(dVar);
                }
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (C4164d<T> dVar : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(dVar);
                }
            }
        }

        void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int i = 1;
                while (!isDisposed()) {
                    C4164d<T>[] dVarArr = this.subscribers.get();
                    long j = this.maxChildRequested;
                    long j2 = j;
                    for (C4164d<T> dVar : dVarArr) {
                        j2 = Math.max(j2, dVar.totalRequested.get());
                    }
                    long j3 = this.maxUpstreamRequested;
                    dby dbyVar = get();
                    long j4 = j2 - j;
                    if (j4 != 0) {
                        this.maxChildRequested = j2;
                        if (dbyVar == null) {
                            long j5 = j3 + j4;
                            if (j5 < 0) {
                                j5 = cjm.f20759b;
                            }
                            this.maxUpstreamRequested = j5;
                        } else if (j3 != 0) {
                            this.maxUpstreamRequested = 0L;
                            dbyVar.request(j3 + j4);
                        } else {
                            dbyVar.request(j4);
                        }
                    } else if (!(j3 == 0 || dbyVar == null)) {
                        this.maxUpstreamRequested = 0L;
                        dbyVar.request(j3);
                    }
                    i = this.management.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$d */
    /* loaded from: classes3.dex */
    public static final class C4164d<T> extends AtomicLong implements Disposable, dby {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final C4171j<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        C4164d(C4171j<T> jVar, Subscriber<? super T> dbxVar) {
            this.parent = jVar;
            this.child = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.m9447b(this, j) != Long.MIN_VALUE) {
                BackpressureHelper.m9449a(this.totalRequested, j);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long j) {
            return BackpressureHelper.m9445d(this, j);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // p110z1.dby
        public void cancel() {
            dispose();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
                this.index = null;
            }
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$n */
    /* loaded from: classes3.dex */
    static final class C4175n<T> extends ArrayList<Object> implements AbstractC4168g<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        C4175n(int i) {
            super(i);
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public void replay(C4164d<T> dVar) {
            synchronized (dVar) {
                if (dVar.emitting) {
                    dVar.missed = true;
                    return;
                }
                dVar.emitting = true;
                Subscriber<? super T> dbxVar = dVar.child;
                while (!dVar.isDisposed()) {
                    int i = this.size;
                    Integer num = (Integer) dVar.index();
                    int intValue = num != null ? num.intValue() : 0;
                    long j = dVar.get();
                    long j2 = j;
                    long j3 = 0;
                    while (j2 != 0 && intValue < i) {
                        Object obj = get(intValue);
                        try {
                            if (!NotificationLite.accept(obj, dbxVar) && !dVar.isDisposed()) {
                                intValue++;
                                j2--;
                                j3++;
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            dVar.dispose();
                            if (!NotificationLite.isError(obj) && !NotificationLite.isComplete(obj)) {
                                dbxVar.onError(th);
                                return;
                            }
                            return;
                        }
                    }
                    if (j3 != 0) {
                        dVar.index = Integer.valueOf(intValue);
                        if (j != cjm.f20759b) {
                            dVar.produced(j3);
                        }
                    }
                    synchronized (dVar) {
                        if (!dVar.missed) {
                            dVar.emitting = false;
                            return;
                        }
                        dVar.missed = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$f */
    /* loaded from: classes3.dex */
    public static final class C4167f extends AtomicReference<C4167f> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        C4167f(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$a */
    /* loaded from: classes3.dex */
    static class C4161a<T> extends AtomicReference<C4167f> implements AbstractC4168g<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        C4167f tail;

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        void truncate() {
        }

        C4161a() {
            C4167f fVar = new C4167f(null, 0L);
            this.tail = fVar;
            set(fVar);
        }

        final void addLast(C4167f fVar) {
            this.tail.set(fVar);
            this.tail = fVar;
            this.size++;
        }

        final void removeFirst() {
            C4167f fVar = get().get();
            if (fVar != null) {
                this.size--;
                setFirst(fVar);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        final void removeSome(int i) {
            C4167f fVar = get();
            while (i > 0) {
                fVar = fVar.get();
                i--;
                this.size--;
            }
            setFirst(fVar);
        }

        final void setFirst(C4167f fVar) {
            set(fVar);
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new C4167f(enterTransform, j));
            truncate();
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new C4167f(enterTransform, j));
            truncateFinal();
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new C4167f(enterTransform, j));
            truncateFinal();
        }

        final void trimHead() {
            C4167f fVar = get();
            if (fVar.value != null) {
                C4167f fVar2 = new C4167f(null, 0L);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }

        @Override // p110z1.FlowableReplay.AbstractC4168g
        public final void replay(C4164d<T> dVar) {
            long j;
            C4167f fVar;
            synchronized (dVar) {
                if (dVar.emitting) {
                    dVar.missed = true;
                    return;
                }
                dVar.emitting = true;
                while (!dVar.isDisposed()) {
                    long j2 = dVar.get();
                    boolean z = j2 == cjm.f20759b;
                    C4167f fVar2 = (C4167f) dVar.index();
                    if (fVar2 == null) {
                        fVar2 = getHead();
                        dVar.index = fVar2;
                        BackpressureHelper.m9449a(dVar.totalRequested, fVar2.index);
                        j = 0;
                    } else {
                        j = 0;
                    }
                    while (j2 != 0 && (fVar = fVar2.get()) != null) {
                        Object leaveTransform = leaveTransform(fVar.value);
                        try {
                            if (NotificationLite.accept(leaveTransform, dVar.child)) {
                                dVar.index = null;
                                return;
                            }
                            j++;
                            j2--;
                            if (dVar.isDisposed()) {
                                dVar.index = null;
                                return;
                            }
                            fVar2 = fVar;
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            dVar.index = null;
                            dVar.dispose();
                            if (!NotificationLite.isError(leaveTransform) && !NotificationLite.isComplete(leaveTransform)) {
                                dVar.child.onError(th);
                                return;
                            }
                            return;
                        }
                    }
                    if (j != 0) {
                        dVar.index = fVar2;
                        if (!z) {
                            dVar.produced(j);
                        }
                    }
                    synchronized (dVar) {
                        if (!dVar.missed) {
                            dVar.emitting = false;
                            return;
                        }
                        dVar.missed = false;
                    }
                }
                dVar.index = null;
            }
        }

        void truncateFinal() {
            trimHead();
        }

        final void collect(Collection<? super T> collection) {
            C4167f head = getHead();
            while (true) {
                head = head.get();
                if (head != null) {
                    Object leaveTransform = leaveTransform(head.value);
                    if (!NotificationLite.isComplete(leaveTransform) && !NotificationLite.isError(leaveTransform)) {
                        collection.add((Object) NotificationLite.getValue(leaveTransform));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        C4167f getHead() {
            return get();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$m */
    /* loaded from: classes3.dex */
    public static final class C4174m<T> extends C4161a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        C4174m(int i) {
            this.limit = i;
        }

        @Override // p110z1.FlowableReplay.C4161a
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$l */
    /* loaded from: classes3.dex */
    public static final class C4173l<T> extends C4161a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final Scheduler scheduler;
        final TimeUnit unit;

        C4173l(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.scheduler = astVar;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // p110z1.FlowableReplay.C4161a
        Object enterTransform(Object obj) {
            return new Timed(obj, this.scheduler.mo9035a(this.unit), this.unit);
        }

        @Override // p110z1.FlowableReplay.C4161a
        Object leaveTransform(Object obj) {
            return ((Timed) obj).m9027a();
        }

        @Override // p110z1.FlowableReplay.C4161a
        void truncate() {
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4167f fVar = (C4167f) get();
            C4167f fVar2 = fVar.get();
            int i = 0;
            C4167f fVar3 = fVar;
            while (fVar2 != null) {
                if (this.size <= this.limit) {
                    if (((Timed) fVar2.value).m9024c() > a) {
                        break;
                    }
                    i++;
                    this.size--;
                    fVar2 = fVar2.get();
                    fVar3 = fVar2;
                } else {
                    i++;
                    this.size--;
                    fVar2 = fVar2.get();
                    fVar3 = fVar2;
                }
            }
            if (i != 0) {
                setFirst(fVar3);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0041, code lost:
            setFirst(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
            return;
         */
        @Override // p110z1.FlowableReplay.C4161a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void truncateFinal() {
            /*
                r10 = this;
                z1.ast r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.mo9035a(r1)
                long r2 = r10.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                z1.bct$f r2 = (p110z1.FlowableReplay.C4167f) r2
                java.lang.Object r3 = r2.get()
                z1.bct$f r3 = (p110z1.FlowableReplay.C4167f) r3
                r4 = 0
                r9 = r3
                r3 = r2
                r2 = r9
            L_0x001b:
                if (r2 == 0) goto L_0x003f
                int r5 = r10.size
                r6 = 1
                if (r5 <= r6) goto L_0x003f
                java.lang.Object r5 = r2.value
                z1.buq r5 = (p110z1.Timed) r5
                long r7 = r5.m9024c()
                int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r5 > 0) goto L_0x003f
                int r4 = r4 + 1
                int r3 = r10.size
                int r3 = r3 - r6
                r10.size = r3
                java.lang.Object r3 = r2.get()
                z1.bct$f r3 = (p110z1.FlowableReplay.C4167f) r3
                r9 = r3
                r3 = r2
                r2 = r9
                goto L_0x001b
            L_0x003f:
                if (r4 == 0) goto L_0x0044
                r10.setFirst(r3)
            L_0x0044:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableReplay.C4173l.truncateFinal():void");
        }

        @Override // p110z1.FlowableReplay.C4161a
        C4167f getHead() {
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4167f fVar = (C4167f) get();
            C4167f fVar2 = fVar.get();
            C4167f fVar3 = fVar;
            while (fVar2 != null) {
                Timed buqVar = (Timed) fVar2.value;
                if (NotificationLite.isComplete(buqVar.m9027a()) || NotificationLite.isError(buqVar.m9027a()) || buqVar.m9024c() > a) {
                    break;
                }
                fVar2 = fVar2.get();
                fVar3 = fVar2;
            }
            return fVar3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$e */
    /* loaded from: classes3.dex */
    public static final class C4165e<R, U> extends Flowable<R> {

        /* renamed from: b */
        private final Callable<? extends ConnectableFlowable<U>> f18285b;

        /* renamed from: c */
        private final Function<? super Flowable<U>, ? extends Publisher<R>> f18286c;

        C4165e(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends Publisher<R>> aunVar) {
            this.f18285b = callable;
            this.f18286c = aunVar;
        }

        @Override // p110z1.Flowable
        /* renamed from: d */
        protected void mo9054d(Subscriber<? super R> dbxVar) {
            try {
                ConnectableFlowable aueVar = (ConnectableFlowable) ObjectHelper.m9873a(this.f18285b.call(), "The connectableFactory returned null");
                try {
                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f18286c.apply(aueVar), "The selector returned a null Publisher");
                    SubscriberResourceWrapper bsaVar = new SubscriberResourceWrapper(dbxVar);
                    dbwVar.subscribe(bsaVar);
                    aueVar.mo9740l((Consumer<? super Disposable>) new C4166a(bsaVar));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    EmptySubscription.error(th, dbxVar);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                EmptySubscription.error(th2, dbxVar);
            }
        }

        /* compiled from: FlowableReplay.java */
        /* renamed from: z1.bct$e$a */
        /* loaded from: classes3.dex */
        final class C4166a implements Consumer<Disposable> {

            /* renamed from: b */
            private final SubscriberResourceWrapper<R> f18288b;

            C4166a(SubscriberResourceWrapper<R> bsaVar) {
                this.f18288b = bsaVar;
            }

            /* renamed from: a */
            public void accept(Disposable atrVar) {
                this.f18288b.setResource(atrVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$b */
    /* loaded from: classes3.dex */
    public static final class C4162b<T> extends ConnectableFlowable<T> {

        /* renamed from: b */
        private final ConnectableFlowable<T> f18283b;

        /* renamed from: c */
        private final Flowable<T> f18284c;

        C4162b(ConnectableFlowable<T> aueVar, Flowable<T> arvVar) {
            this.f18283b = aueVar;
            this.f18284c = arvVar;
        }

        @Override // p110z1.ConnectableFlowable
        /* renamed from: l */
        public void mo9740l(Consumer<? super Disposable> aumVar) {
            this.f18283b.mo9740l(aumVar);
        }

        @Override // p110z1.Flowable
        /* renamed from: d */
        protected void mo9054d(Subscriber<? super T> dbxVar) {
            this.f18284c.subscribe(dbxVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$h */
    /* loaded from: classes3.dex */
    public static final class CallableC4169h<T> implements Callable<AbstractC4168g<T>> {

        /* renamed from: a */
        private final int f18289a;

        CallableC4169h(int i) {
            this.f18289a = i;
        }

        /* renamed from: a */
        public AbstractC4168g<T> call() {
            return new C4174m(this.f18289a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$k */
    /* loaded from: classes3.dex */
    public static final class CallableC4172k<T> implements Callable<AbstractC4168g<T>> {

        /* renamed from: a */
        private final int f18292a;

        /* renamed from: b */
        private final long f18293b;

        /* renamed from: c */
        private final TimeUnit f18294c;

        /* renamed from: d */
        private final Scheduler f18295d;

        CallableC4172k(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f18292a = i;
            this.f18293b = j;
            this.f18294c = timeUnit;
            this.f18295d = astVar;
        }

        /* renamed from: a */
        public AbstractC4168g<T> call() {
            return new C4173l(this.f18292a, this.f18293b, this.f18294c, this.f18295d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$i */
    /* loaded from: classes3.dex */
    public static final class C4170i<T> implements Publisher<T> {

        /* renamed from: a */
        private final AtomicReference<C4171j<T>> f18290a;

        /* renamed from: b */
        private final Callable<? extends AbstractC4168g<T>> f18291b;

        C4170i(AtomicReference<C4171j<T>> atomicReference, Callable<? extends AbstractC4168g<T>> callable) {
            this.f18290a = atomicReference;
            this.f18291b = callable;
        }

        @Override // p110z1.Publisher
        public void subscribe(Subscriber<? super T> dbxVar) {
            C4171j<T> jVar;
            while (true) {
                jVar = this.f18290a.get();
                if (jVar != null) {
                    break;
                }
                try {
                    C4171j<T> jVar2 = new C4171j<>((AbstractC4168g) this.f18291b.call());
                    if (this.f18290a.compareAndSet(null, jVar2)) {
                        jVar = jVar2;
                        break;
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    EmptySubscription.error(th, dbxVar);
                    return;
                }
            }
            C4164d<T> dVar = new C4164d<>(jVar, dbxVar);
            dbxVar.onSubscribe(dVar);
            jVar.add(dVar);
            if (dVar.isDisposed()) {
                jVar.remove(dVar);
                return;
            }
            jVar.manageRequests();
            jVar.buffer.replay(dVar);
        }
    }

    /* compiled from: FlowableReplay.java */
    /* renamed from: z1.bct$c */
    /* loaded from: classes3.dex */
    static final class CallableC4163c implements Callable<Object> {
        CallableC4163c() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return new C4175n(16);
        }
    }
}
