package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brr */
/* loaded from: classes3.dex */
public final class LambdaSubscriber<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, Disposable, LambdaConsumerIntrospection, dby {
    private static final long serialVersionUID = -7251123623727029452L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;
    final Consumer<? super dby> onSubscribe;

    public LambdaSubscriber(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Consumer<? super dby> aumVar3) {
        this.onNext = aumVar;
        this.onError = aumVar2;
        this.onComplete = augVar;
        this.onSubscribe = aumVar3;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.setOnce(this, dbyVar)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                dbyVar.cancel();
                onError(th);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                get().cancel();
                onError(th);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
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

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
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
        cancel();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    @Override // p110z1.dby
    public void request(long j) {
        get().request(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    @Override // p110z1.LambdaConsumerIntrospection
    public boolean hasCustomOnError() {
        return this.onError != Functions.f17560f;
    }
}
