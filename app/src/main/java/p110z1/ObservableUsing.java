package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bni */
/* loaded from: classes3.dex */
public final class ObservableUsing<T, D> extends Observable<T> {

    /* renamed from: a */
    final Callable<? extends D> f19478a;

    /* renamed from: b */
    final Function<? super D, ? extends ObservableSource<? extends T>> f19479b;

    /* renamed from: c */
    final Consumer<? super D> f19480c;

    /* renamed from: d */
    final boolean f19481d;

    public ObservableUsing(Callable<? extends D> callable, Function<? super D, ? extends ObservableSource<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        this.f19478a = callable;
        this.f19479b = aunVar;
        this.f19480c = aumVar;
        this.f19481d = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        try {
            Object call = this.f19478a.call();
            try {
                ((ObservableSource) ObjectHelper.m9873a(this.f19479b.apply(call), "The sourceSupplier returned a null ObservableSource")).subscribe(new C4593a(assVar, call, this.f19480c, this.f19481d));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                try {
                    this.f19480c.accept(call);
                    EmptyDisposable.error(th, assVar);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    EmptyDisposable.error(new CompositeException(th, th2), assVar);
                }
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            EmptyDisposable.error(th3, assVar);
        }
    }

    /* compiled from: ObservableUsing.java */
    /* renamed from: z1.bni$a */
    /* loaded from: classes3.dex */
    static final class C4593a<T, D> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = 5904473792286235046L;
        final Consumer<? super D> disposer;
        final Observer<? super T> downstream;
        final boolean eager;
        final D resource;
        Disposable upstream;

        C4593a(Observer<? super T> assVar, D d, Consumer<? super D> aumVar, boolean z) {
            this.downstream = assVar;
            this.resource = d;
            this.disposer = aumVar;
            this.eager = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept((D) this.resource);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                this.upstream.dispose();
                this.downstream.onError(th);
                return;
            }
            this.downstream.onError(th);
            this.upstream.dispose();
            disposeAfter();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept((D) this.resource);
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.upstream.dispose();
                this.downstream.onComplete();
                return;
            }
            this.downstream.onComplete();
            this.upstream.dispose();
            disposeAfter();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            disposeAfter();
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get();
        }

        void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept((D) this.resource);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
