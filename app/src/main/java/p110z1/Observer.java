package p110z1;

/* renamed from: z1.ass */
/* loaded from: classes3.dex */
public interface Observer<T> {
    void onComplete();

    void onError(@AbstractC3889atm Throwable th);

    void onNext(@AbstractC3889atm T t);

    void onSubscribe(@AbstractC3889atm Disposable atrVar);
}
