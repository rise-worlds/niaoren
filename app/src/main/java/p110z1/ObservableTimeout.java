package p110z1;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.ObservableTimeoutTimed;

/* renamed from: z1.bnc */
/* loaded from: classes3.dex */
public final class ObservableTimeout<T, U, V> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final ObservableSource<U> f19453b;

    /* renamed from: c */
    final Function<? super T, ? extends ObservableSource<V>> f19454c;

    /* renamed from: d */
    final ObservableSource<? extends T> f19455d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableTimeout.java */
    /* renamed from: z1.bnc$d */
    /* loaded from: classes3.dex */
    public interface AbstractC4582d extends ObservableTimeoutTimed.AbstractC4586d {
        void onTimeoutError(long j, Throwable th);
    }

    public ObservableTimeout(Observable<T> aslVar, ObservableSource<U> asqVar, Function<? super T, ? extends ObservableSource<V>> aunVar, ObservableSource<? extends T> asqVar2) {
        super(aslVar);
        this.f19453b = asqVar;
        this.f19454c = aunVar;
        this.f19455d = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        ObservableSource<? extends T> asqVar = this.f19455d;
        if (asqVar == null) {
            C4581c cVar = new C4581c(assVar, this.f19454c);
            assVar.onSubscribe(cVar);
            cVar.startFirstTimeout(this.f19453b);
            this.f18807a.subscribe(cVar);
            return;
        }
        C4580b bVar = new C4580b(assVar, this.f19454c, asqVar);
        assVar.onSubscribe(bVar);
        bVar.startFirstTimeout(this.f19453b);
        this.f18807a.subscribe(bVar);
    }

    /* compiled from: ObservableTimeout.java */
    /* renamed from: z1.bnc$c */
    /* loaded from: classes3.dex */
    static final class C4581c<T> extends AtomicLong implements Observer<T>, Disposable, AbstractC4582d {
        private static final long serialVersionUID = 3764492702657003550L;
        final Observer<? super T> downstream;
        final Function<? super T, ? extends ObservableSource<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4581c(Observer<? super T> assVar, Function<? super T, ? extends ObservableSource<?>> aunVar) {
            this.downstream = assVar;
            this.itemTimeoutIndicator = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    Disposable atrVar = this.task.get();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        C4579a aVar = new C4579a(j2, this);
                        if (this.task.replace(aVar)) {
                            asqVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.upstream.get().dispose();
                        getAndSet(cjm.f20759b);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(ObservableSource<?> asqVar) {
            if (asqVar != null) {
                C4579a aVar = new C4579a(0L, this);
                if (this.task.replace(aVar)) {
                    asqVar.subscribe(aVar);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.ObservableTimeoutTimed.AbstractC4586d
        public void onTimeout(long j) {
            if (compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        @Override // p110z1.ObservableTimeout.AbstractC4582d
        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.task.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }
    }

    /* compiled from: ObservableTimeout.java */
    /* renamed from: z1.bnc$b */
    /* loaded from: classes3.dex */
    static final class C4580b<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, AbstractC4582d {
        private static final long serialVersionUID = -7508389464265974549L;
        final Observer<? super T> downstream;
        ObservableSource<? extends T> fallback;
        final Function<? super T, ? extends ObservableSource<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicLong index = new AtomicLong();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4580b(Observer<? super T> assVar, Function<? super T, ? extends ObservableSource<?>> aunVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.itemTimeoutIndicator = aunVar;
            this.fallback = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = this.index.get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    Disposable atrVar = this.task.get();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        C4579a aVar = new C4579a(j2, this);
                        if (this.task.replace(aVar)) {
                            asqVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.upstream.get().dispose();
                        this.index.getAndSet(cjm.f20759b);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(ObservableSource<?> asqVar) {
            if (asqVar != null) {
                C4579a aVar = new C4579a(0L, this);
                if (this.task.replace(aVar)) {
                    asqVar.subscribe(aVar);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        @Override // p110z1.ObservableTimeoutTimed.AbstractC4586d
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this.upstream);
                ObservableSource<? extends T> asqVar = this.fallback;
                this.fallback = null;
                asqVar.subscribe(new ObservableTimeoutTimed.C4583a(this.downstream, this));
            }
        }

        @Override // p110z1.ObservableTimeout.AbstractC4582d
        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableTimeout.java */
    /* renamed from: z1.bnc$a */
    /* loaded from: classes3.dex */
    public static final class C4579a extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final AbstractC4582d parent;

        C4579a(long j, AbstractC4582d dVar) {
            this.idx = j;
            this.parent = dVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            Disposable atrVar = (Disposable) get();
            if (atrVar != DisposableHelper.DISPOSED) {
                atrVar.dispose();
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
