package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awl */
/* loaded from: classes3.dex */
public final class EmptyCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -7545121636549663526L;

    @Override // p110z1.LambdaConsumerIntrospection
    public boolean hasCustomOnError() {
        return false;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        RxJavaPlugins.m9212a(new OnErrorNotImplementedException(th));
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this, atrVar);
    }
}
