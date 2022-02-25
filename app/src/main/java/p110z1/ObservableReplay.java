package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.blw */
/* loaded from: classes3.dex */
public final class ObservableReplay<T> extends ConnectableObservable<T> implements ResettableConnectable, HasUpstreamObservableSource<T> {

    /* renamed from: e */
    static final AbstractC4519b f19299e = new C4532o();

    /* renamed from: a */
    final ObservableSource<T> f19300a;

    /* renamed from: b */
    final AtomicReference<C4527j<T>> f19301b;

    /* renamed from: c */
    final AbstractC4519b<T> f19302c;

    /* renamed from: d */
    final ObservableSource<T> f19303d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4519b<T> {
        /* renamed from: a */
        AbstractC4525h<T> mo9577a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$h */
    /* loaded from: classes3.dex */
    public interface AbstractC4525h<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(C4521d<T> dVar);
    }

    /* renamed from: a */
    public static <U, R> Observable<R> m9586a(Callable<? extends ConnectableObservable<U>> callable, Function<? super Observable<U>, ? extends ObservableSource<R>> aunVar) {
        return RxJavaPlugins.m9203a(new C4522e(callable, aunVar));
    }

    /* renamed from: a */
    public static <T> ConnectableObservable<T> m9581a(ConnectableObservable<T> btkVar, Scheduler astVar) {
        return RxJavaPlugins.m9190a((ConnectableObservable) new C4524g(btkVar, btkVar.m10491a(astVar)));
    }

    /* renamed from: w */
    public static <T> ConnectableObservable<T> m9579w(ObservableSource<? extends T> asqVar) {
        return m9583a((ObservableSource) asqVar, f19299e);
    }

    /* renamed from: h */
    public static <T> ConnectableObservable<T> m9580h(ObservableSource<T> asqVar, int i) {
        if (i == Integer.MAX_VALUE) {
            return m9579w(asqVar);
        }
        return m9583a((ObservableSource) asqVar, (AbstractC4519b) new C4526i(i));
    }

