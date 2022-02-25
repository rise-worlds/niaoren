package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.biv */
/* loaded from: classes3.dex */
public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final long f18889b;

    /* renamed from: c */
    final long f18890c;

    /* renamed from: d */
    final TimeUnit f18891d;

    /* renamed from: e */
    final Scheduler f18892e;

    /* renamed from: f */
    final Callable<U> f18893f;

    /* renamed from: g */
    final int f18894g;

    /* renamed from: h */
    final boolean f18895h;

    public ObservableBufferTimed(ObservableSource<T> asqVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, Callable<U> callable, int i, boolean z) {
        super(asqVar);
        this.f18889b = j;
        this.f18890c = j2;
        this.f18891d = timeUnit;
        this.f18892e = astVar;
        this.f18893f = callable;
        this.f18894g = i;
        this.f18895h = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        if (this.f18889b == this.f18890c && this.f18894g == Integer.MAX_VALUE) {
            this.f18807a.subscribe(new RunnableC4399b(new SerializedObserver(assVar), this.f18893f, this.f18889b, this.f18891d, this.f18892e));
            return;
        }
        Scheduler.AbstractC3881c b = this.f18892e.mo9034b();
        if (this.f18889b == this.f18890c) {
            this.f18807a.subscribe(new RunnableC4398a(new SerializedObserver(assVar), this.f18893f, this.f18889b, this.f18891d, this.f18894g, this.f18895h, b));
        } else {
            this.f18807a.subscribe(new RunnableC4400c(new SerializedObserver(assVar), this.f18893f, this.f18889b, this.f18890c, this.f18891d, b));
        }
    }

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: z1.biv$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4399b<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {

        /* renamed from: K */
        final Callable<U> f18907K;

        /* renamed from: L */
        final long f18908L;

        /* renamed from: M */
        final TimeUnit f18909M;

        /* renamed from: N */
        final Scheduler f18910N;

        /* renamed from: O */
        Disposable f18911O;

        /* renamed from: P */
        U f18912P;

        /* renamed from: Q */
        final AtomicReference<Disposable> f18913Q = new AtomicReference<>();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9398a(Observer assVar, Object obj) {
            m9651a((Observer<? super Observer>) assVar, (Observer) ((Collection) obj));
        }

        RunnableC4399b(Observer<? super U> assVar, Callable<U> callable, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(assVar, new MpscLinkedQueue());
            this.f18907K = callable;
            this.f18908L = j;
            this.f18909M = timeUnit;
            this.f18910N = astVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18911O, atrVar)) {
                this.f18911O = atrVar;
                try {
                    this.f18912P = (U) ((Collection) ObjectHelper.m9873a(this.f18907K.call(), "The buffer supplied is null"));
                    this.f17632a.onSubscribe(this);
                    if (!this.f17634c) {
                        Scheduler astVar = this.f18910N;
                        long j = this.f18908L;
                        Disposable a = astVar.mo9485a(this, j, j, this.f18909M);
                        if (!this.f18913Q.compareAndSet(null, a)) {
                            a.dispose();
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    dispose();
                    EmptyDisposable.error(th, this.f17632a);
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f18912P;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.f18912P = null;
            }
            this.f17632a.onError(th);
            DisposableHelper.dispose(this.f18913Q);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.f18912P;
                this.f18912P = null;
            }
            if (u != null) {
                this.f17633b.offer(u);
                this.f17635d = true;
                if (mo9396c()) {
                    QueueDrainHelper.m9374a((SimplePlainQueue) this.f17633b, (Observer) this.f17632a, false, (Disposable) null, (ObservableQueueDrain) this);
                }
            }
            DisposableHelper.dispose(this.f18913Q);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.f18913Q);
            this.f18911O.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18913Q.get() == DisposableHelper.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            U u;
            try {
                U u2 = (U) ((Collection) ObjectHelper.m9873a(this.f18907K.call(), "The bufferSupplier returned a null buffer"));
                synchronized (this) {
                    u = this.f18912P;
                    if (u != null) {
                        this.f18912P = u2;
                    }
                }
                if (u == null) {
                    DisposableHelper.dispose(this.f18913Q);
                } else {
                    m9843a(u, false, this);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f17632a.onError(th);
                dispose();
            }
        }

        /* renamed from: a */
        public void m9651a(Observer<? super U> assVar, U u) {
            this.f17632a.onNext(u);
        }
    }

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: z1.biv$c */
    /* loaded from: classes3.dex */
    static final class RunnableC4400c<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {

        /* renamed from: K */
        final Callable<U> f18914K;

        /* renamed from: L */
        final long f18915L;

        /* renamed from: M */
        final long f18916M;

        /* renamed from: N */
        final TimeUnit f18917N;

        /* renamed from: O */
        final Scheduler.AbstractC3881c f18918O;

        /* renamed from: P */
        final List<U> f18919P = new LinkedList();

        /* renamed from: Q */
        Disposable f18920Q;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9398a(Observer assVar, Object obj) {
            m9650a((Observer<? super Observer>) assVar, (Observer) ((Collection) obj));
        }

        RunnableC4400c(Observer<? super U> assVar, Callable<U> callable, long j, long j2, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            super(assVar, new MpscLinkedQueue());
            this.f18914K = callable;
            this.f18915L = j;
            this.f18916M = j2;
            this.f18917N = timeUnit;
            this.f18918O = cVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18920Q, atrVar)) {
                this.f18920Q = atrVar;
                try {
                    Collection collection = (Collection) ObjectHelper.m9873a(this.f18914K.call(), "The buffer supplied is null");
                    this.f18919P.add(collection);
                    this.f17632a.onSubscribe(this);
                    Scheduler.AbstractC3881c cVar = this.f18918O;
                    long j = this.f18916M;
                    cVar.mo9511a(this, j, j, this.f18917N);
                    this.f18918O.mo9030a(new RunnableC4402b(collection), this.f18915L, this.f18917N);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    atrVar.dispose();
                    EmptyDisposable.error(th, this.f17632a);
                    this.f18918O.dispose();
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (U u : this.f18919P) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f17635d = true;
            m9647f();
            this.f17632a.onError(th);
            this.f18918O.dispose();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            ArrayList<Collection> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.f18919P);
                this.f18919P.clear();
            }
            for (Collection collection : arrayList) {
                this.f17633b.offer(collection);
            }
            this.f17635d = true;
            if (mo9396c()) {
                QueueDrainHelper.m9374a((SimplePlainQueue) this.f17633b, (Observer) this.f17632a, false, (Disposable) this.f18918O, (ObservableQueueDrain) this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f17634c) {
                this.f17634c = true;
                m9647f();
                this.f18920Q.dispose();
                this.f18918O.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        /* renamed from: f */
        void m9647f() {
            synchronized (this) {
                this.f18919P.clear();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            if (!this.f17634c) {
                try {
                    Collection collection = (Collection) ObjectHelper.m9873a(this.f18914K.call(), "The bufferSupplier returned a null buffer");
                    synchronized (this) {
                        if (!this.f17634c) {
                            this.f18919P.add(collection);
                            this.f18918O.mo9030a(new RunnableC4401a(collection), this.f18915L, this.f18917N);
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17632a.onError(th);
                    dispose();
                }
            }
        }

        /* renamed from: a */
        public void m9650a(Observer<? super U> assVar, U u) {
            assVar.onNext(u);
        }

        /* compiled from: ObservableBufferTimed.java */
        /* renamed from: z1.biv$c$a */
        /* loaded from: classes3.dex */
        final class RunnableC4401a implements Runnable {

            /* renamed from: b */
            private final U f18922b;

            RunnableC4401a(U u) {
                this.f18922b = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (RunnableC4400c.this) {
                    RunnableC4400c.this.f18919P.remove(this.f18922b);
                }
                RunnableC4400c cVar = RunnableC4400c.this;
                cVar.m9842b(this.f18922b, false, cVar.f18918O);
            }
        }

        /* compiled from: ObservableBufferTimed.java */
        /* renamed from: z1.biv$c$b */
        /* loaded from: classes3.dex */
        final class RunnableC4402b implements Runnable {

            /* renamed from: b */
            private final U f18924b;

            RunnableC4402b(U u) {
                this.f18924b = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (RunnableC4400c.this) {
                    RunnableC4400c.this.f18919P.remove(this.f18924b);
                }
                RunnableC4400c cVar = RunnableC4400c.this;
                cVar.m9842b(this.f18924b, false, cVar.f18918O);
            }
        }
    }

    /* compiled from: ObservableBufferTimed.java */
    /* renamed from: z1.biv$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4398a<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {

        /* renamed from: K */
        final Callable<U> f18896K;

        /* renamed from: L */
        final long f18897L;

        /* renamed from: M */
        final TimeUnit f18898M;

        /* renamed from: N */
        final int f18899N;

        /* renamed from: O */
        final boolean f18900O;

        /* renamed from: P */
        final Scheduler.AbstractC3881c f18901P;

        /* renamed from: Q */
        U f18902Q;

        /* renamed from: R */
        Disposable f18903R;

        /* renamed from: S */
        Disposable f18904S;

        /* renamed from: T */
        long f18905T;

        /* renamed from: U */
        long f18906U;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9398a(Observer assVar, Object obj) {
            m9652a((Observer<? super Observer>) assVar, (Observer) ((Collection) obj));
        }

        RunnableC4398a(Observer<? super U> assVar, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.AbstractC3881c cVar) {
            super(assVar, new MpscLinkedQueue());
            this.f18896K = callable;
            this.f18897L = j;
            this.f18898M = timeUnit;
            this.f18899N = i;
            this.f18900O = z;
            this.f18901P = cVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18904S, atrVar)) {
                this.f18904S = atrVar;
                try {
                    this.f18902Q = (U) ((Collection) ObjectHelper.m9873a(this.f18896K.call(), "The buffer supplied is null"));
                    this.f17632a.onSubscribe(this);
                    Scheduler.AbstractC3881c cVar = this.f18901P;
                    long j = this.f18897L;
                    this.f18903R = cVar.mo9511a(this, j, j, this.f18898M);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    atrVar.dispose();
                    EmptyDisposable.error(th, this.f17632a);
                    this.f18901P.dispose();
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f18902Q;
                if (u != null) {
                    u.add(t);
                    if (u.size() >= this.f18899N) {
                        this.f18902Q = null;
                        this.f18905T++;
                        if (this.f18900O) {
                            this.f18903R.dispose();
                        }
                        m9842b(u, false, this);
                        try {
                            U u2 = (U) ((Collection) ObjectHelper.m9873a(this.f18896K.call(), "The buffer supplied is null"));
                            synchronized (this) {
                                this.f18902Q = u2;
                                this.f18906U++;
                            }
                            if (this.f18900O) {
                                Scheduler.AbstractC3881c cVar = this.f18901P;
                                long j = this.f18897L;
                                this.f18903R = cVar.mo9511a(this, j, j, this.f18898M);
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            this.f17632a.onError(th);
                            dispose();
                        }
                    }
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.f18902Q = null;
            }
            this.f17632a.onError(th);
            this.f18901P.dispose();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            U u;
            this.f18901P.dispose();
            synchronized (this) {
                u = this.f18902Q;
                this.f18902Q = null;
            }
            this.f17633b.offer(u);
            this.f17635d = true;
            if (mo9396c()) {
                QueueDrainHelper.m9374a((SimplePlainQueue) this.f17633b, (Observer) this.f17632a, false, (Disposable) this, (ObservableQueueDrain) this);
            }
        }

        /* renamed from: a */
        public void m9652a(Observer<? super U> assVar, U u) {
            assVar.onNext(u);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f17634c) {
                this.f17634c = true;
                this.f18904S.dispose();
                this.f18901P.dispose();
                synchronized (this) {
                    this.f18902Q = null;
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f18896K.call(), "The bufferSupplier returned a null buffer"));
                synchronized (this) {
                    U u2 = this.f18902Q;
                    if (u2 != null && this.f18905T == this.f18906U) {
                        this.f18902Q = u;
                        m9842b(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                dispose();
                this.f17632a.onError(th);
            }
        }
    }
}
