package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bcp */
/* loaded from: classes3.dex */
public final class FlowableRefCount<T> extends Flowable<T> {

    /* renamed from: b */
    final ConnectableFlowable<T> f18267b;

    /* renamed from: c */
    final int f18268c;

    /* renamed from: d */
    final long f18269d;

    /* renamed from: e */
    final TimeUnit f18270e;

    /* renamed from: f */
    final Scheduler f18271f;

    /* renamed from: g */
    RunnableC4154a f18272g;

    public FlowableRefCount(ConnectableFlowable<T> aueVar) {
        this(aueVar, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public FlowableRefCount(ConnectableFlowable<T> aueVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f18267b = aueVar;
        this.f18268c = i;
        this.f18269d = j;
        this.f18270e = timeUnit;
        this.f18271f = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        RunnableC4154a aVar;
        boolean z;
        synchronized (this) {
            aVar = this.f18272g;
            if (aVar == null) {
                aVar = new RunnableC4154a(this);
                this.f18272g = aVar;
            }
            long j = aVar.subscriberCount;
            if (j == 0 && aVar.timer != null) {
                aVar.timer.dispose();
            }
            long j2 = j + 1;
            aVar.subscriberCount = j2;
            z = true;
            if (aVar.connected || j2 != this.f18268c) {
                z = false;
            } else {
                aVar.connected = true;
            }
        }
        this.f18267b.m11187a((FlowableSubscriber) new C4155b(dbxVar, this, aVar));
        if (z) {
            this.f18267b.mo9740l((Consumer<? super Disposable>) aVar);
        }
    }

    /* renamed from: a */
    void m9751a(RunnableC4154a aVar) {
        synchronized (this) {
            if (this.f18272g != null && this.f18272g == aVar) {
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0 && aVar.connected) {
                    if (this.f18269d == 0) {
                        m9749c(aVar);
                        return;
                    }
                    SequentialDisposable avfVar = new SequentialDisposable();
                    aVar.timer = avfVar;
                    avfVar.replace(this.f18271f.mo9480a(aVar, this.f18269d, this.f18270e));
                }
            }
        }
    }

    /* renamed from: b */
    void m9750b(RunnableC4154a aVar) {
        synchronized (this) {
            if (this.f18272g != null && this.f18272g == aVar) {
                this.f18272g = null;
                if (aVar.timer != null) {
                    aVar.timer.dispose();
                }
            }
            long j = aVar.subscriberCount - 1;
            aVar.subscriberCount = j;
            if (j == 0) {
                if (this.f18267b instanceof Disposable) {
                    ((Disposable) this.f18267b).dispose();
                } else if (this.f18267b instanceof ResettableConnectable) {
                    ((ResettableConnectable) this.f18267b).mo9582a(aVar.get());
                }
            }
        }
    }

    /* renamed from: c */
    void m9749c(RunnableC4154a aVar) {
        synchronized (this) {
            if (aVar.subscriberCount == 0 && aVar == this.f18272g) {
                this.f18272g = null;
                Disposable atrVar = aVar.get();
                DisposableHelper.dispose(aVar);
                if (this.f18267b instanceof Disposable) {
                    ((Disposable) this.f18267b).dispose();
                } else if (this.f18267b instanceof ResettableConnectable) {
                    if (atrVar == null) {
                        aVar.disconnectedEarly = true;
                    } else {
                        ((ResettableConnectable) this.f18267b).mo9582a(atrVar);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableRefCount.java */
    /* renamed from: z1.bcp$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4154a extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final FlowableRefCount<?> parent;
        long subscriberCount;
        Disposable timer;

        RunnableC4154a(FlowableRefCount<?> bcpVar) {
            this.parent = bcpVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.m9749c(this);
        }

        public void accept(Disposable atrVar) throws Exception {
            DisposableHelper.replace(this, atrVar);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((ResettableConnectable) this.parent.f18267b).mo9582a(atrVar);
                }
            }
        }
    }

    /* compiled from: FlowableRefCount.java */
    /* renamed from: z1.bcp$b */
    /* loaded from: classes3.dex */
    static final class C4155b<T> extends AtomicBoolean implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -7419642935409022375L;
        final RunnableC4154a connection;
        final Subscriber<? super T> downstream;
        final FlowableRefCount<T> parent;
        dby upstream;

        C4155b(Subscriber<? super T> dbxVar, FlowableRefCount<T> bcpVar, RunnableC4154a aVar) {
            this.downstream = dbxVar;
            this.parent = bcpVar;
            this.connection = aVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.m9750b(this.connection);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.m9750b(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
            if (compareAndSet(false, true)) {
                this.parent.m9751a(this.connection);
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
