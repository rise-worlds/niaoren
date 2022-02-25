package p110z1;

/* renamed from: z1.bss */
/* loaded from: classes3.dex */
public enum EmptyComponent implements CompletableObserver, FlowableSubscriber<Object>, MaybeObserver<Object>, Observer<Object>, SingleObserver<Object>, Disposable, dby {
    INSTANCE;

    @Override // p110z1.dby
    public void cancel() {
    }

    @Override // p110z1.Disposable
    public void dispose() {
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return true;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
    }

    @Override // p110z1.Subscriber
    public void onNext(Object obj) {
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(Object obj) {
    }

    @Override // p110z1.dby
    public void request(long j) {
    }

    public static <T> Subscriber<T> asSubscriber() {
        return INSTANCE;
    }

    public static <T> Observer<T> asObserver() {
        return INSTANCE;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        atrVar.dispose();
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        dbyVar.cancel();
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        RxJavaPlugins.m9212a(th);
    }
}
