package p110z1;

/* renamed from: z1.asn */
/* loaded from: classes3.dex */
public interface ObservableEmitter<T> extends Emitter<T> {
    boolean isDisposed();

    @AbstractC3889atm
    ObservableEmitter<T> serialize();

    void setCancellable(@atn Cancellable aulVar);

    void setDisposable(@atn Disposable atrVar);

    boolean tryOnError(@AbstractC3889atm Throwable th);
}
