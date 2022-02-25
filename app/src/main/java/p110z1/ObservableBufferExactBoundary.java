package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.biu */
/* loaded from: classes3.dex */
public final class ObservableBufferExactBoundary<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final ObservableSource<B> f18881b;

    /* renamed from: c */
    final Callable<U> f18882c;

    public ObservableBufferExactBoundary(ObservableSource<T> asqVar, ObservableSource<B> asqVar2, Callable<U> callable) {
        super(asqVar);
        this.f18881b = asqVar2;
        this.f18882c = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        this.f18807a.subscribe(new C4397b(new SerializedObserver(assVar), this.f18882c, this.f18881b));
    }

    /* compiled from: ObservableBufferExactBoundary.java */
    /* renamed from: z1.biu$b */
    /* loaded from: classes3.dex */
    static final class C4397b<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T>, Disposable {

        /* renamed from: K */
        final Callable<U> f18884K;

        /* renamed from: L */
        final ObservableSource<B> f18885L;

        /* renamed from: M */
        Disposable f18886M;

        /* renamed from: N */
        Disposable f18887N;

        /* renamed from: O */
        U f18888O;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9398a(Observer assVar, Object obj) {
            m9654a((Observer<? super Observer>) assVar, (Observer) ((Collection) obj));
        }

        C4397b(Observer<? super U> assVar, Callable<U> callable, ObservableSource<B> asqVar) {
            super(assVar, new MpscLinkedQueue());
            this.f18884K = callable;
            this.f18885L = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18886M, atrVar)) {
                this.f18886M = atrVar;
                try {
                    this.f18888O = (U) ((Collection) ObjectHelper.m9873a(this.f18884K.call(), "The buffer supplied is null"));
                    C4396a aVar = new C4396a(this);
                    this.f18887N = aVar;
                    this.f17632a.onSubscribe(this);
                    if (!this.f17634c) {
                        this.f18885L.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17634c = true;
                    atrVar.dispose();
                    EmptyDisposable.error(th, this.f17632a);
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f18888O;
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
                U u = this.f18888O;
                if (u != null) {
                    this.f18888O = null;
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
                this.f18887N.dispose();
                this.f18886M.dispose();
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
        void m9653f() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f18884K.call(), "The buffer supplied is null"));
                synchronized (this) {
                    U u2 = this.f18888O;
                    if (u2 != null) {
                        this.f18888O = u;
                        m9843a(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                dispose();
                this.f17632a.onError(th);
            }
        }

        /* renamed from: a */
        public void m9654a(Observer<? super U> assVar, U u) {
            this.f17632a.onNext(u);
        }
    }

    /* compiled from: ObservableBufferExactBoundary.java */
    /* renamed from: z1.biu$a */
    /* loaded from: classes3.dex */
    static final class C4396a<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {

        /* renamed from: a */
        final C4397b<T, U, B> f18883a;

        C4396a(C4397b<T, U, B> bVar) {
            this.f18883a = bVar;
        }

        @Override // p110z1.Observer
        public void onNext(B b) {
            this.f18883a.m9653f();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18883a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18883a.onComplete();
        }
    }
}
