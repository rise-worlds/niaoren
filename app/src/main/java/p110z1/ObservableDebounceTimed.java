package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bjj */
/* loaded from: classes3.dex */
public final class ObservableDebounceTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f18990b;

    /* renamed from: c */
    final TimeUnit f18991c;

    /* renamed from: d */
    final Scheduler f18992d;

    public ObservableDebounceTimed(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        super(asqVar);
        this.f18990b = j;
        this.f18991c = timeUnit;
        this.f18992d = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4425b(new SerializedObserver(assVar), this.f18990b, this.f18991c, this.f18992d.mo9034b()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableDebounceTimed.java */
    /* renamed from: z1.bjj$b */
    /* loaded from: classes3.dex */
    public static final class C4425b<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f18993a;

        /* renamed from: b */
        final long f18994b;

        /* renamed from: c */
        final TimeUnit f18995c;

        /* renamed from: d */
        final Scheduler.AbstractC3881c f18996d;

        /* renamed from: e */
        Disposable f18997e;

        /* renamed from: f */
        Disposable f18998f;

        /* renamed from: g */
        volatile long f18999g;

        /* renamed from: h */
        boolean f19000h;

        C4425b(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.f18993a = assVar;
            this.f18994b = j;
            this.f18995c = timeUnit;
            this.f18996d = cVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18997e, atrVar)) {
                this.f18997e = atrVar;
                this.f18993a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19000h) {
                long j = this.f18999g + 1;
                this.f18999g = j;
                Disposable atrVar = this.f18998f;
                if (atrVar != null) {
                    atrVar.dispose();
                }
                RunnableC4424a aVar = new RunnableC4424a(t, j, this);
                this.f18998f = aVar;
                aVar.setResource(this.f18996d.mo9030a(aVar, this.f18994b, this.f18995c));
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19000h) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            Disposable atrVar = this.f18998f;
            if (atrVar != null) {
                atrVar.dispose();
            }
            this.f19000h = true;
            this.f18993a.onError(th);
            this.f18996d.dispose();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19000h) {
                this.f19000h = true;
                Disposable atrVar = this.f18998f;
                if (atrVar != null) {
                    atrVar.dispose();
                }
                RunnableC4424a aVar = (RunnableC4424a) atrVar;
                if (aVar != null) {
                    aVar.run();
                }
                this.f18993a.onComplete();
                this.f18996d.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18997e.dispose();
            this.f18996d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18996d.isDisposed();
        }

        /* renamed from: a */
        void m9636a(long j, T t, RunnableC4424a<T> aVar) {
            if (j == this.f18999g) {
                this.f18993a.onNext(t);
                aVar.dispose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableDebounceTimed.java */
    /* renamed from: z1.bjj$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4424a<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final C4425b<T> parent;
        final T value;

        RunnableC4424a(T t, long j, C4425b<T> bVar) {
            this.value = t;
            this.idx = j;
            this.parent = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.m9636a(this.idx, this.value, this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }
    }
}
