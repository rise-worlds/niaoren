package p110z1;

import p110z1.ObservableScalarXMap;

/* renamed from: z1.bkx */
/* loaded from: classes3.dex */
public final class ObservableJust<T> extends Observable<T> implements ScalarCallable<T> {

    /* renamed from: a */
    private final T f19212a;

    public ObservableJust(T t) {
        this.f19212a = t;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        ObservableScalarXMap.RunnableC4546a aVar = new ObservableScalarXMap.RunnableC4546a(assVar, this.f19212a);
        assVar.onSubscribe(aVar);
        aVar.run();
    }

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public T call() {
        return this.f19212a;
    }
}
