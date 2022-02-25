package p110z1;

/* renamed from: z1.aru */
/* loaded from: classes3.dex */
public interface Emitter<T> {
    void onComplete();

    void onError(@AbstractC3889atm Throwable th);

    void onNext(@AbstractC3889atm T t);
}
