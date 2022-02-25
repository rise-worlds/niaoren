package p110z1;

/* renamed from: z1.dbx */
/* loaded from: classes3.dex */
public interface Subscriber<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(dby dbyVar);
}
