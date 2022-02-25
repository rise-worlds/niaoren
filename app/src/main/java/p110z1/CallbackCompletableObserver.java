package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awg */
/* loaded from: classes3.dex */
public final class CallbackCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, Consumer<Throwable>, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -4361286194466301354L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;

    public CallbackCompletableObserver(Action augVar) {
        this.onError = this;
        this.onComplete = augVar;
    }

    public CallbackCompletableObserver(Consumer<? super Throwable> aumVar, Action augVar) {
        this.onError = aumVar;
        this.onComplete = augVar;
    }

    public void accept(Throwable th) {
        RxJavaPlugins.m9212a(new OnErrorNotImplementedException(th));
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        try {
            this.onComplete.mo9442a();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(th2);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this, atrVar);
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
        return this.onError != this;
    }
}
