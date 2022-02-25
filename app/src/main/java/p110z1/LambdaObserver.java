package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awr */
/* loaded from: classes3.dex */
public final class LambdaObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -7251123623727029452L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;
    final Consumer<? super Disposable> onSubscribe;

    public LambdaObserver(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Consumer<? super Disposable> aumVar3) {
        this.onNext = aumVar;
        this.onError = aumVar2;
        this.onComplete = augVar;
        this.onSubscribe = aumVar3;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.setOnce(this, atrVar)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                atrVar.dispose();
                onError(th);
            }
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                get().dispose();
                onError(th);
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                RxJavaPlugins.m9212a(new CompositeException(th, th2));
            }
        } else {
            RxJavaPlugins.m9212a(th);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onComplete.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // p110z1.LambdaConsumerIntrospection
    public boolean hasCustomOnError() {
        return this.onError != Functions.f17560f;
    }
}
