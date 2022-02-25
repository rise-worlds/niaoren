package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayp */
/* loaded from: classes3.dex */
public final class CompletableUsing<R> extends Completable {

    /* renamed from: a */
    final Callable<R> f17808a;

    /* renamed from: b */
    final Function<? super R, ? extends CompletableSource> f17809b;

    /* renamed from: c */
    final Consumer<? super R> f17810c;

    /* renamed from: d */
    final boolean f17811d;

    public CompletableUsing(Callable<R> callable, Function<? super R, ? extends CompletableSource> aunVar, Consumer<? super R> aumVar, boolean z) {
        this.f17808a = callable;
        this.f17809b = aunVar;
        this.f17810c = aumVar;
        this.f17811d = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        try {
            R call = this.f17808a.call();
            try {
                ((CompletableSource) ObjectHelper.m9873a(this.f17809b.apply(call), "The completableFunction returned a null CompletableSource")).mo11309a(new C3977a(arpVar, call, this.f17810c, this.f17811d));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (this.f17811d) {
                    try {
                        this.f17810c.accept(call);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        EmptyDisposable.error(new CompositeException(th, th2), arpVar);
                        return;
                    }
                }
                EmptyDisposable.error(th, arpVar);
                if (!this.f17811d) {
                    try {
                        this.f17810c.accept(call);
                    } catch (Throwable th3) {
                        Exceptions.m9983b(th3);
                        RxJavaPlugins.m9212a(th3);
                    }
                }
            }
        } catch (Throwable th4) {
            Exceptions.m9983b(th4);
            EmptyDisposable.error(th4, arpVar);
        }
    }

    /* compiled from: CompletableUsing.java */
    /* renamed from: z1.ayp$a */
    /* loaded from: classes3.dex */
    static final class C3977a<R> extends AtomicReference<Object> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = -674404550052917487L;
        final Consumer<? super R> disposer;
        final CompletableObserver downstream;
        final boolean eager;
        Disposable upstream;

        C3977a(CompletableObserver arpVar, R r, Consumer<? super R> aumVar, boolean z) {
            super(r);
            this.downstream = arpVar;
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

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
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

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
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
