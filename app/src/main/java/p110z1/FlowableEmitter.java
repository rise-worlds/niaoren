package p110z1;

/* renamed from: z1.arx */
/* loaded from: classes3.dex */
public interface FlowableEmitter<T> extends Emitter<T> {
    boolean isCancelled();

    long requested();

    @AbstractC3889atm
    FlowableEmitter<T> serialize();

    void setCancellable(@atn Cancellable aulVar);

    void setDisposable(@atn Disposable atrVar);

    boolean tryOnError(@AbstractC3889atm Throwable th);
}
