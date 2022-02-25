package p110z1;

/* renamed from: z1.asf */
/* loaded from: classes3.dex */
public interface MaybeObserver<T> {
    void onComplete();

    void onError(@AbstractC3889atm Throwable th);

    void onSubscribe(@AbstractC3889atm Disposable atrVar);

    void onSuccess(@AbstractC3889atm T t);
}
