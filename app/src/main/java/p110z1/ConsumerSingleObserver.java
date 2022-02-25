package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awh */
/* loaded from: classes3.dex */
public final class ConsumerSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -7012088219455310787L;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public ConsumerSingleObserver(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        this.onSuccess = aumVar;
        this.onError = aumVar2;
    }

    @Override // p110z1.SingleObserver
    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(th, th2));
        }
    }

    @Override // p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this, atrVar);
    }

    @Override // p110z1.SingleObserver
    public void onSuccess(T t) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(t);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
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
