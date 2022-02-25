package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bnm */
/* loaded from: classes3.dex */
public final class ObservableWindowBoundarySupplier<T, B> extends AbstractObservableWithUpstream<T, Observable<T>> {

    /* renamed from: b */
    final Callable<? extends ObservableSource<B>> f19507b;

    /* renamed from: c */
    final int f19508c;

    public ObservableWindowBoundarySupplier(ObservableSource<T> asqVar, Callable<? extends ObservableSource<B>> callable, int i) {
        super(asqVar);
        this.f19507b = callable;
        this.f19508c = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Observable<T>> assVar) {
        this.f18807a.subscribe(new RunnableC4603b(assVar, this.f19508c, this.f19507b));
    }

    /* compiled from: ObservableWindowBoundarySupplier.java */
    /* renamed from: z1.bnm$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4603b<T, B> extends AtomicInteger implements Runnable, Observer<T>, Disposable {
        static final C4602a<Object, Object> BOUNDARY_DISPOSED = new C4602a<>(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final int capacityHint;
        volatile boolean done;
        final Observer<? super Observable<T>> downstream;
        final Callable<? extends ObservableSource<B>> other;
        Disposable upstream;
        UnicastSubject<T> window;
        final AtomicReference<C4602a<T, B>> boundaryObserver = new AtomicReference<>();
        final AtomicInteger windows = new AtomicInteger(1);
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicBoolean stopWindows = new AtomicBoolean();

        RunnableC4603b(Observer<? super Observable<T>> assVar, int i, Callable<? extends ObservableSource<B>> callable) {
            this.downstream = assVar;
            this.capacityHint = i;
            this.other = callable;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.dispose();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void disposeBoundary() {
            Disposable atrVar = (Disposable) this.boundaryObserver.getAndSet(BOUNDARY_DISPOSED);
            if (atrVar != null && atrVar != BOUNDARY_DISPOSED) {
                atrVar.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        void innerNext(C4602a<T, B> aVar) {
            this.boundaryObserver.compareAndSet(aVar, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        void innerError(Throwable th) {
            this.upstream.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            this.upstream.dispose();
            this.done = true;
            drain();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super Observable<T>> assVar = this.downstream;
                MpscLinkedQueue<Object> bqjVar = this.queue;
                AtomicThrowable bsnVar = this.errors;
                int i = 1;
                while (this.windows.get() != 0) {
                    UnicastSubject<T> bvaVar = this.window;
                    boolean z = this.done;
                    if (!z || bsnVar.get() == null) {
                        Object poll = bqjVar.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable terminate = bsnVar.terminate();
                            if (terminate == null) {
                                if (bvaVar != 0) {
                                    this.window = null;
                                    bvaVar.onComplete();
                                }
                                assVar.onComplete();
                                return;
                            }
                            if (bvaVar != 0) {
                                this.window = null;
                                bvaVar.onError(terminate);
                            }
                            assVar.onError(terminate);
                            return;
                        } else if (z2) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else if (poll != NEXT_WINDOW) {
                            bvaVar.onNext(poll);
                        } else {
                            if (bvaVar != 0) {
                                this.window = null;
                                bvaVar.onComplete();
                            }
                            if (!this.stopWindows.get()) {
                                UnicastSubject<T> a = UnicastSubject.m8942a(this.capacityHint, (Runnable) this);
                                this.window = a;
                                this.windows.getAndIncrement();
                                try {
                                    ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.other.call(), "The other Callable returned a null ObservableSource");
                                    C4602a<T, B> aVar = new C4602a<>(this);
                                    if (this.boundaryObserver.compareAndSet(null, aVar)) {
                                        asqVar.subscribe(aVar);
                                        assVar.onNext(a);
                                    }
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    bsnVar.addThrowable(th);
                                    this.done = true;
                                }
                            }
                        }
                    } else {
                        bqjVar.clear();
                        Throwable terminate2 = bsnVar.terminate();
                        if (bvaVar != 0) {
                            this.window = null;
                            bvaVar.onError(terminate2);
                        }
                        assVar.onError(terminate2);
                        return;
                    }
                }
                bqjVar.clear();
                this.window = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableWindowBoundarySupplier.java */
    /* renamed from: z1.bnm$a */
    /* loaded from: classes3.dex */
    public static final class C4602a<T, B> extends DisposableObserver<B> {

        /* renamed from: a */
        final RunnableC4603b<T, B> f19509a;

        /* renamed from: b */
        boolean f19510b;

        C4602a(RunnableC4603b<T, B> bVar) {
            this.f19509a = bVar;
        }

        @Override // p110z1.Observer
        public void onNext(B b) {
            if (!this.f19510b) {
                this.f19510b = true;
                dispose();
                this.f19509a.innerNext(this);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19510b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19510b = true;
            this.f19509a.innerError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19510b) {
                this.f19510b = true;
                this.f19509a.innerComplete();
            }
        }
    }
}
