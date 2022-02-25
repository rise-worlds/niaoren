package p110z1;

/* renamed from: z1.asx */
/* loaded from: classes3.dex */
public interface SingleObserver<T> {
    void onError(@AbstractC3889atm Throwable th);

    void onSubscribe(@AbstractC3889atm Disposable atrVar);

    void onSuccess(@AbstractC3889atm T t);
}
