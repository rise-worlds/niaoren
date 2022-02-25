package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhj */
/* loaded from: classes3.dex */
public final class MaybeUsing<T, D> extends Maybe<T> {

    /* renamed from: a */
    final Callable<? extends D> f18725a;

    /* renamed from: b */
    final Function<? super D, ? extends MaybeSource<? extends T>> f18726b;

    /* renamed from: c */
    final Consumer<? super D> f18727c;

    /* renamed from: d */
    final boolean f18728d;

    public MaybeUsing(Callable<? extends D> callable, Function<? super D, ? extends MaybeSource<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        this.f18725a = callable;
        this.f18726b = aunVar;
        this.f18727c = aumVar;
        this.f18728d = z;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        try {
            Object call = this.f18725a.call();
            try {
                ((MaybeSource) ObjectHelper.m9873a(this.f18726b.apply(call), "The sourceSupplier returned a null MaybeSource")).mo10646a(new C4342a(asfVar, call, this.f18727c, this.f18728d));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (this.f18728d) {
                    try {
                        this.f18727c.accept(call);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        EmptyDisposable.error(new CompositeException(th, th2), asfVar);
                        return;
                    }
                }
                EmptyDisposable.error(th, asfVar);
                if (!this.f18728d) {
                    try {
                        this.f18727c.accept(call);
                    } catch (Throwable th3) {
                        Exceptions.m9983b(th3);
                        RxJavaPlugins.m9212a(th3);
                    }
                }
            }
        } catch (Throwable th4) {
            Exceptions.m9983b(th4);
            EmptyDisposable.error(th4, asfVar);
        }
    }

    /* compiled from: MaybeUsing.java */
    /* renamed from: z1.bhj$a */
    /* loaded from: classes3.dex */
    static final class C4342a<T, D> extends AtomicReference<Object> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -674404550052917487L;
        final Consumer<? super D> disposer;
        final MaybeObserver<? super T> downstream;
        final boolean eager;
        Disposable upstream;

        C4342a(MaybeObserver<? super T> asfVar, D d, Consumer<? super D> aumVar, boolean z) {
            super(d);
            this.downstream = asfVar;
            this.disposer = aumVar;
            this.eager = z;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
            disposeResourceAfter();
        }

        void disposeResourceAfter() {
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

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
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
                disposeResourceAfter();
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
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
                disposeResourceAfter();
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
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
            this.downstream.onComplete();
            if (!this.eager) {
                disposeResourceAfter();
            }
        }
    }
}
