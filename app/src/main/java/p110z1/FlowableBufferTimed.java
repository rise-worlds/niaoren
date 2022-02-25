package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.azg */
/* loaded from: classes3.dex */
public final class FlowableBufferTimed<T, U extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final long f17887c;

    /* renamed from: d */
    final long f17888d;

    /* renamed from: e */
    final TimeUnit f17889e;

    /* renamed from: f */
    final Scheduler f17890f;

    /* renamed from: g */
    final Callable<U> f17891g;

    /* renamed from: h */
    final int f17892h;

    /* renamed from: i */
    final boolean f17893i;

    public FlowableBufferTimed(Flowable<T> arvVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, Callable<U> callable, int i, boolean z) {
        super(arvVar);
        this.f17887c = j;
        this.f17888d = j2;
        this.f17889e = timeUnit;
        this.f17890f = astVar;
        this.f17891g = callable;
        this.f17892h = i;
        this.f17893i = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        if (this.f17887c == this.f17888d && this.f17892h == Integer.MAX_VALUE) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4002b(new SerializedSubscriber(dbxVar), this.f17891g, this.f17887c, this.f17889e, this.f17890f));
            return;
        }
        Scheduler.AbstractC3881c b = this.f17890f.mo9034b();
        if (this.f17887c == this.f17888d) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4001a(new SerializedSubscriber(dbxVar), this.f17891g, this.f17887c, this.f17889e, this.f17892h, this.f17893i, b));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4003c(new SerializedSubscriber(dbxVar), this.f17891g, this.f17887c, this.f17888d, this.f17889e, b));
        }
    }

    /* compiled from: FlowableBufferTimed.java */
    /* renamed from: z1.azg$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4002b<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Runnable, Disposable, dby {

        /* renamed from: a */
        final Callable<U> f17905a;

        /* renamed from: b */
        final long f17906b;

        /* renamed from: c */
        final TimeUnit f17907c;

        /* renamed from: d */
        final Scheduler f17908d;

        /* renamed from: e */
        dby f17909e;

        /* renamed from: f */
        U f17910f;

        /* renamed from: g */
        final AtomicReference<Disposable> f17911g = new AtomicReference<>();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo9383a(Subscriber dbxVar, Object obj) {
            return m9807a((Subscriber<? super Subscriber>) dbxVar, (Subscriber) ((Collection) obj));
        }

        RunnableC4002b(Subscriber<? super U> dbxVar, Callable<U> callable, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(dbxVar, new MpscLinkedQueue());
            this.f17905a = callable;
            this.f17906b = j;
            this.f17907c = timeUnit;
            this.f17908d = astVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17909e, dbyVar)) {
                this.f17909e = dbyVar;
                try {
                    this.f17910f = (U) ((Collection) ObjectHelper.m9873a(this.f17905a.call(), "The supplied buffer is null"));
                    this.f20003n.onSubscribe(this);
                    if (!this.f20005p) {
                        dbyVar.request(cjm.f20759b);
                        Scheduler astVar = this.f17908d;
                        long j = this.f17906b;
                        Disposable a = astVar.mo9485a(this, j, j, this.f17907c);
                        if (!this.f17911g.compareAndSet(null, a)) {
                            a.dispose();
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    EmptySubscription.error(th, this.f20003n);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f17910f;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.f17911g);
            synchronized (this) {
                this.f17910f = null;
            }
            this.f20003n.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            DisposableHelper.dispose(this.f17911g);
            synchronized (this) {
                U u = this.f17910f;
                if (u != null) {
                    this.f17910f = null;
                    this.f20004o.offer(u);
                    this.f20006q = true;
                    if (mo9380e()) {
                        QueueDrainHelper.m9373a((SimplePlainQueue) this.f20004o, (Subscriber) this.f20003n, false, (Disposable) null, (QueueDrain) this);
                    }
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f20005p = true;
            this.f17909e.cancel();
            DisposableHelper.dispose(this.f17911g);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f17905a.call(), "The supplied buffer is null"));
                synchronized (this) {
                    U u2 = this.f17910f;
                    if (u2 != null) {
                        this.f17910f = u;
                        m9467a(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                this.f20003n.onError(th);
            }
        }

        /* renamed from: a */
        public boolean m9807a(Subscriber<? super U> dbxVar, U u) {
            this.f20003n.onNext(u);
            return true;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            cancel();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17911g.get() == DisposableHelper.DISPOSED;
        }
    }

    /* compiled from: FlowableBufferTimed.java */
    /* renamed from: z1.azg$c */
    /* loaded from: classes3.dex */
    static final class RunnableC4003c<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Runnable, dby {

        /* renamed from: a */
        final Callable<U> f17912a;

        /* renamed from: b */
        final long f17913b;

        /* renamed from: c */
        final long f17914c;

        /* renamed from: d */
        final TimeUnit f17915d;

        /* renamed from: e */
        final Scheduler.AbstractC3881c f17916e;

        /* renamed from: f */
        final List<U> f17917f = new LinkedList();

        /* renamed from: g */
        dby f17918g;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo9383a(Subscriber dbxVar, Object obj) {
            return m9804a((Subscriber<? super Subscriber>) dbxVar, (Subscriber) ((Collection) obj));
        }

        RunnableC4003c(Subscriber<? super U> dbxVar, Callable<U> callable, long j, long j2, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            super(dbxVar, new MpscLinkedQueue());
            this.f17912a = callable;
            this.f17913b = j;
            this.f17914c = j2;
            this.f17915d = timeUnit;
            this.f17916e = cVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17918g, dbyVar)) {
                this.f17918g = dbyVar;
                try {
                    Collection collection = (Collection) ObjectHelper.m9873a(this.f17912a.call(), "The supplied buffer is null");
                    this.f17917f.add(collection);
                    this.f20003n.onSubscribe(this);
                    dbyVar.request(cjm.f20759b);
                    Scheduler.AbstractC3881c cVar = this.f17916e;
                    long j = this.f17914c;
                    cVar.mo9511a(this, j, j, this.f17915d);
                    this.f17916e.mo9030a(new RunnableC4004a(collection), this.f17913b, this.f17915d);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17916e.dispose();
                    dbyVar.cancel();
                    EmptySubscription.error(th, this.f20003n);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                for (U u : this.f17917f) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f20006q = true;
            this.f17916e.dispose();
            m9806a();
            this.f20003n.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            ArrayList<Collection> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.f17917f);
                this.f17917f.clear();
            }
            for (Collection collection : arrayList) {
                this.f20004o.offer(collection);
            }
            this.f20006q = true;
            if (mo9380e()) {
                QueueDrainHelper.m9373a((SimplePlainQueue) this.f20004o, (Subscriber) this.f20003n, false, (Disposable) this.f17916e, (QueueDrain) this);
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f20005p = true;
            this.f17918g.cancel();
            this.f17916e.dispose();
            m9806a();
        }

        /* renamed from: a */
        void m9806a() {
            synchronized (this) {
                this.f17917f.clear();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            if (!this.f20005p) {
                try {
                    Collection collection = (Collection) ObjectHelper.m9873a(this.f17912a.call(), "The supplied buffer is null");
                    synchronized (this) {
                        if (!this.f20005p) {
                            this.f17917f.add(collection);
                            this.f17916e.mo9030a(new RunnableC4004a(collection), this.f17913b, this.f17915d);
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    this.f20003n.onError(th);
                }
            }
        }

        /* renamed from: a */
        public boolean m9804a(Subscriber<? super U> dbxVar, U u) {
            dbxVar.onNext(u);
            return true;
        }

        /* compiled from: FlowableBufferTimed.java */
        /* renamed from: z1.azg$c$a */
        /* loaded from: classes3.dex */
        final class RunnableC4004a implements Runnable {

            /* renamed from: b */
            private final U f17920b;

            RunnableC4004a(U u) {
                this.f17920b = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (RunnableC4003c.this) {
                    RunnableC4003c.this.f17917f.remove(this.f17920b);
                }
                RunnableC4003c cVar = RunnableC4003c.this;
                cVar.m9465b(this.f17920b, false, cVar.f17916e);
            }
        }
    }

    /* compiled from: FlowableBufferTimed.java */
    /* renamed from: z1.azg$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4001a<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Runnable, Disposable, dby {

        /* renamed from: a */
        final Callable<U> f17894a;

        /* renamed from: b */
        final long f17895b;

        /* renamed from: c */
        final TimeUnit f17896c;

        /* renamed from: d */
        final int f17897d;

        /* renamed from: e */
        final boolean f17898e;

        /* renamed from: f */
        final Scheduler.AbstractC3881c f17899f;

        /* renamed from: g */
        U f17900g;

        /* renamed from: h */
        Disposable f17901h;

        /* renamed from: i */
        dby f17902i;

        /* renamed from: j */
        long f17903j;

        /* renamed from: k */
        long f17904k;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo9383a(Subscriber dbxVar, Object obj) {
            return m9808a((Subscriber<? super Subscriber>) dbxVar, (Subscriber) ((Collection) obj));
        }

        RunnableC4001a(Subscriber<? super U> dbxVar, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.AbstractC3881c cVar) {
            super(dbxVar, new MpscLinkedQueue());
            this.f17894a = callable;
            this.f17895b = j;
            this.f17896c = timeUnit;
            this.f17897d = i;
            this.f17898e = z;
            this.f17899f = cVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17902i, dbyVar)) {
                this.f17902i = dbyVar;
                try {
                    this.f17900g = (U) ((Collection) ObjectHelper.m9873a(this.f17894a.call(), "The supplied buffer is null"));
                    this.f20003n.onSubscribe(this);
                    Scheduler.AbstractC3881c cVar = this.f17899f;
                    long j = this.f17895b;
                    this.f17901h = cVar.mo9511a(this, j, j, this.f17896c);
                    dbyVar.request(cjm.f20759b);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17899f.dispose();
                    dbyVar.cancel();
                    EmptySubscription.error(th, this.f20003n);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f17900g;
                if (u != null) {
                    u.add(t);
                    if (u.size() >= this.f17897d) {
                        this.f17900g = null;
                        this.f17903j++;
                        if (this.f17898e) {
                            this.f17901h.dispose();
                        }
                        m9465b(u, false, this);
                        try {
                            U u2 = (U) ((Collection) ObjectHelper.m9873a(this.f17894a.call(), "The supplied buffer is null"));
                            synchronized (this) {
                                this.f17900g = u2;
                                this.f17904k++;
                            }
                            if (this.f17898e) {
                                Scheduler.AbstractC3881c cVar = this.f17899f;
                                long j = this.f17895b;
                                this.f17901h = cVar.mo9511a(this, j, j, this.f17896c);
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            cancel();
                            this.f20003n.onError(th);
                        }
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            synchronized (this) {
                this.f17900g = null;
            }
            this.f20003n.onError(th);
            this.f17899f.dispose();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.f17900g;
                this.f17900g = null;
            }
            this.f20004o.offer(u);
            this.f20006q = true;
            if (mo9380e()) {
                QueueDrainHelper.m9373a((SimplePlainQueue) this.f20004o, (Subscriber) this.f20003n, false, (Disposable) this, (QueueDrain) this);
            }
            this.f17899f.dispose();
        }

        /* renamed from: a */
        public boolean m9808a(Subscriber<? super U> dbxVar, U u) {
            dbxVar.onNext(u);
            return true;
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.f20005p) {
                this.f20005p = true;
                dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            synchronized (this) {
                this.f17900g = null;
            }
            this.f17902i.cancel();
            this.f17899f.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17899f.isDisposed();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f17894a.call(), "The supplied buffer is null"));
                synchronized (this) {
                    U u2 = this.f17900g;
                    if (u2 != null && this.f17903j == this.f17904k) {
                        this.f17900g = u;
                        m9465b(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                this.f20003n.onError(th);
            }
        }
    }
}
