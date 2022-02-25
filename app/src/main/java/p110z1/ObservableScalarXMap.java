package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bmc */
/* loaded from: classes3.dex */
public final class ObservableScalarXMap {
    private ObservableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T, R> boolean m9575a(ObservableSource<T> asqVar, Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        if (!(asqVar instanceof Callable)) {
            return false;
        }
        try {
            Object obj = (Object) ((Callable) asqVar).call();
            if (obj == 0) {
                EmptyDisposable.complete(assVar);
                return true;
            }
            try {
                ObservableSource asqVar2 = (ObservableSource) ObjectHelper.m9873a(aunVar.apply(obj), "The mapper returned a null ObservableSource");
                if (asqVar2 instanceof Callable) {
                    try {
                        Object call = ((Callable) asqVar2).call();
                        if (call == null) {
                            EmptyDisposable.complete(assVar);
                            return true;
                        }
                        RunnableC4546a aVar = new RunnableC4546a(assVar, call);
                        assVar.onSubscribe(aVar);
                        aVar.run();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        EmptyDisposable.error(th, assVar);
                        return true;
                    }
                } else {
                    asqVar2.subscribe(assVar);
                }
                return true;
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                EmptyDisposable.error(th2, assVar);
                return true;
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            EmptyDisposable.error(th3, assVar);
            return true;
        }
    }

    /* renamed from: a */
    public static <T, U> Observable<U> m9576a(T t, Function<? super T, ? extends ObservableSource<? extends U>> aunVar) {
        return RxJavaPlugins.m9203a(new C4547b(t, aunVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableScalarXMap.java */
    /* renamed from: z1.bmc$b */
    /* loaded from: classes3.dex */
    public static final class C4547b<T, R> extends Observable<R> {

        /* renamed from: a */
        final T f19327a;

        /* renamed from: b */
        final Function<? super T, ? extends ObservableSource<? extends R>> f19328b;

        C4547b(T t, Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
            this.f19327a = t;
            this.f19328b = aunVar;
        }

        @Override // p110z1.Observable
        /* renamed from: a */
        public void mo34a(Observer<? super R> assVar) {
            try {
                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19328b.apply((T) this.f19327a), "The mapper returned a null ObservableSource");
                if (asqVar instanceof Callable) {
                    try {
                        Object call = ((Callable) asqVar).call();
                        if (call == null) {
                            EmptyDisposable.complete(assVar);
                            return;
                        }
                        RunnableC4546a aVar = new RunnableC4546a(assVar, call);
                        assVar.onSubscribe(aVar);
                        aVar.run();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        EmptyDisposable.error(th, assVar);
                    }
                } else {
                    asqVar.subscribe(assVar);
                }
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, assVar);
            }
        }
    }

    /* compiled from: ObservableScalarXMap.java */
    /* renamed from: z1.bmc$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4546a<T> extends AtomicInteger implements Runnable, QueueDisposable<T> {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final Observer<? super T> observer;
        final T value;

        public RunnableC4546a(Observer<? super T> assVar, T t) {
            this.observer = assVar;
            this.value = t;
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            lazySet(3);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            set(3);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext((T) this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }
}
