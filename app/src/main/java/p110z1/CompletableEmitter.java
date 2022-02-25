package p110z1;

/* renamed from: z1.aro */
/* loaded from: classes3.dex */
public interface CompletableEmitter {
    boolean isDisposed();

    void onComplete();

    void onError(@AbstractC3889atm Throwable th);

    void setCancellable(@atn Cancellable aulVar);

    void setDisposable(@atn Disposable atrVar);

    boolean tryOnError(@AbstractC3889atm Throwable th);
}
