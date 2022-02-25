package p110z1;

/* renamed from: z1.awx */
/* loaded from: classes3.dex */
public final class SubscriberCompletableObserver<T> implements CompletableObserver, dby {

    /* renamed from: a */
    final Subscriber<? super T> f17670a;

    /* renamed from: b */
    Disposable f17671b;

    @Override // p110z1.dby
    public void request(long j) {
    }

    public SubscriberCompletableObserver(Subscriber<? super T> dbxVar) {
        this.f17670a = dbxVar;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        this.f17670a.onComplete();
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        this.f17670a.onError(th);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.validate(this.f17671b, atrVar)) {
            this.f17671b = atrVar;
            this.f17670a.onSubscribe(this);
        }
    }

    @Override // p110z1.dby
    public void cancel() {
        this.f17671b.dispose();
    }
}
