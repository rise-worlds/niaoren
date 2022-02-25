package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bls */
/* loaded from: classes3.dex */
public final class ObservableRefCount<T> extends Observable<T> {

    /* renamed from: a */
    final ConnectableObservable<T> f19289a;

    /* renamed from: b */
    final int f19290b;

    /* renamed from: c */
    final long f19291c;

    /* renamed from: d */
    final TimeUnit f19292d;

    /* renamed from: e */
    final Scheduler f19293e;

    /* renamed from: f */
    RunnableC4512a f19294f;

    public ObservableRefCount(ConnectableObservable<T> btkVar) {
        this(btkVar, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public ObservableRefCount(ConnectableObservable<T> btkVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f19289a = btkVar;
        this.f19290b = i;
        this.f19291c = j;
        this.f19292d = timeUnit;
        this.f19293e = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        RunnableC4512a aVar;
        boolean z;
        synchronized (this) {
            aVar = this.f19294f;
            if (aVar == null) {
                aVar = new RunnableC4512a(this);
                this.f19294f = aVar;
            }
            long j = aVar.subscriberCount;
            if (j == 0 && aVar.timer != null) {
                aVar.timer.dispose();
            }
            long j2 = j + 1;
            aVar.subscriberCount = j2;
            z = true;
            if (aVar.connected || j2 != this.f19290b) {
                z = false;
            } else {
                aVar.connected = true;
            }
        }
        this.f19289a.subscribe(new C4513b(assVar, this, aVar));
        if (z) {
            this.f19289a.mo9358k((Consumer<? super Disposable>) aVar);
        }
    }

    /* renamed from: a */
    void m9590a(RunnableC4512a aVar) {
        synchronized (this) {
            if (this.f19294f != null && this.f19294f == aVar) {
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0 && aVar.connected) {
                    if (this.f19291c == 0) {
                        m9588c(aVar);
                        return;
                    }
                    SequentialDisposable avfVar = new SequentialDisposable();
                    aVar.timer = avfVar;
                    avfVar.replace(this.f19293e.mo9480a(aVar, this.f19291c, this.f19292d));
                }
            }
        }
    }

    /* renamed from: b */
    void m9589b(RunnableC4512a aVar) {
        synchronized (this) {
            if (this.f19294f != null && this.f19294f == aVar) {
                this.f19294f = null;
                if (aVar.timer != null) {
                    aVar.timer.dispose();
                }
            }
            long j = aVar.subscriberCount - 1;
            aVar.subscriberCount = j;
            if (j == 0) {
                if (this.f19289a instanceof Disposable) {
                    ((Disposable) this.f19289a).dispose();
                } else if (this.f19289a instanceof ResettableConnectable) {
                    ((ResettableConnectable) this.f19289a).mo9582a(aVar.get());
                }
            }
        }
    }

    /* renamed from: c */
    void m9588c(RunnableC4512a aVar) {
        synchronized (this) {
            if (aVar.subscriberCount == 0 && aVar == this.f19294f) {
                this.f19294f = null;
                Disposable atrVar = aVar.get();
                DisposableHelper.dispose(aVar);
                if (this.f19289a instanceof Disposable) {
                    ((Disposable) this.f19289a).dispose();
                } else if (this.f19289a instanceof ResettableConnectable) {
                    if (atrVar == null) {
                        aVar.disconnectedEarly = true;
                    } else {
                        ((ResettableConnectable) this.f19289a).mo9582a(atrVar);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableRefCount.java */
    /* renamed from: z1.bls$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4512a extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final ObservableRefCount<?> parent;
        long subscriberCount;
        Disposable timer;

        RunnableC4512a(ObservableRefCount<?> blsVar) {
            this.parent = blsVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.m9588c(this);
        }

        public void accept(Disposable atrVar) throws Exception {
            DisposableHelper.replace(this, atrVar);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((ResettableConnectable) this.parent.f19289a).mo9582a(atrVar);
                }
            }
        }
    }

    /* compiled from: ObservableRefCount.java */
    /* renamed from: z1.bls$b */
    /* loaded from: classes3.dex */
    static final class C4513b<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -7419642935409022375L;
        final RunnableC4512a connection;
        final Observer<? super T> downstream;
        final ObservableRefCount<T> parent;
        Disposable upstream;

        C4513b(Observer<? super T> assVar, ObservableRefCount<T> blsVar, RunnableC4512a aVar) {
            this.downstream = assVar;
            this.parent = blsVar;
            this.connection = aVar;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.m9589b(this.connection);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.m9589b(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            if (compareAndSet(false, true)) {
                this.parent.m9590a(this.connection);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
