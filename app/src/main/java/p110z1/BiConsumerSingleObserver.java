package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awa */
/* loaded from: classes3.dex */
public final class BiConsumerSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = 4943102778943297569L;
    final BiConsumer<? super T, ? super Throwable> onCallback;

    public BiConsumerSingleObserver(BiConsumer<? super T, ? super Throwable> auhVar) {
        this.onCallback = auhVar;
    }

    @Override // p110z1.SingleObserver
    public void onError(Throwable th) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.mo9895a(null, th);
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
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.mo9895a(t, null);
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
}
