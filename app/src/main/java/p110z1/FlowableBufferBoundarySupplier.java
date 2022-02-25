package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.aze */
/* loaded from: classes3.dex */
public final class FlowableBufferBoundarySupplier<T, U extends Collection<? super T>, B> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Callable<? extends Publisher<B>> f17870c;

    /* renamed from: d */
    final Callable<U> f17871d;

    public FlowableBufferBoundarySupplier(Flowable<T> arvVar, Callable<? extends Publisher<B>> callable, Callable<U> callable2) {
        super(arvVar);
        this.f17870c = callable;
        this.f17871d = callable2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C3998b(new SerializedSubscriber(dbxVar), this.f17871d, this.f17870c));
    }

    /* compiled from: FlowableBufferBoundarySupplier.java */
    /* renamed from: z1.aze$b */
    /* loaded from: classes3.dex */
    static final class C3998b<T, U extends Collection<? super T>, B> extends QueueDrainSubscriber<T, U, U> implements FlowableSubscriber<T>, Disposable, dby {

        /* renamed from: a */
        final Callable<U> f17874a;

        /* renamed from: b */
        final Callable<? extends Publisher<B>> f17875b;

        /* renamed from: c */
        dby f17876c;

        /* renamed from: d */
        final AtomicReference<Disposable> f17877d = new AtomicReference<>();

        /* renamed from: e */
        U f17878e;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo9383a(Subscriber dbxVar, Object obj) {
            return m9812a((Subscriber<? super Subscriber>) dbxVar, (Subscriber) ((Collection) obj));
        }

        C3998b(Subscriber<? super U> dbxVar, Callable<U> callable, Callable<? extends Publisher<B>> callable2) {
            super(dbxVar, new MpscLinkedQueue());
            this.f17874a = callable;
            this.f17875b = callable2;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17876c, dbyVar)) {
                this.f17876c = dbyVar;
                Subscriber<? super V> dbxVar = this.f20003n;
                try {
                    this.f17878e = (U) ((Collection) ObjectHelper.m9873a(this.f17874a.call(), "The buffer supplied is null"));
                    try {
                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f17875b.call(), "The boundary publisher supplied is null");
                        C3997a aVar = new C3997a(this);
                        this.f17877d.set(aVar);
                        dbxVar.onSubscribe(this);
                        if (!this.f20005p) {
                            dbyVar.request(cjm.f20759b);
                            dbwVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.f20005p = true;
                        dbyVar.cancel();
                        EmptySubscription.error(th, dbxVar);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.f20005p = true;
                    dbyVar.cancel();
                    EmptySubscription.error(th2, dbxVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f17878e;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            cancel();
            this.f20003n.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            synchronized (this) {
                U u = this.f17878e;
                if (u != null) {
                    this.f17878e = null;
                    this.f20004o.offer(u);
                    this.f20006q = true;
                    if (mo9380e()) {
                        QueueDrainHelper.m9373a((SimplePlainQueue) this.f20004o, (Subscriber) this.f20003n, false, (Disposable) this, (QueueDrain) this);
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
            if (!this.f20005p) {
                this.f20005p = true;
                this.f17876c.cancel();
                m9813a();
                if (mo9380e()) {
                    this.f20004o.clear();
                }
            }
        }

        /* renamed from: a */
        void m9813a() {
            DisposableHelper.dispose(this.f17877d);
        }

        /* renamed from: b */
        void m9811b() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f17874a.call(), "The buffer supplied is null"));
                try {
                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f17875b.call(), "The boundary publisher supplied is null");
                    C3997a aVar = new C3997a(this);
                    if (DisposableHelper.replace(this.f17877d, aVar)) {
                        synchronized (this) {
                            U u2 = this.f17878e;
                            if (u2 != null) {
                                this.f17878e = u;
                                dbwVar.subscribe(aVar);
                                m9467a(u2, false, this);
                            }
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f20005p = true;
                    this.f17876c.cancel();
                    this.f20003n.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                cancel();
                this.f20003n.onError(th2);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17876c.cancel();
            m9813a();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17877d.get() == DisposableHelper.DISPOSED;
        }

        /* renamed from: a */
        public boolean m9812a(Subscriber<? super U> dbxVar, U u) {
            this.f20003n.onNext(u);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableBufferBoundarySupplier.java */
    /* renamed from: z1.aze$a */
    /* loaded from: classes3.dex */
    public static final class C3997a<T, U extends Collection<? super T>, B> extends DisposableSubscriber<B> {

        /* renamed from: a */
        final C3998b<T, U, B> f17872a;

        /* renamed from: b */
        boolean f17873b;

        C3997a(C3998b<T, U, B> bVar) {
            this.f17872a = bVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(B b) {
            if (!this.f17873b) {
                this.f17873b = true;
                m8927d();
                this.f17872a.m9811b();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f17873b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17873b = true;
            this.f17872a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f17873b) {
                this.f17873b = true;
                this.f17872a.m9811b();
            }
        }
    }
}
