package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bit */
/* loaded from: classes3.dex */
public final class ObservableBufferBoundarySupplier<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Callable<? extends ObservableSource<B>> f18872b;

    /* renamed from: c */
    final Callable<U> f18873c;

    public ObservableBufferBoundarySupplier(ObservableSource<T> asqVar, Callable<? extends ObservableSource<B>> callable, Callable<U> callable2) {
        super(asqVar);
        this.f18872b = callable;
        this.f18873c = callable2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        this.f18807a.subscribe(new C4395b(new SerializedObserver(assVar), this.f18873c, this.f18872b));
    }

    /* compiled from: ObservableBufferBoundarySupplier.java */
    /* renamed from: z1.bit$b */
    /* loaded from: classes3.dex */
    static final class C4395b<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T>, Disposable {

        /* renamed from: K */
        final Callable<U> f18876K;

        /* renamed from: L */
        final Callable<? extends ObservableSource<B>> f18877L;

        /* renamed from: M */
        Disposable f18878M;

        /* renamed from: N */
        final AtomicReference<Disposable> f18879N = new AtomicReference<>();

        /* renamed from: O */
        U f18880O;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9398a(Observer assVar, Object obj) {
            m9657a((Observer<? super Observer>) assVar, (Observer) ((Collection) obj));
        }

        C4395b(Observer<? super U> assVar, Callable<U> callable, Callable<? extends ObservableSource<B>> callable2) {
            super(assVar, new MpscLinkedQueue());
            this.f18876K = callable;
            this.f18877L = callable2;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18878M, atrVar)) {
                this.f18878M = atrVar;
                Observer<? super V> assVar = this.f17632a;
                try {
                    this.f18880O = (U) ((Collection) ObjectHelper.m9873a(this.f18876K.call(), "The buffer supplied is null"));
                    try {
                        ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f18877L.call(), "The boundary ObservableSource supplied is null");
                        C4394a aVar = new C4394a(this);
                        this.f18879N.set(aVar);
                        assVar.onSubscribe(this);
                        if (!this.f17634c) {
                            asqVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.f17634c = true;
                        atrVar.dispose();
                        EmptyDisposable.error(th, assVar);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.f17634c = true;
                    atrVar.dispose();
                    EmptyDisposable.error(th2, assVar);
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f18880O;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            dispose();
            this.f17632a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            synchronized (this) {
                U u = this.f18880O;
                if (u != null) {
                    this.f18880O = null;
                    this.f17633b.offer(u);
                    this.f17635d = true;
                    if (mo9396c()) {
                        QueueDrainHelper.m9374a((SimplePlainQueue) this.f17633b, (Observer) this.f17632a, false, (Disposable) this, (ObservableQueueDrain) this);
                    }
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f17634c) {
                this.f17634c = true;
                this.f18878M.dispose();
                m9656f();
                if (mo9396c()) {
                    this.f17633b.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17634c;
        }

        /* renamed from: f */
        void m9656f() {
            DisposableHelper.dispose(this.f18879N);
        }

        /* renamed from: g */
        void m9655g() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f18876K.call(), "The buffer supplied is null"));
                try {
                    ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f18877L.call(), "The boundary ObservableSource supplied is null");
                    C4394a aVar = new C4394a(this);
                    if (DisposableHelper.replace(this.f18879N, aVar)) {
                        synchronized (this) {
                            U u2 = this.f18880O;
                            if (u2 != null) {
                                this.f18880O = u;
                                asqVar.subscribe(aVar);
                                m9843a(u2, false, this);
                            }
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17634c = true;
                    this.f18878M.dispose();
                    this.f17632a.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                dispose();
                this.f17632a.onError(th2);
            }
        }

        /* renamed from: a */
        public void m9657a(Observer<? super U> assVar, U u) {
            this.f17632a.onNext(u);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableBufferBoundarySupplier.java */
    /* renamed from: z1.bit$a */
    /* loaded from: classes3.dex */
    public static final class C4394a<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {

        /* renamed from: a */
        final C4395b<T, U, B> f18874a;

        /* renamed from: b */
        boolean f18875b;

        C4394a(C4395b<T, U, B> bVar) {
            this.f18874a = bVar;
        }

        @Override // p110z1.Observer
        public void onNext(B b) {
            if (!this.f18875b) {
                this.f18875b = true;
                dispose();
                this.f18874a.m9655g();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18875b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18875b = true;
            this.f18874a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18875b) {
                this.f18875b = true;
                this.f18874a.m9655g();
            }
        }
    }
}
