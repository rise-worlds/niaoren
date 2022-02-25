package p110z1;

/* renamed from: z1.asw */
/* loaded from: classes3.dex */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(@AbstractC3889atm Throwable th);

    void onSuccess(@AbstractC3889atm T t);

    void setCancellable(@atn Cancellable aulVar);

    void setDisposable(@atn Disposable atrVar);

    boolean tryOnError(@AbstractC3889atm Throwable th);
}
