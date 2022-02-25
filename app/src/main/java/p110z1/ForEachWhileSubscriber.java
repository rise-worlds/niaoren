package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brn */
/* loaded from: classes3.dex */
public final class ForEachWhileSubscriber<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Predicate<? super T> onNext;

    public ForEachWhileSubscriber(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar, Action augVar) {
        this.onNext = auxVar;
        this.onError = aumVar;
        this.onComplete = augVar;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
    }

    @Override // p110z1.Subscriber
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

    @Override // p110z1.Subscriber
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

    @Override // p110z1.Subscriber
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
        SubscriptionHelper.cancel(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
