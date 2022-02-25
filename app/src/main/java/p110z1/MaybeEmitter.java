package p110z1;

/* renamed from: z1.ase */
/* loaded from: classes3.dex */
public interface MaybeEmitter<T> {
    boolean isDisposed();

    void onComplete();

    void onError(@AbstractC3889atm Throwable th);

    void onSuccess(@AbstractC3889atm T t);

    void setCancellable(@atn Cancellable aulVar);

    void setDisposable(@atn Disposable atrVar);

    boolean tryOnError(@AbstractC3889atm Throwable th);
}
