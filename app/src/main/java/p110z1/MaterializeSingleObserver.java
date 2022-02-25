package p110z1;

@Experimental
/* renamed from: z1.bhu */
/* loaded from: classes3.dex */
public final class MaterializeSingleObserver<T> implements CompletableObserver, MaybeObserver<T>, SingleObserver<T>, Disposable {

    /* renamed from: a */
    final SingleObserver<? super Notification<T>> f18768a;

    /* renamed from: b */
    Disposable f18769b;

    public MaterializeSingleObserver(SingleObserver<? super Notification<T>> asxVar) {
        this.f18768a = asxVar;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.validate(this.f18769b, atrVar)) {
            this.f18769b = atrVar;
            this.f18768a.onSubscribe(this);
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        this.f18768a.onSuccess(Notification.m10637f());
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(T t) {
        this.f18768a.onSuccess(Notification.m10643a(t));
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        this.f18768a.onSuccess(Notification.m10642a(th));
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f18769b.isDisposed();
    }

    @Override // p110z1.Disposable
    public void dispose() {
        this.f18769b.dispose();
    }
}
