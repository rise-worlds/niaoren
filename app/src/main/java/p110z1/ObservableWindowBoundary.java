package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bnk */
/* loaded from: classes3.dex */
public final class ObservableWindowBoundary<T, B> extends AbstractObservableWithUpstream<T, Observable<T>> {

    /* renamed from: b */
    final ObservableSource<B> f19485b;

    /* renamed from: c */
    final int f19486c;

    public ObservableWindowBoundary(ObservableSource<T> asqVar, ObservableSource<B> asqVar2, int i) {
        super(asqVar);
        this.f19485b = asqVar2;
        this.f19486c = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Observable<T>> assVar) {
        RunnableC4597b bVar = new RunnableC4597b(assVar, this.f19486c);
        assVar.onSubscribe(bVar);
        this.f19485b.subscribe(bVar.boundaryObserver);
        this.f18807a.subscribe(bVar);
    }

    /* compiled from: ObservableWindowBoundary.java */
    /* renamed from: z1.bnk$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4597b<T, B> extends AtomicInteger implements Runnable, Observer<T>, Disposable {
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final int capacityHint;
        volatile boolean done;
        final Observer<? super Observable<T>> downstream;
        UnicastSubject<T> window;
        final C4596a<T, B> boundaryObserver = new C4596a<>(this);
        final AtomicReference<Disposable> upstream = new AtomicReference<>();
        final AtomicInteger windows = new AtomicInteger(1);
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicBoolean stopWindows = new AtomicBoolean();

        RunnableC4597b(Observer<? super Observable<T>> assVar, int i) {
            this.downstream = assVar;
            this.capacityHint = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this.upstream, atrVar)) {
                innerNext();
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.boundaryObserver.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.boundaryObserver.dispose();
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                this.boundaryObserver.dispose();
                if (this.windows.decrementAndGet() == 0) {
                    DisposableHelper.dispose(this.upstream);
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.upstream);
            }
        }

        void innerNext() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            DisposableHelper.dispose(this.upstream);
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
                                assVar.onNext(a);
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

    /* compiled from: ObservableWindowBoundary.java */
    /* renamed from: z1.bnk$a */
    /* loaded from: classes3.dex */
    static final class C4596a<T, B> extends DisposableObserver<B> {

        /* renamed from: a */
        final RunnableC4597b<T, B> f19487a;

        /* renamed from: b */
        boolean f19488b;

        C4596a(RunnableC4597b<T, B> bVar) {
            this.f19487a = bVar;
        }

        @Override // p110z1.Observer
        public void onNext(B b) {
            if (!this.f19488b) {
                this.f19487a.innerNext();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19488b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19488b = true;
            this.f19487a.innerError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19488b) {
                this.f19488b = true;
                this.f19487a.innerComplete();
            }
        }
    }
}