    /* renamed from: a */
    public static <T> ConnectableObservable<T> m9585a(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        return m9584a(asqVar, j, timeUnit, astVar, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static <T> ConnectableObservable<T> m9584a(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return m9583a((ObservableSource) asqVar, (AbstractC4519b) new C4529l(i, j, timeUnit, astVar));
    }

    /* renamed from: a */
    static <T> ConnectableObservable<T> m9583a(ObservableSource<T> asqVar, AbstractC4519b<T> bVar) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.m9190a((ConnectableObservable) new ObservableReplay(new C4528k(atomicReference, bVar), asqVar, atomicReference, bVar));
    }

    private ObservableReplay(ObservableSource<T> asqVar, ObservableSource<T> asqVar2, AtomicReference<C4527j<T>> atomicReference, AbstractC4519b<T> bVar) {
        this.f19303d = asqVar;
        this.f19300a = asqVar2;
        this.f19301b = atomicReference;
        this.f19302c = bVar;
    }

    @Override // p110z1.HasUpstreamObservableSource
    /* renamed from: a */
    public ObservableSource<T> mo9587a() {
        return this.f19300a;
    }

    @Override // p110z1.ResettableConnectable
    /* renamed from: a */
    public void mo9582a(Disposable atrVar) {
        this.f19301b.compareAndSet((C4527j) atrVar, null);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f19303d.subscribe(assVar);
    }

    @Override // p110z1.ConnectableObservable
    /* renamed from: k */
    public void mo9358k(Consumer<? super Disposable> aumVar) {
        C4527j<T> jVar;
        while (true) {
            jVar = this.f19301b.get();
            if (jVar != null && !jVar.isDisposed()) {
                break;
            }
            C4527j<T> jVar2 = new C4527j<>(this.f19302c.mo9577a());
            if (this.f19301b.compareAndSet(jVar, jVar2)) {
                jVar = jVar2;
                break;
            }
        }
        boolean z = !jVar.shouldConnect.get() && jVar.shouldConnect.compareAndSet(false, true);
        try {
            aumVar.accept(jVar);
            if (z) {
                this.f19300a.subscribe(jVar);
            }
        } catch (Throwable th) {
            if (z) {
                jVar.shouldConnect.compareAndSet(true, false);
            }
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$j */
    /* loaded from: classes3.dex */
    static final class C4527j<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        static final C4521d[] EMPTY = new C4521d[0];
        static final C4521d[] TERMINATED = new C4521d[0];
        private static final long serialVersionUID = -533785617179540163L;
        final AbstractC4525h<T> buffer;
        boolean done;
        final AtomicReference<C4521d[]> observers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        C4527j(AbstractC4525h<T> hVar) {
            this.buffer = hVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.observers.set(TERMINATED);
            DisposableHelper.dispose(this);
        }

        boolean add(C4521d<T> dVar) {
            C4521d[] dVarArr;
            C4521d[] dVarArr2;
            do {
                dVarArr = this.observers.get();
                if (dVarArr == TERMINATED) {
                    return false;
                }
                int length = dVarArr.length;
                dVarArr2 = new C4521d[length + 1];
                System.arraycopy(dVarArr, 0, dVarArr2, 0, length);
                dVarArr2[length] = dVar;
            } while (!this.observers.compareAndSet(dVarArr, dVarArr2));
            return true;
        }

        void remove(C4521d<T> dVar) {
            C4521d[] dVarArr;
            C4521d[] dVarArr2;
            do {
                dVarArr = this.observers.get();
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
                            C4521d[] dVarArr3 = new C4521d[length - 1];
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
            } while (!this.observers.compareAndSet(dVarArr, dVarArr2));
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                replay();
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                replayFinal();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                replayFinal();
            }
        }

        void replay() {
            for (C4521d<T> dVar : this.observers.get()) {
                this.buffer.replay(dVar);
            }
        }

        void replayFinal() {
            for (C4521d<T> dVar : this.observers.getAndSet(TERMINATED)) {
                this.buffer.replay(dVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$d */
    /* loaded from: classes3.dex */
    public static final class C4521d<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2728361546769921047L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object index;
        final C4527j<T> parent;

        C4521d(C4527j<T> jVar, Observer<? super T> assVar) {
            this.parent = jVar;
            this.child = assVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.parent.remove(this);
                this.index = null;
            }
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$p */
    /* loaded from: classes3.dex */
    static final class C4533p<T> extends ArrayList<Object> implements AbstractC4525h<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        C4533p(int i) {
            super(i);
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public void replay(C4521d<T> dVar) {
            if (dVar.getAndIncrement() == 0) {
                Observer<? super T> assVar = dVar.child;
                int i = 1;
                while (!dVar.isDisposed()) {
                    int i2 = this.size;
                    Integer num = (Integer) dVar.index();
                    int intValue = num != null ? num.intValue() : 0;
                    while (intValue < i2) {
                        if (!NotificationLite.accept(get(intValue), assVar) && !dVar.isDisposed()) {
                            intValue++;
                        } else {
                            return;
                        }
                    }
                    dVar.index = Integer.valueOf(intValue);
                    i = dVar.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$f */
    /* loaded from: classes3.dex */
    public static final class C4523f extends AtomicReference<C4523f> {
        private static final long serialVersionUID = 245354315435971818L;
        final Object value;

        C4523f(Object obj) {
            this.value = obj;
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4518a<T> extends AtomicReference<C4523f> implements AbstractC4525h<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        int size;
        C4523f tail;

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        abstract void truncate();

        AbstractC4518a() {
            C4523f fVar = new C4523f(null);
            this.tail = fVar;
            set(fVar);
        }

        final void addLast(C4523f fVar) {
            this.tail.set(fVar);
            this.tail = fVar;
            this.size++;
        }

        final void removeFirst() {
            this.size--;
            setFirst(get().get());
        }

        final void trimHead() {
            C4523f fVar = get();
            if (fVar.value != null) {
                C4523f fVar2 = new C4523f(null);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }

        final void removeSome(int i) {
            C4523f fVar = get();
            while (i > 0) {
                fVar = fVar.get();
                i--;
                this.size--;
            }
            setFirst(fVar);
        }

        final void setFirst(C4523f fVar) {
            set(fVar);
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public final void next(T t) {
            addLast(new C4523f(enterTransform(NotificationLite.next(t))));
            truncate();
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public final void error(Throwable th) {
            addLast(new C4523f(enterTransform(NotificationLite.error(th))));
            truncateFinal();
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public final void complete() {
            addLast(new C4523f(enterTransform(NotificationLite.complete())));
            truncateFinal();
        }

        @Override // p110z1.ObservableReplay.AbstractC4525h
        public final void replay(C4521d<T> dVar) {
            if (dVar.getAndIncrement() == 0) {
                int i = 1;
                do {
                    C4523f fVar = (C4523f) dVar.index();
                    if (fVar == null) {
                        fVar = getHead();
                        dVar.index = fVar;
                    }
                    while (!dVar.isDisposed()) {
                        C4523f fVar2 = fVar.get();
                        if (fVar2 == null) {
                            dVar.index = fVar;
                            i = dVar.addAndGet(-i);
                        } else if (NotificationLite.accept(leaveTransform(fVar2.value), dVar.child)) {
                            dVar.index = null;
                            return;
                        } else {
                            fVar = fVar2;
                        }
                    }
                    dVar.index = null;
                    return;
                } while (i != 0);
            }
        }

        void truncateFinal() {
            trimHead();
        }

        final void collect(Collection<? super T> collection) {
            C4523f head = getHead();
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

        C4523f getHead() {
            return get();
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$n */
    /* loaded from: classes3.dex */
    static final class C4531n<T> extends AbstractC4518a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        C4531n(int i) {
            this.limit = i;
        }

        @Override // p110z1.ObservableReplay.AbstractC4518a
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$m */
    /* loaded from: classes3.dex */
    static final class C4530m<T> extends AbstractC4518a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final Scheduler scheduler;
        final TimeUnit unit;

        C4530m(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.scheduler = astVar;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // p110z1.ObservableReplay.AbstractC4518a
        Object enterTransform(Object obj) {
            return new Timed(obj, this.scheduler.mo9035a(this.unit), this.unit);
        }

        @Override // p110z1.ObservableReplay.AbstractC4518a
        Object leaveTransform(Object obj) {
            return ((Timed) obj).m9027a();
        }

        @Override // p110z1.ObservableReplay.AbstractC4518a
        void truncate() {
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4523f fVar = (C4523f) get();
            C4523f fVar2 = fVar.get();
            int i = 0;
            C4523f fVar3 = fVar;
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
        @Override // p110z1.ObservableReplay.AbstractC4518a
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
                z1.blw$f r2 = (p110z1.ObservableReplay.C4523f) r2
                java.lang.Object r3 = r2.get()
                z1.blw$f r3 = (p110z1.ObservableReplay.C4523f) r3
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
                z1.blw$f r3 = (p110z1.ObservableReplay.C4523f) r3
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
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableReplay.C4530m.truncateFinal():void");
        }

        @Override // p110z1.ObservableReplay.AbstractC4518a
        C4523f getHead() {
            long a = this.scheduler.mo9035a(this.unit) - this.maxAge;
            C4523f fVar = (C4523f) get();
            C4523f fVar2 = fVar.get();
            C4523f fVar3 = fVar;
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

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$o */
    /* loaded from: classes3.dex */
    static final class C4532o implements AbstractC4519b<Object> {
        C4532o() {
        }

        @Override // p110z1.ObservableReplay.AbstractC4519b
        /* renamed from: a */
        public AbstractC4525h<Object> mo9577a() {
            return new C4533p(16);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$c */
    /* loaded from: classes3.dex */
    static final class C4520c<R> implements Consumer<Disposable> {

        /* renamed from: a */
        private final ObserverResourceWrapper<R> f19304a;

        C4520c(ObserverResourceWrapper<R> bnsVar) {
            this.f19304a = bnsVar;
        }

        /* renamed from: a */
        public void accept(Disposable atrVar) {
            this.f19304a.setResource(atrVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$i */
    /* loaded from: classes3.dex */
    public static final class C4526i<T> implements AbstractC4519b<T> {

        /* renamed from: a */
        private final int f19309a;

        C4526i(int i) {
            this.f19309a = i;
        }

        @Override // p110z1.ObservableReplay.AbstractC4519b
        /* renamed from: a */
        public AbstractC4525h<T> mo9577a() {
            return new C4531n(this.f19309a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$l */
    /* loaded from: classes3.dex */
    public static final class C4529l<T> implements AbstractC4519b<T> {

        /* renamed from: a */
        private final int f19312a;

        /* renamed from: b */
        private final long f19313b;

        /* renamed from: c */
        private final TimeUnit f19314c;

        /* renamed from: d */
        private final Scheduler f19315d;

        C4529l(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f19312a = i;
            this.f19313b = j;
            this.f19314c = timeUnit;
            this.f19315d = astVar;
        }

        @Override // p110z1.ObservableReplay.AbstractC4519b
        /* renamed from: a */
        public AbstractC4525h<T> mo9577a() {
            return new C4530m(this.f19312a, this.f19313b, this.f19314c, this.f19315d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$k */
    /* loaded from: classes3.dex */
    public static final class C4528k<T> implements ObservableSource<T> {

        /* renamed from: a */
        private final AtomicReference<C4527j<T>> f19310a;

        /* renamed from: b */
        private final AbstractC4519b<T> f19311b;

        C4528k(AtomicReference<C4527j<T>> atomicReference, AbstractC4519b<T> bVar) {
            this.f19310a = atomicReference;
            this.f19311b = bVar;
        }

        @Override // p110z1.ObservableSource
        public void subscribe(Observer<? super T> assVar) {
            C4527j<T> jVar;
            while (true) {
                jVar = this.f19310a.get();
                if (jVar != null) {
                    break;
                }
                C4527j<T> jVar2 = new C4527j<>(this.f19311b.mo9577a());
                if (this.f19310a.compareAndSet(null, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            }
            C4521d<T> dVar = new C4521d<>(jVar, assVar);
            assVar.onSubscribe(dVar);
            jVar.add(dVar);
            if (dVar.isDisposed()) {
                jVar.remove(dVar);
            } else {
                jVar.buffer.replay(dVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$e */
    /* loaded from: classes3.dex */
    public static final class C4522e<R, U> extends Observable<R> {

        /* renamed from: a */
        private final Callable<? extends ConnectableObservable<U>> f19305a;

        /* renamed from: b */
        private final Function<? super Observable<U>, ? extends ObservableSource<R>> f19306b;

        C4522e(Callable<? extends ConnectableObservable<U>> callable, Function<? super Observable<U>, ? extends ObservableSource<R>> aunVar) {
            this.f19305a = callable;
            this.f19306b = aunVar;
        }

        @Override // p110z1.Observable
        /* renamed from: a */
        protected void mo34a(Observer<? super R> assVar) {
            try {
                ConnectableObservable btkVar = (ConnectableObservable) ObjectHelper.m9873a(this.f19305a.call(), "The connectableFactory returned a null ConnectableObservable");
                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19306b.apply(btkVar), "The selector returned a null ObservableSource");
                ObserverResourceWrapper bnsVar = new ObserverResourceWrapper(assVar);
                asqVar.subscribe(bnsVar);
                btkVar.mo9358k((Consumer<? super Disposable>) new C4520c(bnsVar));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, assVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableReplay.java */
    /* renamed from: z1.blw$g */
    /* loaded from: classes3.dex */
    public static final class C4524g<T> extends ConnectableObservable<T> {

        /* renamed from: a */
        private final ConnectableObservable<T> f19307a;

        /* renamed from: b */
        private final Observable<T> f19308b;

        C4524g(ConnectableObservable<T> btkVar, Observable<T> aslVar) {
            this.f19307a = btkVar;
            this.f19308b = aslVar;
        }

        @Override // p110z1.ConnectableObservable
        /* renamed from: k */
        public void mo9358k(Consumer<? super Disposable> aumVar) {
            this.f19307a.mo9358k(aumVar);
        }

        @Override // p110z1.Observable
        /* renamed from: a */
        protected void mo34a(Observer<? super T> assVar) {
            this.f19308b.subscribe(assVar);
        }
    }
}
