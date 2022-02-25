package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqg */
/* loaded from: classes3.dex */
public final class SingleUsing<T, U> extends Single<T> {

    /* renamed from: a */
    final Callable<U> f19841a;

    /* renamed from: b */
    final Function<? super U, ? extends SingleSource<? extends T>> f19842b;

    /* renamed from: c */
    final Consumer<? super U> f19843c;

    /* renamed from: d */
    final boolean f19844d;

    public SingleUsing(Callable<U> callable, Function<? super U, ? extends SingleSource<? extends T>> aunVar, Consumer<? super U> aumVar, boolean z) {
        this.f19841a = callable;
        this.f19842b = aunVar;
        this.f19843c = aumVar;
        this.f19844d = z;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        try {
            U call = this.f19841a.call();
            try {
                ((SingleSource) ObjectHelper.m9873a(this.f19842b.apply(call), "The singleFunction returned a null SingleSource")).mo10018a(new C4705a(asxVar, call, this.f19844d, this.f19843c));
            } catch (Throwable th) {
                th = th;
                Exceptions.m9983b(th);
                if (this.f19844d) {
                    try {
                        this.f19843c.accept(call);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                EmptyDisposable.error(th, asxVar);
                if (!this.f19844d) {
                    try {
                        this.f19843c.accept(call);
                    } catch (Throwable th3) {
                        Exceptions.m9983b(th3);
                        RxJavaPlugins.m9212a(th3);
                    }
                }
            }
        } catch (Throwable th4) {
            Exceptions.m9983b(th4);
            EmptyDisposable.error(th4, asxVar);
        }
    }

    /* compiled from: SingleUsing.java */
    /* renamed from: z1.bqg$a */
    /* loaded from: classes3.dex */
    static final class C4705a<T, U> extends AtomicReference<Object> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5331524057054083935L;
        final Consumer<? super U> disposer;
        final SingleObserver<? super T> downstream;
        final boolean eager;
        Disposable upstream;

        C4705a(SingleObserver<? super T> asxVar, U u, boolean z, Consumer<? super U> aumVar) {
            super(u);
            this.downstream = asxVar;
            this.eager = z;
            this.disposer = aumVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
            disposeAfter();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.downstream.onError(th);
                        return;
                    }
                } else {
                    return;
                }
            }
            this.downstream.onSuccess(t);
            if (!this.eager) {
                disposeAfter();
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        th = new CompositeException(th, th2);
                    }
                } else {
                    return;
                }
            }
            this.downstream.onError(th);
            if (!this.eager) {
                disposeAfter();
            }
        }

        void disposeAfter() {
            Object andSet = getAndSet(this);
            if (andSet != this) {
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
