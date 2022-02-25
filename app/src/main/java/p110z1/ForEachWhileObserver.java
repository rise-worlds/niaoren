package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awm */
/* loaded from: classes3.dex */
public final class ForEachWhileObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Predicate<? super T> onNext;

    public ForEachWhileObserver(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar, Action augVar) {
        this.onNext = auxVar;
        this.onError = aumVar;
        this.onComplete = augVar;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this, atrVar);
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                dispose();
                onError(th);
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(th, th2));
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.done) {
            this.done = true;
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
        return DisposableHelper.isDisposed(get());
    }
}
